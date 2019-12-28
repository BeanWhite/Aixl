package com.aixl.m.dao;

import com.aixl.m.model.aiAdmin;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface aiAdminMapper {
    int deleteByPrimaryKey(@Param("aiAdminId") String aiAdminId, @Param("aiAdminDepartment") String aiAdminDepartment);

    int insert(aiAdmin record);

    aiAdmin selectByPrimaryKey(@Param("aiAdminId") String aiAdminId, @Param("aiAdminDepartment") String aiAdminDepartment);

    List<aiAdmin> selectAll();

    int updateByPrimaryKey(aiAdmin record);
}