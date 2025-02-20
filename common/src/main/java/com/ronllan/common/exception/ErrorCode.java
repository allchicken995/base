package com.ronllan.common.exception;

/**
 * 错误编码，由5位数字组成，前2位为模块编码，后3位为业务编码
 * <p>
 * 如：10001（10代表系统模块，001代表业务代码）
 * </p>
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
public interface ErrorCode {
	/**
     * 服务器内部异常
     */
    int INTERNAL_SERVER_ERROR = 500;
    /**
     * 未授权
     */
    int UNAUTHORIZED = 401;
    /**
     * 拒绝访问，没有权限
     */
    int FORBIDDEN = 403;
    /**
     * {0}不能为空
     */
    int NOT_NULL_1 = 10001;
    /**
     * 数据库中已存在该记录
     */
    int DB_RECORD_EXISTS_0 = 10002;
    /**
     * 数据删除失败
     */
    int DATA_DELETE_ERROR_0 = 10003;
    /**
     * 账号或密码错误
     */
    int ACCOUNT_PASSWORD_ERROR_0 = 10004;
    /**
     * 账号已被停用
     */
    int ACCOUNT_DISABLE_0 = 10005;
    /**
     * 唯一标识不能为空
     */
    int IDENTIFIER_NOT_NULL_0 = 10006;
    /**
     * 验证码不正确
     */
    int CAPTCHA_ERROR_0 = 10007;
    /**
     * 处理对象不能为空
     */
    int HANDLE_OBJECT_NOT_NULL_0 = 10008;
    /**
     * 原密码不正确
     */
    int PASSWORD_ERROR_0 = 10009;
    /**
     * 存在关联数据，不允许删除
     */
    int DATA_DELETE_NOT_ALLOW_0 = 10010;
    /**
     * 上级部门选择错误
     */
    int SUPERIOR_DEPT_ERROR_0 = 10011;
    /**
     * 上级菜单不能为自身
     */
    int SUPERIOR_MENU_ERROR_0 = 10012;
    /**
     * 数据权限接口，只能是Map类型参数
     */
    int DATA_SCOPE_PARAMS_ERROR_0 = 10013;
    /**
     * 数据插入失败
     */
    int DATA_INSERT_ERROR_0 = 10014;
    /**
     * 数据更新失败
     */
    int DATA_UPDATE_ERROR_0 = 10015;
    /**
     * sql方法{0}调用出错
     */
    int SQLMETHOD_ERROR_1 = 10016;
    /**
     * 文件上传失败
     */
    int UPLOAD_FILE_ERROR_0 = 10017;
    /**
     * 文件读取失败
     */
    int READ_FILE_ERROR_0 = 10018;
    int UPLOAD_FILE_EMPTY = 10019;
    int TOKEN_NOT_EMPTY = 10020;
    int TOKEN_INVALID = 10021;
    int ACCOUNT_LOCK = 10022;
    int ACT_DEPLOY_FORMAT_ERROR = 10023;
    int OSS_UPLOAD_FILE_ERROR = 10024;
    int SEND_SMS_ERROR = 10025;
    int MAIL_TEMPLATE_NOT_EXISTS = 10026;
    int REDIS_ERROR = 10027;
    int JOB_ERROR = 10028;
    int INVALID_SYMBOL = 10029;
    int JSON_FORMAT_ERROR = 10030;
    int SMS_CONFIG = 10031;
    int TASK_CLIME_FAIL = 10032;
    int NONE_EXIST_PROCESS = 10033;
    int SUPERIOR_NOT_EXIST = 10034;
    int REJECT_MESSAGE = 10035;
    int ROLLBACK_MESSAGE = 10036;
    int UNCLAIM_ERROR_MESSAGE = 10037;
    int SUPERIOR_REGION_ERROR = 10038;
    int REGION_SUB_DELETE_ERROR = 10039;
    int PROCESS_START_ERROR = 10040;
    int REJECT_PROCESS_PARALLEL_ERROR = 10041;
    int REJECT_PROCESS_HANDLEING_ERROR = 10042;
    int END_PROCESS_PARALLEL_ERROR = 10043;
    int END_PROCESS_HANDLEING_ERROR = 10044;
    int END_PROCESS_MESSAGE = 10045;
    int BACK_PROCESS_PARALLEL_ERROR = 10046;
    int BACK_PROCESS_HANDLEING_ERROR = 10047;
    /**
     * 不能删除自己
     */
    int DEL_MYSELF_ERROR_0 = 10048;
}
