package com.yamon.chatroom.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yamon.chatroom.domain.Room;
import com.yamon.chatroom.service.RoomService;
import com.yamon.chatroom.mapper.RoomMapper;
import org.springframework.stereotype.Service;

/**
* @author chenyameng
* @description 针对表【room】的数据库操作Service实现
* @createDate 2023-06-05 17:52:59
*/
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room>
    implements RoomService{

}




