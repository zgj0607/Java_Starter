/*
 * Copyright (c) 2016
 * Just for learn java coding
 */

package com.restree.domain;


import java.io.Serializable;

public class ResTreeMain implements Serializable {

    private int id;

    private String accountNo;

    private String oltPonPortId;

    private String oltId;

    private String oltIp;

    private String oltNo;

    private String oltPonId;

    private String oltPonNo;

    private String svlan;

    private String cvlan;

    private String obdId;

    private String obdNo;

    private String obdPortId;

    private String obdPortNo;

    private String mainOptRoadId;

    private String childOptRaodId;

    private String loid;

    private String ontId;

    private String brasId;

    private String brasNo;

    private String brasIp;

    private String brasPortId;

    private String brasPortNo;

    private String upBusiId;

    private String downBusiId;

    private String routeAllId;

    private String customerId;

    private String prodInstId;

    private int state;

    private String areaId;

    private String accessType;

    private String userMac;

    private String terminalSn;

    private String terminalMac;

    private String onuId;

    private String onuNo;

    private String onuIp;

    private String onuPortId;

    private String onuPortNo;

    public ResTreeMain() {
    }

    public ResTreeMain(String accountNo) {
        this.accountNo = accountNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getOltPonPortId() {
        return oltPonPortId;
    }

    public void setOltPonPortId(String oltPonPortId) {
        this.oltPonPortId = oltPonPortId;
    }

    public String getOltId() {
        return oltId;
    }

    public void setOltId(String oltId) {
        this.oltId = oltId;
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

    public String getObdId() {
        return obdId;
    }

    public void setObdId(String obdId) {
        this.obdId = obdId;
    }

    public String getLoid() {
        return loid;
    }

    public void setLoid(String loid) {
        this.loid = loid;
    }

    public String getOntId() {
        return ontId;
    }

    public void setOntId(String ontId) {
        this.ontId = ontId;
    }

    public String getBrasId() {
        return brasId;
    }

    public void setBrasId(String brasId) {
        this.brasId = brasId;
    }

    public String getBrasPortId() {
        return brasPortId;
    }

    public void setBrasPortId(String brasPortId) {
        this.brasPortId = brasPortId;
    }

    public String getUpBusiId() {
        return upBusiId;
    }

    public void setUpBusiId(String upBusiId) {
        this.upBusiId = upBusiId;
    }

    public String getDownBusiId() {
        return downBusiId;
    }

    public void setDownBusiId(String downBusiId) {
        this.downBusiId = downBusiId;
    }

    public String getRouteAllId() {
        return routeAllId;
    }

    public void setRouteAllId(String routeAllId) {
        this.routeAllId = routeAllId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProdInstId() {
        return prodInstId;
    }

    public void setProdInstId(String prodInstId) {
        this.prodInstId = prodInstId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getUserMac() {
        return userMac;
    }

    public void setUserMac(String userMac) {
        this.userMac = userMac;
    }

    public String getObdNo() {
        return obdNo;
    }

    public void setObdNo(String obdNo) {
        this.obdNo = obdNo;
    }

    public String getBrasPortNo() {
        return brasPortNo;
    }

    public void setBrasPortNo(String brasPortNo) {
        this.brasPortNo = brasPortNo;
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

    public String getOltIp() {
        return oltIp;
    }

    public void setOltIp(String oltIp) {
        this.oltIp = oltIp;
    }

    public String getOltNo() {
        return oltNo;
    }

    public void setOltNo(String oltNo) {
        this.oltNo = oltNo;
    }

    public String getOltPonId() {
        return oltPonId;
    }

    public void setOltPonId(String oltPonId) {
        this.oltPonId = oltPonId;
    }

    public String getOltPonNo() {
        return oltPonNo;
    }

    public void setOltPonNo(String oltPonNo) {
        this.oltPonNo = oltPonNo;
    }

    public String getObdPortId() {
        return obdPortId;
    }

    public void setObdPortId(String obdPortId) {
        this.obdPortId = obdPortId;
    }

    public String getObdPortNo() {
        return obdPortNo;
    }

    public void setObdPortNo(String obdPortNo) {
        this.obdPortNo = obdPortNo;
    }

    public String getMainOptRoadId() {
        return mainOptRoadId;
    }

    public void setMainOptRoadId(String mainOptRoadId) {
        this.mainOptRoadId = mainOptRoadId;
    }

    public String getChildOptRaodId() {
        return childOptRaodId;
    }

    public void setChildOptRaodId(String childOptRaodId) {
        this.childOptRaodId = childOptRaodId;
    }

    public String getBrasNo() {
        return brasNo;
    }

    public void setBrasNo(String brasNo) {
        this.brasNo = brasNo;
    }

    public String getBrasIp() {
        return brasIp;
    }

    public void setBrasIp(String brasIp) {
        this.brasIp = brasIp;
    }

    public String getOnuId() {
        return onuId;
    }

    public void setOnuId(String onuId) {
        this.onuId = onuId;
    }

    public String getOnuNo() {
        return onuNo;
    }

    public void setOnuNo(String onuNo) {
        this.onuNo = onuNo;
    }

    public String getOnuIp() {
        return onuIp;
    }

    public void setOnuIp(String onuIp) {
        this.onuIp = onuIp;
    }

    public String getOnuPortId() {
        return onuPortId;
    }

    public void setOnuPortId(String onuPortId) {
        this.onuPortId = onuPortId;
    }

    public String getOnuPortNo() {
        return onuPortNo;
    }

    public void setOnuPortNo(String onuPortNo) {
        this.onuPortNo = onuPortNo;
    }
}
