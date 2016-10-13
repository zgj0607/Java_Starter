/*
 * Copyright (c) 2016
 * Just for learn java coding
 */

package com.restree.service;

import com.restree.constant.ResourceType;
import com.restree.domain.Node;
import com.restree.mapper.IMacForwardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class MacForwardService {
    @Autowired
    private IMacForwardMapper iMacForwardMapper;

    /**
     * 通过用户Mac或Bras口Mac得到交换机和OLT
     *
     * @param mac    端口或设备MAC
     * @param svlan  SVLAN
     * @param areaId 区域ID
     * @return Node类型的List，通过mac转发表获得的设备信息
     */
    List<Node> getOltSwByBrasOrUserMac(String mac, String svlan, String areaId) {
        return iMacForwardMapper.macForward(null, null, null, svlan, mac, areaId);
    }

    /**
     * 依据两个设备ID获得两个设备是否存在Mac转发记录
     *
     * @param deviceId   设备ID
     * @param toDeviceId 关联设备ID
     * @return 关联的FDB记录
     */
    private List<Node> getTwoDeviceFdbRela(String deviceId, String toDeviceId) {
        return iMacForwardMapper.macForwardRela(deviceId, toDeviceId, null, null, null, null);
    }

    /**
     * 依据A设备端口和B设备类型获得该端口是否存在B设备类型的Mac转发记录
     *
     * @param toDeviceType 关联设备类型
     * @param portId       转发设备端口ID
     * @return 关联的FDB记录
     */
    public List<Node> getPortRelaDeviceTypeFdb(String toDeviceType, String portId) {
        return iMacForwardMapper.macForwardRela(null, null, null, portId, toDeviceType, null);
    }

    /**
     * 根据bras端口mac地址查询转发到该bras端口mac地址的OLT端口
     *
     * @param brasPortMac bras端口MAC
     * @param vlan        SVLAN
     * @return FDB记录
     */
    public List<Node> getOltByBrasPortMac(String brasPortMac, String vlan) {
        return iMacForwardMapper.macForward(null, null, brasPortMac, vlan, String.valueOf(ResourceType.OLT), null);
    }

    /**
     * 依据OLT的ID获得关联的交换机
     *
     * @param oltId OLT设备ID
     * @param svlan SVLAN
     * @return 关联的FDB记录
     */
    public List<Node> getSwByOltId(String oltId, String svlan) {
        return iMacForwardMapper.macForwardRela(null, oltId, String.valueOf(ResourceType.SWITCH), null, String.valueOf(ResourceType.OLT), svlan);
    }

    /**
     * 通过OLT 上行端口的ID获取该端口转发的BRAS信息
     *
     * @param portId 端口ID
     * @return 关联的FDB记录
     */
    public List<Node> getBrasByOltPortId(String portId) {
        return iMacForwardMapper.macForwardPort(portId, String.valueOf(ResourceType.BRAS));
    }

    /**
     * 循环list中的设备节点，通过两个设备是否存在mac转发计算设备的权重和关联权重
     *
     * @param devices 设备List
     */
    void calculateDeviceWeight(List<Node> devices) {
        for (int i = 0; i < devices.size(); i++) {
            devices.get(i).setWeight(0);
            for (int j = i + 1; j < devices.size(); j++) {
                devices.get(j).setRelaWeight(0);
                List<Node> relaFlag = getTwoDeviceFdbRela(devices.get(i).getDeviceId(), devices.get(j).getDeviceId());
                if (relaFlag != null && !relaFlag.isEmpty()) {
                    devices.get(i).setWeight(devices.get(i).getWeight() + 1);
                    devices.get(j).setRelaWeight(devices.get(i).getRelaWeight() + 1);
                }
            }
        }
    }

    /**
     * 依据设备的权重按照从小到大排序list
     *
     * @param devices 设备List
     */
    public void sortDevices(List<Node> devices) {
        Collections.sort(devices, new Comparator<Node>() {
                    public int compare(Node device0, Node device1) {
                        if (device0.getWeight() > device1.getWeight()) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                }
        );
    }

    List<Node> getOltPonByUserMac(String oltId, String userMac, String svlan) {
        return iMacForwardMapper.macForward(oltId, null, null, svlan, userMac, null);

    }
}
