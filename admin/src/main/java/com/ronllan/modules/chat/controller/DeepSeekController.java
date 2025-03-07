package com.ronllan.modules.chat.controller;

import com.ronllan.common.utils.Result;
import com.ronllan.modules.chat.utils.DeepSeekClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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

    /**
     * 对话补全
     *
     * @return
     */
    @PostMapping("/completion")
    public Result completion(String question){
        try {
            String response = new DeepSeekClient().getResponse(question);
            System.out.println(response);
            return new Result().ok(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
