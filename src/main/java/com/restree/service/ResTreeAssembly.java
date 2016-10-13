/*
 * Copyright (c) 2016
 * Just for learn java coding
 */

package com.restree.service;

import com.restree.constant.ResourceType;
import com.restree.domain.Link;
import com.restree.domain.Node;
import com.restree.domain.ResTreeMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResTreeAssembly {
    @Autowired
    private RouteCalculateBrasMacPortDesc routeCalculateBrasMacPortDesc;

    @Autowired
    private ResTreeMainService resTreeMainService;

    @Autowired
    private PortService portService;

    @Autowired
    private NodeService nodeService;

    @Autowired
    private RouteService routeService;


    private int crmAssemble(ResTreeMain treeData) {
        //查询rts_res_tree_mian表是否存在,
        //如果不存在插入,如果存在就更新
        treeData.setState(1);
        int result = resTreeMainService.updateCrmTreeMain(treeData);
        if (result != 0) {
            return result;
        } else {
            return resTreeMainService.insertCrmTreeMain(treeData);
        }
    }

    private int aaaAssemble(String accountNo, String svlan, String cvlan,
                            String brasIp, String brasPort,
                            String userMac, String areaId) throws Exception {

        List<Node> bras = nodeService.getDevice(null, brasIp, String.valueOf(ResourceType.BRAS), areaId, null);
        if (bras == null || bras.isEmpty()) {
            throw new Exception("根据brasIp未找到设备信息");
        }
        Node brasPortNode = portService.getPortByPortNo(bras.get(0).getDeviceId(), brasPort);

        if (brasPortNode == null) {
            throw new Exception("根据brasIp和端口编码没有查询到相应的端口信息");
        }
        ResTreeMain treeData = new ResTreeMain(accountNo);
        treeData.setSvlan(svlan);
        treeData.setCvlan(cvlan);
        treeData.setBrasId(brasPortNode.getDeviceId());
        treeData.setBrasPortId(brasPortNode.getPortId());
        treeData.setState(2);
        treeData.setUserMac(userMac);
        int result = resTreeMainService.updateAaaTreeMain(treeData);
        if (result != 0) {
            return result;
        } else {
            return 0;
        }
    }

    private int routeAssemble(ResTreeMain resTreeMain) throws Exception {

        List<Link> upRouteLinks = routeCalculateBrasMacPortDesc.oltUpRouteCalculate(resTreeMain);
        List<Link> routeLinks = routeCalculateBrasMacPortDesc.oltDownRouteCalculate(resTreeMain);
        routeLinks.addAll(upRouteLinks);
        int businessLinkId = routeService.createRoute(routeLinks, 1);
        resTreeMain.setRouteAllId(String.valueOf(businessLinkId));
        resTreeMainService.updateTreeMainField(resTreeMain);
        return 0;
    }

    /**
     * 提供三种方式调用，优先通过账号计算
     *
     * @param areaIds，逗号分隔
     * @param brasIps，逗号分隔，bras的Ip
     * @param accountNos，数组
     */
    public void resTreeAllAssemble(String areaIds, String brasIps, String[] accountNos) throws Exception {
        if (accountNos != null && accountNos.length > 0) {
            for (String accountNo : accountNos) {
                List<ResTreeMain> crmInfo = resTreeMainService.getCrmInfo(null, accountNo);
                if (crmInfo != null && !crmInfo.isEmpty()) {
                    List<ResTreeMain> resTreeMain = resTreeMainService.getAaaInfo(null, null, accountNo);
                    if (resTreeMain != null && !resTreeMain.isEmpty()) {
                        this.aaaAssemble(accountNo, resTreeMain.get(0).getSvlan(), resTreeMain.get(0).getCvlan(),
                                resTreeMain.get(0).getBrasIp(), resTreeMain.get(0).getBrasPortNo(),
                                resTreeMain.get(0).getUserMac(), resTreeMain.get(0).getAreaId());

                        this.routeAssemble(resTreeMain.get(0));
                    }
                }
            }
        } else if (brasIps != null && brasIps.length() != 0) {
            for (String brasIp : brasIps.split(",")) {
                List<ResTreeMain> resTreeMains = resTreeMainService.getAaaInfo(null, brasIp, null);
                for (ResTreeMain resTreeMain : resTreeMains) {
                    List<ResTreeMain> crmInfo = resTreeMainService.getCrmInfo(null, resTreeMain.getAccountNo());
                    if (crmInfo != null && !crmInfo.isEmpty()) {
                        this.aaaAssemble(resTreeMain.getAccountNo(), resTreeMain.getSvlan(), resTreeMain.getCvlan(),
                                resTreeMain.getBrasIp(), resTreeMain.getBrasPortNo(),
                                resTreeMain.getUserMac(), resTreeMain.getAreaId());

                        this.routeAssemble(resTreeMain);
                    }
                }
            }
        } else if (areaIds != null && !areaIds.isEmpty()) {
            for (String areaId : areaIds.split(",")) {
                List<ResTreeMain> crmInfos = resTreeMainService.getCrmInfo(areaId, null);
                for (ResTreeMain crmInfo : crmInfos) {
                    List<ResTreeMain> resTreeMains = resTreeMainService.getAaaInfo(crmInfo.getAccountNo(), null, null);
                    if (resTreeMains != null && !resTreeMains.isEmpty()) {
                        for (ResTreeMain resTreeMain : resTreeMains) {
                            this.aaaAssemble(resTreeMain.getAccountNo(), resTreeMain.getSvlan(), resTreeMain.getCvlan(),
                                    resTreeMain.getBrasIp(), resTreeMain.getBrasPortNo(),
                                    resTreeMain.getUserMac(), resTreeMain.getAreaId());

                            this.routeAssemble(resTreeMain);
                        }
                    }

                }
            }
        }
    }
}
