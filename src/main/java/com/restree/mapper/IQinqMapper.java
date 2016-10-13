/*
 * Copyright (c) 2016
 * Just for learn java coding
 */

package com.restree.mapper;

import com.restree.domain.Qinq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IQinqMapper {
    /**
     * 依据设备ID等信息获得Qinq信息
     *
     * @param deviceId 设备ID
     * @param portId   端口ID
     * @param svlan    SVLAN
     * @param cvlan    CVLAN
     * @param ontId    ONT ID
     * @return Node类型的LIST
     */
    List<Qinq> qinq(@Param("deviceId") String deviceId,
                    @Param("portId") String portId,
                    @Param("svlan") String svlan,
                    @Param("cvlan") String cvlan,
                    @Param("ontId") int ontId);

    int getQinqCount(@Param("deviceId") String deviceId,
                     @Param("portId") String portId,
                     @Param("svlan") String slvan,
                     @Param("cvlan") String cvlan,
                     @Param("accessType") String accessType,
                     @Param("cvlanFlag") int cvlanFlag);
}
