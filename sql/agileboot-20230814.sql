create table sys_config
(
    config_id       int auto_increment comment '参数主键'
        primary key,
    config_name     varchar(128)  default '' not null comment '配置名称',
    config_key      varchar(128)  default '' not null comment '配置键名',
    config_options  varchar(1024) default '' not null comment '可选的选项',
    config_value    varchar(256)  default '' not null comment '配置值',
    is_allow_change tinyint(1)               not null comment '是否允许修改',
    creator_id      bigint                   null comment '创建者ID',
    updater_id      bigint                   null comment '更新者ID',
    update_time     datetime                 null comment '更新时间',
    create_time     datetime                 null comment '创建时间',
    remark          varchar(128)             null comment '备注',
    deleted         tinyint(1)    default 0  not null comment '逻辑删除',
    constraint config_key_uniq_idx
        unique (config_key)
)
    comment '参数配置表';

INSERT INTO  sys_config (config_id, config_name, config_key, config_options, config_value, is_allow_change, creator_id, updater_id, update_time, create_time, remark, deleted) VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', '["skin-blue","skin-green","skin-purple","skin-red","skin-yellow"]', 'skin-blue', 1, null, null, '2022-08-28 22:12:19', '2022-05-21 08:30:55', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow', 0);
INSERT INTO  sys_config (config_id, config_name, config_key, config_options, config_value, is_allow_change, creator_id, updater_id, update_time, create_time, remark, deleted) VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '', '123456', 1, null, 1, '2023-07-20 14:42:08', '2022-05-21 08:30:55', '初始化密码 123456', 0);
INSERT INTO  sys_config (config_id, config_name, config_key, config_options, config_value, is_allow_change, creator_id, updater_id, update_time, create_time, remark, deleted) VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', '["theme-dark","theme-light"]', 'theme-dark', 1, null, null, '2022-08-28 22:12:15', '2022-08-20 08:30:55', '深色主题theme-dark，浅色主题theme-light', 0);
INSERT INTO  sys_config (config_id, config_name, config_key, config_options, config_value, is_allow_change, creator_id, updater_id, update_time, create_time, remark, deleted) VALUES (4, '账号自助-验证码开关', 'sys.account.captchaOnOff', '["true","false"]', 'false', 0, null, 1, '2023-07-20 14:39:36', '2022-05-21 08:30:55', '是否开启验证码功能（true开启，false关闭）', 0);
INSERT INTO  sys_config (config_id, config_name, config_key, config_options, config_value, is_allow_change, creator_id, updater_id, update_time, create_time, remark, deleted) VALUES (5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', '["true","false"]', 'true', 0, null, 1, '2022-10-05 22:18:57', '2022-05-21 08:30:55', '是否开启注册用户功能（true开启，false关闭）', 0);

create table sys_dept
(
    dept_id     bigint auto_increment comment '部门id'
        primary key,
    parent_id   bigint      default 0  not null comment '父部门id',
    ancestors   text                   not null comment '祖级列表',
    dept_name   varchar(64) default '' not null comment '部门名称',
    order_num   int         default 0  not null comment '显示顺序',
    leader_id   bigint                 null,
    leader_name varchar(64)            null comment '负责人',
    phone       varchar(16)            null comment '联系电话',
    email       varchar(128)           null comment '邮箱',
    status      smallint    default 0  not null comment '部门状态（0停用 1启用）',
    creator_id  bigint                 null comment '创建者ID',
    create_time datetime               null comment '创建时间',
    updater_id  bigint                 null comment '更新者ID',
    update_time datetime               null comment '更新时间',
    deleted     tinyint(1)  default 0  not null comment '逻辑删除'
)
    comment '部门表';

INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader_id, leader_name, phone, email, status, creator_id, create_time, updater_id, update_time, deleted) VALUES (1, 0, '0', 'AgileBoot科技', 0, null, 'valarchie', '15888888888', 'valarchie@163.com', 1, null, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader_id, leader_name, phone, email, status, creator_id, create_time, updater_id, update_time, deleted) VALUES (2, 1, '0,1', '深圳总公司', 1, null, 'valarchie', '15888888888', 'valarchie@163.com', 1, null, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader_id, leader_name, phone, email, status, creator_id, create_time, updater_id, update_time, deleted) VALUES (3, 1, '0,1', '长沙分公司', 2, null, 'valarchie', '15888888888', 'valarchie@163.com', 1, null, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader_id, leader_name, phone, email, status, creator_id, create_time, updater_id, update_time, deleted) VALUES (4, 2, '0,1,2', '研发部门', 1, null, 'valarchie', '15888888888', 'valarchie@163.com', 1, null, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader_id, leader_name, phone, email, status, creator_id, create_time, updater_id, update_time, deleted) VALUES (5, 2, '0,1,2', '市场部门', 2, null, 'valarchie', '15888888888', 'valarchie@163.com', 0, null, '2022-05-21 08:30:54', 1, '2023-07-20 22:46:41', 0);
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader_id, leader_name, phone, email, status, creator_id, create_time, updater_id, update_time, deleted) VALUES (6, 2, '0,1,2', '测试部门', 3, null, 'valarchie', '15888888888', 'valarchie@163.com', 1, null, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader_id, leader_name, phone, email, status, creator_id, create_time, updater_id, update_time, deleted) VALUES (7, 2, '0,1,2', '财务部门', 4, null, 'valarchie', '15888888888', 'valarchie@163.com', 1, null, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader_id, leader_name, phone, email, status, creator_id, create_time, updater_id, update_time, deleted) VALUES (8, 2, '0,1,2', '运维部门', 5, null, 'valarchie', '15888888888', 'valarchie@163.com', 1, null, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader_id, leader_name, phone, email, status, creator_id, create_time, updater_id, update_time, deleted) VALUES (9, 3, '0,1,3', '市场部!', 1, null, 'valarchie!!', '15888188888', 'valarc1hie@163.com', 0, null, '2022-05-21 08:30:54', 1, '2023-07-20 22:33:31', 0);
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader_id, leader_name, phone, email, status, creator_id, create_time, updater_id, update_time, deleted) VALUES (10, 3, '0,1,3', '财务部门', 2, null, 'valarchie', '15888888888', 'valarchie@163.com', 0, null, '2022-05-21 08:30:54', null, null, 0);

create table sys_login_info
(
    info_id          bigint auto_increment comment '访问ID'
        primary key,
    username         varchar(50)  default '' not null comment '用户账号',
    ip_address       varchar(128) default '' not null comment '登录IP地址',
    login_location   varchar(255) default '' not null comment '登录地点',
    browser          varchar(50)  default '' not null comment '浏览器类型',
    operation_system varchar(50)  default '' not null comment '操作系统',
    status           smallint     default 0  not null comment '登录状态（1成功 0失败）',
    msg              varchar(255) default '' not null comment '提示消息',
    login_time       datetime                null comment '访问时间',
    deleted          tinyint(1)   default 0  not null comment '逻辑删除'
)
    comment '系统访问记录';

INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (415, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-06-29 22:49:37', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (416, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-02 22:12:30', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (417, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 0, '验证码过期', '2023-07-02 22:16:06', 0);

create table sys_menu
(
    menu_id     bigint auto_increment comment '菜单ID'
        primary key,
    menu_name   varchar(64)                not null comment '菜单名称',
    menu_type   smallint      default 0    not null comment '菜单的类型(1为普通菜单2为目录3为内嵌iFrame4为外链跳转)',
    router_name varchar(255)  default ''   not null comment '路由名称（需保持和前端对应的vue文件中的name保持一致defineOptions方法中设置的name）',
    parent_id   bigint        default 0    not null comment '父菜单ID',
    path        varchar(255)               null comment '组件路径（对应前端项目view文件夹中的路径）',
    is_button   tinyint(1)    default 0    not null comment '是否按钮',
    permission  varchar(128)               null comment '权限标识',
    meta_info   varchar(1024) default '{}' not null comment '路由元信息（前端根据这个信息进行逻辑处理）',
    status      smallint      default 0    not null comment '菜单状态（1启用 0停用）',
    remark      varchar(256)  default ''   null comment '备注',
    creator_id  bigint                     null comment '创建者ID',
    create_time datetime                   null comment '创建时间',
    updater_id  bigint                     null comment '更新者ID',
    update_time datetime                   null comment '更新时间',
    deleted     tinyint(1)    default 0    not null comment '逻辑删除'
)
    comment '菜单权限表';

INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (1, '系统管理', 2, '', 0, '/system', 0, '', '{"title":"系统管理","icon":"ep:management","showParent":true,"rank":1}', 1, '系统管理目录', 0, '2022-05-21 08:30:54', 1, '2023-08-14 23:08:50', 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (2, '系统监控', 2, '', 0, '/monitor', 0, '', '{"title":"系统监控","icon":"ep:monitor","showParent":true,"rank":3}', 1, '系统监控目录', 0, '2022-05-21 08:30:54', 1, '2023-08-14 23:09:15', 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (3, '系统工具', 2, '', 0, '/tool', 0, '', '{"title":"系统工具","icon":"ep:tools","showParent":true,"rank":2}', 1, '系统工具目录', 0, '2022-05-21 08:30:54', 1, '2023-08-14 23:09:03', 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (4, 'AgileBoot官网', 3, 'AgileBootguanwangIframeRouter', 0, '/AgileBootguanwangIframeLink', 0, '', '{"title":"AgileBoot官网","icon":"ep:link","showParent":true,"frameSrc":"https://element-plus.org/zh-CN/","rank":8}', 1, 'Agileboot官网地址', 0, '2022-05-21 08:30:54', 1, '2023-08-14 23:09:40', 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (5, '用户管理', 1, 'SystemUser', 1, '/system/user/index', 0, 'system:user:list', '{"title":"用户管理","icon":"ep:user-filled","showParent":true}', 1, '用户管理菜单', 0, '2022-05-21 08:30:54', 1, '2023-08-14 23:16:13', 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (6, '角色管理', 1, 'SystemRole', 1, '/system/role/index', 0, 'system:role:list', '{"title":"角色管理","icon":"ep:user","showParent":true}', 1, '角色管理菜单', 0, '2022-05-21 08:30:54', 1, '2023-08-14 23:16:23', 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (7, '菜单管理', 1, 'MenuManagement', 1, '/system/menu/index', 0, 'system:menu:list', '{"title":"菜单管理","icon":"ep:menu","showParent":true}', 1, '菜单管理菜单', 0, '2022-05-21 08:30:54', 1, '2023-08-14 23:15:41', 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (8, '部门管理', 1, 'Department', 1, '/system/dept/index', 0, 'system:dept:list', '{"title":"部门管理","icon":"fa-solid:code-branch","showParent":true}', 1, '部门管理菜单', 0, '2022-05-21 08:30:54', 1, '2023-08-14 23:15:35', 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (9, '岗位管理', 1, 'Post', 1, '/system/post/index', 0, 'system:post:list', '{"title":"岗位管理","icon":"ep:postcard","showParent":true}', 1, '岗位管理菜单', 0, '2022-05-21 08:30:54', 1, '2023-08-14 23:15:11', 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (10, '参数设置', 1, 'Config', 1, '/system/config/index', 0, 'system:config:list', '{"title":"参数设置","icon":"ep:setting","showParent":true}', 1, '参数设置菜单', 0, '2022-05-21 08:30:54', 1, '2023-08-14 23:15:03', 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (11, '通知公告', 1, 'SystemNotice', 1, '/system/notice/index', 0, 'system:notice:list', '{"title":"通知公告","icon":"ep:notification","showParent":true}', 1, '通知公告菜单', 0, '2022-05-21 08:30:54', 1, '2023-08-14 23:14:56', 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (12, '日志管理', 1, 'LogManagement', 1, '/system/logd', 0, '', '{"title":"日志管理","icon":"ep:document","showParent":true}', 1, '日志管理菜单', 0, '2022-05-21 08:30:54', 1, '2023-08-14 23:14:47', 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (13, '在线用户', 1, 'OnlineUser', 2, '/system/monitor/onlineUser/index', 0, 'monitor:online:list', '{"title":"在线用户","icon":"fa-solid:users","showParent":true}', 1, '在线用户菜单', 0, '2022-05-21 08:30:54', 1, '2023-08-14 23:13:13', 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (14, '数据监控', 1, 'DataMonitor', 2, '/system/monitor/druid/index', 0, 'monitor:druid:list', '{"title":"数据监控","icon":"fa:database","showParent":true,"frameSrc":"/druid/login.html","isFrameSrcInternal":true}', 1, '数据监控菜单', 0, '2022-05-21 08:30:54', 1, '2023-08-14 23:13:25', 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (15, '服务监控', 1, 'ServerInfo', 2, '/system/monitor/server/index', 0, 'monitor:server:list', '{"title":"服务监控","icon":"fa:server","showParent":true}', 1, '服务监控菜单', 0, '2022-05-21 08:30:54', 1, '2023-08-14 23:13:34', 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (16, '缓存监控', 1, 'CacheInfo', 2, '/system/monitor/cache/index', 0, 'monitor:cache:list', '{"title":"缓存监控","icon":"ep:reading","showParent":true}', 1, '缓存监控菜单', 0, '2022-05-21 08:30:54', 1, '2023-08-14 23:12:59', 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (17, '系统接口', 1, 'SystemAPI', 3, '/tool/swagger/index', 0, 'tool:swagger:list', '{"title":"系统接口","icon":"ep:document-remove","showParent":true,"frameSrc":"/swagger-ui/index.html","isFrameSrcInternal":true}', 1, '系统接口菜单', 0, '2022-05-21 08:30:54', 1, '2023-08-14 23:14:01', 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (18, '操作日志', 1, 'OperationLog', 12, '/system/log/operationLog/index', 0, 'monitor:operlog:list', '{"title":"操作日志"}', 1, '操作日志菜单', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (19, '登录日志', 1, 'LoginLog', 12, '/system/log/loginLog/index', 0, 'monitor:logininfor:list', '{"title":"登录日志"}', 1, '登录日志菜单', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (20, '用户查询', 0, ' ', 5, '', 1, 'system:user:query', '{"title":"用户查询"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (21, '用户新增', 0, ' ', 5, '', 1, 'system:user:add', '{"title":"用户新增"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (22, '用户修改', 0, ' ', 5, '', 1, 'system:user:edit', '{"title":"用户修改"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (23, '用户删除', 0, ' ', 5, '', 1, 'system:user:remove', '{"title":"用户删除"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (24, '用户导出', 0, ' ', 5, '', 1, 'system:user:export', '{"title":"用户导出"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (25, '用户导入', 0, ' ', 5, '', 1, 'system:user:import', '{"title":"用户导入"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (26, '重置密码', 0, ' ', 5, '', 1, 'system:user:resetPwd', '{"title":"重置密码"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (27, '角色查询', 0, ' ', 6, '', 1, 'system:role:query', '{"title":"角色查询"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (28, '角色新增', 0, ' ', 6, '', 1, 'system:role:add', '{"title":"角色新增"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (29, '角色修改', 0, ' ', 6, '', 1, 'system:role:edit', '{"title":"角色修改"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (30, '角色删除', 0, ' ', 6, '', 1, 'system:role:remove', '{"title":"角色删除"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (31, '角色导出', 0, ' ', 6, '', 1, 'system:role:export', '{"title":"角色导出"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (32, '菜单查询', 0, ' ', 7, '', 1, 'system:menu:query', '{"title":"菜单查询"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (33, '菜单新增', 0, ' ', 7, '', 1, 'system:menu:add', '{"title":"菜单新增"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (34, '菜单修改', 0, ' ', 7, '', 1, 'system:menu:edit', '{"title":"菜单修改"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (35, '菜单删除', 0, ' ', 7, '', 1, 'system:menu:remove', '{"title":"菜单删除"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (36, '部门查询', 0, ' ', 8, '', 1, 'system:dept:query', '{"title":"部门查询"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (37, '部门新增', 0, ' ', 8, '', 1, 'system:dept:add', '{"title":"部门新增"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (38, '部门修改', 0, ' ', 8, '', 1, 'system:dept:edit', '{"title":"部门修改"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (39, '部门删除', 0, ' ', 8, '', 1, 'system:dept:remove', '{"title":"部门删除"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (40, '岗位查询', 0, ' ', 9, '', 1, 'system:post:query', '{"title":"岗位查询"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (41, '岗位新增', 0, ' ', 9, '', 1, 'system:post:add', '{"title":"岗位新增"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (42, '岗位修改', 0, ' ', 9, '', 1, 'system:post:edit', '{"title":"岗位修改"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (43, '岗位删除', 0, ' ', 9, '', 1, 'system:post:remove', '{"title":"岗位删除"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (44, '岗位导出', 0, ' ', 9, '', 1, 'system:post:export', '{"title":"岗位导出"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (45, '参数查询', 0, ' ', 10, '', 1, 'system:config:query', '{"title":"参数查询"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (46, '参数新增', 0, ' ', 10, '', 1, 'system:config:add', '{"title":"参数新增"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (47, '参数修改', 0, ' ', 10, '', 1, 'system:config:edit', '{"title":"参数修改"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (48, '参数删除', 0, ' ', 10, '', 1, 'system:config:remove', '{"title":"参数删除"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (49, '参数导出', 0, ' ', 10, '', 1, 'system:config:export', '{"title":"参数导出"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (50, '公告查询', 0, ' ', 11, '', 1, 'system:notice:query', '{"title":"公告查询"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (51, '公告新增', 0, ' ', 11, '', 1, 'system:notice:add', '{"title":"公告新增"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (52, '公告修改', 0, ' ', 11, '', 1, 'system:notice:edit', '{"title":"公告修改"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (53, '公告删除', 0, ' ', 11, '', 1, 'system:notice:remove', '{"title":"公告删除"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (54, '操作查询', 0, ' ', 18, '', 1, 'monitor:operlog:query', '{"title":"操作查询"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (55, '操作删除', 0, ' ', 18, '', 1, 'monitor:operlog:remove', '{"title":"操作删除"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (56, '日志导出', 0, ' ', 18, '', 1, 'monitor:operlog:export', '{"title":"日志导出"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (57, '登录查询', 0, ' ', 19, '', 1, 'monitor:logininfor:query', '{"title":"登录查询"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (58, '登录删除', 0, ' ', 19, '', 1, 'monitor:logininfor:remove', '{"title":"登录删除"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (59, '日志导出', 0, ' ', 19, '', 1, 'monitor:logininfor:export', '{"title":"日志导出","rank":22}', 1, '', 0, '2022-05-21 08:30:54', 1, '2023-07-22 17:02:28', 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (60, '在线查询', 0, ' ', 13, '', 1, 'monitor:online:query', '{"title":"在线查询"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (61, '批量强退', 0, ' ', 13, '', 1, 'monitor:online:batchLogout', '{"title":"批量强退"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (62, '单条强退', 0, ' ', 13, '', 1, 'monitor:online:forceLogout', '{"title":"单条强退"}', 1, '', 0, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (63, 'AgileBoot Github地址', 4, 'https://github.com/valarchie/AgileBoot-Back-End', 0, '/external', 0, '', '{"title":"AgileBoot Github地址","icon":"fa-solid:external-link-alt","showParent":true,"rank":9}', 1, 'Agileboot github地址', 0, '2022-05-21 08:30:54', 1, '2023-08-14 23:12:13', 0);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (64, '首页', 2, '', 0, '/global', 0, '121212', '{"title":"首页","showParent":true,"rank":3}', 1, '', 1, '2023-07-24 22:36:03', 1, '2023-07-24 22:38:37', 1);
INSERT INTO  sys_menu (menu_id, menu_name, menu_type, router_name, parent_id, path, is_button, permission, meta_info, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (65, '个人中心', 1, 'PersonalCenter', 2053, '/system/user/profile', 0, '434sdf', '{"title":"个人中心","showParent":true,"rank":3}', 1, '', 1, '2023-07-24 22:36:55', null, null, 1);

create table sys_notice
(
    notice_id      int auto_increment comment '公告ID'
        primary key,
    notice_title   varchar(64)             not null comment '公告标题',
    notice_type    smallint                not null comment '公告类型（1通知 2公告）',
    notice_content text                    null comment '公告内容',
    status         smallint     default 0  not null comment '公告状态（1正常 0关闭）',
    creator_id     bigint                  not null comment '创建者ID',
    create_time    datetime                null comment '创建时间',
    updater_id     bigint                  null comment '更新者ID',
    update_time    datetime                null comment '更新时间',
    remark         varchar(255) default '' not null comment '备注',
    deleted        tinyint(1)   default 0  not null comment '逻辑删除'
)
    comment '通知公告表';

INSERT INTO  sys_notice (notice_id, notice_title, notice_type, notice_content, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (1, '温馨提醒：2018-07-01 AgileBoot新版本发布啦', 2, '新版本内容~~~~~~~~~~', 1, 1, '2022-05-21 08:30:55', 1, '2022-08-29 20:12:37', '管理员', 0);
INSERT INTO  sys_notice (notice_id, notice_title, notice_type, notice_content, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (2, '维护通知：2018-07-01 AgileBoot系统凌晨维护', 1, '维护内容', 1, 1, '2022-05-21 08:30:55', null, null, '管理员', 0);

create table sys_operation_log
(
    operation_id      bigint auto_increment comment '日志主键'
        primary key,
    business_type     smallint      default 0  not null comment '业务类型（0其它 1新增 2修改 3删除）',
    request_method    smallint      default 0  not null comment '请求方式',
    request_module    varchar(64)   default '' not null comment '请求模块',
    request_url       varchar(256)  default '' not null comment '请求URL',
    called_method     varchar(128)  default '' not null comment '调用方法',
    operator_type     smallint      default 0  not null comment '操作类别（0其它 1后台用户 2手机端用户）',
    user_id           bigint        default 0  null comment '用户ID',
    username          varchar(32)   default '' null comment '操作人员',
    operator_ip       varchar(128)  default '' null comment '操作人员ip',
    operator_location varchar(256)  default '' null comment '操作地点',
    dept_id           bigint        default 0  null comment '部门ID',
    dept_name         varchar(64)              null comment '部门名称',
    operation_param   varchar(2048) default '' null comment '请求参数',
    operation_result  varchar(2048) default '' null comment '返回参数',
    status            smallint      default 1  not null comment '操作状态（1正常 0异常）',
    error_stack       varchar(2048) default '' null comment '错误消息',
    operation_time    datetime                 not null comment '操作时间',
    deleted           tinyint(1)    default 0  not null comment '逻辑删除'
)
    comment '操作日志记录';

INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (561, 1, 2, '菜单管理', '/system/menus', 'com.agileboot.admin.controller.system.SysMenuController.add()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{"menuName":"","permission":"","parentId":2035,"path":"","isButton":false,"routerName":"","meta":{"showParent":true,"rank":0},"status":1},', '', 1, '', '2023-07-22 17:06:57', 0);

create table sys_post
(
    post_id     bigint auto_increment comment '岗位ID'
        primary key,
    post_code   varchar(64)          not null comment '岗位编码',
    post_name   varchar(64)          not null comment '岗位名称',
    post_sort   int                  not null comment '显示顺序',
    status      smallint             not null comment '状态（1正常 0停用）',
    remark      varchar(512)         null comment '备注',
    creator_id  bigint               null,
    create_time datetime             null comment '创建时间',
    updater_id  bigint               null,
    update_time datetime             null comment '更新时间',
    deleted     tinyint(1) default 0 not null comment '逻辑删除'
)
    comment '岗位信息表';

INSERT INTO  sys_post (post_id, post_code, post_name, post_sort, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (1, 'ceo', '董事长', 1, 1, '', null, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_post (post_id, post_code, post_name, post_sort, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (2, 'se', '项目经理', 2, 1, '', null, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_post (post_id, post_code, post_name, post_sort, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (3, 'hr', '人力资源', 3, 1, '', null, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_post (post_id, post_code, post_name, post_sort, status, remark, creator_id, create_time, updater_id, update_time, deleted) VALUES (4, 'user', '普通员工', 5, 0, '', null, '2022-05-21 08:30:54', null, null, 0);

create table sys_role
(
    role_id     bigint auto_increment comment '角色ID'
        primary key,
    role_name   varchar(32)              not null comment '角色名称',
    role_key    varchar(128)             not null comment '角色权限字符串',
    role_sort   int                      not null comment '显示顺序',
    data_scope  smallint      default 1  null comment '数据范围（1：全部数据权限 2：自定数据权限 3: 本部门数据权限 4: 本部门及以下数据权限 5: 本人权限）',
    dept_id_set varchar(1024) default '' null comment '角色所拥有的部门数据权限',
    status      smallint                 not null comment '角色状态（1正常 0停用）',
    creator_id  bigint                   null comment '创建者ID',
    create_time datetime                 null comment '创建时间',
    updater_id  bigint                   null comment '更新者ID',
    update_time datetime                 null comment '更新时间',
    remark      varchar(512)             null comment '备注',
    deleted     tinyint(1)    default 0  not null comment '删除标志（0代表存在 1代表删除）'
)
    comment '角色信息表';

INSERT INTO  sys_role (role_id, role_name, role_key, role_sort, data_scope, dept_id_set, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (1, '超级管理员', 'admin', 1, 1, '', 1, null, '2022-05-21 08:30:54', null, null, '超级管理员', 0);
INSERT INTO  sys_role (role_id, role_name, role_key, role_sort, data_scope, dept_id_set, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (2, '普通角色', 'common', 3, 2, '', 1, null, '2022-05-21 08:30:54', null, null, '普通角色', 0);
INSERT INTO  sys_role (role_id, role_name, role_key, role_sort, data_scope, dept_id_set, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (3, '闲置角色', 'unused', 4, 2, '', 0, null, '2022-05-21 08:30:54', null, null, '未使用的角色', 0);

create table sys_role_menu
(
    role_id bigint not null comment '角色ID',
    menu_id bigint not null comment '菜单ID',
    primary key (role_id, menu_id)
)
    comment '角色和菜单关联表';

INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 3);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 4);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 5);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 6);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 7);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 8);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 9);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 10);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 11);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 12);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 13);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 14);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 15);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 16);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 17);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 18);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 19);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 20);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 21);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 22);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 23);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 24);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 25);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 26);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 27);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 28);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 29);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 30);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 31);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 32);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 33);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 34);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 35);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 36);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 37);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 38);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 39);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 40);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 41);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 42);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 43);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 44);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 45);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 46);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 47);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 48);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 49);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 50);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 51);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 52);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 53);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 54);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 55);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 56);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 57);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 58);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 59);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 60);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 61);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (3, 1);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (111, 1);

create table sys_user
(
    user_id      bigint auto_increment comment '用户ID'
        primary key,
    post_id      bigint                  null comment '职位id',
    role_id      bigint                  null comment '角色id',
    dept_id      bigint                  null comment '部门ID',
    username     varchar(64)             not null comment '用户账号',
    nickname     varchar(32)             not null comment '用户昵称',
    user_type    smallint     default 0  null comment '用户类型（00系统用户）',
    email        varchar(128) default '' null comment '用户邮箱',
    phone_number varchar(18)  default '' null comment '手机号码',
    sex          smallint     default 0  null comment '用户性别（0男 1女 2未知）',
    avatar       varchar(512) default '' null comment '头像地址',
    password     varchar(128) default '' not null comment '密码',
    status       smallint     default 0  not null comment '帐号状态（1正常 2停用 3冻结）',
    login_ip     varchar(128) default '' null comment '最后登录IP',
    login_date   datetime                null comment '最后登录时间',
    is_admin     tinyint(1)   default 0  not null comment '超级管理员标志（1是，0否）',
    creator_id   bigint                  null comment '更新者ID',
    create_time  datetime                null comment '创建时间',
    updater_id   bigint                  null comment '更新者ID',
    update_time  datetime                null comment '更新时间',
    remark       varchar(512)            null comment '备注',
    deleted      tinyint(1)   default 0  not null comment '删除标志（0代表存在 1代表删除）'
)
    comment '用户信息表';

INSERT INTO  sys_user (user_id, post_id, role_id, dept_id, username, nickname, user_type, email, phone_number, sex, avatar, password, status, login_ip, login_date, is_admin, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (1, 1, 1, 4, 'admin', 'valarchie1', 0, 'agileboot@163.com', '15888888883', 0, '/profile/avatar/20230725164110_blob_6b7a989b1cdd4dd396665d2cfd2addc5.png', '$2a$10$o55UFZAtyWnDpRV6dvQe8.c/MjlFacC49ASj2usNXm9BY74SYI/uG', 1, '127.0.0.1', '2023-08-14 23:07:03', 1, null, '2022-05-21 08:30:54', 1, '2023-08-14 23:07:03', '管理员', 0);
INSERT INTO  sys_user (user_id, post_id, role_id, dept_id, username, nickname, user_type, email, phone_number, sex, avatar, password, status, login_ip, login_date, is_admin, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (2, 2, 2, 5, 'ag1', 'valarchie2', 0, 'agileboot1@qq.com', '15666666666', 1, '/profile/avatar/20230725114818_avatar_b5bf400732bb43369b4df58802049b22.png', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', 1, '127.0.0.1', '2022-05-21 08:30:54', 0, null, '2022-05-21 08:30:54', null, null, '测试员1', 0);
INSERT INTO  sys_user (user_id, post_id, role_id, dept_id, username, nickname, user_type, email, phone_number, sex, avatar, password, status, login_ip, login_date, is_admin, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (3, 2, 0, 5, 'ag2', 'valarchie3', 0, 'agileboot2@qq.com', '15666666667', 1, '/profile/avatar/20230725114818_avatar_b5bf400732bb43369b4df58802049b22.png', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', 1, '127.0.0.1', '2022-05-21 08:30:54', 0, null, '2022-05-21 08:30:54', null, null, '测试员2', 0);

