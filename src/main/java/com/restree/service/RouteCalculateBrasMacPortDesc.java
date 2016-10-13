/*
 * Copyright (c) 2016
 * Just for learn java coding
 */

package com.restree.service;

import com.restree.constant.ResourceType;
import com.restree.domain.Link;
import com.restree.domain.Node;
import com.restree.domain.ResData;
import com.restree.domain.ResTreeMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class RouteCalculateBrasMacPortDesc implements IRouteCalculate {
    @Autowired
    private ResTreeExceptionService resTreeExceptionService;

    @Autowired
    private PortService portService;

    @Autowired
    private NodeService nodeService;

    @Autowired
    private MacForwardService macForwardService;

    @Autowired
    private ResTreeMainService resTreeMainService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private QinqService qinqService;

    @Autowired
    private ResDataService resDataService;

    @Override
    public void portDescriptionParse(String areaId) {
        System.out.print(areaId);
    }

    @Override
    public List<Link> oltUpRouteCalculate(ResTreeMain resTreeMain) {

        //判断BAS口是否为空，如果为空，则报错
        if (resTreeMain.getBrasPortId() == null || resTreeMain.getBrasPortId().equals("")) {
            resTreeExceptionService.catchException(
                    "303",
                    resTreeMain.getAreaId(),
                    "300",
                    String.valueOf(resTreeMain.getId()),
                    "sysAdmin",
                    null);
            return new ArrayList<>(0);
        }

        Node bras = new Node();
        //依据BAS口获得BAS口MAC，如果找不到MAC，则报错
        bras.setDeviceId(resTreeMain.getBrasId());
        bras.setPortId(resTreeMain.getBrasPortId());
        bras.setUpDownPortId(resTreeMain.getBrasPortId());
        bras.setMac(portService.getPortMac(bras.getPortId()));
        if (bras.getMac() == null || bras.getMac().equals("")) {
            resTreeExceptionService.catchException(
                    "403",
                    resTreeMain.getAreaId(),
                    "400",
                    String.valueOf(resTreeMain.getId()),
                    "sysAdmin",
                    null);
            return new ArrayList<>(0);
        }

        //判断BAS口的MAC地址是否唯一，如果不唯一，则报错
        int macCount = portService.getPortCountByMac(bras.getMac());
        if (macCount > 1) {
            resTreeExceptionService.catchException(
                    "409",
                    resTreeMain.getAreaId(),
                    "400",
                    String.valueOf(resTreeMain.getId()),
                    "sysAdmin",
                    null);
            return new ArrayList<>(0);
        }

        //依据BAS口MAC地址查找OLT
        List<Node> oltSws = macForwardService.getOltSwByBrasOrUserMac(
                bras.getMac(), resTreeMain.getSvlan(), resTreeMain.getAreaId());

        List<Node> olts = new ArrayList<>();
        List<Node> sws = new ArrayList<>();
        List<Link> routes = new ArrayList<>();
        String targetDeviceType = String.valueOf(ResourceType.BRAS);
        for (Node device : oltSws) {
            if (device.getDeviceType().equals(String.valueOf(ResourceType.OLT))) {
                olts.add(device);
            }
            if (device.getDeviceType().equals(String.valueOf(ResourceType.SWITCH))) {
                List<Node> tempSws = portService.getPortByDeviceVlan(device.getDeviceId(),
                        device.getSvlan(), device.getPortId(), targetDeviceType);
                if (tempSws == null || tempSws.isEmpty()) {
                    resTreeExceptionService.catchException(
                            "408",
                            resTreeMain.getAreaId(),
                            "400",
                            String.valueOf(resTreeMain.getId()),
                            "sysAdmin",
                            null);
                    return new ArrayList<>(0);
                } else if (tempSws.size() == 1) {
                    device.setUpDownPortId(tempSws.get(0).getUpDownPortId());
                    sws.add(device);
                } else {
                    resTreeExceptionService.catchException(
                            "407",
                            resTreeMain.getAreaId(),
                            "400",
                            String.valueOf(resTreeMain.getId()),
                            "sysAdmin",
                            null);
                    return new ArrayList<>(0);
                }
            }
        }

        //如果未找到OLT，报错
        if (olts.isEmpty()) {
            resTreeExceptionService.catchException(
                    "501",
                    resTreeMain.getAreaId(),
                    "500",
                    String.valueOf(resTreeMain.getId()),
                    "sysAdmin",
                    null);
            return null;
        }

        //依据OLT互相出现对方MAC地址的数量对OLT进行排序，数量越少，越靠近用户
        else {
            //计算OLT互相关联的权重
            macForwardService.calculateDeviceWeight(olts);

            //如果多个OLT在经过关联权重的计算后，本身的权重和关联权重都为零，则说明同一个BAS口下SVLAN分配给了多个OLT
            for (int i = 0; i < olts.size(); i++) {
                if (olts.size() > 1 && olts.get(i).getWeight() == 0 && olts.get(i).getRelaWeight() == 0) {
                    resTreeExceptionService.catchException(
                            "801",
                            resTreeMain.getAreaId(),
                            "800",
                            String.valueOf(resTreeMain.getId()),
                            "sysAdmin",
                            null);
                    return new ArrayList<>(0);
                }
            }
            //依据OLT关联权重排序
            macForwardService.sortDevices(olts);
        }

        //OLT信息写入主表
        resTreeMain.setOltId(olts.get(0).getDeviceId());
        resTreeMain.setOltIp(nodeService.getDevice(olts.get(0).getDeviceId(), null, null, null, null).get(0).getIp());


        //如果找到SW，则先排序后获得上联后或下联口
        if (!sws.isEmpty()) {
            //依据SW互相出现对方MAC地址的数量对SW进行排序，数量越少，越靠近用户
            macForwardService.calculateDeviceWeight(sws);

            //如果多个SW在经过关联权重的计算后，本身的权重和关联权重都为零，则说明同一个BAS口下SVLAN分配给了多个OLT
            for (int i = 0; i < sws.size(); i++) {
                if (sws.size() > 1 && sws.get(i).getWeight() == 0 && sws.get(i).getRelaWeight() == 0) {
                    resTreeExceptionService.catchException(
                            "801",
                            resTreeMain.getAreaId(),
                            "800",
                            String.valueOf(resTreeMain.getId()),
                            "sysAdmin",
                            null);
                    return new ArrayList<>(0);
                }
            }
            //依据SW关联权重排序
            macForwardService.sortDevices(sws);
        }

        Link brasLink = new Link();

        //循环交换机List，合并到route中
        if (!sws.isEmpty()) {
            brasLink.setLink(sws.get(sws.size() - 1), bras, resTreeMain.getSvlan(), olts.size() + sws.size());

            Link oltSwLink = new Link();
            oltSwLink.setLink(olts.get(olts.size() - 1), sws.get(0), resTreeMain.getSvlan(), olts.size());
            routes.add(oltSwLink);

            for (int i = 0; i + 1 < sws.size(); i++) {
                Link swLink = new Link();
                swLink.setLink(olts.get(i), sws.get(i + 1), resTreeMain.getSvlan(), olts.size() + i);
                routes.add(swLink);
            }
        } else {
            brasLink.setLink(olts.get(olts.size() - 1), bras, resTreeMain.getSvlan(), olts.size());
        }

        //循环OLT的List，获得级联OLT下联口后，并合并到route中
        for (int i = 0; i + 1 < olts.size(); i++) {
            List<Node> tempOlts = portService.getPortByDeviceVlan(olts.get(i + 1).getDeviceId(), resTreeMain.getSvlan(),
                    null, targetDeviceType);
            if (tempOlts == null || tempOlts.isEmpty()) {
                resTreeExceptionService.catchException(
                        "524",
                        resTreeMain.getAreaId(),
                        "500",
                        String.valueOf(resTreeMain.getId()),
                        "sysAdmin",
                        null);
                return new ArrayList<>(0);
            } else if (tempOlts.size() == 1) {
                olts.get(i + 1).setUpDownPortId(tempOlts.get(0).getUpDownPortId());
                Link oltLink = new Link();
                oltLink.setLink(olts.get(i), olts.get(i + 1), resTreeMain.getSvlan(), i);
                routes.add(i, oltLink);
            } else {
                resTreeExceptionService.catchException(
                        "523",
                        resTreeMain.getAreaId(),
                        "500",
                        String.valueOf(resTreeMain.getId()),
                        "sysAdmin",
                        null);
                return new ArrayList<>(0);
            }
        }

        routes.add(brasLink);
        return routes;
    }

    @Override
    public List<Link> oltDownRouteCalculate(ResTreeMain resTreeMain) {
        Node olt = new Node();
        List<Link> routes = new ArrayList<>();
        olt.setDeviceId(resTreeMain.getOltId());
        olt.setSvlan(resTreeMain.getSvlan());
        olt.setCvlan(resTreeMain.getCvlan());
        //获得OLT的PON、ONT、ONU或终端信息
        if (qinqService.getQinqCountBySvlan(olt, 1) == 0) {
            resTreeExceptionService.catchException(
                    "510",
                    resTreeMain.getAreaId(),
                    "500",
                    String.valueOf(resTreeMain.getId()),
                    "sysAdmin",
                    null);
            return new ArrayList<>(0);
        }

        //依据接入方式查找OLT的PON口,先判断FTTB，再判断FTTH
        String accessType = ResourceType.FTTB;
        if (qinqService.getQinqCountBySVlanAccessType(olt, accessType) == 0) {
            accessType = ResourceType.FTTH;
        }

        List<Node> pons = portService.getPonPortByVlan(olt, accessType);

        if (pons == null || pons.isEmpty()) {
            resTreeExceptionService.catchException(
                    "505",
                    resTreeMain.getAreaId(),
                    "500",
                    String.valueOf(resTreeMain.getId()),
                    "sysAdmin",
                    null);
            return new ArrayList<>(0);
        } else if (pons.size() == 1) {
            resTreeMain.setOltPonId(pons.get(0).getPortId());
            if (accessType.equals(ResourceType.FTTB)) {
                resTreeMain.setAccessType(ResourceType.FTTB_DICT);
                resTreeMain.setOntId(pons.get(0).getOntId());
                resTreeMain.setOnuId(pons.get(0).getDeviceId());
                resTreeMain.setOnuPortId(pons.get(0).getPortId());
                resTreeMain.setOltPonId(pons.get(0).getUpDownPortId());
            } else {
                resTreeMain.setAccessType(ResourceType.FTTH_DICT);

                List<Node> terminals = portService.getFtthTerminal(pons.get(0));
                if (terminals == null || terminals.isEmpty()) {
                    resTreeExceptionService.catchException(
                            "603",
                            resTreeMain.getAreaId(),
                            "600",
                            String.valueOf(resTreeMain.getId()),
                            "sysAdmin",
                            null);
                    return new ArrayList<>(0);
                } else if (terminals.size() == 1) {
                    terminals.get(0).setDeviceType(String.valueOf(ResourceType.TERMINAL));
                    resTreeMain.setTerminalMac(terminals.get(0).getTerminalMac());
                    resTreeMain.setTerminalSn(terminals.get(0).getTerminalSn());
                    resTreeMain.setLoid(terminals.get(0).getLoid());
                }
            }
        } else {
            resTreeExceptionService.catchException(
                    "503",
                    resTreeMain.getAreaId(),
                    "500",
                    String.valueOf(resTreeMain.getId()),
                    "sysAdmin",
                    null);
            return new ArrayList<>(0);
        }

        //获取资源系统数据
        List<ResData> resDatas = resDataService.getResDataByAccount(resTreeMain.getAccountNo());

        if (resDatas == null || resDatas.isEmpty()) {
            resTreeExceptionService.catchException(
                    "603",
                    resTreeMain.getAreaId(),
                    "600",
                    String.valueOf(resTreeMain.getId()),
                    "sysAdmin",
                    null);
            return new ArrayList<>(0);
        } else if (resDatas.size() == 1) {
            if (accessType.equals(ResourceType.FTTH)) {
                resTreeMain.setObdId(resDatas.get(0).getaDeviceId());
                resTreeMain.setObdPortId(resDatas.get(0).getaPortId());
                resTreeMain.setMainOptRoadId(resDatas.get(0).getMainOptRoadId());
                resTreeMain.setChildOptRaodId(resDatas.get(0).getChildOptRoadId());
            }
        }

        //拼接OLT向下的路由，依据接入类型分别获得路由，
        //FTTH拼接终端-OBD-OBD-OLT
        if (accessType.equals(ResourceType.FTTH)) {
            //主光路
            Link mainOptRoad = resDataService.getOptLinkByNoOrId(null,
                    resTreeMain.getMainOptRoadId(), resTreeMain.getAreaId());
            //子光路
            Link childOptRoad = resDataService.getOptLinkByNoOrId(null,
                    resTreeMain.getChildOptRaodId(), resTreeMain.getAreaId());

            pons.get(0).setDeviceType(String.valueOf(ResourceType.OLT));
            pons.get(0).setUpDownPortId(pons.get(0).getPortId());

            routes.add(childOptRoad);

            //判断是否存在多级分光器情况
            String[] linkNoList = childOptRoad.getLinkNo().split("-");
            if (linkNoList.length > 2) {
                String middleOptRoadNo = linkNoList[0] + "-" + linkNoList[1];
                Link middleOptRoad = resDataService.getOptLinkByNoOrId(middleOptRoadNo,
                        null, resTreeMain.getAreaId());
                routes.add(middleOptRoad);
            }
            //主光路路由
            if (mainOptRoad.getStartDeviceType().equals(String.valueOf(ResourceType.OLT))) {
                mainOptRoad.setStartDeviceId(mainOptRoad.getNextDeviceId());
                mainOptRoad.setStartPortId(mainOptRoad.getNextPortId());
                mainOptRoad.setStartDeviceType(mainOptRoad.getNextDeviceType());
                mainOptRoad.setLinkNextNode(pons.get(0));
            } else {
                mainOptRoad.setLinkStartNode(pons.get(0));
            }
            routes.add(mainOptRoad);
        }

        //FTTB拼接虑ONU-OLT
        else {
            Link fttbLink = new Link();
            fttbLink.setStartDeviceType(String.valueOf(ResourceType.ONU));
            fttbLink.setStartDeviceId(resTreeMain.getOnuId());
            fttbLink.setStartPortId(resTreeMain.getOnuPortId());
            fttbLink.setNextDeviceType(String.valueOf(ResourceType.OLT));
            fttbLink.setNextDeviceId(resTreeMain.getOltId());
            fttbLink.setNextDeviceId(resTreeMain.getOltPonId());
            routes.add(fttbLink);
        }

        return routes;
    }

}
