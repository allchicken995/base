package com.ronllan.modules.chat.utils;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DeepSeekRequestModel {
    /**
     * 所用DeepSeek模型
     */
    private String model;
    private List<Message> messages;

    /**
     * 消息体
     */
    @Data
    @Builder
    public static class Message {
        private String role;
        private String content;
    }
}