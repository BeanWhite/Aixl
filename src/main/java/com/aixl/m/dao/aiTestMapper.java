package com.aixl.m.dao;

import com.aixl.m.model.aiTest;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface aiTestMapper {
    int insert(aiTest record);

    List<aiTest> selectAll();

    List<aiTest> selectByScaleId(Integer id);
}