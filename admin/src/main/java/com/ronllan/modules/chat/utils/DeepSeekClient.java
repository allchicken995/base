package com.ronllan.modules.chat.utils;

import com.alibaba.fastjson.JSON;
import okhttp3.*;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class DeepSeekClient {
    /**
     * 请求API地址
     */
    private static final String API_URL = "https://api.deepseek.com/v1/chat/completions";
    /**
     * 你在DeepSeek官网申请的API KEY，注意不要泄露给他人！
     */
    private static String API_KEY = "sk-13c6b35039f8460ab849d41804488326";

    public String getResponse(String prompt) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS) // 连接超时
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)    // 读取超时
                .writeTimeout(60 * 1000, TimeUnit.MILLISECONDS)   // 写入超时
                .build();
        // 构建请求体
        DeepSeekRequestModel.Message message = DeepSeekRequestModel.Message.builder()
                .role("user")
                .content(prompt).build();
        DeepSeekRequestModel requestBody = DeepSeekRequestModel.builder()
                .model("deepseek-chat")
                .messages(Collections.singletonList(message))
                .build();

        //构建请求体json：{"messages":[{"content":"你好，DeepSeek！","role":"user"}],"model":"deepseek-chat"}
        String jsonBody = JSON.toJSONString(requestBody);
        System.out.println(jsonBody);
        // 创建HTTP请求
        Request request = new Request.Builder()
                .url(API_URL)
                .post(RequestBody.create(jsonBody, MediaType.get("application/json")))
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        // 发送请求并处理响应
        try (Response response = client.newCall(request).execute()) {
            //如果响应成功，并且返回体有内容，就输出内容，否则表示响应失败
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            }
            throw new IOException("Unexpected code " + response);
        }
    }

    public static void main(String[] args) {
        String question = "Java的优势";
        try {
            String response = new DeepSeekClient().getResponse(question);
            System.out.println(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}