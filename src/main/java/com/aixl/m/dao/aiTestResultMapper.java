package com.aixl.m.dao;

import com.aixl.m.model.aiTestResult;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface aiTestResultMapper {
    /**
     *
     * @param aiUserId
     * @return
     */
    int deleteByPrimaryKey(String aiUserId);

    /**
     *
     * @param record
     * @return
     */
    int insert(aiTestResult record);

    /**
     *
     * @param aiUserId
     * @param aiScaleId
     * @return
     */
    aiTestResult selectByPrimaryKey(@Param("aiUserId") String aiUserId,@Param("aiScaleId")Integer aiScaleId);

    //List<aiTestResult> selectAll();

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(aiTestResult record);

    /**
     *
     * @return
     */
    Page<aiTestResult> selectAll();

    /**
     *
     * @return
     */
    List<aiTestResult> findAll();

}