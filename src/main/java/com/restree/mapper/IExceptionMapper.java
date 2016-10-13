/*
 * Copyright (c) 2016
 * Just for learn java coding
 */

package com.restree.mapper;

import com.restree.domain.ResTreeException;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IExceptionMapper {
    void insertException(ResTreeException e);

    ResTreeException getResTreeException(@Param("exceptionType") String exceptionType);
}
