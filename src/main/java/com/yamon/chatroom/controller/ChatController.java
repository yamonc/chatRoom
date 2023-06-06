package com.yamon.chatroom.controller;

import com.yamon.chatroom.domain.Message;
import com.yamon.chatroom.domain.Room;
import com.yamon.chatroom.service.MessageService;
import com.yamon.chatroom.utils.WebSocketUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author yamon
 * @Date 2023-06-05 10:30
 * @Description 聊天室发送消息
 * @Version 1.0
 */
@RestController
@RequestMapping("/message")
public class ChatController {
    @Resource
    private WebSocketUtils webSocketUtils;

    @Resource
    private MessageService messageService;

    @RequestMapping(value = "/list/{roomId}", method = RequestMethod.GET)
    public List<Room> list(@PathVariable(value = "roomId") String roomId) {
        List<Room> list = messageService.getAllMsgByRoomId(roomId);
        return list;
    }
}
