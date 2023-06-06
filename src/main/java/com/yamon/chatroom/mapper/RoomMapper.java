package com.yamon.chatroom.mapper;

import com.yamon.chatroom.domain.Room;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author chenyameng
* @description 针对表【room】的数据库操作Mapper
* @createDate 2023-06-05 17:52:59
* @Entity com.yamon.chatroom.domain.Room
*/
@Repository
@Mapper
public interface RoomMapper extends BaseMapper<Room> {

}




