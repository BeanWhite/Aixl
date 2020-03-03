package com.aixl.m.service;


import com.aixl.m.dao.aiTestHistoryMapper;
import com.aixl.m.dao.aiTestHtmlMapper;
import com.aixl.m.dao.aiTestImgMapper;
import com.aixl.m.dao.aiTestResultMapper;
import com.aixl.m.model.*;
import com.aixl.m.utils.ReturnObject;
import com.aixl.m.utils.ReturnUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class aiReportService {

    @Autowired
    private aiTestResultMapper testResultMapper;

    @Autowired
    private aiTestImgMapper testImgMapper;

    @Autowired
    private aiTestHtmlMapper testHtmlMapper;

    @Autowired
    private aiTestHistoryMapper testHistoryMapper;

    private aiReport report;


    /**
     * 新增用户报告
     * @param report
     * @return
     */
    public ReturnObject<Object> addReport(aiReport report){
        if(report.getTestResult()==null||report.getTestHtml()==null)
            return null;
        aiTestResult testResult = report.getTestResult();
        ArrayList<aiTestImg> testImgs = report.getTestImgs();
        aiTestHtml testHtml = report.getTestHtml();
        aiTestResult test = testResultMapper.selectByPrimaryKey(testResult.getAiUserId(),testResult.getAiScaleId());
       if(test == null)
            testResultMapper.insert(testResult);
        testHtmlMapper.insert(testHtml);
        for(Integer i =0;i<testImgs.size();i++){
            testImgMapper.insert(testImgs.get(i));
        }
        aiTestHistory testHistory = new aiTestHistory();
        testHistory.setAiDocId(report.getTestHtml().getAiDocId());
        testHistory.setAiDocName(report.getTestHtml().getAiDocName());
        testHistory.setAiScaleId(report.getTestResult().getAiScaleId());
        testHistory.setAiScaleName(report.getTestResult().getAiTestName());
        testHistory.setAiTestTime(report.getTestHtml().getTime());
        testHistory.setAiUserId(report.getTestResult().getAiUserId());
       int a = testHistoryMapper.insert(testHistory);
        return ReturnUtils.success(a);
    }

    /**
     * 删除报告
     * @return
     */
    public ReturnObject<Object> deleteReport(){
        return ReturnUtils.success();
    }

    /**
     * 新增报告
     * @return
     */
    public ReturnObject<Object> setReport(){
        return ReturnUtils.success();
    }

    /**
     * 获取最新报告
     * @return
     */
    public ReturnObject<Object> getReport(String userId){

        return ReturnUtils.success();
    }

    /**
     * 分页获取历史报告
     */
    public ReturnObject<Object> getReports(Integer currentPage,Integer pageSize){
        PageHelper.startPage(currentPage,pageSize);
        Page<aiTestHistory> page = testHistoryMapper.selectAll();

        return  ReturnUtils.success(page.getTotal(),page);
    }

    /**
     * 分页获取历史报告
     * @param currentPage 页号
     * @param pageSize  页面大小
     * @param userId    用户id
     * @return
     */
    public ReturnObject<Object> getReports(Integer currentPage,Integer pageSize, String userId){
        //可以存入到redi中，方便下次快速取出，也可以存入Map中（小数据）
        PageHelper.startPage(currentPage,pageSize);
        Page<aiTestHistory> page = testHistoryMapper.getHistory(userId);
        return  ReturnUtils.success(page.getTotal(),page);

    }
}