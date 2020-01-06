package com.aixl.m.service;

import com.aixl.m.model.httpHeaderDataPackage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
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

    private static ConcurrentMap<String,WebSocketServer> webSocketServersMap = new ConcurrentHashMap<>();

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
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.session = session;
        this.sid = sid;
        webSocketServers.add(this);
        webSocketServersMap.put(sid,this);
        addOnlineCount();
        try {
            sendMessage("连接成功！！！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接关闭调用
     */
    @OnClose
    public void onClose() {
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
        //System.out.println("接受byte字节");
        //群发消息
        for (WebSocketServer item : webSocketServers) {
            try {
                item.sendMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
       // System.out.println(message.toString());
        httpHeaderDataPackage dataPackage = JSON.parseObject(message, httpHeaderDataPackage.class);
        //httpHeaderDataPackage dataPackage = new httpHeaderDataPackage();
        if (dataPackage.is_isMain()) {
            //新版本
            if (dataPackage.getTargets() != null || dataPackage.getTarget() != null) {
                if (dataPackage.isLongData()) {
                    //发送长数据，可能需要切分
                } else {
                        WebSocketServer webSocketServer = webSocketServersMap.get(dataPackage.getTarget());
                        if(webSocketServer!=null)
                            webSocketServer.sendMessage(JSON.toJSONString(dataPackage));

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
            }
        }
        //有目标id才能发送数据


    }


    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
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
