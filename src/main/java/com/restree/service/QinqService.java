/*
 * Copyright (c) 2016
 * Just for learn java coding
 */

package com.restree.service;

import com.restree.domain.Node;
import com.restree.mapper.IQinqMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class QinqService {
    @Autowired
    private IQinqMapper iQinqMapper;

    int getQinqCountBySvlan(Node device, int cvlanFlag) {

        return iQinqMapper.getQinqCount(device.getDeviceId(), null, device.getSvlan(), null, null, cvlanFlag);
    }

    int getQinqCountBySVlanAccessType(Node device, String accessType) {

        return iQinqMapper.getQinqCount(device.getDeviceId(), null, device.getSvlan(), device.getCvlan(), accessType, 0);
    }
}
