package com.yamon.chatroom.service;

import com.yamon.chatroom.domain.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yamon.chatroom.domain.Room;

import java.util.List;

/**
* @author chenyameng
* @description 针对表【message】的数据库操作Service
* @createDate 2023-06-05 15:37:49
*/
public interface MessageService extends IService<Message> {

    List<Room> getAllMsgByRoomId(String roomId);
}
