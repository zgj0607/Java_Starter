/*
 * Copyright (c) 2016
 * Just for learn java coding
 */

package com.restree.mapper;

import com.restree.domain.Link;
import com.restree.domain.ResData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IResDataMapper {

    /**
     * 依据账号获得资源系统设备关联信息
     *
     * @param accountNo 账号
     * @return 资源系统信息
     */
    List<ResData> getResDeviceRelaByAccount(String accountNo);

    /**
     * 依据光路编码或ID获得光路AZ设备和端口
     *
     * @param optRoadNo 光路编码
     * @param optRoadId 光路ID
     * @return 光路路由Link
     */
    Link getOptRoadByNoOrId(@Param("optRoadNo") String optRoadNo,
                            @Param("optRoadId") String optRoadId,
                            @Param("areaId") String areaId);
}
