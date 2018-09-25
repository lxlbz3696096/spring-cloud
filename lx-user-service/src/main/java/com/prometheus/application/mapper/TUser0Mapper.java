package com.prometheus.application.mapper;

import com.prometheus.application.model.TUser0;

public interface TUser0Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUser0 record);

    int insertSelective(TUser0 record);

    TUser0 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUser0 record);

    int updateByPrimaryKey(TUser0 record);

	TUser0 selectByName(String name);
}