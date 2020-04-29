package com.aixl.m.service;


import com.aixl.m.dao.aiTestHistoryMapper;
import com.aixl.m.dao.aiTestHtmlMapper;
import com.aixl.m.dao.aiTestImgMapper;
import com.aixl.m.dao.aiTestResultMapper;
import com.aixl.m.model.*;
import com.aixl.m.utils.RedisUtils;
import com.aixl.m.utils.ReturnObject;
import com.aixl.m.utils.ReturnUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    private RedisUtils<Object> redisUtils;

    //设置报告缓存时间为一天
    private long keepTime = 3600 * 24;


    /**
     * 新增用户报告
     *
     * @param report 报告模型
     * @return ReturnObject类
     */
    public ReturnObject<Object> addReport(aiReport report) {
        if (report.getTestResult() == null || report.getTestHtml() == null)
            return null;
        aiTestResult testResult = report.getTestResult();
        ArrayList<aiTestImg> testImgs = report.getTestImgs();
        aiTestHtml testHtml = report.getTestHtml();
        aiTestResult test = testResultMapper.selectByPrimaryKey(testResult.getAiUserId(), testResult.getAiScaleId());
        if (test == null)
            testResultMapper.insert(testResult);
        testHtmlMapper.insert(testHtml);
        for (Integer i = 0; i < testImgs.size(); i++) {
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
     * 新增一个历史报告记录
     *
     * @param testHistory 历史报告模型
     * @return 新增结果
     */
    public ReturnObject<Object> addHistory(aiTestHistory testHistory) {
        String id = testHistory.getAiDocId();
        if (id == null) {
            System.out.println(testHistory.toString());
            ReturnUtils.success("-1", testHistoryMapper.insert(testHistory));
        } else
            setReportToRedis(id, testHistory);
        return ReturnUtils.success(testHistoryMapper.insert(testHistory));
    }

    /**
     * 删除报告
     *
     * @return
     */
    public ReturnObject<Object> deleteReport() {
        return ReturnUtils.success();
    }

    /**
     * 新增报告
     *
     * @return
     */
    public ReturnObject<Object> setReport() {
        return ReturnUtils.success();
    }

    /**
     * 获取最新报告
     *
     * @return
     */
    public ReturnObject<Object> getReport(String userId) {

        return ReturnUtils.success();
    }

    /**
     * 分页获取历史报告
     */
    public ReturnObject<Object> getReports(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        Page<aiTestHistory> page = testHistoryMapper.selectAll();

        return ReturnUtils.success(page.getTotal(), page);
    }

    /**
     * 分页获取历史报告
     *
     * @param currentPage 页号
     * @param pageSize    页面大小
     * @param userId      用户id
     * @return
     */
    public ReturnObject<Object> getReports(Integer currentPage, Integer pageSize, String userId) {
        //可以存入到redi中，方便下次快速取出，也可以存入Map中（小数据）
        PageHelper.startPage(currentPage, pageSize);
        Page<aiTestHistory> page = testHistoryMapper.getHistory(userId);
        return ReturnUtils.success(page.getTotal(), page);

    }


    /**
     * 获取所有历史记录信息
     *
     * @return 返回结果信息
     */
    public ReturnObject<Object> getHistoryMsgAll() {
        return ReturnUtils.success(testHistoryMapper.selectMsgAll());
    }

    /**
     * 分页获取内容
     * @param currentPage
     * @param pageSize
     * @return
     */
    public ReturnObject<Object> getHistoryMsgAllByPage(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        Page<aiTestHistory> page = testHistoryMapper.selectMsgAllByPage();
        ArrayList<Date> arrayList = new ArrayList<>();//存放开始时间
        ArrayList arr = new ArrayList();
        ArrayList<aiTestHistory> arr2 = new ArrayList<>();
        System.out.println(page.size() + "========");
        for (int i = 0; i < page.size(); i++) {
           if(arrayList.indexOf(page.get(i).getStartTime()) <0) {
                arrayList.add(page.get(i).getStartTime());
                if (arr2.size() > 0) {
                    arr.add((ArrayList<aiTestHistory>) arr2.clone());
                    arr2.clear();
                }
            }
           arr2.add(page.get(i));
        }
        arr.add((ArrayList<aiTestHistory>)arr2.clone());
        arr2.clone();
        return ReturnUtils.success(page.getTotal(),arr);
    }


    /**
     * 根据用户id获取历史信息记录
     *
     * @param id
     * @return
     */
    public ReturnObject<Object> getHistoryMsgByUserId(String id) {
        return ReturnUtils.success(testHistoryMapper.selectByUserId(id));
    }

    /**
     * 根据时间，用户id，量表id精准获取报告内容
     *
     * @param testHistory
     * @return
     */
    public ReturnObject<Object> getReport(aiTestHistory testHistory) {
        return ReturnUtils.success(testHistoryMapper.getReport(testHistory));
    }


    /**
     * 根据id，时间，量表名批量获取报告内容
     *
     * @param testHistories
     * @return
     */
    public ReturnObject<Object> getReports(List<aiTestHistory> testHistories) {
        ArrayList<aiTestHistory> arr = new ArrayList<>();
        for (aiTestHistory testHistory : testHistories) {
            aiTestHistory a = testHistoryMapper.getReport(testHistory);
            if (a != null) {
                arr.add(a);
            }
        }
        return ReturnUtils.success(arr);
    }

    /**
     * 根据用户id获取当前用户的所有报告内容
     *
     * @param id 用户id
     * @return
     */
    public ReturnObject<Object> getReport2(String id) {
        return ReturnUtils.success(testHistoryMapper.getReportById(id));
    }


    /**
     * 根据用户名查询信息
     *
     * @param name 用户名
     * @return 结果信息
     */
    public ReturnObject<Object> getByUserName(String name) {
        return ReturnUtils.success(testHistoryMapper.selectByUserName(name));
    }

    /**
     * 根据日期范围查询信息
     *
     * @param time1 开始时间
     * @param time2 结束时间
     * @return 返回结果List
     */
    public ReturnObject<Object> getByTime(String time1, String time2) {
        return ReturnUtils.success(testHistoryMapper.selectByTime(time1, time2));
    }

    /**
     * 根据用户id和名字获取信息
     *
     * @param id   用户id
     * @param name 用户名
     * @return 结果集
     */
    public ReturnObject<Object> getByIdAndName(String id, String name) {
        return ReturnUtils.success(testHistoryMapper.selectByIdAndName(id, name));
    }

    /**
     * 根据用户id和和时间范围获取信息
     *
     * @param id    用户id
     * @param time1 开始时间
     * @param time2 结束时间
     * @return 结果集
     */
    public ReturnObject<Object> getByIdAndTime(String id, String time1, String time2) {
        return ReturnUtils.success(testHistoryMapper.selectByIdAndTime(id, time1, time2));
    }

    /**
     * 根据用户名字和时间范围获取信息
     *
     * @param name 用户名
     * @param t1   开始时间
     * @param t2   结束时间
     * @return 结果集
     */
    public ReturnObject<Object> getByNameAndTime(String name, String t1, String t2) {
        return ReturnUtils.success(testHistoryMapper.selectByNameAndTiem(name, t1, t1));
    }

    /**
     * 根据用户id，用户名，时间范围获取信息
     *
     * @param id
     * @param name
     * @param t1
     * @param t2
     * @return
     */
    public ReturnObject<Object> getINT(String id, String name, String t1, String t2) {
        return ReturnUtils.success(testHistoryMapper.selectINT(id, name, t1, t2));
    }


    /**
     * 将前端需要缓存的部分存入服务器
     *
     * @param key   医生id号
     * @param value 存入的value值，json格式对象，多个报告之间用英文逗号隔开
     * @return boolean
     */
    public boolean setReportToRedis(String key, aiTestHistory value) {
        //存入一个新的之前需要将之前的数据取出来，防止丢失
        ArrayList<aiTestHistory> arrayList = null;
        try {
            arrayList = (ArrayList<aiTestHistory>) redisUtils.getCache("reports="+key);
        } catch (Exception e) {
            arrayList = null;
        }
        if (arrayList == null)
            arrayList = new ArrayList<>();
        arrayList.add(value);
        return redisUtils.setCache("reports=" + key, arrayList, keepTime);
    }


    /**
     * 进行在线打印报告时使用
     *
     * @param key 医生id号
     * @return json格式结果
     */
    public ReturnObject<Object> getReportFromRedis(String key) {
        ArrayList arrayList = (ArrayList) redisUtils.getCache("reports=" + key);
        return ReturnUtils.success(arrayList);
    }

    /**
     * 删除对应医生的缓存报告
     *
     * @param key
     * @return
     */
    public Boolean clearReportFromRedis(String key) {
        boolean b = redisUtils.clear("reports=" + key);
        return b;
    }

    /**
     * 按照发布时间，按组获取数据
     *
     * @return
     */
    @Deprecated
    public ReturnObject<Object> selectAllGroupByStartTime() {
        return ReturnUtils.success(testHistoryMapper.selectAllGroupByStartTime());
    }

    /**
     * 分页，按发布时间分组
     * @param currentPage
     * @param pageSize
     * @return  返回所有信息记录
     */
    public ReturnObject<Object> selectGroupByStartTime(Integer currentPage,Integer pageSize){
        PageHelper.startPage(currentPage, pageSize);
        Page<aiTestHistory> page = testHistoryMapper.selectGroupByStartTime();
        return ReturnUtils.success(page.getTotal(),page);
    }

    /**
     * 分页，按发布是时间分组，根据用户名获取信息
     * @param currentPage
     * @param pageSize
     * @param name  用户名
     * @return
     */
    public ReturnObject<Object> selectGroupByName(Integer currentPage,Integer pageSize,String name){
        PageHelper.startPage(currentPage, pageSize);
        Page<aiTestHistory> page = testHistoryMapper.selectGroupByName(name);
        return ReturnUtils.success(page.getTotal(),page);
    }

    /**
     * 分页，按发布时间分组，根据用户id获取信息
     * @param currentPage
     * @param pageSize
     * @param id
     * @return
     */
    public ReturnObject<Object> selectGroupById(Integer currentPage,Integer pageSize,String id){
        PageHelper.startPage(currentPage, pageSize);
        Page<aiTestHistory> page = testHistoryMapper.selectGroupById(id);
        return ReturnUtils.success(page.getTotal(),page);
    }

    /**
     * 分页，按发布时间分组，根据时间段获取信息
     * @param currentPage
     * @param pageSize
     * @param t1    开始时间
     * @param t2    结束时间
     * @return
     */
    public ReturnObject<Object> selectGroupByTime(Integer currentPage,Integer pageSize,String t1,String t2){
        PageHelper.startPage(currentPage, pageSize);
        Page<aiTestHistory> page = testHistoryMapper.selectGroupTime(t1,t2);
        return ReturnUtils.success(page.getTotal(),page);
    }

    /**
     * 分页，按发布时间分组，根据名字，id获取信息
     * @param currentPage
     * @param pageSize
     * @param name  用户名
     * @param id    用户id
     * @return
     */
    public ReturnObject<Object> selectGroupByNameId(Integer currentPage,Integer pageSize,String name,String id){
        PageHelper.startPage(currentPage, pageSize);
        Page<aiTestHistory> page = testHistoryMapper.selectGroupNameId(name,id);
        return ReturnUtils.success(page.getTotal(),page);
    }

    /**
     * 分页，按发布时间分组，根据用户名，时间段获取信息
     * @param currentPage
     * @param pageSize
     * @param name  用户名
     * @param t1    开始时间
     * @param t2    结束时间
     * @return
     */
    public ReturnObject<Object> selectGroupByNameTime(Integer currentPage,Integer pageSize,String name,String t1,String t2){
        PageHelper.startPage(currentPage, pageSize);
        Page<aiTestHistory> page = testHistoryMapper.selectGroupNameTime(name,t1,t2);
        return ReturnUtils.success(page.getTotal(),page);
    }

    /**
     * 分页，按发布时间分组，根据用户id，时间段获取信息
     * @param currentPage
     * @param pageSize
     * @param id    用户id
     * @param t1    开始时间
     * @param t2    结束时间
     * @return
     */
    public ReturnObject<Object> selectGroupByIdTime(Integer currentPage,Integer pageSize,String id,String t1,String t2){
        PageHelper.startPage(currentPage, pageSize);
        Page<aiTestHistory> page = testHistoryMapper.selectGroupIdTime(id,t1,t2);
        return ReturnUtils.success(page.getTotal(),page);
    }

    /**
     * 分页，按发布时间分组，根据用户id，时间段，用户名获取信息
     * @param currentPage
     * @param pageSize
     * @param name  用户名
     * @param id    用户id
     * @param t1    开始时间
     * @param t2    结束时间
     * @return
     */
    public ReturnObject<Object> selectGroupByNameIdTime(Integer currentPage,Integer pageSize,String name,String id,
                                                        String t1,String t2){
        PageHelper.startPage(currentPage, pageSize);
        Page<aiTestHistory> page = testHistoryMapper.selectGroupINT(id,name,t1,t2);
        return ReturnUtils.success(page.getTotal(),page);
    }
}
