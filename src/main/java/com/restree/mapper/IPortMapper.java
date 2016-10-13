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
public interface IPortMapper {
    /**
     * 根据设备ID和VLAN号查询配置了该VLAN的端口
     * 除去传入的PORT,如果传入上联则只查询下联口信息
     *
     * @param deviceId         设备ID
     * @param svlan            SVLAN
     * @param portId           端口ID
     * @param targetDeviceType 目标设备类型
     */
    List<Node> getPortByDeviceVlan(@Param("deviceId") String deviceId,
                                   @Param("svlan") String svlan,
                                   @Param("portId") String portId,
                                   @Param("targetDeviceType") String targetDeviceType);

    /**
     * 依据设备ID和端口别名获得端口ID
     *
     * @param deviceId 设备ID
     * @param portNo   端口别名
     */
    Node getPortByPortNo(@Param("deviceId") String deviceId,
                         @Param("portNo") String portNo);

    /**
     * 依据端口获得端口MAC地址
     *
     * @param portId 端口ID
     */
    String getPortMac(@Param("portId") String portId);

    /**
     * 通过端口Mac获得端口数量
     *
     * @param mac 端口MAC
     * @return 端口数量
     */
    int getPortCountByMac(@Param("mac") String mac);

    /**
     * 通过OLTPON口、ONTID查询
     * OLT下行PON口信息的业务信息
     *
     * @param ponPortId OLT的PON口ID
     * @param ontId     ONT ID
     * @return OLT设备Node
     */
    List<Node> getOltPonPortBusi(@Param("ponPortId") String ponPortId,
                                 @Param("ontId") String ontId);

    /**
     * 通过账号和SVLAN获取
     * OLT的上行口信息
     *
     * @param accountInfo 带有用户信息的设备
     * @return 设备节点
     */
    Node getUpPortOltByAccount(Node accountInfo);

    List<Node> getFtthOltPonByVlan(@Param("deviceId") String deviceId,
                                   @Param("portId") String portId,
                                   @Param("svlan") String svlan,
                                   @Param("cvlan") String clvan,
                                   @Param("accessType") String accessType);

    List<Node> getFttbOltPonByVlan(@Param("deviceId") String deviceId,
                                   @Param("portId") String portId,
                                   @Param("svlan") String svlan,
                                   @Param("cvlan") String clvan,
                                   @Param("onuType") String onuType,
                                   @Param("ontId") String ontId);
}
