package com.ronllan.modules.oss.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ronllan.common.annotation.LogOperation;
import com.ronllan.common.utils.Result;
import com.ronllan.modules.oss.service.Base64Service;
import com.ronllan.modules.oss.service.FastDFSService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;


/**
 * Base64文件接口
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
@AllArgsConstructor
@RestController
@RequestMapping("oss/base64")
@Tag(name = "Base64文件服务")
public class Base64Controller {
    private final Base64Service base64ServiceImpl;

    @PostMapping("upload")
    @Operation(summary = "上传")
    @LogOperation("上传")
    @RequiresPermissions("oss:base64:upload")
    public Result<Map<String, Object>> upload(@RequestParam("file") MultipartFile file) throws Exception {
        return new Result<Map<String, Object>>().ok(base64ServiceImpl.uploadFile(file));
    }

}