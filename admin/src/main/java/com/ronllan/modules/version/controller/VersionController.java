package com.ronllan.modules.version.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ronllan.common.utils.Result;
import com.ronllan.modules.version.dto.VersionDto;
import com.ronllan.modules.version.service.VersionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

/**
 * 版本管理
 *
 * @author glq gugameds066@gmail.com
 */
@AllArgsConstructor
@RestController
@RequestMapping("/version")
@Tag(name = "版本管理")
public class VersionController {
    private final VersionService versionServiceImpl;

    @GetMapping("list")
    @Operation(summary = "列表")
    public Result<List<VersionDto>> list(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        List<VersionDto> list = versionServiceImpl.list(params);
        return new Result<List<VersionDto>>().ok(list);
    }

}