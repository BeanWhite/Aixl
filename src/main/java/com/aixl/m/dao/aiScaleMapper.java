package com.aixl.m.dao;

import com.aixl.m.model.aiScale;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface aiScaleMapper {
    int deleteByPrimaryKey(Integer aiScaleId);

    int insert(aiScale record);

    aiScale selectByPrimaryKey(Integer aiScaleId);

    List<aiScale> selectAll();

    int updateByPrimaryKey(aiScale record);

    Page<aiScale> selectByPages();

    List<aiScale> getScaleIdList();
}