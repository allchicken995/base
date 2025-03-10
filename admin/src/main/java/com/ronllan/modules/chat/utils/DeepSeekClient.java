package com.ronllan.modules.chat.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
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

    public String getResponse(DeepSeekRequestModel requestBody) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10 * 60 * 1000, TimeUnit.MILLISECONDS) // 连接超时
                .readTimeout(10 * 60 * 1000, TimeUnit.MILLISECONDS)    // 读取超时
                .writeTimeout(10 * 60 * 1000, TimeUnit.MILLISECONDS)   // 写入超时
                .build();
        String jsonBody = JSON.toJSONString(requestBody);
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
                String result = response.body().string();
                return getContent(result);
            }
            throw new IOException("Unexpected code " + response);
        }
    }

    private String getContent(String response){
        JSONObject jso = JSONObject.parseObject(response);
        JSONArray jsa = jso.getJSONArray("choices");
        return jsa.getJSONObject(0).getJSONObject("message").getString("content");
    }
}