package com.aixl.m.dao;

import com.aixl.m.model.aiTestImg;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface aiTestImgMapper {
    /**
     *
     * @param aiImgId
     * @param aiReportTime
     * @return
     */
    int deleteByPrimaryKey(@Param("aiImgId") String aiImgId, @Param("aiReportTime") Date aiReportTime);

    /**
     *
     * @param record
     * @return
     */
    int insert(aiTestImg record);

    /**
     *
     * @param aiImgId
     * @param aiReportTime
     * @return
     */
    aiTestImg selectByPrimaryKey(@Param("aiImgId") String aiImgId, @Param("aiReportTime") Date aiReportTime);

    /**
     *
     * @return
     */
    List<aiTestImg> selectAll();

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(aiTestImg record);
}