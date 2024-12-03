package com.ronllan.modules.security.controller;

import com.ronllan.common.exception.ErrorCode;
import com.ronllan.common.exception.DefineException;
import com.ronllan.common.user.UserDetail;
import com.ronllan.common.utils.IpUtils;
import com.ronllan.common.utils.Result;
import com.ronllan.common.validator.AssertUtils;
import com.ronllan.common.validator.ValidatorUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ronllan.modules.log.entity.SysLogLoginEntity;
import com.ronllan.modules.log.enums.LoginOperationEnum;
import com.ronllan.modules.log.enums.LoginStatusEnum;
import com.ronllan.modules.log.service.SysLogLoginService;
import com.ronllan.modules.security.dto.LoginDTO;
import com.ronllan.modules.security.password.PasswordUtils;
import com.ronllan.modules.security.service.CaptchaService;
import com.ronllan.modules.security.service.SysUserTokenService;
import com.ronllan.modules.security.user.SecurityUser;
import com.ronllan.modules.sys.dto.SysUserDto;
import com.ronllan.modules.sys.enums.UserStatusEnum;
import com.ronllan.modules.sys.service.SysUserService;

import java.io.IOException;
import java.util.Date;

/**
 * 登录
 *
 * @author Mark sunlightcs@gmail.com
 */
@AllArgsConstructor
@RestController
@Tag(name = "登录管理")
public class LoginController {
    private final SysUserService sysUserService;
    private final SysUserTokenService sysUserTokenService;
    private final CaptchaService captchaService;
    private final SysLogLoginService sysLogLoginService;

    @GetMapping("captcha")
    @Operation(summary = "验证码")
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        //uuid不能为空
        AssertUtils.isBlank(uuid, ErrorCode.IDENTIFIER_NOT_NULL);
        //生成验证码
        captchaService.create(response, uuid);
    }

    @PostMapping("login")
    @Operation(summary = "登录")
    public Result login(HttpServletRequest request, @RequestBody LoginDTO login) {
        //效验数据
        ValidatorUtils.validateEntity(login);
        //验证码是否正确
        boolean flag = captchaService.validate(login.getUuid(), login.getCaptcha());
        if (!flag) {
            return new Result().error(ErrorCode.CAPTCHA_ERROR);
        }
        //用户信息
        SysUserDto user = sysUserService.getByUsername(login.getUsername());
        SysLogLoginEntity log = new SysLogLoginEntity();
        log.setOperation(LoginOperationEnum.LOGIN.value());
        log.setCreateDate(new Date());
        log.setIp(IpUtils.getIpAddr(request));
        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        log.setIp(IpUtils.getIpAddr(request));
        //用户不存在
        if (user == null) {
            log.setStatus(LoginStatusEnum.FAIL.value());
            log.setCreatorName(login.getUsername());
            sysLogLoginService.save(log);
            throw new DefineException(ErrorCode.ACCOUNT_PASSWORD_ERROR);
        }
        //密码错误
        if (!PasswordUtils.matches(login.getPassword(), user.getPassword())) {
            log.setStatus(LoginStatusEnum.FAIL.value());
            log.setCreator(user.getId());
            log.setCreatorName(user.getUsername());
            sysLogLoginService.save(log);
            throw new DefineException(ErrorCode.ACCOUNT_PASSWORD_ERROR);
        }
        //账号停用
        if (user.getStatus() == UserStatusEnum.DISABLE.value()) {
            log.setStatus(LoginStatusEnum.LOCK.value());
            log.setCreator(user.getId());
            log.setCreatorName(user.getUsername());
            sysLogLoginService.save(log);
            throw new DefineException(ErrorCode.ACCOUNT_DISABLE);
        }
        //登录成功
        log.setStatus(LoginStatusEnum.SUCCESS.value());
        log.setCreator(user.getId());
        log.setCreatorName(user.getUsername());
        sysLogLoginService.save(log);
        return sysUserTokenService.createToken(user.getId());
    }

    @PostMapping("logout")
    @Operation(summary = "退出")
    public Result logout(HttpServletRequest request) {
        UserDetail user = SecurityUser.getUser();

        //退出
        sysUserTokenService.logout(user.getId());

        //用户信息
        SysLogLoginEntity log = new SysLogLoginEntity();
        log.setOperation(LoginOperationEnum.LOGOUT.value());
        log.setIp(IpUtils.getIpAddr(request));
        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        log.setIp(IpUtils.getIpAddr(request));
        log.setStatus(LoginStatusEnum.SUCCESS.value());
        log.setCreator(user.getId());
        log.setCreatorName(user.getUsername());
        log.setCreateDate(new Date());
        sysLogLoginService.save(log);

        return new Result();
    }

}