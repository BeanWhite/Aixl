package com.aixl.m.dao;

import com.aixl.m.model.aiUser;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface aiUserMapper {
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
    int insert(aiUser record);

    /**
     *
     * @param aiUserId
     * @return
     */
    aiUser selectByPrimaryKey(String aiUserId);

    /**
     *
     * @return
     */
    List<aiUser> selectAll();

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(aiUser record);

    /**
     * @param aiUser
     * @return
     */
    aiUser getStatusById(aiUser aiUser);

    /**
     *

     * @return
     */
    Page<aiUser> getStatus();

    /**
     * 修改用户状态
     * @param id        用户id
     * @param status    状态值
     * @return
     */
    int setStatusById(@Param("id") String id, @Param("status") String status);
}