package com.aixl.m.dao;

import com.aixl.m.model.aiScale;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface aiScaleMapper {
    /**
     *
     * @param aiScaleId
     * @return
     */
    int deleteByPrimaryKey(Integer aiScaleId);

    /**
     *
     * @param record
     * @return
     */
    int insert(aiScale record);

    /**
     *
     * @param aiScaleId
     * @return
     */
    aiScale selectByPrimaryKey(Integer aiScaleId);

    /**
     *
     * @return
     */
    List<aiScale> selectAll();

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(aiScale record);

    /**
     *
     * @return
     */
    Page<aiScale> selectByPages();

    /**
     *
     * @return
     */
    List<aiScale> getScaleIdList();
}