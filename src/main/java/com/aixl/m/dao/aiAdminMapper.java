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
    /**
     *
     * @param aiAdminId
     * @param aiAdminDepartment
     * @return
     */
    int deleteByPrimaryKey(@Param("aiAdminId") String aiAdminId, @Param("aiAdminDepartment") String aiAdminDepartment);

    /**
     *
     * @param record
     * @return
     */
    int insert(aiAdmin record);

    /**
     *
     * @param aiAdminId
     * @param aiAdminPwd
     * @return
     */
    aiAdmin selectByPrimaryKey(@Param("aiAdminId") String aiAdminId, @Param("aiAdminPwd") String aiAdminPwd);

    /**
     *
     * @return
     */
    List<aiAdmin> selectAll();

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(aiAdmin record);
}