package com.ronllan.modules.chat.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ronllan.common.redis.RedisUtils;
import com.ronllan.common.utils.Result;
import com.ronllan.modules.chat.utils.DeepSeekClient;
import com.ronllan.modules.chat.utils.DeepSeekRequestModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * DeepSeek接入
 *
 * @author xyj godlikexyj@gmail.com
 */

@AllArgsConstructor
@RestController
@RequestMapping("/chat/DeepSeek")
@Tag(name = "DeepSeek")
public class DeepSeekController {

    @Resource
    private RedisUtils redisUtils;

    /**
     * 对话补全
     *
     */
    @PostMapping("/completion")
    public Result completion(Long userId, String question){
        DeepSeekRequestModel.Message message = DeepSeekRequestModel.Message.builder()
                .role("user").content(question).build();
        String cache = null;
        if (redisUtils.get(userId + "_chat_cache") != null){
            cache = redisUtils.get(userId + "_chat_cache").toString();
        }
        DeepSeekClient deepSeekClient = new DeepSeekClient();
        DeepSeekRequestModel requestBody = DeepSeekRequestModel.builder().model("deepseek-chat").build();
        try {
            if (StringUtils.isEmpty(cache)){
                requestBody.setMessages(new ArrayList<>(Collections.singletonList(message)));
            }else {
                List<DeepSeekRequestModel.Message> msgList = JSON.parseObject(cache, new TypeReference<>() {});
                msgList.add(message);
                requestBody.setMessages(msgList);
            }
            String response = deepSeekClient.getResponse(requestBody);
            DeepSeekRequestModel.Message respMsg = DeepSeekRequestModel.Message.builder()
                    .role("assistant").content(response).build();
            requestBody.getMessages().add(respMsg);
            List<DeepSeekRequestModel.Message> msgList = requestBody.getMessages();
            String jsonStr = JSON.toJSONString(msgList);
            redisUtils.set(userId + "_chat_cache", jsonStr);
            return new Result().ok(respMsg);
        }catch (Exception e){
            e.printStackTrace();
            return new Result().error();
        }
    }

    /**
     * 删除对话缓存
     *
     */
    @PostMapping("/deleteCache")
    public Result deleteCache(Long userId){
        redisUtils.delete(userId + "_chat_cache");
        return new Result();
    }

}
