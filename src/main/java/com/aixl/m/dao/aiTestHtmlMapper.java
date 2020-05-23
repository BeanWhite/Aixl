package com.aixl.m.dao;

import com.aixl.m.model.aiTestHtml;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface aiTestHtmlMapper {
    /**
     *
     * @param aiReportTextTime
     * @param aiReportTextId
     * @return
     */
    int deleteByPrimaryKey(@Param("aiReportTextTime") Date aiReportTextTime, @Param("aiReportTextId") String aiReportTextId);

    /**
     *
     * @param record
     * @return
     */
    int insert(aiTestHtml record);

    /**
     *
     * @param aiReportTextTime
     * @param aiReportTextId
     * @return
     */
    aiTestHtml selectByPrimaryKey(@Param("aiReportTextTime") Date aiReportTextTime, @Param("aiReportTextId") String aiReportTextId);

    /**
     *
     * @return
     */
    List<aiTestHtml> selectAll();

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(aiTestHtml record);
}