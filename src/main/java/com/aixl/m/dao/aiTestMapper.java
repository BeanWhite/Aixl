package com.aixl.m.dao;

import com.aixl.m.model.aiTest;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface aiTestMapper {
    int insert(aiTest record);

    List<aiTest> selectAll();

    List<aiTest> selectByScaleId(Integer id);

    Page<aiTest> selectByIdOrName(String param);

    int selectByDoubleId(@Param("scaleId")String scaleId,@Param("questionId")String questionId);

    int setTest(aiTest test);

    int addTest(aiTest test);

    int deleteTest(aiTest test);
}