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


    @Test
    public void testRouteAssebly() throws Exception {
        String[] accountNos = new String[1];
        accountNos[0] = "35790181106";
        resTreeAssembly.resTreeAllAssemble(null, null, accountNos);
    }

}
