/*
 * Copyright (c) 2016
 * Just for learn java coding
 */

package com.restree.service;

import com.restree.domain.Node;
import com.restree.mapper.INodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class NodeService {
    @Autowired
    private INodeMapper iNodeMapper;

    List<Node> getDevice(String deviceId,
                         String ip,
                         String deviceType,
                         String areaId,
                         String deviceName) {
        return iNodeMapper.getDevice(deviceId, ip, deviceType, areaId, deviceName);

    }
}
