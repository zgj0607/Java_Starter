/*
 * Copyright (c) 2016
 * Just for learn java coding
 */

package com.restree.service;

import com.restree.domain.Link;
import com.restree.domain.ResTreeMain;

import java.util.List;

interface IRouteCalculate {

    void portDescriptionParse(String areaId);

    List<Link> oltUpRouteCalculate(ResTreeMain resTreeMain);

    List<Link> oltDownRouteCalculate(ResTreeMain resTreeMain);

}
