package com.aixl.m.dao;

import com.aixl.m.model.aiTestResult;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface aiTestResultMapper {
    int insert(aiTestResult record);

    List<aiTestResult> selectAll();
}