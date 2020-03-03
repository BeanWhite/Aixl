package com.aixl.m.dao;

import com.aixl.m.model.aiAdmin;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 数据库设计主码为id和部门，服务器目前只需用id即可
 */
@Repository
public interface aiAdminMapper {
    int deleteByPrimaryKey(@Param("aiAdminId") String aiAdminId, @Param("aiAdminDepartment") String aiAdminDepartment);

    int insert(aiAdmin record);

    aiAdmin selectByPrimaryKey(@Param("aiAdminId") String aiAdminId, @Param("aiAdminPwd") String aiAdminPwd);

    List<aiAdmin> selectAll();

    int updateByPrimaryKey(aiAdmin record);
}