/*
 * Copyright (c) 2016
 * Just for learn java coding
 */

package com.restree.domain;

/*
 * Created by zhou.guangjie on 9/27/16.
 */

import java.io.Serializable;

public class Node implements Serializable {
    /**
     * 设备ID
     */
    private String deviceId;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 设备类型
     */
    private String deviceType;
    /**
     * 端口ID
     */
    private String portId;
    /**
     * 端口编码
     */
    private String portNo;
    /**
     * 上联或下联端口ID
     */
    private String upDownPortId;
    /**
     * 是否是聚合口
     */
    private int isGroupPort;

    /**
     * MAC
     */
    private String mac;
    /**
     * 端口描述
     */
    private String portDesc;
    /**
     * ONT ID
     */
    private String ontId;
    /**
     * SVLAN
     */
    private String svlan;
    /**
     * CVLAN
     */
    private String cvlan;
    /**
     * 目标MAC
     */
    private String targetMac;
    /**
     * 账号
     */
    private String accountNo;
    /**
     * 区域ID
     */
    private String areaId;
    /*
    * IP地址
    */
    private String ip;

    private int weight;

    private int relaWeight;

    private String accessType;

    private String terminalSn;

    private String terminalMac;

    private String loid;

    public Node() {
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getPortId() {
        return portId;
    }

    public void setPortId(String portId) {
        this.portId = portId;
    }

    public String getUpDownPortId() {
        return upDownPortId;
    }

    public void setUpDownPortId(String upDownPortId) {
        this.upDownPortId = upDownPortId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getPortDesc() {
        return portDesc;
    }

    public void setPortDesc(String portDesc) {
        this.portDesc = portDesc;
    }

    public String getOntId() {
        return ontId;
    }

    public void setOntId(String ontId) {
        this.ontId = ontId;
    }

    public String getSvlan() {
        return svlan;
    }

    public void setSvlan(String svlan) {
        this.svlan = svlan;
    }

    public String getCvlan() {
        return cvlan;
    }

    public void setCvlan(String cvlan) {
        this.cvlan = cvlan;
    }

    public String getTargetMac() {
        return targetMac;
    }

    public void setTargetMac(String targetMac) {
        this.targetMac = targetMac;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getPortNo() {
        return portNo;
    }

    public void setPortNo(String portNo) {
        this.portNo = portNo;
    }

    public int getIsGroupPort() {
        return isGroupPort;
    }

    public void setIsGroupPort(int isGroupPort) {
        this.isGroupPort = isGroupPort;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getRelaWeight() {
        return relaWeight;
    }

    public void setRelaWeight(int relaWeight) {
        this.relaWeight = relaWeight;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getTerminalSn() {
        return terminalSn;
    }

    public void setTerminalSn(String terminalSn) {
        this.terminalSn = terminalSn;
    }

    public String getTerminalMac() {
        return terminalMac;
    }

    public void setTerminalMac(String terminalMac) {
        this.terminalMac = terminalMac;
    }

    public String getLoid() {
        return loid;
    }

    public void setLoid(String loid) {
        this.loid = loid;
    }
}
