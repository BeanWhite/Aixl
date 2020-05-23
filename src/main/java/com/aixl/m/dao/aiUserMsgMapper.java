package com.aixl.m.dao;

import com.aixl.m.model.aiUserMsg;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface aiUserMsgMapper {
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
    int insert(aiUserMsg record);

    /**
     *
     * @param aiUserId
     * @return
     */
    aiUserMsg selectByPrimaryKey(String aiUserId);

    /**
     *
     * @return
     */
    List<aiUserMsg> selectAll();

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(aiUserMsg record);

    /**
     *
     * @param parameter
     * @return
     */
    aiUserMsg selectMsgForDoc(String parameter);

    /**
     *
     * @param record
     * @return
     */
    int updataMsgForDoc(aiUserMsg record);

    /**
     *
     * @return
     */
    Page<aiUserMsg> selectAllByPage();
}