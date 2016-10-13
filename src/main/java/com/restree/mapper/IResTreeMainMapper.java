/*
 * Copyright (c) 2016
 * Just for learn java coding
 */

package com.restree.mapper;

import com.restree.domain.ResTreeMain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IResTreeMainMapper {


    List<ResTreeMain> getCrmInfo(@Param("areaId") String areaId,
                                 @Param("accountNo") String accountNo);

    List<ResTreeMain> getAaaInfo(@Param("areaId") String areaId,
                                 @Param("brasIp") String brasIp,
                                 @Param("accountNo") String accountNo);

    int updateCrmTreeMain(ResTreeMain treeData);

    int insertCrmTreeMain(ResTreeMain treeData);

    int updateAaaTreeMain(ResTreeMain treeData);

    int updateBusiTreeMain(ResTreeMain treeData);

    int updateTreeMainField(ResTreeMain treeData);
}
