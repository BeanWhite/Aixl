package com.aixl.m.service;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
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
    public void onOpen(Session session,@PathParam("sid") String sid) {
        this.session = session;
        webSocketServers.add(this);
        addOnlineCount();
        this.sid = sid;
        try {
            //sendMessage("连接成功");
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
                ByteBuffer bf = ByteBuffer.wrap(message);
                item.sendMessage(bf);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        //向指定用户发送消息
        //Set<String> targerUsers = new HashSet<>(Arrays.asList(message.split("[|]")));

        String a[] = message.split("[|]");
        //目标用户id
        Set<String> targetUsers = new HashSet<>(Arrays.asList(Arrays.copyOf(a,a.length-1)));
        //消息内容
        String msg = a[a.length-1];
        for (WebSocketServer item : webSocketServers) {
            //判断是否为目标用户
            if(targetUsers.contains(item.sid)){
                try {
                    item.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
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

            this.session.getBasicRemote().sendObject("dafdfa");
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
