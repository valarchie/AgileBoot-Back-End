package com.agileboot.common.exception.error;

/**
 * 常用错误码 以及 保留错误码
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
    SUCCESS(0,"操作成功"),
    FAIL(9999, "操作失败"),


    UNKNOWN_ERROR(99999,"未知错误");

    private final int code;
    private final String msg;


    ErrorCode(int code, String msg) {
        this.code = code;
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

    /**
     * 40000~49999 为业务逻辑 错误码 （无代码异常，代码正常流转，并返回提示给用户）
     */
    public enum Business implements ErrorCodeInterface{

        // ----------------------------- Common -----------------------------------------

        OBJECT_NOT_FOUND(Module.COMMON, 1, "找不到ID为%s 的%s"),

        UNSUPPORTED_OPERATION(Module.COMMON, 2, "不支持的操作"),

        BULK_DELETE_IDS_IS_INVALID(Module.COMMON, 3, "批量参数ID列表为空"),

        // ----------------------------- Permission -----------------------------------------

        FORBIDDEN_TO_MODIFY_ADMIN(Module.PERMISSION, 1, "不允许修改管理员的信息"),

        NO_PERMISSION_TO_OPERATE(Module.PERMISSION, 2, "没有权限进行此操作，请联系管理员"),

        // ----------------------------- Login -----------------------------------------

        LOGIN_WRONG_USER_PASSWORD(Module.LOGIN, 1, "用户密码错误，请重输"),

        LOGIN_ERROR(Module.LOGIN, 2, "登录失败：{}"),

        CAPTCHA_CODE_WRONG(Module.LOGIN, 3, "验证码错误"),

        CAPTCHA_CODE_EXPIRE(Module.LOGIN, 4, "验证码过期"),

        CAPTCHA_CODE_NULL(Module.LOGIN, 5, "验证码为空"),


// ----------------------------- Upload -----------------------------------------

        UPLOAD_FILE_TYPE_NOT_ALLOWED(Module.UPLOAD, 1, "不允许上传的文件类型，仅允许：%s"),

        UPLOAD_FILE_NAME_EXCEED_MAX_LENGTH(Module.UPLOAD, 2, "文件名长度超过：%s "),

        UPLOAD_FILE_SIZE_EXCEED_MAX_SIZE(Module.UPLOAD, 3, "文件名大小超过：%s MB"),

        UPLOAD_IMPORT_EXCEL_FAILED(Module.UPLOAD, 4, "导入excel失败：%s"),

        UPLOAD_FILE_IS_EMPTY(Module.UPLOAD, 5, "上传文件为空"),

        // ----------------------------- Config -----------------------------------------

        CONFIG_VALUE_IS_NOT_ALLOW_TO_EMPTY(Module.CONFIG, 1, "参数键值不允许为空"),

        CONFIG_VALUE_IS_NOT_IN_OPTIONS(Module.CONFIG, 2, "参数键值不存在列表中"),

        // ----------------------------- Post -----------------------------------------

        POST_NAME_IS_NOT_UNIQUE(Module.POST, 1, "岗位名称:%s, 已存在"),

        POST_CODE_IS_NOT_UNIQUE(Module.POST, 2, "岗位编号:%s, 已存在"),

        POST_ALREADY_ASSIGNED_TO_USER_CAN_NOT_BE_DELETED(Module.POST, 3, "职位已分配给用户，请先取消分配再删除"),

        // ------------------------------- Dept ---------------------------------------------

        DEPT_NAME_IS_NOT_UNIQUE(Module.DEPT, 1, "部门名称:%s, 已存在"),

        DEPT_PARENT_ID_IS_NOT_ALLOWED_SELF(Module.DEPT, 2, "父级部门不能选择自己"),

        DEPT_STATUS_ID_IS_NOT_ALLOWED_CHANGE(Module.DEPT, 3, "子部门还有正在启用的部门，暂时不能停用该部门"),

        DEPT_EXIST_CHILD_DEPT_NOT_ALLOW_DELETE(Module.DEPT, 4, "该部门存在下级部门不允许删除"),

        DEPT_EXIST_LINK_USER_NOT_ALLOW_DELETE(Module.DEPT, 5, "该部门存在关联的用户不允许删除"),

        DEPT_PARENT_DEPT_NO_EXIST_OR_DISABLED(Module.DEPT, 6, "该父级部门不存在或已停用"),

        // -------------------------------  Menu -------------------------------------------------------

        MENU_NAME_IS_NOT_UNIQUE(Module.MENU, 1, "新增菜单:%s 失败，菜单名称已存在"),

        MENU_EXTERNAL_LINK_MUST_BE_HTTP(Module.MENU, 2, "菜单外链必须以 http(s)://开头"),

        MENU_PARENT_ID_NOT_ALLOW_SELF(Module.MENU, 3, "父级菜单不能选择自身"),

        MENU_EXIST_CHILD_MENU_NOT_ALLOW_DELETE(Module.MENU, 4, "存在子菜单不允许删除"),

        MENU_ALREADY_ASSIGN_TO_ROLE_NOT_ALLOW_DELETE(Module.MENU, 5, "菜单已分配给角色，不允许"),

        // -------------------------------- Role ----------------------------------------------------

        ROLE_NAME_IS_NOT_UNIQUE(Module.ROLE, 1, "角色名称：%s, 已存在"),

        ROLE_KEY_IS_NOT_UNIQUE(Module.ROLE, 2, "角色标识：%s, 已存在"),

        ROLE_DATA_SCOPE_DUPLICATED_DEPT(Module.ROLE, 3, "重复的部门id"),

        ROLE_ALREADY_ASSIGN_TO_USER(Module.ROLE, 4, "角色已分配给用户，请先取消分配，再删除角色"),


        // ------------------------  User ------------------------------


        USER_NON_EXIST(Module.USER, 1, "登录用户：%s 不存在"),

        USER_IS_DISABLE(Module.USER, 2, "对不起， 您的账号：{} 已停用"),

        USER_CACHE_IS_EXPIRE(Module.USER, 3, "用户缓存信息已经过期"),

        USER_FAIL_TO_GET_USER_ID(Module.USER, 3, "获取用户ID失败"),

        USER_FAIL_TO_GET_DEPT_ID(Module.USER, 4, "获取用户部门ID失败"),

        USER_FAIL_TO_GET_ACCOUNT(Module.USER, 5, "获取用户账户失败"),

        USER_FAIL_TO_GET_USER_INFO(Module.USER, 6, "获取用户信息失败"),

        USER_IMPORT_DATA_IS_NULL(Module.USER, 7, "导入的用户为空"),

        USER_PHONE_NUMBER_IS_NOT_UNIQUE(Module.USER, 8, "该电话号码已被其他用户占用"),

        USER_EMAIL_IS_NOT_UNIQUE(Module.USER, 9, "该邮件地址已被其他用户占用"),

        USER_PASSWORD_IS_NOT_CORRECT(Module.USER, 10, "用户密码错误"),

        USER_NEW_PASSWORD_IS_THE_SAME_AS_OLD(Module.USER, 11, "用户新密码与旧密码相同"),

        USER_UPLOAD_FILE_FAILED(Module.USER, 12, "用户上传文件失败"),

        USER_NAME_IS_NOT_UNIQUE(Module.USER, 13, "用户名已被其他用户占用"),

        USER_CURRENT_USER_CAN_NOT_BE_DELETE(Module.USER, 14, "当前用户不允许被删除"),

        ;


        private final int code;
        private final String msg;

        private static final int BASE_CODE = 40000;

        Business(Module module, int code, String msg) {
            this.code = BASE_CODE + module.code() + code;
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

    }


    /**
     * 30000~39999是外部错误码  比如调用支付失败
     */
    public enum External implements ErrorCodeInterface {

        /**
         * 支付宝调用失败
         */
        FAIL_TO_PAY_ON_ALIPAY(Module.COMMON, 1,"支付宝调用失败");


        private final int code;
        private final String msg;

        private static final int BASE_CODE = 30000;

        External(Module module, int code, String msg) {
            this.code = BASE_CODE + module.code() + code;
            this.msg = msg;
        }

        @Override
        public int code() {
            return this.code;
        }

        @Override
        public String message() { return this.msg; }


    }


    /**
     * 20000~29999是客户端错误码
     */
    public enum Client implements ErrorCodeInterface {


        /**
         * 客户端错误码
         */
        COMMON_FORBIDDEN_TO_CALL(Module.COMMON, 1,"禁止调用"),

        COMMON_REQUEST_TO_OFTEN(Module.COMMON, 2, "调用太过频繁"),

        COMMON_REQUEST_PARAMETERS_INVALID(Module.COMMON, 3, "请求参数异常，%s"),

        COMMON_REQUEST_METHOD_INVALID(Module.COMMON, 4, "请求方式不支持"),

        COMMON_NO_AUTHORIZATION(Module.PERMISSION, 1, "请求接口：%s 失败，用户未授权"),

        ;


        private final int code;
        private final String msg;

        private static final int BASE_CODE = 20000;

        Client(Module module, int code, String msg) {
            this.code = BASE_CODE + module.code() + code;
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

    }


    /**
     * 10000~19999是内部错误码  例如 框架有问题之类的
     */
    public enum Internal implements ErrorCodeInterface {

        /**
         * 内部错误码
         */
        INVALID_PARAMETER(Module.COMMON, 1,"参数异常"),

        UNKNOWN_ERROR(Module.COMMON, 2,"未知异常: %s"),

        GET_ENUM_FAILED(Module.COMMON, 3,"获取枚举类型失败, 枚举类:%s"),

        LOGIN_CAPTCHA_GENERATE_FAIL(Module.LOGIN, 1,"验证码生成失败"),

        INVALID_TOKEN(Module.PERMISSION, 1,"token异常"),

        DB_INTERNAL_ERROR(Module.DB, 1,  "数据库异常:%s"),

        ;


        private final int code;
        private final String msg;

        private static final int BASE_CODE = 10000;

        Internal(Module module, int code, String msg) {
            this.code = BASE_CODE + module.code() + code;
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

    }


}
