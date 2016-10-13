/*
 * Copyright (c) 2016
 * Just for learn java coding
 */

package com.restree.mapper;

import com.restree.domain.Node;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface INodeMapper {
    /**
     * 依据设备ID/IP/类型/区域获得设备信息
     *
     * @param deviceId   设备ID
     * @param ip         设备IP
     * @param deviceType 设备类型
     * @param areaId     区域ID
     * @return 设备ID
     */
    List<Node> getDevice(@Param("deviceId") String deviceId,
                         @Param("ip") String ip,
                         @Param("deviceType") String deviceType,
                         @Param("areaId") String areaId,
                         @Param("deviceName") String deviceName);
}
