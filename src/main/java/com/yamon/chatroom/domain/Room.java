package com.yamon.chatroom.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @TableName room
 */
@TableName(value ="room")
@Data
public class Room implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String content;

    /**
     * 发送人
     */
    private String roomId;

    /**
     * 目标人
     */
    private String sendId;

    /**
     * 是否已读
     */
    private Boolean isRead;

    /**
     * 是否可见
     */
    private Boolean isDisplay;

    /**
     * 是否已过期
     */
    private Boolean isExpire;

    /**
     * 发送时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 过期时间
     */
    private long expireTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", content=").append(content);
        sb.append(", roomId=").append(roomId);
        sb.append(", sendId=").append(sendId);
        sb.append(", isRead=").append(isRead);
        sb.append(", isDisplay=").append(isDisplay);
        sb.append(", isExpire=").append(isExpire);
        sb.append(", createTime=").append(createTime);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}