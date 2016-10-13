/*
 * Copyright (c) 2016
 * Just for learn java coding
 */

package com.restree.service;

import com.restree.domain.ResTreeMain;
import com.restree.mapper.IResTreeMainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ResTreeMainService {

    @Autowired
    private IResTreeMainMapper iResTreeMainMapper;


    List<ResTreeMain> getCrmInfo(String areaId, String accountNo) {
        return iResTreeMainMapper.getCrmInfo(areaId, accountNo);
    }

    List<ResTreeMain> getAaaInfo(String areaId, String brasIp, String accountNo) {
        return iResTreeMainMapper.getAaaInfo(areaId, brasIp, accountNo);
    }

    int updateCrmTreeMain(ResTreeMain treeData) {
        return iResTreeMainMapper.updateCrmTreeMain(treeData);
    }

    int insertCrmTreeMain(ResTreeMain treeData) {
        return iResTreeMainMapper.insertCrmTreeMain(treeData);
    }

    int updateAaaTreeMain(ResTreeMain treeData) {
        return iResTreeMainMapper.updateAaaTreeMain(treeData);
    }

    public int updateBusiTreeMain(ResTreeMain treeData) {
        return iResTreeMainMapper.updateBusiTreeMain(treeData);
    }

    int updateTreeMainField(ResTreeMain treeData) {
        return iResTreeMainMapper.updateTreeMainField(treeData);
    }
}
