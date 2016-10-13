/*
 * Copyright (c) 2016
 * Just for learn java coding
 */

package com.restree.service;

import com.restree.domain.Link;
import com.restree.mapper.IRouteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class RouteService {

    @Autowired
    private IRouteMapper iRouteMapper;

    /**
     * 创建业务路径
     *
     * @param links Link类型List，路由信息
     * @param type  路由类型
     * @return int
     * @throws Exception sql相关的异常
     */
    int createRouteBusi(List<Link> links, int type) throws Exception {
        int busiLinkId = iRouteMapper.getRouteBusiId();
        if (links != null && !links.isEmpty()) {
            for (Link link : links) {
                link.setType(type);
                link.setLinkId(busiLinkId);
                iRouteMapper.createRouteBusi(link);
            }
        }
        return busiLinkId;
    }

    /**
     * 创建资源树路由
     *
     * @param links 路由Link
     * @param type  类型
     * @return int
     * @throws Exception sql相关异常
     */
    int createRoute(List<Link> links, int type) throws Exception {
        int busiLinkId = iRouteMapper.getRouteBusiId();
        if (links != null && !links.isEmpty()) {
            for (Link link : links) {
                link.setType(type);
                link.setLinkId(busiLinkId);
                iRouteMapper.createRouteBusi(link);
                iRouteMapper.createRoute(link);
            }
        }
        return busiLinkId;
    }

    /**
     * 根据上行口获取连接的
     * BRAS端口信息
     *
     * @param upPortId 上联端口ID
     * @return 路由信息
     */
    public Link getBrasPortByUpPort(String upPortId) {
        List<Link> links = iRouteMapper.getPhyRoute(null, null, upPortId, null, "2406", null);
        if (links != null && !links.isEmpty()) {
            return links.get(0);
        } else {
            return null;
        }
    }

    /**
     * 根据关联端口查询OLT的上行口
     * 信息
     *
     * @param relaPortId 关联端口ID
     * @return 路由信息
     */
    public Link getOltPortByRelaPort(String relaPortId) {
        List<Link> links = iRouteMapper.getPhyRoute(null, "2510", null, null, null, relaPortId);
        if (links != null && !links.isEmpty()) {
            return links.get(0);
        } else {
            return null;
        }
    }
}
