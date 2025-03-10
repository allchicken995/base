package com.ronllan.modules.chat.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @NoArgsConstructor   // ✅ 生成无参构造方法
    @AllArgsConstructor  // ✅ 生成全参构造方法
    public static class Message {
        private String role;
        private String content;
    }
}