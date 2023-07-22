package com.agileboot.common.exception.error;

/**
 * 错误码集合
 *
 * @author valarchie
 */
public enum ErrorCode implements ErrorCodeInterface {

    /**
     * 错误码集合
     * 1~9999 为保留错误码 或者 常用错误码
     * 10000~19999 为内部错误码
     * 20000~29999 客户端错误码 （客户端异常调用之类的错误）
     * 30000~39999 为第三方错误码 （代码正常，但是第三方异常）
     * 40000~49999 为业务逻辑 错误码 （无异常，代码正常流转，并返回提示给用户）
     * 由于系统内的错误码都是独一无二的，所以错误码应该放在common包集中管理
     */
    // -------------- 普通错误码 及保留错误码 ---------------
    SUCCESS(0, "SUCCESS", "操作成功"),
    FAIL(9999, "FAIL", "操作失败"),

    UNKNOWN_ERROR(99999, "UNKNOWN_ERROR", "未知错误");

    private final int code;
    private final String msg;

    private final String i18nKey;

    ErrorCode(int code, String i18nKey, String msg) {
        this.code = code;
        this.i18nKey = i18nKey;
        this.msg = msg;
    }

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.msg;
    }

    @Override
    public String i18nKey() {
        return this.i18nKey;
    }

    /**
     * 40000~49999 为业务逻辑 错误码 （无代码异常，代码正常流转，并返回提示给用户）
     */
    public enum Business implements ErrorCodeInterface {

        // ----------------------------- Common --------------------------------------

        OBJECT_NOT_FOUND(Module.COMMON, 1, "Business.OBJECT_NOT_FOUND", "找不到ID为 {} 的 {}"),

        UNSUPPORTED_OPERATION(Module.COMMON, 2, "Business.UNSUPPORTED_OPERATION", "不支持的操作"),

        BULK_DELETE_IDS_IS_INVALID(Module.COMMON, 3, "Business.BULK_DELETE_IDS_IS_INVALID", "批量参数ID列表为空"),

        FILE_NOT_ALLOWED_TO_DOWNLOAD(Module.COMMON, 3, "Business.FILE_NOT_ALLOWED_TO_DOWNLOAD",
            "文件名称({})非法，不允许下载"),

        // ----------------------------- Permission -----------------------------------

        FORBIDDEN_TO_MODIFY_ADMIN(Module.PERMISSION, 1, "Business.FORBIDDEN_TO_MODIFY_ADMIN", "不允许修改管理员的信息"),

        NO_PERMISSION_TO_OPERATE(Module.PERMISSION, 2, "Business.NO_PERMISSION_TO_OPERATE",
            "没有权限进行此操作，请联系管理员"),

        // ----------------------------- Login -----------------------------------------

        LOGIN_WRONG_USER_PASSWORD(Module.LOGIN, 1, "Business.LOGIN_WRONG_USER_PASSWORD", "用户密码错误，请重输"),

        LOGIN_ERROR(Module.LOGIN, 2, "Business.LOGIN_ERROR", "登录失败：{}"),

        LOGIN_CAPTCHA_CODE_WRONG(Module.LOGIN, 3, "Business.LOGIN_CAPTCHA_CODE_WRONG", "验证码错误"),

        LOGIN_CAPTCHA_CODE_EXPIRE(Module.LOGIN, 4, "Business.LOGIN_CAPTCHA_CODE_EXPIRE", "验证码过期"),

        LOGIN_CAPTCHA_CODE_NULL(Module.LOGIN, 5, "Business.LOGIN_CAPTCHA_CODE_NULL", "验证码为空"),

        // ----------------------------- Upload -----------------------------------------

        UPLOAD_FILE_TYPE_NOT_ALLOWED(Module.UPLOAD, 1, "Business.UPLOAD_FILE_TYPE_NOT_ALLOWED",
            "不允许上传的文件类型，仅允许：{}"),

        UPLOAD_FILE_NAME_EXCEED_MAX_LENGTH(Module.UPLOAD, 2, "Business.UPLOAD_FILE_NAME_EXCEED_MAX_LENGTH",
            "文件名长度超过：{} "),

        UPLOAD_FILE_SIZE_EXCEED_MAX_SIZE(Module.UPLOAD, 3, "Business.UPLOAD_FILE_SIZE_EXCEED_MAX_SIZE",
            "文件名大小超过：{} MB"),

        UPLOAD_IMPORT_EXCEL_FAILED(Module.UPLOAD, 4, "Business.UPLOAD_IMPORT_EXCEL_FAILED", "导入excel失败：{}"),

        UPLOAD_FILE_IS_EMPTY(Module.UPLOAD, 5, "Business.UPLOAD_FILE_IS_EMPTY", "上传文件为空"),

        UPLOAD_FILE_FAILED(Module.UPLOAD, 6, "Business.UPLOAD_FILE_FAILED", "上传文件失败：{}"),

        // ----------------------------- Config -----------------------------------------

        CONFIG_VALUE_IS_NOT_ALLOW_TO_EMPTY(Module.CONFIG, 1, "Business.CONFIG_VALUE_IS_NOT_ALLOW_TO_EMPTY",
            "参数键值不允许为空"),

        CONFIG_VALUE_IS_NOT_IN_OPTIONS(Module.CONFIG, 2, "Business.CONFIG_VALUE_IS_NOT_IN_OPTIONS",
            "参数键值不存在列表中"),

        // ------------------------------- Post --------------------------------------------

        POST_NAME_IS_NOT_UNIQUE(Module.POST, 1, "Business.POST_NAME_IS_NOT_UNIQUE", "岗位名称:{}, 已存在"),

        POST_CODE_IS_NOT_UNIQUE(Module.POST, 2, "Business.POST_CODE_IS_NOT_UNIQUE", "岗位编号:{}, 已存在"),

        POST_ALREADY_ASSIGNED_TO_USER_CAN_NOT_BE_DELETED(Module.POST, 3,
            "Business.POST_ALREADY_ASSIGNED_TO_USER_CAN_NOT_BE_DELETED", "职位已分配给用户，请先取消分配再删除"),

        // ------------------------------- Dept ---------------------------------------------

        DEPT_NAME_IS_NOT_UNIQUE(Module.DEPT, 1, "Business.DEPT_NAME_IS_NOT_UNIQUE", "部门名称:{}, 已存在"),

        DEPT_PARENT_ID_IS_NOT_ALLOWED_SELF(Module.DEPT, 2, "Business.DEPT_PARENT_ID_IS_NOT_ALLOWED_SELF",
            "父级部门不能选择自己"),

        DEPT_STATUS_ID_IS_NOT_ALLOWED_CHANGE(Module.DEPT, 3, "Business.DEPT_STATUS_ID_IS_NOT_ALLOWED_CHANGE",
            "子部门还有正在启用的部门，暂时不能停用该部门"),

        DEPT_EXIST_CHILD_DEPT_NOT_ALLOW_DELETE(Module.DEPT, 4, "Business.DEPT_EXIST_CHILD_DEPT_NOT_ALLOW_DELETE",
            "该部门存在下级部门不允许删除"),

        DEPT_EXIST_LINK_USER_NOT_ALLOW_DELETE(Module.DEPT, 5, "Business.DEPT_EXIST_LINK_USER_NOT_ALLOW_DELETE",
            "该部门存在关联的用户不允许删除"),

        DEPT_PARENT_DEPT_NO_EXIST_OR_DISABLED(Module.DEPT, 6, "Business.DEPT_PARENT_DEPT_NO_EXIST_OR_DISABLED",
            "该父级部门不存在或已停用"),

        // -------------------------------  Menu -------------------------------------------------

        MENU_NAME_IS_NOT_UNIQUE(Module.MENU, 1, "Business.MENU_NAME_IS_NOT_UNIQUE", "新增菜单:{} 失败，菜单名称已存在"),

        MENU_EXTERNAL_LINK_MUST_BE_HTTP(Module.MENU, 2, "Business.MENU_EXTERNAL_LINK_MUST_BE_HTTP",
            "菜单外链必须以 http(s)://开头"),

        MENU_PARENT_ID_NOT_ALLOW_SELF(Module.MENU, 3, "Business.MENU_PARENT_ID_NOT_ALLOW_SELF", "父级菜单不能选择自身"),

        MENU_EXIST_CHILD_MENU_NOT_ALLOW_DELETE(Module.MENU, 4, "Business.MENU_EXIST_CHILD_MENU_NOT_ALLOW_DELETE",
            "存在子菜单不允许删除"),

        MENU_ALREADY_ASSIGN_TO_ROLE_NOT_ALLOW_DELETE(Module.MENU, 5,
            "Business.MENU_ALREADY_ASSIGN_TO_ROLE_NOT_ALLOW_DELETE", "菜单已分配给角色，不允许"),

        MENU_NOT_ALLOWED_TO_CREATE_BUTTON_ON_IFRAME_OR_OUT_LINK(Module.MENU, 6,
            "Business.MENU_ONLY_ALLOWED_TO_CREATE_BUTTON_ON_PAGE", "不允许在Iframe和外链跳转类型下创建按钮"),

        MENU_ONLY_ALLOWED_TO_CREATE_SUB_MENU_IN_CATALOG(Module.MENU, 7,
            "Business.MENU_ONLY_ALLOWED_TO_CREATE_SUB_MENU_IN_CATALOG", "只允许在目录类型底下创建子菜单"),

        MENU_CAN_NOT_CHANGE_MENU_TYPE(Module.MENU, 8,
            "Business.MENU_CAN_NOT_CHANGE_MENU_TYPE", "不允许更改菜单的类型"),

        // -------------------------------- Role -------------------------------------------------

        ROLE_NAME_IS_NOT_UNIQUE(Module.ROLE, 1, "Business.ROLE_NAME_IS_NOT_UNIQUE", "角色名称：{}, 已存在"),

        ROLE_KEY_IS_NOT_UNIQUE(Module.ROLE, 2, "Business.ROLE_KEY_IS_NOT_UNIQUE", "角色标识：{}, 已存在"),

        ROLE_DATA_SCOPE_DUPLICATED_DEPT(Module.ROLE, 3, "Business.ROLE_DATA_SCOPE_DUPLICATED_DEPT", "重复的部门id"),

        ROLE_ALREADY_ASSIGN_TO_USER(Module.ROLE, 4, "Business.ROLE_ALREADY_ASSIGN_TO_USER",
            "角色已分配给用户，请先取消分配，再删除角色"),

        ROLE_IS_NOT_AVAILABLE(Module.ROLE, 5, "Business.ROLE_IS_NOT_AVAILABLE", "角色：{} 已禁用，无法分配给用户"),

        // ---------------------------------- User -----------------------------------------------

        USER_NON_EXIST(Module.USER, 1, "Business.USER_NON_EXIST", "登录用户：{} 不存在"),

        USER_IS_DISABLE(Module.USER, 2, "Business.USER_IS_DISABLE", "对不起， 您的账号：{} 已停用"),

        USER_CACHE_IS_EXPIRE(Module.USER, 3, "Business.USER_CACHE_IS_EXPIRE", "用户缓存信息已经过期"),

        USER_FAIL_TO_GET_USER_ID(Module.USER, 3, "Business.USER_FAIL_TO_GET_USER_ID", "获取用户ID失败"),

        USER_FAIL_TO_GET_DEPT_ID(Module.USER, 4, "Business.USER_FAIL_TO_GET_DEPT_ID", "获取用户部门ID失败"),

        USER_FAIL_TO_GET_ACCOUNT(Module.USER, 5, "Business.USER_FAIL_TO_GET_ACCOUNT", "获取用户账户失败"),

        USER_FAIL_TO_GET_USER_INFO(Module.USER, 6, "Business.USER_FAIL_TO_GET_USER_INFO", "获取用户信息失败"),

        USER_IMPORT_DATA_IS_NULL(Module.USER, 7, "Business.USER_IMPORT_DATA_IS_NULL", "导入的用户为空"),

        USER_PHONE_NUMBER_IS_NOT_UNIQUE(Module.USER, 8, "Business.USER_PHONE_NUMBER_IS_NOT_UNIQUE",
            "该电话号码已被其他用户占用"),

        USER_EMAIL_IS_NOT_UNIQUE(Module.USER, 9, "Business.USER_EMAIL_IS_NOT_UNIQUE", "该邮件地址已被其他用户占用"),

        USER_PASSWORD_IS_NOT_CORRECT(Module.USER, 10, "Business.USER_PASSWORD_IS_NOT_CORRECT", "用户密码错误"),

        USER_NEW_PASSWORD_IS_THE_SAME_AS_OLD(Module.USER, 11, "Business.USER_NEW_PASSWORD_IS_THE_SAME_AS_OLD",
            "用户新密码与旧密码相同"),

        USER_UPLOAD_FILE_FAILED(Module.USER, 12, "Business.USER_UPLOAD_FILE_FAILED", "用户上传文件失败"),

        USER_NAME_IS_NOT_UNIQUE(Module.USER, 13, "Business.USER_NAME_IS_NOT_UNIQUE", "用户名已被其他用户占用"),

        USER_CURRENT_USER_CAN_NOT_BE_DELETE(Module.USER, 14, "Business.USER_CURRENT_USER_CAN_NOT_BE_DELETE",
            "当前用户不允许被删除"),

        USER_ADMIN_CAN_NOT_BE_MODIFY(Module.USER, 15, "Business.USER_ADMIN_CAN_NOT_BE_MODIFY",
            "管理员不允许做任何修改"),

        ;


        private final int code;
        private final String msg;

        private final String i18nKey;

        private static final int BASE_CODE = 40000;

        Business(Module module, int code, String i18nKey, String msg) {
            this.code = BASE_CODE + module.code() + code;
            this.i18nKey = i18nKey;
            this.msg = msg;
        }

        @Override
        public int code() {
            return this.code;
        }

        @Override
        public String message() {
            return this.msg;
        }

        @Override
        public String i18nKey() {
            return i18nKey;
        }
    }


    /**
     * 30000~39999是外部错误码  比如调用支付失败
     */
    public enum External implements ErrorCodeInterface {

        /**
         * 支付宝调用失败
         */
        FAIL_TO_PAY_ON_ALIPAY(Module.COMMON, 1, "External.FAIL_TO_PAY_ON_ALIPAY", "支付宝调用失败");


        private final int code;
        private final String msg;

        private final String i18nKey;

        private static final int BASE_CODE = 30000;

        External(Module module, int code, String i18nKey, String msg) {
            this.code = BASE_CODE + module.code() + code;
            this.i18nKey = i18nKey;
            this.msg = msg;
        }

        @Override
        public int code() {
            return this.code;
        }

        @Override
        public String message() {
            return this.msg;
        }

        @Override
        public String i18nKey() {
            return this.i18nKey;
        }


    }


    /**
     * 20000~29999是客户端错误码
     */
    public enum Client implements ErrorCodeInterface {

        /**
         * 客户端错误码
         */
        COMMON_FORBIDDEN_TO_CALL(Module.COMMON, 1, "Client.COMMON_FORBIDDEN_TO_CALL", "禁止调用"),

        COMMON_REQUEST_TOO_OFTEN(Module.COMMON, 2, "Client.COMMON_REQUEST_TOO_OFTEN", "调用太过频繁"),

        COMMON_REQUEST_PARAMETERS_INVALID(Module.COMMON, 3, "Client.COMMON_REQUEST_PARAMETERS_INVALID",
            "请求参数异常，{}"),

        COMMON_REQUEST_METHOD_INVALID(Module.COMMON, 4, "Client.COMMON_REQUEST_METHOD_INVALID", "请求方式: {} 不支持"),

        COMMON_REQUEST_RESUBMIT(Module.COMMON, 5, "Client.COMMON_REQUEST_RESUBMIT", "请求重复提交"),

        COMMON_NO_AUTHORIZATION(Module.PERMISSION, 1, "Client.COMMON_NO_AUTHORIZATION", "请求接口：{} 失败，用户未授权"),

        ;

        private final int code;
        private final String msg;

        private final String i18nKey;

        private static final int BASE_CODE = 20000;

        Client(Module module, int code, String i18nKey, String msg) {
            this.code = BASE_CODE + module.code() + code;
            this.i18nKey = i18nKey;
            this.msg = msg;
        }

        @Override
        public int code() {
            return this.code;
        }

        @Override
        public String message() {
            return this.msg;
        }

        @Override
        public String i18nKey() {
            return this.i18nKey;
        }

    }


    /**
     * 10000~19999是内部错误码  例如 框架有问题之类的
     */
    public enum Internal implements ErrorCodeInterface {
        /**
         * 内部错误码
         */
        INVALID_PARAMETER(Module.COMMON, 1, "Internal.INVALID_PARAMETER", "参数异常：{}"),

        UNKNOWN_ERROR(Module.COMMON, 2, "Internal.UNKNOWN_ERROR", "未知异常, 详情请查看系统日志"),

        GET_ENUM_FAILED(Module.COMMON, 3, "Internal.GET_ENUM_FAILED", "获取枚举类型失败, 枚举类: {}"),

        GET_CACHE_FAILED(Module.COMMON, 4, "Internal.GET_CACHE_FAILED", "获取缓存失败"),

        INTERNAL_ERROR(Module.COMMON, 5, "Internal.INTERNAL_ERROR", "系统内部错误：{}"),

        LOGIN_CAPTCHA_GENERATE_FAIL(Module.LOGIN, 1, "Internal.LOGIN_CAPTCHA_GENERATE_FAIL", "验证码生成失败"),

        INVALID_TOKEN(Module.PERMISSION, 1, "Internal.INVALID_TOKEN", "token异常"),

        DB_INTERNAL_ERROR(Module.DB, 1, "Internal.DB_INTERNAL_ERROR", "数据库异常: {}"),

        ;

        private final int code;
        private final String msg;

        private final String i18nKey;

        private static final int BASE_CODE = 10000;

        Internal(Module module, int code, String i18nKey, String msg) {
            this.code = BASE_CODE + module.code() + code;
            this.i18nKey = i18nKey;
            this.msg = msg;
        }

        @Override
        public int code() {
            return this.code;
        }

        @Override
        public String message() {
            return this.msg;
        }

        @Override
        public String i18nKey() {
            return this.i18nKey;
        }

    }

}
