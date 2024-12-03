package com.ronllan.websocket.data;

import jakarta.websocket.Session;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * WebSocket连接数据
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@AllArgsConstructor
public class WebSocketData {
    private Long userId;
    private Session session;
}