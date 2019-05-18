package com.xkazxx.mapper;

import com.xkazxx.bean.Technology;

import java.util.List;

/**
 * @author alan.zhang
 */
public interface TechnologyMapper {
    int deleteByPrimaryKey(String technologyId);

    int insert(Technology record);

    int insertSelective(Technology record);

    Technology selectByPrimaryKey(String technologyId);

    int updateByPrimaryKeySelective(Technology record);

    int updateByPrimaryKey(Technology record);

    List<Technology> findTechnologyAll();
}