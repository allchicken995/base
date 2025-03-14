package com.ronllan.websocket.entity;

import jakarta.websocket.Session;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 客户端实体类
 *
 * @author xyj godlikexyj@gmail.com
 */
@Data
public class ClientInfoEntity {

    /**
     * 客户端唯一标识
     */
    private String token;
    /**
     * 客户端连接的session
     */
    private Session session;
    /**
     * 连接存活时间
     */
    private LocalDateTime existTime;
}

