/*
 * Copyright (c) 2016
 * Just for learn java coding
 */

package com.restree.service;

import com.restree.domain.Link;
import com.restree.domain.ResData;
import com.restree.mapper.IResDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ResDataService {
    @Autowired
    private IResDataMapper iResDataMapper;

    List<ResData> getResDataByAccount(String accountNo) {
        return iResDataMapper.getResDeviceRelaByAccount(accountNo);
    }

    Link getOptLinkByNoOrId(String optRoadNo, String optRoadId, String areaId) {
        return iResDataMapper.getOptRoadByNoOrId(optRoadNo, optRoadId, areaId);
    }
}
