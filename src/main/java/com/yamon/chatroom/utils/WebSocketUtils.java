package com.yamon.chatroom.utils;

import com.yamon.chatroom.domain.Room;
import com.yamon.chatroom.mapper.RoomMapper;
import com.yamon.chatroom.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author yamon
 * @Date 2023/3/16 15:44
 * @Description
 * @Version 1.0
 */
@Component
@Slf4j
@ServerEndpoint("/websocket/{roomId}")
public class WebSocketUtils {


    private Logger logger = LoggerFactory.getLogger(WebSocketUtils.class);

    /**
     * 用户ID
     */
    private String roomId;

    private Session session;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    //虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean，所以可以用一个静态set保存起来。
    //  注：底下WebSocket是当前类名
    // 用来存在线连接用户信息
    private static CopyOnWriteArraySet<Session> SESSIONS = new CopyOnWriteArraySet<>();
    private static ConcurrentHashMap<String, Session> sessionPool = new ConcurrentHashMap<String, Session>();

    private MessageService messageService;

    /**
     * 需要注入的Service声明为静态，让其属于类
     */
    private static RoomMapper roomMapper;

    @Autowired
    public void setMchDeviceInfoService(RoomMapper roomMapper) {
        WebSocketUtils.roomMapper = roomMapper;
    }


    /**
     * 链接成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "roomId") String roomId) {
        try {
            this.roomId = roomId;
            this.session = session;
            SESSIONS.add(session);
            sessionPool.put(roomId, session);
            logger.info("【websocket消息】有新的连接，总数为:" + SESSIONS.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 链接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        try {
            SESSIONS.remove(session);
            sessionPool.remove(this.roomId);
            logger.info("【websocket消息】连接断开，总数为:" + SESSIONS.size());
        } catch (Exception e) {
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     * @param
     */
    @OnMessage
    public void onMessage(String message) {
        logger.info("【websocket消息】收到客户端消息:" + message);
        // 将消息保存到数据库中
        this.sendAllMessage(message);
    }

    /**
     * 保存一条消息到数据库中
     * @param message message
     */
    public void saveMsg(String message){
        Room insertMsg = new Room();
        insertMsg.setContent(message);
        insertMsg.setRoomId(this.roomId);
        insertMsg.setIsDisplay(Boolean.TRUE);
        insertMsg.setIsRead(Boolean.FALSE);
        insertMsg.setIsExpire(Boolean.FALSE);
        roomMapper.insert(insertMsg);
    }

    /**
     * 发送错误时的处理
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {

        logger.error("用户错误,原因:" + error.getMessage());
        error.printStackTrace();
    }


    // 此为广播消息
    public void sendAllMessage(String message) {
        logger.info("【websocket消息】广播消息:" + message);
        for (Session webSocket : SESSIONS) {
            try {
                if (webSocket.isOpen()) {
                    if (webSocket != this.session) {
                        webSocket.getAsyncRemote().sendText(message);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 此为单点消息
    public void sendOneMessage(String userId, String message) {
        Session session = sessionPool.get(userId);
        if (session != null && session.isOpen()) {
            try {
                logger.info("【websocket消息】 单点消息:" + message);
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 此为单点消息(多人)
    public void sendMoreMessage(String[] userIds, String message) {
        for (String userId : userIds) {
            Session session = sessionPool.get(userId);
            if (session != null && session.isOpen()) {
                try {
                    logger.info("【websocket消息】 单点消息:" + message);
                    session.getAsyncRemote().sendText(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
