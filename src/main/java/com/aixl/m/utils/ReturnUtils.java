package com.aixl.m.utils;



public class ReturnUtils {
    private final static int SUCCESS_CODE = 1;
    private final static String SUCCESS_MSG = "SUCCESS";
    private final static int FAIL_CODE = 0;
    private final static String FAIL_MSG = "FAIL";
    private final static int ERROR_CODE = -1;
    private final static String ERROR_MSG = "ERROR";


    /**
     * 数据获取成功返回的消息列表
     * @return
     */
    public static ReturnObject<Object> success() {
        return success(null);
    }

    public static ReturnObject<Object> success(Object o) {
        return success(null, o);
    }

    public static ReturnObject<Object> success(String msg,Object o,String status){
        ReturnObject<Object> object = new ReturnObject<>();
        object.setMsg(msg == null ? SUCCESS_MSG : msg);
        object.setObject(o);
        object.setStatus(status);
        return object;
    }
    public static ReturnObject<Object> success(String msg,Object o,int status){
        ReturnObject<Object> object = new ReturnObject<>();
        object.setMsg(msg == null ? SUCCESS_MSG : msg);
        object.setObject(o);
        object.setStatus_n(status);
        return object;
    }
    public static ReturnObject<Object> success(String msg, Object o) {
        return success(msg,o,null);
    }


    /**
     * 数据库读取出错返回的消息列表
     * @return
     */
    public static ReturnObject<Object> error(){
        return error(null);
    }

    public static ReturnObject<Object> error(Object o){
        return error(null,o);
    }

    public static ReturnObject<Object> error( String msg,Object o){
        ReturnObject returnObject = new ReturnObject();
        returnObject.setObject(o);
        returnObject.setMsg(msg == null ? ERROR_MSG :msg );
        return returnObject;
    }



    public static ReturnObject<Object> serviceError(){
        ReturnObject returnObject = new ReturnObject();
        returnObject.setMsg("服务器出错，请联系管理员！");
        returnObject.setObject(null);
        return returnObject;
    }




}
