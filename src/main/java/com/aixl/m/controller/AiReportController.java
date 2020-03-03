package com.aixl.m.controller;


import com.aixl.m.model.aiReport;
import com.aixl.m.service.aiReportService;
import com.aixl.m.utils.ReturnObject;
import com.aixl.m.utils.ReturnUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;

/**
 * 控制层，报告管理
 */
@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
@RequestMapping("/report")
public class AiReportController {

    @Autowired
    private aiReportService reportService;

    /**
     * 新增用户和报告
     * @return 结果类
     */
    @RequestMapping(value = "/html/{obj}",method = RequestMethod.POST)
    public ReturnObject<Object> addReport(@PathVariable(value = "obj") String obj){
        return reportService.addReport(JSON.parseObject(obj,aiReport.class));
    }

    /**
     * 获取报告并打印
     * @param userId 用户id
     * @param scaleId 量表id
     * @param testTime 测试时间
     * @return 结果类
     */
    @RequestMapping(value = "/report/{userId}/{scaleId}/{testTime}")
    public ReturnObject<Object> toPrintReport(@PathVariable(value = "userId")String userId,
                                              @PathVariable(value = "scaleId") Integer scaleId,
                                              @PathVariable(value = "testTime") String testTime){


        return ReturnUtils.success();
    }

    /**
     * 删除报告
     * @return 结果类
     */
    //@RequestMapping(value = "/html",method = RequestMethod.DELETE)
    public ReturnObject<Object> deleteReport(){
        return ReturnUtils.success();
    }

    /**
     * 新增报告
     * @return 结果类
     */
    public ReturnObject<Object> addReport(){
        return ReturnUtils.success();
    }

    /**
     * 分页获取报告
     * @param currentPage   当前页码
     * @param pageSize  页面大小
     * @return  结果集
     */
    @RequestMapping(value = "/html/{currentPage}/{pageSize}",method = RequestMethod.GET)
    public ReturnObject<Object> getReport(@PathVariable(value = "currentPage")Integer currentPage,
                                          @PathVariable(value = "pageSize")Integer pageSize){
        return reportService.getReports(currentPage,pageSize);
    }


    /**
     * 根据用户id获取所有报告（历史记录）
     * @param currentPage   当前页码
     * @param pageSize  页面大小
     * @param userId    用户id
     * @return  结果集
     */
    @RequestMapping(value = "/html/{currentPage}/{pageSize}/{userId}",method = RequestMethod.GET)
    public ReturnObject<Object> getReportsById( @PathVariable(value = "currentPage")Integer currentPage,
                                                @PathVariable(value = "pageSize")Integer pageSize,
                                                @PathVariable(value = "userId")String userId){
        return reportService.getReports(currentPage,pageSize,userId);
    }
}
