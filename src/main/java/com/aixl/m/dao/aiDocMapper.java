package com.aixl.m.dao;

import com.aixl.m.model.aiDoc;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface aiDocMapper {
    /**
     * 根据医生id删除医生账号
     * @param aiDocId   医生id
     * @return
     */
    int deleteByPrimaryKey(String aiDocId);

    /**
     * 新增一个医生
     * @param record    医生对象模型
     * @return
     */
    int insert(aiDoc record);

    /**
     * 根据医生id获取医生的所有信息
     * @param aiDocId
     * @return
     */
    aiDoc selectByPrimaryKey(String aiDocId);

    /**
     * 获取所有医生信息
     * @return
     */
    List<aiDoc> selectAll();

    /**
     * 根据医生id更新当前医生信息
     * @param record    医生对象模型
     * @return
     */
    int updateByPrimaryKey(aiDoc record);

    /**
     * 设置医生密码
     * @param record    医生对象模型
     * @return
     */
    int setPwd(aiDoc record);

    /**
     * 重置医生密码
     * @param record    医生对象模型
     * @return
     */
    int reSetPwd(aiDoc record);

    /**
     * 设置医生信息
     * @param record    医生对象模型
     * @return
     */
    int setDocMsg(aiDoc record);



}