package com.aixl.m.dao;

import com.aixl.m.model.aiUser;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface aiUserMapper {
    int deleteByPrimaryKey(String aiUserId);

    int insert(aiUser record);

    aiUser selectByPrimaryKey(String aiUserId);

    List<aiUser> selectAll();

    int updateByPrimaryKey(aiUser record);

}