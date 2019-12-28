package com.aixl.m.dao;

import com.aixl.m.model.aiDoc;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface aiDocMapper {
    int deleteByPrimaryKey(String aiDocId);

    int insert(aiDoc record);

    aiDoc selectByPrimaryKey(String aiDocId);

    List<aiDoc> selectAll();

    int updateByPrimaryKey(aiDoc record);
}