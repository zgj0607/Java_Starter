/*
 * Copyright (c) 2016
 * Just for learn java coding
 */

package com.restree.service;

import com.restree.constant.ResourceType;
import com.restree.domain.Node;
import com.restree.mapper.IPortMapper;
import com.restree.mapper.IQinqMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class PortService {
    @Autowired
    private IPortMapper iPortMapper;

    @Autowired
    private IQinqMapper iQinqMapper;

    String getPortMac(String portId) {
        return iPortMapper.getPortMac(portId);
    }

    int getPortCountByMac(String portMac) {
        return iPortMapper.getPortCountByMac(portMac);
    }

    Node getPortByPortNo(String deviceId, String portNo) {
        return iPortMapper.getPortByPortNo(deviceId, portNo);
    }

    List<Node> getPortByDeviceVlan(String deviceId,
                                   String svlan,
                                   String portId,
                                   String targetDeviceType) {
        return iPortMapper.getPortByDeviceVlan(deviceId, svlan, portId, targetDeviceType);
    }

    List<Node> getPonPortByVlan(Node olt, String accessType) {
        List<Node> pons;
        if (accessType.equals(ResourceType.FTTB)) {
            pons = iPortMapper.getFttbOltPonByVlan(olt.getDeviceId(), null, olt.getSvlan(),
                    olt.getCvlan(), String.valueOf(ResourceType.ONU), accessType);
            if (pons == null || pons.isEmpty()) {
                pons = iPortMapper.getFttbOltPonByVlan(olt.getDeviceId(), null, olt.getSvlan(),
                        olt.getCvlan(), String.valueOf(ResourceType.ONU), null);
            }
        } else {
            pons = iPortMapper.getFtthOltPonByVlan(olt.getDeviceId(), null, olt.getSvlan(),
                    olt.getCvlan(), accessType);
        }
        return pons;
    }

    List<Node> getFtthTerminal(Node olt) {
        return iPortMapper.getOltPonPortBusi(olt.getUpDownPortId(), olt.getOntId());
    }

}
