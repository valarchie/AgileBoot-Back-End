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
INSERT INTO  sys_config (config_id, config_name, config_key, config_options, config_value, is_allow_change, creator_id, updater_id, update_time, create_time, remark, deleted) VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '', '1234567', 1, null, null, '2022-08-28 21:54:19', '2022-05-21 08:30:55', '初始化密码 123456', 0);
INSERT INTO  sys_config (config_id, config_name, config_key, config_options, config_value, is_allow_change, creator_id, updater_id, update_time, create_time, remark, deleted) VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', '["theme-dark","theme-light"]', 'theme-dark', 1, null, null, '2022-08-28 22:12:15', '2022-08-20 08:30:55', '深色主题theme-dark，浅色主题theme-light', 0);
INSERT INTO  sys_config (config_id, config_name, config_key, config_options, config_value, is_allow_change, creator_id, updater_id, update_time, create_time, remark, deleted) VALUES (4, '账号自助-验证码开关', 'sys.account.captchaOnOff', '["true","false"]', 'false', 0, null, null, '2022-08-28 22:03:37', '2022-05-21 08:30:55', '是否开启验证码功能（true开启，false关闭）', 0);
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
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader_id, leader_name, phone, email, status, creator_id, create_time, updater_id, update_time, deleted) VALUES (5, 2, '0,1,2', '市场部门', 2, null, 'valarchie', '15888888888', 'valarchie@163.com', 1, null, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader_id, leader_name, phone, email, status, creator_id, create_time, updater_id, update_time, deleted) VALUES (6, 2, '0,1,2', '测试部门', 3, null, 'valarchie', '15888888888', 'valarchie@163.com', 1, null, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader_id, leader_name, phone, email, status, creator_id, create_time, updater_id, update_time, deleted) VALUES (7, 2, '0,1,2', '财务部门', 4, null, 'valarchie', '15888888888', 'valarchie@163.com', 1, null, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader_id, leader_name, phone, email, status, creator_id, create_time, updater_id, update_time, deleted) VALUES (8, 2, '0,1,2', '运维部门', 5, null, 'valarchie', '15888888888', 'valarchie@163.com', 1, null, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader_id, leader_name, phone, email, status, creator_id, create_time, updater_id, update_time, deleted) VALUES (9, 3, '0,1,3', '市场部门', 1, null, 'valarchie', '15888888888', 'valarchie@163.com', 1, null, '2022-05-21 08:30:54', null, null, 0);
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

create table sys_menu
(
    menu_id     bigint auto_increment comment '菜单ID'
        primary key,
    menu_name   varchar(64)                not null comment '菜单名称',
    route_name  varchar(255)  default ''   not null comment '路由名称（前端逻辑需要使用）',
    parent_id   bigint        default 0    not null comment '父菜单ID',
    path        varchar(255)  default ''   null comment '路由地址',
    component   varchar(255)               null comment '组件路径',
    `rank`      int           default 0    not null comment '显示顺序',
    query       varchar(255)               null comment '路由参数',
    is_button   tinyint       default 0    not null comment '是否按钮',
    permission  varchar(128)               null comment '权限标识',
    meta_info   varchar(1024) default '{}' not null comment '路由元信息主要由前端处理',
    status      smallint      default 0    not null comment '菜单状态（1启用 0停用）',
    creator_id  bigint                     null comment '创建者ID',
    create_time datetime                   null comment '创建时间',
    updater_id  bigint                     null comment '更新者ID',
    update_time datetime                   null comment '更新时间',
    remark      varchar(256)  default ''   null comment '备注',
    deleted     tinyint(1)    default 0    not null comment '逻辑删除'
)
    comment '菜单权限表';

INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (1, '系统管理', ' ', 0, '/system', null, 1, '', 0, '', '{"title":"系统管理"}', 1, 0, '2022-05-21 08:30:54', null, null, '系统管理目录', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (2, '系统监控', ' ', 0, '/monitor', null, 2, '', 0, '', '{"title":"系统监控"}', 1, 0, '2022-05-21 08:30:54', null, null, '系统监控目录', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (3, '系统工具', ' ', 0, '/tool', null, 3, '', 0, '', '{"title":"系统工具"}', 1, 0, '2022-05-21 08:30:54', null, null, '系统工具目录', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (4, 'AgileBoot官网', ' ', 0, 'https://juejin.cn/column/7159946528827080734', null, 4, '', 0, '', '{"title":"AgileBoot官网"}', 1, 0, '2022-05-21 08:30:54', null, null, 'Agileboot官网地址', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (5, '用户管理', ' ', 1, 'user', 'system/user/index', 1, '', 0, 'system:user:list', '{"title":"用户管理"}', 1, 0, '2022-05-21 08:30:54', null, null, '用户管理菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (6, '角色管理', ' ', 1, 'role', 'system/role/index', 2, '', 0, 'system:role:list', '{"title":"角色管理"}', 1, 0, '2022-05-21 08:30:54', null, null, '角色管理菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (7, '菜单管理', ' ', 1, 'menu', 'system/menu/index', 3, '', 0, 'system:menu:list', '{"title":"菜单管理"}', 1, 0, '2022-05-21 08:30:54', null, null, '菜单管理菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (8, '部门管理', ' ', 1, 'dept', 'system/dept/index', 4, '', 0, 'system:dept:list', '{"title":"部门管理"}', 1, 0, '2022-05-21 08:30:54', null, null, '部门管理菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (9, '岗位管理', ' ', 1, 'post', 'system/post/index', 5, '', 0, 'system:post:list', '{"title":"岗位管理"}', 1, 0, '2022-05-21 08:30:54', null, null, '岗位管理菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (10, '参数设置', ' ', 1, 'config', 'system/config/index', 7, '', 0, 'system:config:list', '{"title":"参数设置"}', 1, 0, '2022-05-21 08:30:54', null, null, '参数设置菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (11, '通知公告', ' ', 1, 'notice', 'system/notice/index', 8, '', 0, 'system:notice:list', '{"title":"通知公告"}', 1, 0, '2022-05-21 08:30:54', null, null, '通知公告菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (12, '日志管理', ' ', 1, 'log', '', 9, '', 0, '', '{"title":"日志管理"}', 1, 0, '2022-05-21 08:30:54', null, null, '日志管理菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (13, '在线用户', ' ', 2, 'online', 'monitor/online/index', 1, '', 0, 'monitor:online:list', '{"title":"在线用户"}', 1, 0, '2022-05-21 08:30:54', null, null, '在线用户菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (14, '数据监控', ' ', 2, 'druid', 'monitor/druid/index', 3, '', 0, 'monitor:druid:list', '{"title":"数据监控"}', 1, 0, '2022-05-21 08:30:54', null, null, '数据监控菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (15, '服务监控', ' ', 2, 'server', 'monitor/server/index', 4, '', 0, 'monitor:server:list', '{"title":"服务监控"}', 1, 0, '2022-05-21 08:30:54', null, null, '服务监控菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (16, '缓存监控', ' ', 2, 'cache', 'monitor/cache/index', 5, '', 0, 'monitor:cache:list', '{"title":"缓存监控"}', 1, 0, '2022-05-21 08:30:54', null, null, '缓存监控菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (17, '系统接口', ' ', 3, 'swagger', 'tool/swagger/index', 3, '', 0, 'tool:swagger:list', '{"title":"系统接口"}', 1, 0, '2022-05-21 08:30:54', null, null, '系统接口菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (18, '操作日志', ' ', 12, 'operlog', 'monitor/operlog/index', 1, '', 0, 'monitor:operlog:list', '{"title":"操作日志"}', 1, 0, '2022-05-21 08:30:54', null, null, '操作日志菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (19, '登录日志', ' ', 12, 'logininfor', 'monitor/logininfor/index', 2, '', 0, 'monitor:logininfor:list', '{"title":"登录日志"}', 1, 0, '2022-05-21 08:30:54', null, null, '登录日志菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (20, '用户查询', ' ', 5, '', '', 1, '', 1, 'system:user:query', '{"title":"用户查询"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (21, '用户新增', ' ', 5, '', '', 2, '', 1, 'system:user:add', '{"title":"用户新增"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (22, '用户修改', ' ', 5, '', '', 3, '', 1, 'system:user:edit', '{"title":"用户修改"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (23, '用户删除', ' ', 5, '', '', 4, '', 1, 'system:user:remove', '{"title":"用户删除"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (24, '用户导出', ' ', 5, '', '', 5, '', 1, 'system:user:export', '{"title":"用户导出"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (25, '用户导入', ' ', 5, '', '', 6, '', 1, 'system:user:import', '{"title":"用户导入"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (26, '重置密码', ' ', 5, '', '', 7, '', 1, 'system:user:resetPwd', '{"title":"重置密码"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (27, '角色查询', ' ', 6, '', '', 1, '', 1, 'system:role:query', '{"title":"角色查询"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (28, '角色新增', ' ', 6, '', '', 2, '', 1, 'system:role:add', '{"title":"角色新增"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (29, '角色修改', ' ', 6, '', '', 3, '', 1, 'system:role:edit', '{"title":"角色修改"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (30, '角色删除', ' ', 6, '', '', 4, '', 1, 'system:role:remove', '{"title":"角色删除"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (31, '角色导出', ' ', 6, '', '', 5, '', 1, 'system:role:export', '{"title":"角色导出"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (32, '菜单查询', ' ', 7, '', '', 1, '', 1, 'system:menu:query', '{"title":"菜单查询"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (33, '菜单新增', ' ', 7, '', '', 2, '', 1, 'system:menu:add', '{"title":"菜单新增"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (34, '菜单修改', ' ', 7, '', '', 3, '', 1, 'system:menu:edit', '{"title":"菜单修改"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (35, '菜单删除', ' ', 7, '', '', 4, '', 1, 'system:menu:remove', '{"title":"菜单删除"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (36, '部门查询', ' ', 8, '', '', 1, '', 1, 'system:dept:query', '{"title":"部门查询"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (37, '部门新增', ' ', 8, '', '', 2, '', 1, 'system:dept:add', '{"title":"部门新增"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (38, '部门修改', ' ', 8, '', '', 3, '', 1, 'system:dept:edit', '{"title":"部门修改"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (39, '部门删除', ' ', 8, '', '', 4, '', 1, 'system:dept:remove', '{"title":"部门删除"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (40, '岗位查询', ' ', 9, '', '', 1, '', 1, 'system:post:query', '{"title":"岗位查询"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (41, '岗位新增', ' ', 9, '', '', 2, '', 1, 'system:post:add', '{"title":"岗位新增"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (42, '岗位修改', ' ', 9, '', '', 3, '', 1, 'system:post:edit', '{"title":"岗位修改"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (43, '岗位删除', ' ', 9, '', '', 4, '', 1, 'system:post:remove', '{"title":"岗位删除"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (44, '岗位导出', ' ', 9, '', '', 5, '', 1, 'system:post:export', '{"title":"岗位导出"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (45, '参数查询', ' ', 10, '#', '', 1, '', 1, 'system:config:query', '{"title":"参数查询"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (46, '参数新增', ' ', 10, '#', '', 2, '', 1, 'system:config:add', '{"title":"参数新增"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (47, '参数修改', ' ', 10, '#', '', 3, '', 1, 'system:config:edit', '{"title":"参数修改"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (48, '参数删除', ' ', 10, '#', '', 4, '', 1, 'system:config:remove', '{"title":"参数删除"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (49, '参数导出', ' ', 10, '#', '', 5, '', 1, 'system:config:export', '{"title":"参数导出"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (50, '公告查询', ' ', 11, '#', '', 1, '', 1, 'system:notice:query', '{"title":"公告查询"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (51, '公告新增', ' ', 11, '#', '', 2, '', 1, 'system:notice:add', '{"title":"公告新增"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (52, '公告修改', ' ', 11, '#', '', 3, '', 1, 'system:notice:edit', '{"title":"公告修改"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (53, '公告删除', ' ', 11, '#', '', 4, '', 1, 'system:notice:remove', '{"title":"公告删除"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (54, '操作查询', ' ', 18, '#', '', 1, '', 1, 'monitor:operlog:query', '{"title":"操作查询"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (55, '操作删除', ' ', 18, '#', '', 2, '', 1, 'monitor:operlog:remove', '{"title":"操作删除"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (56, '日志导出', ' ', 18, '#', '', 4, '', 1, 'monitor:operlog:export', '{"title":"日志导出"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (57, '登录查询', ' ', 19, '#', '', 1, '', 1, 'monitor:logininfor:query', '{"title":"登录查询"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (58, '登录删除', ' ', 19, '#', '', 2, '', 1, 'monitor:logininfor:remove', '{"title":"登录删除"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (59, '日志导出', ' ', 19, '#', '', 3, '', 1, 'monitor:logininfor:export', '{"title":"日志导出"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (60, '在线查询', ' ', 13, '#', '', 1, '', 1, 'monitor:online:query', '{"title":"在线查询"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (61, '批量强退', ' ', 13, '#', '', 2, '', 1, 'monitor:online:batchLogout', '{"title":"批量强退"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (62, '单条强退', ' ', 13, '#', '', 3, '', 1, 'monitor:online:forceLogout', '{"title":"单条强退"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);

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

create table sys_user
(
    user_id      bigint auto_increment comment '用户ID'
        primary key,
    post_id      bigint                  null comment '职位id',
    role_id      bigint                  null comment '角色id',
    dept_id      bigint                  null comment '部门ID',
    username     varchar(64)             not null comment '用户账号',
    nick_name    varchar(32)             not null comment '用户昵称',
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

INSERT INTO  sys_user (user_id, post_id, role_id, dept_id, username, nick_name, user_type, email, phone_number, sex, avatar, password, status, login_ip, login_date, is_admin, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (1, 1, 1, 4, 'admin', 'valarchie1', 0, 'agileboot@163.com', '15888888889', 0, '', '$2a$10$rb1wRoEIkLbIknREEN1LH.FGs4g0oOS5t6l5LQ793nRaFO.SPHDHy', 1, '127.0.0.1', '2023-06-29 22:49:37', 1, null, '2022-05-21 08:30:54', 1, '2023-06-29 22:49:37', '管理员', 0);
INSERT INTO  sys_user (user_id, post_id, role_id, dept_id, username, nick_name, user_type, email, phone_number, sex, avatar, password, status, login_ip, login_date, is_admin, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (2, 2, 2, 5, 'ag1', 'valarchie2', 0, 'agileboot1@qq.com', '15666666666', 1, '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', 1, '127.0.0.1', '2022-05-21 08:30:54', 0, null, '2022-05-21 08:30:54', null, null, '测试员1', 0);
INSERT INTO  sys_user (user_id, post_id, role_id, dept_id, username, nick_name, user_type, email, phone_number, sex, avatar, password, status, login_ip, login_date, is_admin, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (3, 2, 0, 5, 'ag2', 'valarchie3', 0, 'agileboot2@qq.com', '15666666667', 1, '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', 1, '127.0.0.1', '2022-05-21 08:30:54', 0, null, '2022-05-21 08:30:54', null, null, '测试员2', 0);


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
INSERT INTO  sys_config (config_id, config_name, config_key, config_options, config_value, is_allow_change, creator_id, updater_id, update_time, create_time, remark, deleted) VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '', '1234567', 1, null, null, '2022-08-28 21:54:19', '2022-05-21 08:30:55', '初始化密码 123456', 0);
INSERT INTO  sys_config (config_id, config_name, config_key, config_options, config_value, is_allow_change, creator_id, updater_id, update_time, create_time, remark, deleted) VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', '["theme-dark","theme-light"]', 'theme-dark', 1, null, null, '2022-08-28 22:12:15', '2022-08-20 08:30:55', '深色主题theme-dark，浅色主题theme-light', 0);
INSERT INTO  sys_config (config_id, config_name, config_key, config_options, config_value, is_allow_change, creator_id, updater_id, update_time, create_time, remark, deleted) VALUES (4, '账号自助-验证码开关', 'sys.account.captchaOnOff', '["true","false"]', 'true', 0, null, null, '2022-08-28 22:03:37', '2022-05-21 08:30:55', '是否开启验证码功能（true开启，false关闭）', 0);
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
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader_id, leader_name, phone, email, status, creator_id, create_time, updater_id, update_time, deleted) VALUES (5, 2, '0,1,2', '市场部门', 2, null, 'valarchie', '15888888888', 'valarchie@163.com', 1, null, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader_id, leader_name, phone, email, status, creator_id, create_time, updater_id, update_time, deleted) VALUES (6, 2, '0,1,2', '测试部门', 3, null, 'valarchie', '15888888888', 'valarchie@163.com', 1, null, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader_id, leader_name, phone, email, status, creator_id, create_time, updater_id, update_time, deleted) VALUES (7, 2, '0,1,2', '财务部门', 4, null, 'valarchie', '15888888888', 'valarchie@163.com', 1, null, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader_id, leader_name, phone, email, status, creator_id, create_time, updater_id, update_time, deleted) VALUES (8, 2, '0,1,2', '运维部门', 5, null, 'valarchie', '15888888888', 'valarchie@163.com', 1, null, '2022-05-21 08:30:54', null, null, 0);
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader_id, leader_name, phone, email, status, creator_id, create_time, updater_id, update_time, deleted) VALUES (9, 3, '0,1,3', '市场部门', 1, null, 'valarchie', '15888888888', 'valarchie@163.com', 1, null, '2022-05-21 08:30:54', null, null, 0);
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
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (418, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-02 22:16:16', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (419, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 0, '验证码错误', '2023-07-02 22:17:31', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (420, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-02 22:17:46', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (421, 'admin', '127.0.0.1', '内网IP', 'Firefox 11', 'Mac OS X', 1, '登录成功', '2023-07-04 20:51:05', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (422, 'admin', '127.0.0.1', '内网IP', 'Firefox 11', 'Mac OS X', 1, '登录成功', '2023-07-04 20:58:08', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (423, 'admin', '127.0.0.1', '内网IP', 'Firefox 11', 'Mac OS X', 1, '登录成功', '2023-07-04 21:26:13', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (424, 'admin', '127.0.0.1', '内网IP', 'Firefox 11', 'Mac OS X', 1, '登录成功', '2023-07-04 21:46:11', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (425, 'admin', '127.0.0.1', '内网IP', 'Firefox 11', 'Mac OS X', 0, '验证码错误', '2023-07-04 22:00:17', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (426, 'admin', '127.0.0.1', '内网IP', 'Firefox 11', 'Mac OS X', 1, '登录成功', '2023-07-06 22:01:06', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (427, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-06 22:12:36', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (428, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-06 22:13:52', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (429, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 0, '用户不存在/密码错误', '2023-07-07 11:06:46', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (430, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 0, '用户不存在/密码错误', '2023-07-07 11:09:26', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (431, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 0, '验证码过期', '2023-07-07 11:09:27', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (432, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 0, '验证码过期', '2023-07-07 11:09:28', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (433, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 0, '验证码过期', '2023-07-07 11:09:29', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (434, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 0, '验证码过期', '2023-07-07 11:09:30', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (435, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 0, '验证码过期', '2023-07-07 11:09:31', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (436, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 0, '用户不存在/密码错误', '2023-07-07 11:09:34', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (437, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-07 11:21:51', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (438, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-07 14:16:47', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (439, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 0, '验证码过期', '2023-07-07 14:38:37', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (440, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-07 14:39:24', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (441, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 0, '验证码过期', '2023-07-07 15:04:45', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (442, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-07 15:10:14', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (443, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-07 15:18:24', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (444, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-07 15:20:11', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (445, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 0, '验证码过期', '2023-07-07 16:00:02', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (446, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-07 16:00:12', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (447, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-07 16:07:55', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (448, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-07 16:09:20', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (449, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-07 16:17:43', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (450, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-07 16:19:03', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (451, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-07 16:23:32', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (452, 'admin', '127.0.0.1', '内网IP', 'Firefox 11', 'Mac OS X', 1, '登录成功', '2023-07-07 16:31:17', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (453, 'admin', '127.0.0.1', '内网IP', 'Firefox 11', 'Mac OS X', 1, '登录成功', '2023-07-07 16:53:39', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (454, 'admin', '127.0.0.1', '内网IP', 'Firefox 11', 'Mac OS X', 1, '登录成功', '2023-07-07 16:54:09', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (455, 'admin', '127.0.0.1', '内网IP', 'Firefox 11', 'Mac OS X', 1, '登录成功', '2023-07-07 16:54:28', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (456, 'admin', '127.0.0.1', '内网IP', 'Firefox 11', 'Mac OS X', 1, '登录成功', '2023-07-07 16:59:47', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (457, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-07 17:41:22', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (458, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-07 17:47:40', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (459, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-07 17:48:24', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (460, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-07 17:50:23', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (461, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 0, '验证码过期', '2023-07-07 18:04:26', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (462, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 0, '验证码错误', '2023-07-07 18:04:28', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (463, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 0, '验证码过期', '2023-07-07 18:04:31', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (464, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-07 18:04:35', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (465, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-07 18:04:57', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (466, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-07 18:19:11', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (467, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-07 20:52:42', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (468, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-07 21:42:37', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (469, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-07 21:49:29', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (470, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-08 08:49:16', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (471, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-08 16:18:10', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (472, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-10 09:52:24', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (473, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-10 10:28:25', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (474, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-10 14:08:20', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (475, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-10 19:02:09', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (476, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-10 21:23:49', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (477, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-11 09:30:39', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (478, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-11 10:00:47', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (479, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-11 10:16:22', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (480, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-11 10:25:40', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (481, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-11 14:55:41', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (482, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-11 15:59:31', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (483, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-11 16:01:12', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (484, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-11 16:52:11', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (485, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-11 16:54:56', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (486, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-11 16:58:38', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (487, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-11 17:02:20', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (488, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-11 17:03:44', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (489, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-11 17:05:03', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (490, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-11 17:09:03', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (491, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-11 19:47:23', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (492, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-11 20:10:06', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (493, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-11 21:59:20', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (494, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-12 08:48:06', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (495, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-12 10:29:28', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (496, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-12 10:32:49', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (497, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-12 10:45:11', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (498, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-12 11:37:37', 0);
INSERT INTO  sys_login_info (info_id, username, ip_address, login_location, browser, operation_system, status, msg, login_time, deleted) VALUES (499, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-12 15:12:50', 0);

create table sys_menu
(
    menu_id     bigint auto_increment comment '菜单ID'
        primary key,
    menu_name   varchar(64)                not null comment '菜单名称',
    redirect    varchar(512)               null,
    route_name  varchar(255)  default ''   not null comment '路由名称（前端逻辑需要使用）',
    parent_id   bigint        default 0    not null comment '父菜单ID',
    path        varchar(255)               null comment '组件路径',
    component   varchar(255)  default ''   null comment '组件（该参数暂无使用）',
    `rank`      int           default 0    not null comment '显示顺序',
    query       varchar(255)               null comment '路由参数',
    is_button   tinyint       default 0    not null comment '是否按钮',
    permission  varchar(128)               null comment '权限标识',
    meta_info   varchar(1024) default '{}' not null comment '路由元信息主要由前端处理',
    status      smallint      default 0    not null comment '菜单状态（1启用 0停用）',
    creator_id  bigint                     null comment '创建者ID',
    create_time datetime                   null comment '创建时间',
    updater_id  bigint                     null comment '更新者ID',
    update_time datetime                   null comment '更新时间',
    remark      varchar(256)  default ''   null comment '备注',
    deleted     tinyint(1)    default 0    not null comment '逻辑删除'
)
    comment '菜单权限表';

INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (1, '系统管理', null, 'System', 0, '/system', '/system', 1, '', 0, '', '{"title":"系统管理"}', 1, 0, '2022-05-21 08:30:54', null, null, '系统管理目录', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (2, '系统监控', null, 'SystemMonitor', 0, '/monitor', '/monitor', 2, '', 0, '', '{"title":"系统监控"}', 1, 0, '2022-05-21 08:30:54', null, null, '系统监控目录', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (3, '系统工具', null, 'SystemTool', 0, '/tool', '/tool', 3, '', 0, '', '{"title":"系统工具"}', 1, 0, '2022-05-21 08:30:54', null, null, '系统工具目录', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (4, 'AgileBoot官网', null, 'OfficialWeb', 0, '/test', 'https://juejin.cn/column/7159946528827080734', 4, '', 0, '', '{"title":"AgileBoot官网","frameSrc": "https://element-plus.org/zh-CN/"}', 1, 0, '2022-05-21 08:30:54', null, null, 'Agileboot官网地址', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (5, '用户管理', null, 'SystemUser', 1, '/system/user/index', 'user', 1, '', 0, 'system:user:list', '{"title":"用户管理"}', 1, 0, '2022-05-21 08:30:54', null, null, '用户管理菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (6, '角色管理', null, 'SystemRole', 1, '/system/role/index', 'role', 2, '', 0, 'system:role:list', '{"title":"角色管理"}', 1, 0, '2022-05-21 08:30:54', null, null, '角色管理菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (7, '菜单管理', null, 'MenuManagement', 1, '/system/menu/index', 'menu', 3, '', 0, 'system:menu:list', '{"title":"菜单管理"}', 1, 0, '2022-05-21 08:30:54', null, null, '菜单管理菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (8, '部门管理', null, 'Department', 1, '/system/dept/index', 'dept', 4, '', 0, 'system:dept:list', '{"title":"部门管理"}', 1, 0, '2022-05-21 08:30:54', null, null, '部门管理菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (9, '岗位管理', null, 'Post', 1, '/system/post/index', 'post', 5, '', 0, 'system:post:list', '{"title":"岗位管理"}', 1, 0, '2022-05-21 08:30:54', null, null, '岗位管理菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (10, '参数设置', null, 'Config', 1, '/system/config/index', 'config', 7, '', 0, 'system:config:list', '{"title":"参数设置"}', 1, 0, '2022-05-21 08:30:54', null, null, '参数设置菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (11, '通知公告', null, 'SystemNotice', 1, '/system/notice/index', 'notice', 8, '', 0, 'system:notice:list', '{"title":"通知公告"}', 1, 0, '2022-05-21 08:30:54', null, null, '通知公告菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (12, '日志管理', '/monitor/operationLog/index', 'LogManagement', 1, '/system/log', 'log', 9, '', 0, '', '{"title":"日志管理"}', 1, 0, '2022-05-21 08:30:54', null, null, '日志管理菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (13, '在线用户', null, 'OnlineUser', 2, '/monitor/online/index', 'online', 1, '', 0, 'monitor:online:list', '{"title":"在线用户"}', 1, 0, '2022-05-21 08:30:54', null, null, '在线用户菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (14, '数据监控', null, 'DataMonitor', 2, '/monitor/druid/index', 'druid', 3, '', 0, 'monitor:druid:list', '{"title":"数据监控"}', 1, 0, '2022-05-21 08:30:54', null, null, '数据监控菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (15, '服务监控', null, 'ServerMonitor', 2, '/monitor/server/index', 'server', 4, '', 0, 'monitor:server:list', '{"title":"服务监控"}', 1, 0, '2022-05-21 08:30:54', null, null, '服务监控菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (16, '缓存监控', null, 'CacheMonitor', 2, '/monitor/cache/index', 'cache', 5, '', 0, 'monitor:cache:list', '{"title":"缓存监控"}', 1, 0, '2022-05-21 08:30:54', null, null, '缓存监控菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (17, '系统接口', null, 'SystemAPI', 3, '/tool/swagger/index', 'swagger', 3, '', 0, 'tool:swagger:list', '{"title":"系统接口"}', 1, 0, '2022-05-21 08:30:54', null, null, '系统接口菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (18, '操作日志', null, 'OperationLog', 12, '/monitor/operationLog/index', 'operlog', 1, '', 0, 'monitor:operlog:list', '{"title":"操作日志"}', 1, 0, '2022-05-21 08:30:54', null, null, '操作日志菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (19, '登录日志', null, 'LoginLog', 12, '/monitor/loginInfo/index', 'logininfor', 2, '', 0, 'monitor:logininfor:list', '{"title":"登录日志"}', 1, 0, '2022-05-21 08:30:54', null, null, '登录日志菜单', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (20, '用户查询', null, ' ', 5, '', '', 1, '', 1, 'system:user:query', '{"title":"用户查询"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (21, '用户新增', null, ' ', 5, '', '', 2, '', 1, 'system:user:add', '{"title":"用户新增"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (22, '用户修改', null, ' ', 5, '', '', 3, '', 1, 'system:user:edit', '{"title":"用户修改"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (23, '用户删除', null, ' ', 5, '', '', 4, '', 1, 'system:user:remove', '{"title":"用户删除"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (24, '用户导出', null, ' ', 5, '', '', 5, '', 1, 'system:user:export', '{"title":"用户导出"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (25, '用户导入', null, ' ', 5, '', '', 6, '', 1, 'system:user:import', '{"title":"用户导入"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (26, '重置密码', null, ' ', 5, '', '', 7, '', 1, 'system:user:resetPwd', '{"title":"重置密码"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (27, '角色查询', null, ' ', 6, '', '', 1, '', 1, 'system:role:query', '{"title":"角色查询"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (28, '角色新增', null, ' ', 6, '', '', 2, '', 1, 'system:role:add', '{"title":"角色新增"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (29, '角色修改', null, ' ', 6, '', '', 3, '', 1, 'system:role:edit', '{"title":"角色修改"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (30, '角色删除', null, ' ', 6, '', '', 4, '', 1, 'system:role:remove', '{"title":"角色删除"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (31, '角色导出', null, ' ', 6, '', '', 5, '', 1, 'system:role:export', '{"title":"角色导出"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (32, '菜单查询', null, ' ', 7, '', '', 1, '', 1, 'system:menu:query', '{"title":"菜单查询"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (33, '菜单新增', null, ' ', 7, '', '', 2, '', 1, 'system:menu:add', '{"title":"菜单新增"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (34, '菜单修改', null, ' ', 7, '', '', 3, '', 1, 'system:menu:edit', '{"title":"菜单修改"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (35, '菜单删除', null, ' ', 7, '', '', 4, '', 1, 'system:menu:remove', '{"title":"菜单删除"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (36, '部门查询', null, ' ', 8, '', '', 1, '', 1, 'system:dept:query', '{"title":"部门查询"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (37, '部门新增', null, ' ', 8, '', '', 2, '', 1, 'system:dept:add', '{"title":"部门新增"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (38, '部门修改', null, ' ', 8, '', '', 3, '', 1, 'system:dept:edit', '{"title":"部门修改"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (39, '部门删除', null, ' ', 8, '', '', 4, '', 1, 'system:dept:remove', '{"title":"部门删除"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (40, '岗位查询', null, ' ', 9, '', '', 1, '', 1, 'system:post:query', '{"title":"岗位查询"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (41, '岗位新增', null, ' ', 9, '', '', 2, '', 1, 'system:post:add', '{"title":"岗位新增"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (42, '岗位修改', null, ' ', 9, '', '', 3, '', 1, 'system:post:edit', '{"title":"岗位修改"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (43, '岗位删除', null, ' ', 9, '', '', 4, '', 1, 'system:post:remove', '{"title":"岗位删除"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (44, '岗位导出', null, ' ', 9, '', '', 5, '', 1, 'system:post:export', '{"title":"岗位导出"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (45, '参数查询', null, ' ', 10, '', '#', 1, '', 1, 'system:config:query', '{"title":"参数查询"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (46, '参数新增', null, ' ', 10, '', '#', 2, '', 1, 'system:config:add', '{"title":"参数新增"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (47, '参数修改', null, ' ', 10, '', '#', 3, '', 1, 'system:config:edit', '{"title":"参数修改"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (48, '参数删除', null, ' ', 10, '', '#', 4, '', 1, 'system:config:remove', '{"title":"参数删除"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (49, '参数导出', null, ' ', 10, '', '#', 5, '', 1, 'system:config:export', '{"title":"参数导出"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (50, '公告查询', null, ' ', 11, '', '#', 1, '', 1, 'system:notice:query', '{"title":"公告查询"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (51, '公告新增', null, ' ', 11, '', '#', 2, '', 1, 'system:notice:add', '{"title":"公告新增"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (52, '公告修改', null, ' ', 11, '', '#', 3, '', 1, 'system:notice:edit', '{"title":"公告修改"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (53, '公告删除', null, ' ', 11, '', '#', 4, '', 1, 'system:notice:remove', '{"title":"公告删除"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (54, '操作查询', null, ' ', 18, '', '#', 1, '', 1, 'monitor:operlog:query', '{"title":"操作查询"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (55, '操作删除', null, ' ', 18, '', '#', 2, '', 1, 'monitor:operlog:remove', '{"title":"操作删除"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (56, '日志导出', null, ' ', 18, '', '#', 4, '', 1, 'monitor:operlog:export', '{"title":"日志导出"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (57, '登录查询', null, ' ', 19, '', '#', 1, '', 1, 'monitor:logininfor:query', '{"title":"登录查询"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (58, '登录删除', null, ' ', 19, '', '#', 2, '', 1, 'monitor:logininfor:remove', '{"title":"登录删除"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (59, '日志导出', null, ' ', 19, '', '#', 3, '', 1, 'monitor:logininfor:export', '{"title":"日志导出"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (60, '在线查询', null, ' ', 13, '', '#', 1, '', 1, 'monitor:online:query', '{"title":"在线查询"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (61, '批量强退', null, ' ', 13, '', '#', 2, '', 1, 'monitor:online:batchLogout', '{"title":"批量强退"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (62, '单条强退', null, ' ', 13, '', '#', 3, '', 1, 'monitor:online:forceLogout', '{"title":"单条强退"}', 1, 0, '2022-05-21 08:30:54', null, null, '', 0);
INSERT INTO  sys_menu (menu_id, menu_name, redirect, route_name, parent_id, path, component, `rank`, query, is_button, permission, meta_info, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (63, 'AgileBoot Github地址', null, 'https://github.com/valarchie/AgileBoot-Back-End', 0, '/anything', '/anything', 4, '', 0, '', '{"title":"Github地址"}', 1, 0, '2022-05-21 08:30:54', null, null, 'Agileboot github地址', 0);

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
INSERT INTO  sys_notice (notice_id, notice_title, notice_type, notice_content, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (31, '12312312xxxxx', 1, '12313123', 1, 1, '2023-07-11 10:33:17', 1, '2023-07-12 09:18:38', '', 0);
INSERT INTO  sys_notice (notice_id, notice_title, notice_type, notice_content, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (32, '2342423', 1, '23423423432', 0, 1, '2023-07-11 21:29:17', null, null, '', 0);
INSERT INTO  sys_notice (notice_id, notice_title, notice_type, notice_content, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (33, '234324234', 2, '2342342343', 0, 1, '2023-07-11 21:30:28', null, null, '', 0);
INSERT INTO  sys_notice (notice_id, notice_title, notice_type, notice_content, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (34, '43214234', 1, '23423434324xxxxxxx', 0, 1, '2023-07-11 21:31:12', 1, '2023-07-11 22:21:44', '', 0);
INSERT INTO  sys_notice (notice_id, notice_title, notice_type, notice_content, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (35, '23423432', 1, '234234234234xxxxx', 0, 1, '2023-07-11 21:31:18', 1, '2023-07-11 22:21:39', '', 0);
INSERT INTO  sys_notice (notice_id, notice_title, notice_type, notice_content, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (36, '23423423', 1, '234234234234234', 0, 1, '2023-07-11 21:31:26', null, null, '', 0);
INSERT INTO  sys_notice (notice_id, notice_title, notice_type, notice_content, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (37, '12334', 1, '23423432', 1, 1, '2023-07-11 21:31:40', null, null, '', 0);
INSERT INTO  sys_notice (notice_id, notice_title, notice_type, notice_content, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (38, '234324', 1, '23424234', 0, 1, '2023-07-11 21:32:05', null, null, '', 0);
INSERT INTO  sys_notice (notice_id, notice_title, notice_type, notice_content, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (39, '123123', 1, '234234234', 0, 1, '2023-07-11 21:59:29', null, null, '', 0);
INSERT INTO  sys_notice (notice_id, notice_title, notice_type, notice_content, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (40, '12312321', 1, 'dd', 0, 1, '2023-07-11 22:11:44', null, null, '', 0);
INSERT INTO  sys_notice (notice_id, notice_title, notice_type, notice_content, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (41, '23423432', 2, '423423432', 1, 1, '2023-07-11 22:12:26', null, null, '', 0);
INSERT INTO  sys_notice (notice_id, notice_title, notice_type, notice_content, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (42, '14423', 2, '1312312对对对对对对', 1, 1, '2023-07-12 09:02:43', 1, '2023-07-12 09:44:30', '', 1);
INSERT INTO  sys_notice (notice_id, notice_title, notice_type, notice_content, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (43, 'for test', 2, '21344234324', 0, 1, '2023-07-12 09:46:08', null, null, '', 0);
INSERT INTO  sys_notice (notice_id, notice_title, notice_type, notice_content, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (44, '2143234234', 2, '234234234234234234', 0, 1, '2023-07-12 09:46:18', null, null, '', 0);
INSERT INTO  sys_notice (notice_id, notice_title, notice_type, notice_content, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (45, 'just for test', 2, '123123123', 1, 1, '2023-07-12 09:46:31', 1, '2023-07-12 09:46:40', '', 1);
INSERT INTO  sys_notice (notice_id, notice_title, notice_type, notice_content, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (46, '3432432xxx', 2, '1231231231xxx', 1, 1, '2023-07-12 14:07:49', 1, '2023-07-12 14:08:29', '', 1);
INSERT INTO  sys_notice (notice_id, notice_title, notice_type, notice_content, status, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (47, '234234xxx', 1, 'xxxxxx', 0, 1, '2023-07-12 14:08:07', 1, '2023-07-12 14:08:29', '', 1);

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

INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (379, 1, 2, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.add()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{"noticeType":"1","noticeTitle":"123123","noticeContent":"12313123","status":"1"},', '', 1, '', '2023-07-11 10:33:17', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (380, 2, 3, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.edit()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{}', '', 0, '找不到ID为 null 的 通知公告', '2023-07-11 21:19:42', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (381, 2, 3, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.edit()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{}', '', 0, '找不到ID为 null 的 通知公告', '2023-07-11 21:25:07', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (382, 1, 2, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.add()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{"noticeType":"1","noticeTitle":"2342423","noticeContent":"23423423432","status":"0"},', '', 1, '', '2023-07-11 21:29:17', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (383, 1, 2, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.add()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{"noticeType":"2","noticeTitle":"234324234","noticeContent":"2342342343","status":"0"},', '', 1, '', '2023-07-11 21:30:28', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (384, 1, 2, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.add()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{"noticeType":"1","noticeTitle":"43214234","noticeContent":"23423434324","status":"0"},', '', 1, '', '2023-07-11 21:31:12', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (385, 1, 2, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.add()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{"noticeType":"1","noticeTitle":"23423432","noticeContent":"234234234234","status":"0"},', '', 1, '', '2023-07-11 21:31:18', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (386, 1, 2, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.add()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{"noticeType":"1","noticeTitle":"23423423","noticeContent":"234234234234234","status":"0"},', '', 1, '', '2023-07-11 21:31:26', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (387, 1, 2, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.add()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{"noticeType":"1","noticeTitle":"12334","noticeContent":"23423432","status":"1"},', '', 1, '', '2023-07-11 21:31:40', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (388, 1, 2, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.add()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{"noticeType":"1","noticeTitle":"234324","noticeContent":"23424234","status":"0"},', '', 1, '', '2023-07-11 21:32:05', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (389, 1, 2, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.add()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{"noticeType":"1","noticeTitle":"123123","noticeContent":"234234234","status":"0"},', '', 1, '', '2023-07-11 21:59:29', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (390, 2, 3, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.edit()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{}', '', 0, '找不到ID为 null 的 通知公告', '2023-07-11 22:00:52', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (391, 2, 3, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.edit()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{}', '', 0, '找不到ID为 null 的 通知公告', '2023-07-11 22:00:59', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (392, 2, 3, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.edit()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{}', '', 0, '找不到ID为 null 的 通知公告', '2023-07-11 22:05:45', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (393, 2, 3, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.edit()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{}', '', 0, '找不到ID为 null 的 通知公告', '2023-07-11 22:05:50', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (394, 1, 2, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.add()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{"noticeType":"1","noticeTitle":"12312321","noticeContent":"dd","status":"0"},', '', 1, '', '2023-07-11 22:11:44', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (395, 2, 3, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.edit()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{}', '', 0, '找不到ID为 null 的 通知公告', '2023-07-11 22:11:48', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (396, 2, 3, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.edit()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{}', '', 0, '找不到ID为 null 的 通知公告', '2023-07-11 22:11:55', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (397, 1, 2, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.add()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{"noticeType":"2","noticeTitle":"23423432","noticeContent":"423423432","status":"1"},', '', 1, '', '2023-07-11 22:12:26', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (398, 2, 3, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.edit()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{}', '', 0, '找不到ID为 null 的 通知公告', '2023-07-11 22:12:30', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (399, 2, 3, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.edit()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{}', '', 0, '找不到ID为 null 的 通知公告', '2023-07-11 22:12:53', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (400, 2, 3, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.edit()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{}', '', 0, '找不到ID为 null 的 通知公告', '2023-07-11 22:12:55', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (401, 2, 3, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.edit()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{}', '', 1, '', '2023-07-11 22:21:39', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (402, 2, 3, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.edit()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{}', '', 1, '', '2023-07-11 22:21:44', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (403, 1, 2, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.add()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{"noticeType":"2","noticeTitle":"14423","noticeContent":"1312312对对对对对对","status":"1"},', '', 1, '', '2023-07-12 09:02:43', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (404, 2, 3, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.edit()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{}', '', 1, '', '2023-07-12 09:18:38', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (405, 3, 4, '通知公告', '/system/notice/42', 'com.agileboot.admin.controller.system.SysNoticeController.remove()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{noticeIds=42}', '', 1, '', '2023-07-12 09:44:30', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (406, 1, 2, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.add()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{"noticeType":"2","noticeTitle":"for test","noticeContent":"21344234324","status":"0"},', '', 1, '', '2023-07-12 09:46:08', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (407, 1, 2, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.add()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{"noticeType":"2","noticeTitle":"2143234234","noticeContent":"234234234234234234","status":"0"},', '', 1, '', '2023-07-12 09:46:18', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (408, 1, 2, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.add()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{"noticeType":"2","noticeTitle":"just for test","noticeContent":"123123123","status":"1"},', '', 1, '', '2023-07-12 09:46:31', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (409, 3, 4, '通知公告', '/system/notice/45', 'com.agileboot.admin.controller.system.SysNoticeController.remove()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{noticeIds=45}', '', 1, '', '2023-07-12 09:46:40', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (410, 1, 2, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.add()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{"noticeType":"2","noticeTitle":"3432432","noticeContent":"1231231231","status":"1"},', '', 1, '', '2023-07-12 14:07:49', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (411, 2, 3, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.edit()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{}', '', 1, '', '2023-07-12 14:07:56', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (412, 1, 2, '通知公告', '/system/notice/', 'com.agileboot.admin.controller.system.SysNoticeController.add()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{"noticeType":"1","noticeTitle":"234234xxx","noticeContent":"xxxxxx","status":"0"},', '', 1, '', '2023-07-12 14:08:07', 0);
INSERT INTO  sys_operation_log (operation_id, business_type, request_method, request_module, request_url, called_method, operator_type, user_id, username, operator_ip, operator_location, dept_id, dept_name, operation_param, operation_result, status, error_stack, operation_time, deleted) VALUES (413, 3, 4, '通知公告', '/system/notice/46,47', 'com.agileboot.admin.controller.system.SysNoticeController.remove()', 1, 0, 'admin', '127.0.0.1', '内网IP', 0, null, '{noticeIds=46,47}', '', 1, '', '2023-07-12 14:08:29', 0);

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

create table sys_user
(
    user_id      bigint auto_increment comment '用户ID'
        primary key,
    post_id      bigint                  null comment '职位id',
    role_id      bigint                  null comment '角色id',
    dept_id      bigint                  null comment '部门ID',
    username     varchar(64)             not null comment '用户账号',
    nick_name    varchar(32)             not null comment '用户昵称',
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

INSERT INTO  sys_user (user_id, post_id, role_id, dept_id, username, nick_name, user_type, email, phone_number, sex, avatar, password, status, login_ip, login_date, is_admin, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (1, 1, 1, 4, 'admin', 'valarchie1', 0, 'agileboot@163.com', '15888888889', 0, '', '$2a$10$rb1wRoEIkLbIknREEN1LH.FGs4g0oOS5t6l5LQ793nRaFO.SPHDHy', 1, '127.0.0.1', '2023-07-12 15:12:50', 1, null, '2022-05-21 08:30:54', 1, '2023-07-12 15:12:50', '管理员', 0);
INSERT INTO  sys_user (user_id, post_id, role_id, dept_id, username, nick_name, user_type, email, phone_number, sex, avatar, password, status, login_ip, login_date, is_admin, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (2, 2, 2, 5, 'ag1', 'valarchie2', 0, 'agileboot1@qq.com', '15666666666', 1, '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', 1, '127.0.0.1', '2022-05-21 08:30:54', 0, null, '2022-05-21 08:30:54', null, null, '测试员1', 0);
INSERT INTO  sys_user (user_id, post_id, role_id, dept_id, username, nick_name, user_type, email, phone_number, sex, avatar, password, status, login_ip, login_date, is_admin, creator_id, create_time, updater_id, update_time, remark, deleted) VALUES (3, 2, 0, 5, 'ag2', 'valarchie3', 0, 'agileboot2@qq.com', '15666666667', 1, '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', 1, '127.0.0.1', '2022-05-21 08:30:54', 0, null, '2022-05-21 08:30:54', null, null, '测试员2', 0);

