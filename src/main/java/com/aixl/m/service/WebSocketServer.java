package com.aixl.m.service;

import com.aixl.m.model.httpHeaderDataPackage;
import com.aixl.m.utils.RedisUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;


/**
 * 消息传递服务
 */
@ServerEndpoint("/websocket/{sid}")
@Component
public class WebSocketServer {
    //用来记录当前在线连接数,定义为线程安全类型volatile关键字
    private static int onlineCount = 0;

    //concurrent包的线程安全set,用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketServers = new CopyOnWriteArraySet<>();

    //存放每个客户端对应的websocket对象<websocket对象id，websocket对象>
    private static ConcurrentMap<String, WebSocketServer> webSocketServersMap = new ConcurrentHashMap<>();

    //待发送的的消息列表,用户登录成功后即发送推送信息<目标对象id，消息列表>
    private static ConcurrentMap<String, ArrayList<Object>> beSentMSG = new ConcurrentHashMap<>();

    @Autowired
    private  RedisUtils<Object> redisUtils;

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收sid,用户id
    private String sid = "";




    /**
     * 连接建立成功调用
     */
    @OnOpen
    //sid为必须参数，用于确定是哪一个用户
    //@PathParam 为根据路径值取值，@QueryParam为根据键值对取值
    public void onOpen(Session session, @PathParam("sid") String sid) throws Exception {
        this.session = session;
        this.sid = sid;
        //检查该账号是否登录，若为已登录则不允许登录
//        if(webSocketServersMap.get(sid)!=null){
//            //session.close();
//            return;
//        }
        //连接成功后检查是否有为接受的消息
        System.out.println("===="+this.sid+"\t"+"用户登录sid");
        webSocketServers.add(this);
        webSocketServersMap.put(sid, this);
        checkMsg(sid,session);

        //设置client的连接时间，超出这个时间将会关闭，单位ms
        session.setMaxIdleTimeout(3600*24*1000);//设置最大连接时长为24小时
    }
    /**
     * 连接关闭调用
     */
    @OnClose
    public void onClose() {
        System.out.println(this.sid+"删除的sid");
        webSocketServers.remove(this);//从set中删除
        webSocketServersMap.remove(this.sid);
        subOnlineCount();//在线人数减一
    }
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发过来的消息
     * @param session
     */
    @OnMessage
    public void onMessage(byte[] message, Session session) {
        System.out.println("接受byte字节");
        //群发消息
        for (WebSocketServer item : webSocketServers) {
            try {
                //item.sendMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        //有消息到来4种处理情况1、转发；2、存入消息队列；3、取消本次发送; 4、处理计算本次数据，然后转发给目标用户
        pointUser(message, session);
        //有目标id才能发送数据
        //发送短数据，不需要切分
//                    System.out.println(dataPackage.getData().toString());
//                    // ByteBuffer buffer = encodeKey(dataPackage.getData().toString());
//                    byte[] o = dataPackage.getData().toString().getBytes();
//
////                System.out.println(buffer);
////                byte[] o = decodeValue(buffer);
//                    for (int i = 0; i < o.length; i++)
//                        System.out.println(o[i]);
//                    System.out.println(new String(o));
//                    ByteBuffer buffer1 = encodeValue(o);
////                System.out.println(buffer1);
//                    String string = decodeKey(buffer1);
//                    //  String str = decodeKey(buffer);
//                    //  System.out.println(str);
//                    System.out.println(string);

    }


    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }


    public void dateOption(httpHeaderDataPackage obj){
        System.out.println(obj.getStatus());
        switch (obj.getStatus()){
            case 201:
                String[] a = (String[]) obj.getData();
                for(int i=0;i<a.length;i++)
                    System.out.println("**"+a[i]+"**");
                break;//处理连线测验结果
            default:break;
        }
    }



    /**
     * 服务器接收客户端发过来的消息，如果status > 100则需要服务器处理
     * @param message
     * @param session
     * @throws Exception
     */
    public void pointUser(String message, Session session) throws Exception {
        //System.out.println(message.toString());
        httpHeaderDataPackage dataPackage = JSON.parseObject(message, httpHeaderDataPackage.class);
        if(dataPackage.getStatus()>100){
         dateOption(dataPackage);
            return;//处理完成后直接返回
        }

        if (dataPackage.is_isMain()) {
            //新版本
            if (dataPackage.getTargets() != null || dataPackage.getTarget() != null) {
                if (dataPackage.isLongData()) {
                    //发送长数据，可能需要切分
                } else {
                    //检查当前用户是否在线
                    WebSocketServer webSocketServer = webSocketServersMap.get(dataPackage.getTarget());
                    if (webSocketServer != null)
                        webSocketServer.sendMessage(JSON.toJSONString(dataPackage));
                    else if (dataPackage.isCache()) {//判断是否需要存入消息队列，默认不需要
                        //不在线就存入消息列表缓存堆里面
                        if (beSentMSG.get(dataPackage.getTarget()) != null) {
                            //如果该用户已有没推送的消息
                            beSentMSG.get(dataPackage.getTarget()).add(dataPackage);
                        } else {
                            ArrayList<Object> list = new ArrayList<>();
                            list.add(dataPackage);
                            beSentMSG.put(dataPackage.getTarget(), list);
                        }
                    }
                }
            }
        }
    }


    /**
     * 检查待发送消息列表
     */
    public void checkMsg(String sid,Session session) throws Exception {
        ArrayList<Object> list = beSentMSG.get(sid);
        //如果有待发送的消息
       WebSocketServer webSocketServer = webSocketServersMap.get(sid);

        if (list != null && list.size() > 0&&webSocketServer!=null) {
            for (int i = 0; i < list.size(); i++) {
                webSocketServersMap.get(sid).sendMessage(JSON.toJSONString(list.get(i)));
            }
            //发送完移除
            beSentMSG.remove(sid);
            System.out.println(beSentMSG.get(sid)+"\t"+"检查到该用户由消息推送未完成");
        } else return;
    }

    /**
     * 实现服务器主动推送
     */

    public void sendMessage(ByteBuffer message) throws Exception {
        this.session.getBasicRemote().sendBinary(message);
    }

    public void sendMessage(String s) throws Exception {
        try {
            //this.session.getAsyncRemote().sendText(s);//异步发送消息
            this.session.getBasicRemote().sendText(s);//同步发送消息
            System.out.println("服务器推送消息");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(Object s) throws Exception {
        try {

            this.session.getBasicRemote().sendObject(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(ByteBuffer message, @PathParam("sid") String sid) throws Exception {
        for (WebSocketServer item : webSocketServers) {
            try {
                if (sid == null) {
                    item.sendMessage(message);
                } else if (item.sid.equals(sid)) {
                    item.sendMessage(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendInfo(String message, @PathParam("sid") String sid) throws Exception {
        for (WebSocketServer item : webSocketServers) {

            try {
                if (sid == null) {
                    item.sendMessage(message);
                } else if (item.sid.equals(sid)) {
                    item.sendMessage(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    //ByteBuffer、byte[]、string转化
    public String decodeKey(ByteBuffer bytes) {
        Charset charset = Charset.forName("utf-8");
        return charset.decode(bytes).toString();
    }

    public byte[] decodeValue(ByteBuffer bytes) {
        int len = bytes.limit() - bytes.position();
        byte[] bytes1 = new byte[len];
        bytes.get(bytes1);
        return bytes1;
    }


    public ByteBuffer encodeKey(String key) {
        try {
            return ByteBuffer.wrap(key.getBytes("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ByteBuffer.wrap(key.getBytes());
    }


    public ByteBuffer encodeValue(byte[] value) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(value.length);
        byteBuffer.clear();
        byteBuffer.get(value, 0, value.length);
        return byteBuffer;
    }

    //定义位线程安全类型
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

}
