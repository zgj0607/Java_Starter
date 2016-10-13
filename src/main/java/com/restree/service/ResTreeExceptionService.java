/*
 * Copyright (c) 2016
 * Just for learn java coding
 */

package com.restree.service;

import com.restree.domain.ResTreeException;
import com.restree.mapper.IExceptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ResTreeExceptionService {
    @Autowired
    private IExceptionMapper iExceptionMapper;

    private ResTreeException getExceptionTypeDesc(String exceptionType) {
        return iExceptionMapper.getResTreeException(exceptionType);
    }

    void catchException(String exceptionType,
                        String areaId,
                        String objectType,
                        String objectId,
                        String operatorId,
                        String versionNo) {
        ResTreeException e = this.getExceptionTypeDesc(exceptionType);
        e.setAreaId(areaId);
        e.setObjectId(objectId);
        e.setObjectType(objectType);
        e.setOperatorId(operatorId);
        e.setVersionNo(versionNo);
        iExceptionMapper.insertException(e);

    }
}
