package com.ronllan.common.user;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 登录用户信息
 *
 * @author glq gugameds066@gmail.com
 */
@Data
public class UserDetail implements Serializable {
    private Long id;
    private String username;
    private String realName;
    private String headUrl;
    private Integer gender;
    private String email;
    private String mobile;
    private Long deptId;
    private String password;
    private Integer status;
    private Integer superAdmin;
    /**
     * 部门数据权限
     */
    private List<Long> deptIdList;

}