package com.yamon.chatroom.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yamon.chatroom.domain.Message;
import com.yamon.chatroom.domain.Room;
import com.yamon.chatroom.mapper.RoomMapper;
import com.yamon.chatroom.service.MessageService;
import com.yamon.chatroom.mapper.MessageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenyameng
 * @description 针对表【message】的数据库操作Service实现
 * @createDate 2023-06-05 15:37:49
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
        implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private RoomMapper roomMapper;

    @Override
    public List<Room> getAllMsgByRoomId(String roomId) {
        List<Room> roomList = roomMapper.selectList(new LambdaQueryWrapper<Room>().eq(Room::getRoomId, roomId).orderByAsc(Room::getCreateTime));
        return roomList;
    }
}




