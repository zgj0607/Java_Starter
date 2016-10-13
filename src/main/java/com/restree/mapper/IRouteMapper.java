/*
 * Copyright (c) 2016
 * Just for learn java coding
 */

package com.restree.mapper;

import com.restree.domain.Link;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IRouteMapper {
    /**
     * 获取业务路径的ID:SEQ_RTS_CIRCUIT_LINK_ID
     *
     * @return 业务路径ID
     */
    int getRouteBusiId();

    /**
     * 获得路由表ID:SEQ_RTS_CIRCUIT_ROUTE_ID
     *
     * @return 路由表ID
     */
    int getRouteId();

    /**
     * 获得物理路由表ID:SEQ_RTS_CIRCUIT_ROUTE_PHY_ID
     *
     * @return 物理路径ID
     */
    int getRoutePhyId();


    /**
     * 创建业务路径
     *
     * @param businessLink 业务路径Link
     * @return int
     */
    int createRouteBusi(Link businessLink);

    int createRoute(Link link);

    List<Link> getPhyRoute(@Param("deviceId") String deviceId,
                           @Param("deviceType") String deviceType,
                           @Param("portId") String portId,
                           @Param("toDeviceId") String toDeviceId,
                           @Param("toDeviceType") String toDeviceType,
                           @Param("toPortId") String toPortId);

}
