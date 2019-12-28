package com.aixl.m.dao;

import com.aixl.m.model.aiUserMsg;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface aiUserMsgMapper {
    int deleteByPrimaryKey(String aiUserId);

    int insert(aiUserMsg record);

    aiUserMsg selectByPrimaryKey(String aiUserId);

    List<aiUserMsg> selectAll();

    int updateByPrimaryKey(aiUserMsg record);

    List<aiUserMsg> selectMsgForDoc(String parameter);
}