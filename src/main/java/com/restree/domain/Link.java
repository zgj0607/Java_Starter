/*
 * Copyright (c) 2016
 * Just for learn java coding
 */

package com.restree.domain;

import java.io.Serializable;

public class Link implements Serializable {
    public Link(Node startNode, Node nextNode) {
        this.startDeviceType = startNode.getDeviceType();
        this.startDeviceId = startNode.getDeviceId();
        this.startPortId = startNode.getPortId();

        this.nextDeviceType = nextNode.getDeviceType();
        this.nextDeviceId = nextNode.getDeviceId();
        this.nextPortId = nextNode.getPortId();
    }

    public Link() {

    }

    public Link(Node startNode, Node nextNode, String vlan) {
        this.startDeviceType = startNode.getDeviceType();
        this.startDeviceId = startNode.getDeviceId();
        this.startPortId = startNode.getPortId();

        this.nextDeviceType = nextNode.getDeviceType();
        this.nextDeviceId = nextNode.getDeviceId();
        this.nextPortId = nextNode.getPortId();

        this.vlan = vlan;
    }

    public void setLink(Node startNode, Node nextNode, String vlan, int weight) {
        this.startDeviceType = startNode.getDeviceType();
        this.startDeviceId = startNode.getDeviceId();
        this.startPortId = startNode.getPortId();
        this.nextDeviceId = nextNode.getDeviceId();
        this.nextDeviceType = nextNode.getDeviceType();
        this.nextPortId = nextNode.getUpDownPortId();
        this.vlan = vlan;
        this.areaId = startNode.getAreaId();
    }

    public void setLinkStartNode(Node startNode) {
        this.startDeviceType = startNode.getDeviceType();
        this.startDeviceId = startNode.getDeviceId();
        this.startPortId = startNode.getPortId();
    }

    public void setLinkNextNode(Node nextNode) {
        this.nextDeviceId = nextNode.getDeviceId();
        this.nextDeviceType = nextNode.getDeviceType();
        this.nextPortId = nextNode.getUpDownPortId();
    }

    /*
    * 链路主键
    */
    private int key;
    /*
    * 链路ID
    */
    private int linkId;

    /**
     * 存储光路编码
     */
    private String linkNo;

    /*
    * 链路起始节点
    */
    private Node startNode;
    private String startDeviceType;
    private String startDeviceId;
    private String startPortId;

    /*
    * 下一跳节点
    */
    private Node nextNode;
    private String nextDeviceType;
    private String nextDeviceId;
    private String nextPortId;

    /*
    * 终止节点
    */
    private Node endNode;
    private String endDeviceType;
    private String endDeviceId;
    private String endPortId;

    /*
    * vlan
    */
    private String vlan;

    /*
    * 路由序号
    */
    private int sequence;

    private String areaId;

    /*
    * 链路类型，用于区分向上还是向下
    */
    private int type;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public String getLinkNo() {
        return linkNo;
    }

    public void setLinkNo(String linkNo) {
        this.linkNo = linkNo;
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public String getStartDeviceType() {
        return startDeviceType;
    }

    public void setStartDeviceType(String startDeviceType) {
        this.startDeviceType = startDeviceType;
    }

    public String getStartDeviceId() {
        return startDeviceId;
    }

    public void setStartDeviceId(String startDeviceId) {
        this.startDeviceId = startDeviceId;
    }

    public String getStartPortId() {
        return startPortId;
    }

    public void setStartPortId(String startPortId) {
        this.startPortId = startPortId;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public String getNextDeviceType() {
        return nextDeviceType;
    }

    public void setNextDeviceType(String nextDeviceType) {
        this.nextDeviceType = nextDeviceType;
    }

    public String getNextDeviceId() {
        return nextDeviceId;
    }

    public void setNextDeviceId(String nextDeviceId) {
        this.nextDeviceId = nextDeviceId;
    }

    public String getNextPortId() {
        return nextPortId;
    }

    public void setNextPortId(String nextPortId) {
        this.nextPortId = nextPortId;
    }

    public Node getEndNode() {
        return endNode;
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }

    public String getEndDeviceType() {
        return endDeviceType;
    }

    public void setEndDeviceType(String endDeviceType) {
        this.endDeviceType = endDeviceType;
    }

    public String getEndDeviceId() {
        return endDeviceId;
    }

    public void setEndDeviceId(String endDeviceId) {
        this.endDeviceId = endDeviceId;
    }

    public String getEndPortId() {
        return endPortId;
    }

    public void setEndPortId(String endPortId) {
        this.endPortId = endPortId;
    }

    public String getVlan() {
        return vlan;
    }

    public void setVlan(String vlan) {
        this.vlan = vlan;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
