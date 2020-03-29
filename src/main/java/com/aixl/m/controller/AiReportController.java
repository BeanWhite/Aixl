package com.aixl.m.controller;


import com.aixl.m.model.aiReport;
import com.aixl.m.model.aiTestHistory;
import com.aixl.m.service.aiReportService;
import com.aixl.m.utils.ReturnObject;
import com.aixl.m.utils.ReturnUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

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
     *
     * @return 结果类
     */
    @RequestMapping(value = "/html/{obj}", method = RequestMethod.POST)
    public ReturnObject<Object> addReport(@PathVariable(value = "obj") String obj) {
        return reportService.addReport(JSON.parseObject(obj, aiReport.class));
    }

    /**
     * 获取报告并打印
     *
     * @param userId   用户id
     * @param scaleId  量表id
     * @param testTime 测试时间
     * @return 结果类
     */
    @RequestMapping(value = "/report/{userId}/{scaleId}/{testTime}")
    public ReturnObject<Object> toPrintReport(@PathVariable(value = "userId") String userId,
                                              @PathVariable(value = "scaleId") Integer scaleId,
                                              @PathVariable(value = "testTime") String testTime) {


        return ReturnUtils.success();
    }

    /**
     * 删除报告
     *
     * @return 结果类
     */
    //@RequestMapping(value = "/html",method = RequestMethod.DELETE)
    public ReturnObject<Object> deleteReport() {
        return ReturnUtils.success();
    }

    /**
     * 新增报告
     *
     * @return 结果类
     */
    public ReturnObject<Object> addReport() {
        return ReturnUtils.success();
    }

    /**
     * 分页获取报告
     *
     * @param currentPage 当前页码
     * @param pageSize    页面大小
     * @return 结果集
     */
    @RequestMapping(value = "/html/{currentPage}/{pageSize}", method = RequestMethod.GET)
    public ReturnObject<Object> getReport(@PathVariable(value = "currentPage") Integer currentPage,
                                          @PathVariable(value = "pageSize") Integer pageSize) {
        return reportService.getReports(currentPage, pageSize);
    }


    /**
     * 根据用户id获取所有报告（历史记录）
     *
     * @param currentPage 当前页码
     * @param pageSize    页面大小
     * @param userId      用户id
     * @return 结果集
     */
    @RequestMapping(value = "/html/{currentPage}/{pageSize}/{userId}", method = RequestMethod.GET)
    public ReturnObject<Object> getReportsById(@PathVariable(value = "currentPage") Integer currentPage,
                                               @PathVariable(value = "pageSize") Integer pageSize,
                                               @PathVariable(value = "userId") String userId) {
        return reportService.getReports(currentPage, pageSize, userId);
    }

/**
 * new
 */
    /**
     * 获取所有历史记录信息
     * @return 结果信息
     */
    @RequestMapping(value = "/msg", method = RequestMethod.GET)
    public ReturnObject<Object> getHistoryMsgAll() {

        return reportService.getHistoryMsgAll();
    }

    @RequestMapping(value = "/msg/{currentPage}/{pageSize}",method = RequestMethod.GET)
    public ReturnObject<Object> getHistoryMsgAllByPage(@PathVariable(value = "currentPage") Integer currentPage,
                                                       @PathVariable(value = "pageSize") Integer pageSize){

        return reportService.getHistoryMsgAllByPage(currentPage,pageSize);
    }

    /**
     * 根据用户id获取历史记录信息
     *
     * @param id 用户id
     * @return 结果信息
     */
    @RequestMapping(value = "/msg/{id}", method = RequestMethod.GET)
    public ReturnObject<Object> getHistoryMsgByUserId(@PathVariable(value = "id") String id) {
        return reportService.getHistoryMsgByUserId(id);
    }

    /**
     * 根据时间，用户id，量表id精准获取报告内容
     *
     * @param time      时间
     * @param userId    用户id
     * @param scaleName 量表名
     * @return 返回结果
     */
    @RequestMapping(value = "/content/{time}/{userId}/{scaleName}", method = RequestMethod.GET)
    public ReturnObject<Object> getReport(@PathVariable(value = "time") String time,
                                          @PathVariable(value = "userId") String userId,
                                          @PathVariable(value = "scaleName") String scaleName) {
        aiTestHistory testHistory = new aiTestHistory();
        testHistory.setTime(time);
        testHistory.setAiUserId(userId);
        testHistory.setAiScaleName(scaleName);
        return reportService.getReport(testHistory);
    }

    /**
     * 根据时间，用户id，量表id批量获取报告内容
     *
     * @param list 带获取的数组
     * @return 结果集
     */
    @RequestMapping(value = "/contents/{list}", method = RequestMethod.GET)
    public ReturnObject<Object> getReports(@PathVariable(value = "list") String list) {
        List<aiTestHistory> arrayList = JSON.parseArray(list, aiTestHistory.class);
        return reportService.getReports(arrayList);
    }


    /**
     * 根据用户id获取当前用户的所有报告内容
     *
     * @param id 用户id
     * @return
     */
    @RequestMapping(value = "/content/{id}", method = RequestMethod.GET)
    public ReturnObject<Object> getReport2(@PathVariable(value = "id") String id) {
        return reportService.getReport2(id);
    }

    /**
     * 新增历史报告
     *
     * @param time     时间
     * @param userId   用户id
     * @param userName 用户名
     * @param testName 测试名称
     * @param content  报告内容
     * @return
     */
    @RequestMapping(value = "/op/{time}/{userId}/{userName}/{testName}/{content}/{scaleId}/{msg}/{docId}", method = RequestMethod.POST)
    public ReturnObject<Object> addReport(
            @PathVariable(value = "time") String time,
            @PathVariable(value = "userId") String userId,
            @PathVariable(value = "userName") String userName,
            @PathVariable(value = "testName") String testName,
            @PathVariable(value = "content") String content,
            @PathVariable(value = "scaleId") Integer scaleId,
            @PathVariable(value = "msg")String msg,
            @PathVariable(value = "docId")String docId) {
        aiTestHistory testHistory = new aiTestHistory();
        testHistory.setTime(time);
        testHistory.setAiUserId(userId);
        testHistory.setAiUserName(userName);
        testHistory.setAiScaleName(testName);
        testHistory.setReportContent(content);
        testHistory.setAiScaleId(scaleId);
        testHistory.setMsg(msg);
        testHistory.setAiDocId(docId);
        return reportService.addHistory(testHistory);
    }

    /**
     * 新增的报告内容如果有图片，需要使用该方式
     * @param value JSON格式数据，
     *
     * @return
     */
    @RequestMapping(value = "/op/Max",method = RequestMethod.POST)
    public ReturnObject<Object> addReport_Max(String value){
        aiTestHistory testHistory = JSON.parseObject(value,aiTestHistory.class);
        System.out.println(testHistory.toString());
        return reportService.addHistory(testHistory);
    }



    /**
     * 查询用户信息
     * @param map
     * @return
     */
    @RequestMapping(value = "/msgs/{map}", method = RequestMethod.GET)
    public ReturnObject<Object> getMSG(@PathVariable(value = "map") String map) {
        Map<String, String> m = JSON.parseObject(map, Map.class);
        String id = m.get("id");
        String name = m.get("name");
        String t1 = m.get("time1");
        String t2 = m.get("time2");
        if (id == null || name == null || t1 == null || t2 == null)
            return ReturnUtils.success("请保证每一个参数都有值", -1);
        //每一个选项都不为空
        if (!id.equals("") && !name.equals("") && !t1.equals("") && !t2.equals("")) {
            return reportService.getINT(id, name, t1, t2);
        } else if (!id.equals("") && !name.equals("")) {
            //id和名字不为空
            return reportService.getByIdAndName(id, name);
        } else if (!id.equals("") && !t1.equals("") && !t2.equals("")) {
            //id和时间不为空
            return reportService.getByIdAndTime(id, t1, t2);
        } else if (!name.equals("") && !t1.equals("") && !t2.equals("")) {
            //名字和时间不为空
            return reportService.getByNameAndTime(name, t1, t2);
        } else if (!id.equals("")) {
            return reportService.getHistoryMsgByUserId(id);
        } else if (!name.equals("")) {
            return reportService.getByUserName(name);
        } else if (!t1.equals("") && !t2.equals("")) {
            return reportService.getByTime(t1, t2);
        } else if (id.replaceAll(" ", "").equals("")
                && name.replaceAll(" ", "").equals("")
                && t1.replaceAll(" ", "").equals("")
                && t2.replaceAll(" ", "").equals("")) {
            return reportService.getHistoryMsgAll();
        }
        return ReturnUtils.success("未知操作", -1);
    }



    /**
     * 在线打印报告时调用
     * @param key   目前设置为医生id号
     * @return  ArrayListj
     */
    @RequestMapping(value = "/redis/{key}",method = RequestMethod.GET)
    public ReturnObject<Object>  getReportFromRedius(@PathVariable(value = "key") String key){
        return reportService.getReportFromRedis(key);
    }

    /**
     * 医生重新开始新测评是调用，删除之前的报告缓存
     * @param key
     * @return
     */
    @RequestMapping(value = "/redis0/{key}",method = RequestMethod.DELETE)
    public ReturnObject<Object> clearReportFromRedis(@PathVariable(value = "key") String key){
        return ReturnUtils.success(reportService.clearReportFromRedis(key));
    }


    @RequestMapping(value = "/report/group",method = RequestMethod.GET)
    @Deprecated
    public ReturnObject<Object> selectAllGroupByStartTime(){
        return reportService.selectAllGroupByStartTime();
    }

    @RequestMapping(value = "/report/group/start/{a}/{b}",method = RequestMethod.GET)
    public ReturnObject<Object> selectGroupByStartTime(@PathVariable(value = "a") Integer currentPage,@PathVariable(value = "b") Integer pageSize){
        return reportService.selectGroupByStartTime(currentPage,pageSize);
    }

    @RequestMapping(value = "/report/groups/{map}",method = RequestMethod.GET)
    public ReturnObject<Object> groupByName(@PathVariable(value = "map") String map){
        Map<String, String> m = JSON.parseObject(map, Map.class);
        String id = m.get("id");
        String name = m.get("name");
        String t1 = m.get("time1");
        String t2 = m.get("time2");
        Object p = m.get("pageSize");
        Object c =m.get("currentPage");
        Integer pageSize = Integer.valueOf(String.valueOf(p));
        Integer currentPage = Integer.valueOf(String.valueOf(c));
        if (id == null || name == null || t1 == null || t2 == null)
            return ReturnUtils.success("请保证每一个参数都有值", -1);
        //每一个选项都不为空
        if (!id.equals("") && !name.equals("") && !t1.equals("") && !t2.equals("")) {
            return reportService.selectGroupByNameIdTime(currentPage,pageSize,name,id,t1,t2);
        } else if (!id.equals("") && !name.equals("")) {
            //id和名字不为空
            return reportService.selectGroupByNameId(currentPage,pageSize,name,id);
        } else if (!id.equals("") && !t1.equals("") && !t2.equals("")) {
            //id和时间不为空
            return reportService.selectGroupByIdTime(currentPage,pageSize,id,t1,t2);
        } else if (!name.equals("") && !t1.equals("") && !t2.equals("")) {
            //名字和时间不为空
            return reportService.selectGroupByNameTime(currentPage,pageSize,name,t1,t2);
        } else if (!id.equals("")) {
            return reportService.selectGroupById(currentPage,pageSize,id);
        } else if (!name.equals("")) {
            return reportService.selectGroupByName(currentPage,pageSize,name);
        } else if (!t1.equals("") && !t2.equals("")) {
            return reportService.selectGroupByTime(currentPage,pageSize,t1,t2);
        } else if (id.replaceAll(" ", "").equals("")
                && name.replaceAll(" ", "").equals("")
                && t1.replaceAll(" ", "").equals("")
                && t2.replaceAll(" ", "").equals("")) {
            return reportService.selectGroupByStartTime(currentPage,pageSize);
        }
        return ReturnUtils.success("未知操作", -1);
    }
}

