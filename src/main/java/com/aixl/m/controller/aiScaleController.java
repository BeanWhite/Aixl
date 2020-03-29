package com.aixl.m.controller;

import com.aixl.m.model.aiTest;
import com.aixl.m.service.aiScaleService;
import com.aixl.m.utils.ReturnObject;
import com.aixl.m.utils.ReturnUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 量表控制层
 */
@RestController
@RequestMapping("/scales")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class aiScaleController {

    @Autowired
    aiScaleService scaleService;


    /**
     * 获取所有量基本信息表接口
     *
     * @return 量表信息结果集（分页）
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ReturnObject<Object> getAll() {
        return scaleService.getAll();
    }

    @RequestMapping(value = "/pages/{currentPage}/{pageSize}", method = RequestMethod.GET)
    public ReturnObject<Object> getAllByPage(@PathVariable(value = "currentPage") Integer currentPage,
                                             @PathVariable(value = "pageSize") Integer pageSize) {
        return scaleService.getPages(currentPage, pageSize);
    }


    /**
     * 根据量表id获取量表题目信息接口
     *
     * @param id 量表id号
     * @return 题目信息
     */
    @RequestMapping(value = "/testMsg/{id}", method = RequestMethod.GET)
    @Deprecated
    public ReturnObject<Object> getScaleMsg(@PathVariable("id") Integer id) {
        return scaleService.getScaleTest(id);
    }

    @RequestMapping(value = "/testMsg/{id}")
    public ReturnObject<Object> getScaleMsg_n(@PathVariable("id")Integer id){

        return scaleService.getScaleTest_n(id);
    }

    /**
     * 根据id号或名称分页获取
     *
     * @param currentPage   当前页码
     * @param pageSize  页面大小
     * @param string    量表id或名称
     * @return  量表信息结果集
     */
    @RequestMapping(value = "/test/pages/{currentPage}/{pageSize}/{str}", method = RequestMethod.GET)
    public ReturnObject<Object> getTestByIdOrName(@PathVariable(value = "currentPage") Integer currentPage,
                                                  @PathVariable(value = "pageSize") Integer pageSize,
                                                  @PathVariable(value = "str") String string) {
        return scaleService.getTestByIdOrName(currentPage, pageSize, string);

    }


    /**
     * 根据量表id和题号获取题目信息
     *
     * @param a 量表id
     * @param b 题目号
     * @return  结果集
     */
    @RequestMapping(value = "/test/{scaleId}/{testId}", method = RequestMethod.GET)
    public ReturnObject<Object> getTestByDoubleId(@PathVariable(value = "scaleId") String a,
                                                  @PathVariable(value = "testId") String b) {
        return scaleService.getTestByDoubleId(a, b);
    }

    /**
     * 修改题目信息
     *
     * @param json  传入字符串必须为json对象，对应javaBean中aiTest类
     * @return
     */
    @RequestMapping(value = "/test/{json}", method = RequestMethod.PUT)
    public ReturnObject<Object> setTestMsg(@PathVariable(value = "json") String json) {
        aiTest test = JSON.parseObject(json, aiTest.class);
        test.setAiQuestionContent(test.getAiQuestionContent().replace("split","split;"));
        return scaleService.setTest(test);
    }


    /**
     * 获取所有量表的id号
     * @return 返回所有量表的id号
     */
    @RequestMapping(value = "/ids", method = RequestMethod.GET)
    public ReturnObject<Object> getScaleIdList() {
        return scaleService.getScaleIdList();
    }

    /**
     * 新增题目
     * @param test  传入字符串必须是json字符串，对应javaBean为aiTest类
     * @return  成功返回1，失败返回0
     */
    @RequestMapping(value = "/test/{json}",method = RequestMethod.POST)
    public ReturnObject<Object> addTest(@PathVariable(value = "json") String test){
        aiTest test1 = JSON.parseObject(test,aiTest.class);
        System.out.println(test1.toString());
        return scaleService.addTest(test1);
    }

    /**
     * 删除题目----量表id，题目id
     * @param scaleId 量表id
     * @param testId    题目id
     * @return
     */
    @RequestMapping(value = "/test/{scaleId}/{testId}",method = RequestMethod.DELETE)
    public ReturnObject<Object> deleteTest(@PathVariable(value = "scaleId")String scaleId,
                                           @PathVariable(value = "testId")String testId){
        if(!scaleId.matches("[0-9]{1,}"))
            return ReturnUtils.success(0);
        if(!testId.matches("[0-9]{1,}"))
            return ReturnUtils.success(0);

        aiTest test = new aiTest();
        test.setAiScaleId(Integer.parseInt(scaleId));
        test.setAiQuestionId(Integer.parseInt(testId));
        return scaleService.deleteTest(test);

    }
}
