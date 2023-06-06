package com.yamon.chatroom.mapper;

import com.yamon.chatroom.domain.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author chenyameng
* @description 针对表【message】的数据库操作Mapper
* @createDate 2023-06-05 15:37:49
* @Entity generator.domain.Message
*/
@Repository
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

}




