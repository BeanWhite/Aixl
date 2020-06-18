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
     * 获取用户状态
     * @param aiUser    User对象，里面只有id有效
     * @return
     */
    aiUser getStatusById(aiUser aiUser);

    /**
     *根据医生id号分页获取所有病人状态列表
     * @param id    医生id号
     * @return
     */
    Page<aiUser> getStatus(String id);

    /**
     * 修改用户状态
     * @param id        用户id
     * @param status    状态值
     * @return
     */
    int setStatusById(@Param("id") String id, @Param("status") String status);


    /**
     * 分页查询获取获取用户状态
     * @param key 查询的id号或名字
     * @param docId 查询发起者的id号
     * @return
     */
    Page<aiUser> getStatusByIdOrName(@Param("key") String key,@Param("docId") String docId);
}