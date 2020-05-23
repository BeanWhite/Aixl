package com.aixl.m.dao;

import com.aixl.m.model.aiTest;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface aiTestMapper {
    /**
     *
     * @param record
     * @return
     */
    int insert(aiTest record);

    /**
     *
     * @return
     */
    List<aiTest> selectAll();

    /**
     *
     * @param id
     * @return
     */
    List<aiTest> selectByScaleId(Integer id);

    /**
     *
     * @param param
     * @return
     */
    Page<aiTest> selectByIdOrName(String param);

    /**
     *
     * @param scaleId
     * @param questionId
     * @return
     */
    int selectByDoubleId(@Param("scaleId")String scaleId,@Param("questionId")String questionId);

    /**
     *
     * @param test
     * @return
     */
    int setTest(aiTest test);

    /**
     *
     * @param test
     * @return
     */
    int addTest(aiTest test);

    /**
     *
     * @param test
     * @return
     */
    int deleteTest(aiTest test);
}