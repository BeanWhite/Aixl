package com.aixl.m.dao;

import com.aixl.m.model.aiTestHistory;
import java.util.List;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface aiTestHistoryMapper {
    int deleteByPrimaryKey(@Param("aiUserId") String aiUserId, @Param("aiScaleId") Integer aiScaleId);

    int insert(aiTestHistory record);

    aiTestHistory selectByPrimaryKey(@Param("aiUserId") String aiUserId, @Param("aiScaleId") Integer aiScaleId);

    Page<aiTestHistory> selectAll();

    int updateByPrimaryKey(aiTestHistory record);

    Page<aiTestHistory> getHistory(String aiUserId);
}