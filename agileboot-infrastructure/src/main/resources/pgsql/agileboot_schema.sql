-- 创建表 sys_config 参数配置表
CREATE TABLE app.sys_config (
            config_id serial8 PRIMARY KEY NOT NULL, -- 参数主键
            config_name VARCHAR(128) NOT NULL DEFAULT '', -- 配置名称
            config_key VARCHAR(128) NOT NULL, -- 配置键名
            config_options VARCHAR(1024) NOT NULL DEFAULT '', -- 可选的选项
            config_value VARCHAR(256) NOT NULL DEFAULT '', -- 配置值
            is_allow_change bool NOT NULL, -- 是否允许修改
            creator_id int8, -- 创建者ID
            updater_id int8, -- 更新者ID
            update_time TIMESTAMPTZ, -- 更新时间
            create_time TIMESTAMPTZ, -- 创建时间
            remark VARCHAR(128), -- 备注
            deleted int2 DEFAULT 0 NOT NULL -- 逻辑删除
);

-- 创建表 sys_dept 部门表
CREATE TABLE  app.sys_dept (
           dept_id serial8 PRIMARY KEY NOT NULL, -- 部门id
           parent_id int8 NOT NULL DEFAULT 0, -- 父部门id
           ancestors TEXT NOT NULL, -- 祖级列表
           dept_name VARCHAR(64) NOT NULL DEFAULT '', -- 部门名称
           order_num INT NOT NULL DEFAULT 0, -- 显示顺序
           leader_id int8, -- 负责人
           leader_name VARCHAR(64), -- 负责人姓名
           phone VARCHAR(16), -- 联系电话
           email VARCHAR(128), -- 邮箱
           status SMALLINT NOT NULL DEFAULT 0, -- 部门状态（0停用 1启用）
           creator_id int8, -- 创建者ID
           create_time TIMESTAMPTZ, -- 创建时间
           updater_id int8, -- 更新者ID
           update_time TIMESTAMPTZ, -- 更新时间
           deleted int2 DEFAULT 0 NOT NULL -- 逻辑删除
);

-- 创建表 sys_login_info 系统访问记录
CREATE TABLE app.sys_login_info (
            info_id serial8  PRIMARY KEY NOT NULL, -- 访问ID
            username VARCHAR(50) NOT NULL DEFAULT '', -- 用户账号
            ip_address VARCHAR(128) NOT NULL DEFAULT '', -- 登录IP地址
            login_location VARCHAR(255) NOT NULL DEFAULT '', -- 登录地点
            browser VARCHAR(50) NOT NULL DEFAULT '', -- 浏览器类型
            operation_system VARCHAR(50) NOT NULL DEFAULT '', -- 操作系统
            status int2 NOT NULL DEFAULT 0, -- 登录状态（1成功 0失败）
            msg VARCHAR(255) NOT NULL DEFAULT '', -- 提示消息
            login_time TIMESTAMPTZ, -- 访问时间
            deleted int2 DEFAULT 0 NOT NULL -- 逻辑删除
);


-- 创建表 sys_notice 通知公告表
CREATE TABLE app.sys_notice (
            notice_id serial8 PRIMARY KEY NOT NULL, -- 公告ID
            notice_title VARCHAR(64) NOT NULL, -- 公告标题
            notice_type int2 NOT NULL, -- 公告类型（1通知 2公告）
            notice_content TEXT, -- 公告内容
            status int2 NOT NULL DEFAULT 0, -- 公告状态（1正常 0关闭）
            creator_id int8 NOT NULL, -- 创建者ID
            create_time TIMESTAMPTZ, -- 创建时间
            updater_id int8, -- 更新者ID
            update_time TIMESTAMPTZ, -- 更新时间
            remark VARCHAR(255) NOT NULL DEFAULT '', -- 备注
            deleted int2 DEFAULT 0 NOT NULL -- 逻辑删除
);

-- 创建表 sys_operation_log 操作日志记录
CREATE TABLE  app.sys_operation_log (
            operation_id serial8 PRIMARY KEY NOT NULL, -- 日志主键
            business_type int2 NOT NULL DEFAULT 0, -- 业务类型（0其它 1新增 2修改 3删除）
            request_method int2 NOT NULL DEFAULT 0, -- 请求方式
            request_module VARCHAR(64) NOT NULL DEFAULT '', -- 请求模块
            request_url VARCHAR(256) NOT NULL DEFAULT '', -- 请求URL
            called_method VARCHAR(128) NOT NULL DEFAULT '', -- 调用方法
            operator_type int2 NOT NULL DEFAULT 0, -- 操作类别（0其它 1后台用户 2手机端用户）
            user_id int8, -- 用户ID
            username VARCHAR(32), -- 操作人员
            operator_ip VARCHAR(128), -- 操作人员ip
            operator_location VARCHAR(256), -- 操作地点
            dept_id int8, -- 部门ID
            dept_name VARCHAR(64), -- 部门名称
            operation_param VARCHAR(2048), -- 请求参数
            operation_result VARCHAR(2048), -- 返回参数
            status int2 NOT NULL DEFAULT 1, -- 操作状态（1正常 0异常）
            error_stack VARCHAR(2048), -- 错误消息
            operation_time TIMESTAMPTZ NOT NULL, -- 操作时间
            deleted int2 DEFAULT 0 NOT NULL -- 逻辑删除
);

-- 创建表 sys_post 岗位信息表
CREATE TABLE  app.sys_post (
           post_id serial8  PRIMARY KEY NOT NULL, -- 岗位ID
           post_code VARCHAR(64) NOT NULL, -- 岗位编码
           post_name VARCHAR(64) NOT NULL, -- 岗位名称
           post_sort INT NOT NULL, -- 显示顺序
           status int2 NOT NULL, -- 状态（1正常 0停用）
           remark VARCHAR(512), -- 备注
           creator_id int8, -- 创建者ID
           create_time TIMESTAMPTZ, -- 创建时间
           updater_id int8, -- 更新者ID
           update_time TIMESTAMPTZ, -- 更新时间
           deleted int2 DEFAULT 0 NOT NULL -- 逻辑删除
);


-- 创建表 sys_menu 菜单权限表
CREATE TABLE  app.sys_menu (
           menu_id serial8 PRIMARY KEY NOT NULL, -- 菜单ID
           menu_name VARCHAR(64) NOT NULL, -- 菜单名称
           menu_type int2 NOT NULL DEFAULT 0, -- 菜单的类型(1为普通菜单2为目录3为内嵌iFrame4为外链跳转)
           router_name VARCHAR(255) NOT NULL DEFAULT '', -- 路由名称
           parent_id int8 NOT NULL DEFAULT 0, -- 父菜单ID
           path VARCHAR(255), -- 组件路径
           is_button bool NOT NULL DEFAULT false, -- 是否按钮
           permission VARCHAR(128), -- 权限标识
           meta_info VARCHAR(1024) NOT NULL DEFAULT '{}', -- 路由元信息
           status int2 NOT NULL DEFAULT 0, -- 菜单状态（1启用 0停用）
           remark VARCHAR(256), -- 备注
           creator_id int8, -- 创建者ID
           create_time TIMESTAMPTZ, -- 创建时间
           updater_id int8, -- 更新者ID
           update_time TIMESTAMPTZ, -- 更新时间
           deleted int2 DEFAULT 0 NOT NULL -- 逻辑删除
);

-- 创建表 sys_role 角色信息表
CREATE TABLE  app.sys_role (
           role_id serial8 PRIMARY KEY NOT NULL, -- 角色ID
           role_name VARCHAR(32) NOT NULL, -- 角色名称
           role_key VARCHAR(128) NOT NULL, -- 角色权限字符串
           role_sort INT NOT NULL, -- 显示顺序
           data_scope int2, -- 数据范围
           dept_id_set VARCHAR(1024) DEFAULT '', -- 角色所拥有的部门数据权限
           status int2 NOT NULL, -- 角色状态（1正常 0停用）
           creator_id int8, -- 创建者ID
           create_time TIMESTAMPTZ, -- 创建时间
           updater_id int8, -- 更新者ID
           update_time TIMESTAMPTZ, -- 更新时间
           remark VARCHAR(512), -- 备注
           deleted int2 DEFAULT 0 NOT NULL -- 删除标志（0代表存在 1代表删除）
);

-- 创建表 sys_role_menu 角色和菜单关联表
CREATE TABLE  app.sys_role_menu (
        role_id int8 NOT NULL, -- 角色ID
        menu_id int8 NOT NULL, -- 菜单ID
        PRIMARY KEY (role_id, menu_id) -- 设置复合主键
);


-- 创建表 sys_user 用户信息表
CREATE TABLE  app.sys_user (
       user_id serial8 PRIMARY KEY NOT NULL, -- 用户ID
       post_id int8, -- 职位id
       role_id int8, -- 角色id
       dept_id int8, -- 部门ID
       username VARCHAR(64) NOT NULL, -- 用户账号
       nickname VARCHAR(32) NOT NULL, -- 用户昵称
       user_type int2 DEFAULT 0, -- 用户类型（00系统用户）
       email VARCHAR(128), -- 用户邮箱
       phone_number VARCHAR(18), -- 手机号码
       sex int2, -- 用户性别（0男 1女 2未知）
       avatar VARCHAR(512), -- 头像地址
       password VARCHAR(128) NOT NULL, -- 密码
       status int2 NOT NULL, -- 帐号状态（1正常 2停用 3冻结）
       login_ip VARCHAR(128), -- 最后登录IP
       login_date TIMESTAMPTZ, -- 最后登录时间
       is_admin bool DEFAULT false NOT NULL,  -- 超级管理员标志（1是，0否）
       creator_id int8, -- 更新者ID
       create_time TIMESTAMPTZ, -- 创建时间
       updater_id int8, -- 更新者ID
       update_time TIMESTAMPTZ, -- 更新时间
       remark VARCHAR(512), -- 备注
       deleted int2 DEFAULT 0 NOT NULL -- 删除标志（0代表存在 1代表删除）
);
