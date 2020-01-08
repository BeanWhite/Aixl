package com.aixl.m.service;

import com.aixl.m.dao.aiScaleMapper;
import com.aixl.m.dao.aiTestMapper;
import com.aixl.m.model.aiScale;
import com.aixl.m.model.aiTest;
import com.aixl.m.utils.RedisUtils;
import com.aixl.m.utils.ReturnObject;
import com.aixl.m.utils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class aiScaleService {

    @Autowired
    aiScaleMapper scaleMapper;

    @Autowired
    aiTestMapper testMapper;



    @Autowired
    RedisUtils<Object> redisUtils;

    /**
     * 获取所有量表信息
     * @return
     */
    public ReturnObject<Object> getAll(){
        List<aiScale> scale =(List<aiScale>) redisUtils.getCache("allScales");
        if(scale!=null){
            return ReturnUtils.success("1",scale) ;
        }
        else {
            try {
                scale = scaleMapper.selectAll();
                if(scale.size()==0)
                    return ReturnUtils.success("1","没有内容");
                else
                    redisUtils.setCache("allScales",scale);
                return ReturnUtils.success("1",scale);
            }catch (Exception e){
                return ReturnUtils.success("0","没有内容");
            }
        }
    }


    /**
     * 根据量表id获取量表中的题目信息
     * @param id
     * @return
     */
    public ReturnObject<Object> getScaleTest(Integer id){
        List<aiTest> tests =(List<aiTest>)redisUtils.getCache("scaleTestMsg="+id);
        if(tests==null){
            try {
                tests = testMapper.selectByScaleId(id);
                if(tests.size()==0){
                    return ReturnUtils.success("1","没有内容");
                }else {
                    redisUtils.setCache("scaleTestMsg="+id,tests);
                    return ReturnUtils.success("1",tests);
                }
            }catch (Exception e){
                return ReturnUtils.success("0","没有内容");
            }
        }else {
            return ReturnUtils.success("1",tests);
        }
    }

}
