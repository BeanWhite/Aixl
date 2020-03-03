package com.aixl.m.dao;

import com.aixl.m.model.aiTestHtml;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface aiTestHtmlMapper {
    int deleteByPrimaryKey(@Param("aiReportTextTime") Date aiReportTextTime, @Param("aiReportTextId") String aiReportTextId);

    int insert(aiTestHtml record);

    aiTestHtml selectByPrimaryKey(@Param("aiReportTextTime") Date aiReportTextTime, @Param("aiReportTextId") String aiReportTextId);

    List<aiTestHtml> selectAll();

    int updateByPrimaryKey(aiTestHtml record);
}