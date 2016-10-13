/*
 * Copyright (c) 2016
 * Just for learn java coding
 */

package com.restree;

import com.restree.domain.Node;
import com.restree.mapper.INodeMapper;
import com.restree.service.MacForwardService;
import com.restree.service.ResTreeAssembly;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class NodeTest extends ResTreeTestBase {
    @Autowired
    private INodeMapper nodeMapper;

    @Autowired
    private MacForwardService macForwardService;

    @Autowired
    private ResTreeAssembly resTreeAssembly;

    //    @Test
//    public void testGetDevice() throws Exception{
//        String deviceId = "000125081000001011148075";
//        String areaId = "000102000000000000000005";
//        String deviceType = String.valueOf(ResourceType.BRAS);
//        List<Node> devices = nodeMapper.getDevice(null, null, deviceType,null,null);
//        for (Node device : devices) {
//            System.out.println(device.getDeviceName() + "-" + device.getIp());
//        }
//    }
//
    @Test
    public void testSort() throws Exception {
        Node node1 = new Node();
        node1.setWeight(0);
        List<Node> nodes = new ArrayList<>();
        nodes.add(node1);
        macForwardService.sortDevices(nodes);
        for (Node node : nodes) {
            System.out.println(node.getWeight());
        }
    }


//    @Test
//    public void testRouteAssebly() throws Exception{
//        String[] accountNos = new String[1];
//        accountNos[0] = "35790181106";
//        resTreeAssembly.resTreeAllAssemble(null,null,accountNos);
//    }

}
