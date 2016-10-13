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
public interface IMacForwardMapper {

    /*
     * 已知设备、端口或者目标mac，得到mac转发表记录
     */
    List<Node> macForward(@Param("deviceId") String deviceId,
                          @Param("deviceType") String deviceType,
                          @Param("portId") String portId,
                          @Param("vlan") String vlan,
                          @Param("targetMac") String targetMac,
                          @Param("areaId") String areaId);

    /*
     * 依据设备ID判断两个设备是否存在mac转发的记录
     */
    List<Node> macForwardRela(@Param("deviceId") String deviceId,
                              @Param("forwardDeviceId") String forwardDeviceId,
                              @Param("deviceType") String deviceType,
                              @Param("portId") String portId,
                              @Param("forwardDeviceType") String forwardDeviceType,
                              @Param("vlan") String vlan);

    /*
     * 获得端口转发某一设备类型的mac转发记录
     */
    List<Node> macForwardPort(@Param("portId") String portId,
                              @Param("forwardDeviceType") String forwardDeviceType);

}
