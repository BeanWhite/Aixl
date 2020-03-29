package com.aixl.m.dao;

import com.aixl.m.model.aiTestHistory;
import java.util.List;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface aiTestHistoryMapper {
    /**
     * 删除报告
     * @param aiUserId  用户id
     * @param aiScaleId 量表id
     * @return  0 or 1
     */
    int deleteByPrimaryKey(@Param("aiUserId") String aiUserId, @Param("aiScaleId") Integer aiScaleId);

    /**
     * 新增报告
     * @param record    aiTestHistory模型
     * @return
     */
    int insert(aiTestHistory record);



    /**
     * 查询报告
     * @param aiUserId  用户id
     * @param aiScaleId 量表id
     * @return  aiTestHistory 模型
     */
    aiTestHistory selectByPrimaryKey(@Param("aiUserId") String aiUserId, @Param("aiScaleId") Integer aiScaleId);

    /**
     * 分页获取所有报告信息
     * @return
     */
    Page<aiTestHistory> selectAll();

    /**
     * 更新报告
     * @param record
     * @return
     */
    int updateByPrimaryKey(aiTestHistory record);

    /**
     * 分页获取报告信息
     * @param aiUserId  用户id
     * @return
     */
    Page<aiTestHistory> getHistory(String aiUserId);

    /**
     * 按照用户id，量表名字，时间精确获取报告
     * @param testHistory   报告模型
     * @return 报告模型，只有报告属性有值
     */
    aiTestHistory getReport(aiTestHistory testHistory);

    /**
     * 根据用户id获取当前用户的所有记录
     * @param id    用户id
     * @return  报告模型数组
     */
    List<aiTestHistory> getReportById(String id);

    /**
     * 获取所有记录信息
     * @return 记录数组
     */
    List<aiTestHistory> selectMsgAll();

    /**
     * 根据用户id获取所有记录信息
     * @param id    用户id
     * @return  记录数组
     */
    List<aiTestHistory> selectByUserId(String id);

    /**
     * 根据用户名字查询记录信息
     * @param userName  用户名
     * @return  结果数组
     */
    List<aiTestHistory> selectByUserName(String userName);


    /**
     *  按时间范围查询信息
     * @param time1 开始时间
     * @param time2 结束时间
     * @return  结果数组
     */
    List<aiTestHistory> selectByTime(@Param("time1") String time1,@Param("time2") String time2);

    /**
     * 按用户名和用户id查询信息
     * @param id    用户id
     * @param name 用户名
     * @return  结果数组
     */
    List<aiTestHistory> selectByIdAndName(@Param("id") String id,@Param("name") String name);

    /**
     *  按用户id和时间范围查询信息
     * @param id    用户id
     * @param time1 开始时间
     * @param time2 结束时间
     * @return  结果数组
     */
    List<aiTestHistory> selectByIdAndTime(@Param("id") String id,
                                          @Param("time1") String time1,
                                          @Param("time2") String time2);

    /**
     *  按用户名和时间范围查询信息
     * @param name  用户名
     * @param time1 开始时间
     * @param time2 结束时间
     * @return  结果数组
     */
    List<aiTestHistory> selectByNameAndTiem(@Param("name") String name,
                                            @Param("time1") String time1,
                                            @Param("time2") String time2);

    /**
     * 按用户名，用户ID，时间范围进行查询信息
     * @param id    用户id
     * @param name  用户名
     * @param time1 开始时间
     * @param time2 结束时间
     * @return  结果数组
     */
    List<aiTestHistory> selectINT(@Param("id") String id,
                                  @Param("name") String name,
                                  @Param("time1") String time1,
                                  @Param("time2") String time2);


    /**
     * 按照发布时间按组获取
     * @return
     */
    @Deprecated
    List<aiTestHistory> selectAllGroupByStartTime();


    /**
     * 分页获取所有信息
     * @return
     */
    Page<aiTestHistory> selectMsgAllByPage();


    /**
     * 按开始时间分组分页获取所有信息
     * @return
     */
    Page<aiTestHistory> selectGroupByStartTime();

    /**
     * 分组获取，分页获取
     * @param name  用户名
     * @return
     */
    Page<aiTestHistory> selectGroupByName(String name);

    /**
     * 分组获取，分页获取
     * @param id    用户id
     * @return
     */
    Page<aiTestHistory> selectGroupById(String id);

    /**
     * 分组获取，分页获取
     * @param t1    开始时间
     * @param t2    结束时间
     * @return
     */
    Page<aiTestHistory> selectGroupTime(@Param("time1") String t1,
                                        @Param("time2") String t2);

    /**
     * 分组获取，分页获取
     * @param name  用户名
     * @param id    用户id
     * @return
     */
    Page<aiTestHistory> selectGroupNameId(@Param("name") String name,
                                          @Param("id") String id);

    /**
     * 分组获取，分页获取
     * @param name  用户名
     * @param time1 开始时间
     * @param time2 结束时间
     * @return
     */
    Page<aiTestHistory> selectGroupNameTime(@Param("name")String name,
                                            @Param("time1")String time1,
                                            @Param("time2")String time2);
    /**
     * 分组获取，分页获取
     * @param id    用户id
     * @param time1 开始时间
     * @param time2 结束时间
     * @return
     */
    Page<aiTestHistory> selectGroupIdTime(@Param("id")String id,
                                          @Param("time1")String time1,
                                          @Param("time2")String time2);

    /**
     * 分组获取，分页获取
     * @param id    用户id
     * @param name  用户名
     * @param time1 开始时间
     * @param time2 结束时间
     * @return
     */
    Page<aiTestHistory> selectGroupINT(@Param("id")String id,
                                       @Param("name")String name,
                                       @Param("time1")String time1,
                                       @Param("time2")String time2);
}