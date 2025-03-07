package com.ronllan.modules.chat.controller;

import com.ronllan.common.annotation.LogOperation;
import com.ronllan.common.utils.Result;
import com.ronllan.common.validator.ValidatorUtils;
import com.ronllan.common.validator.group.AddGroup;
import com.ronllan.common.validator.group.DefaultGroup;
import com.ronllan.modules.chat.service.ContactService;
import com.ronllan.modules.sys.dto.SysDeptDto;
import com.ronllan.modules.sys.dto.SysUserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 联系人控制
 *
 * @author xyj godlikexyj@gmail.com
 */

@AllArgsConstructor
@RestController
@RequestMapping("/chat/contact")
@Tag(name = "联系人控制")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/add")
    @Operation(summary = "添加联系人")
    @LogOperation("添加联系人")
    //@RequiresPermissions("chat:contact:add")
    public Result add(Long userId, Long contactId) {
        contactService.addContact(userId, contactId);
        return new Result();
    }

    @GetMapping("/list")
    @Operation(summary = "获取好友列表")
    @LogOperation("获取好友列表")
    //@RequiresPermissions("chat:contact:list")
    public Result list(){
        contactService.list();
        return new Result();
    }

}
