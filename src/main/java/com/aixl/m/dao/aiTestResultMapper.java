package com.aixl.m.dao;

import com.aixl.m.model.aiTestResult;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface aiTestResultMapper {
    int deleteByPrimaryKey(String aiUserId);

    int insert(aiTestResult record);

    aiTestResult selectByPrimaryKey(@Param("aiUserId") String aiUserId,@Param("aiScaleId")Integer aiScaleId);

    //List<aiTestResult> selectAll();

    int updateByPrimaryKey(aiTestResult record);

    Page<aiTestResult> selectAll();

    List<aiTestResult> findAll();

}