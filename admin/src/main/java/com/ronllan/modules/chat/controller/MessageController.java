package com.ronllan.modules.chat.controller;

import com.ronllan.common.annotation.LogOperation;
import com.ronllan.common.utils.Result;
import com.ronllan.modules.chat.entity.MessageEntity;
import com.ronllan.modules.chat.service.MessageService;
import com.ronllan.modules.sys.dto.SysDeptDto;
import com.ronllan.websocket.WebSocketServer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 消息处理
 *
 * @author xyj godlikexyj@gmail.com
 */

@AllArgsConstructor
@RestController
@RequestMapping("/chat/msg")
@Tag(name = "消息处理")
public class MessageController {

    @Autowired
    private MessageService messageServiceImpl;

    @Autowired
    private WebSocketServer socketServer;

    @PostMapping("/send")
    @Operation(summary = "发送消息")
    @LogOperation("发送消息")
    //@RequiresPermissions("chat:msg:send")
    public Result send(@RequestBody MessageEntity messageEntity) {
        System.out.println(messageEntity);
        //将消息存入数据库
        messageServiceImpl.save(messageEntity);
        //通过websocket将消息传到前端
        socketServer.sendMessage(messageEntity.getReceiverId(), messageEntity);
        return new Result();
    }

    @GetMapping("/list")
    @Operation(summary = "分页获取消息列表")
    @LogOperation("分页获取消息列表")
    //@RequiresPermissions("chat:msg:list")
    public Result list(Long userId, Long contactId){
        List<MessageEntity> list = messageServiceImpl.list(userId, contactId);
        System.out.println(list.size());
        return new Result().ok(list);
    }

}
