/*
 * Copyright (c) 2016
 * Just for learn java coding
 */

package com.restree.domain;

import java.io.Serializable;

public class Qinq implements Serializable {
    /**
     * 设备类型
     */
    public String deviceType;

    /**
     * 设备ID
     */
    private String deviceId;

    /**
     * 端口ID
     */
    private String portId;

    /**
     * SVLAN信息
     */
    private String svlan;

    /**
     * 起始CVLAN
     */
    private String startCvlan;

    /**
     * 终止CVLAN
     */
    private String endCvlan;

    /**
     * 内层VLAN
     */
    private String innerVlan;

    private int ontId;

    /**
     * 接入类型
     */
    private String accessType;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPortId() {
        return portId;
    }

    public void setPortId(String portId) {
        this.portId = portId;
    }

    public String getSvlan() {
        return svlan;
    }

    public void setSvlan(String svlan) {
        this.svlan = svlan;
    }

    public String getStartCvlan() {
        return startCvlan;
    }

    public void setStartCvlan(String startCvlan) {
        this.startCvlan = startCvlan;
    }

    public String getEndCvlan() {
        return endCvlan;
    }

    public void setEndCvlan(String endCvlan) {
        this.endCvlan = endCvlan;
    }

    public String getInnerVlan() {
        return innerVlan;
    }

    public void setInnerVlan(String innerVlan) {
        this.innerVlan = innerVlan;
    }

    public int getOntId() {
        return ontId;
    }

    public void setOntId(int ontId) {
        this.ontId = ontId;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }
}
