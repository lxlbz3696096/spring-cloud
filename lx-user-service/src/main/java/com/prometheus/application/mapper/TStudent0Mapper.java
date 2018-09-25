package com.prometheus.application.mapper;

import com.prometheus.application.model.TStudent0;

public interface TStudent0Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TStudent0 record);

    int insertSelective(TStudent0 record);

    TStudent0 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TStudent0 record);

    int updateByPrimaryKey(TStudent0 record);
}