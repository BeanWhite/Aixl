package com.aixl.m.dao;

import com.aixl.m.model.aiTestImg;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface aiTestImgMapper {
    int deleteByPrimaryKey(@Param("aiImgId") String aiImgId, @Param("aiReportTime") Date aiReportTime);

    int insert(aiTestImg record);

    aiTestImg selectByPrimaryKey(@Param("aiImgId") String aiImgId, @Param("aiReportTime") Date aiReportTime);

    List<aiTestImg> selectAll();

    int updateByPrimaryKey(aiTestImg record);
}