
--- int后面不能带数字， 索引相关的语句也不允许， 保留最简单原始的语句即可
create sequence if not exists sys_config_seq start with 6 increment by 1;
create table sys_config
(
    config_id       int default next value for sys_config_seq,
    config_name     varchar(128)  default '' not null comment '配置名称',
    config_key      varchar(128)  default '' not null comment '配置键名',
    config_options  varchar(1024) default '' not null comment '可选的选项',
    config_value    varchar(256)  default '' not null comment '配置值',
    is_allow_change int              not null comment '是否允许修改',
    creator_id      int                   null comment '创建者ID',
    updater_id      int                   null comment '更新者ID',
    update_time     datetime                 null comment '更新时间',
    create_time     datetime                 null comment '创建时间',
    remark          varchar(128)             null comment '备注',
    deleted         int    default 0  not null comment '逻辑删除'
);

create sequence if not exists sys_dept_seq start with 11 increment by 1;
create table sys_dept
(
    dept_id      int default next value for sys_dept_seq,
    parent_id    bigint      default 0  not null comment '父部门id',
    ancestors    text                   not null comment '祖级列表',
    dept_name    varchar(64) default '' not null comment '部门名称',
    order_num    int         default 0  not null comment '显示顺序',
    leader_id    bigint                 null,
    leader_name  varchar(64)            null comment '负责人',
    phone        varchar(16)            null comment '联系电话',
    email        varchar(128)           null comment '邮箱',
    status       smallint    default 0  not null comment '部门状态（0正常 1停用）',
    creator_id   bigint                 null comment '创建者ID',
    create_time  datetime               null comment '创建时间',
    updater_id   bigint                 null comment '更新者ID',
    update_time  datetime               null comment '更新时间',
    deleted      tinyint  default 0  not null comment '逻辑删除'
);

create sequence if not exists sys_login_info_seq start with 1 increment by 1;
create table sys_login_info
(
    info_id          bigint default next value for sys_login_info_seq,
    username         varchar(50)  default '' not null comment '用户账号',
    ip_address       varchar(128) default '' not null comment '登录IP地址',
    login_location   varchar(255) default '' not null comment '登录地点',
    browser          varchar(50)  default '' not null comment '浏览器类型',
    operation_system varchar(50)  default '' not null comment '操作系统',
    status           smallint     default 0  not null comment '登录状态（1成功 0失败）',
    msg              varchar(255) default '' not null comment '提示消息',
    login_time       datetime                null comment '访问时间',
    deleted          tinyint   default 0  not null comment '逻辑删除'
);

create sequence if not exists sys_menu_seq start with 63 increment by 1;
create table sys_menu
(
    menu_id      bigint default next value for sys_menu_seq,
    menu_name    varchar(64)              not null comment '菜单名称',
    parent_id    bigint       default 0   not null comment '父菜单ID',
    order_num    int          default 0   not null comment '显示顺序',
    path         varchar(255) default ''  null comment '路由地址',
    component    varchar(255)             null comment '组件路径',
    query        varchar(255)             null comment '路由参数',
    is_external  tinyint   default 1   not null comment '是否为外链（1是 0否）',
    is_cache     tinyint   default 0   not null comment '是否缓存（1缓存 0不缓存）',
    menu_type    smallint     default 0   not null comment '菜单类型（M=1目录 C=2菜单 F=3按钮）',
    is_visible   tinyint   default 0   not null comment '菜单状态（1显示 0隐藏）',
    status       smallint     default 0   not null comment '菜单状态（0正常 1停用）',
    perms        varchar(128)             null comment '权限标识',
    icon         varchar(128) default '#' null comment '菜单图标',
    creator_id   bigint                   null comment '创建者ID',
    create_time  datetime                 null comment '创建时间',
    updater_id   bigint                   null comment '更新者ID',
    update_time  datetime                 null comment '更新时间',
    remark       varchar(512) default ''  null comment '备注',
    deleted      tinyint   default 0   not null comment '逻辑删除'
);

create sequence if not exists sys_notice_seq start with 3 increment by 1;
create table sys_notice
(
    notice_id      int default next value for sys_notice_seq,
    notice_title   varchar(64)             not null comment '公告标题',
    notice_type    smallint                not null comment '公告类型（1通知 2公告）',
    notice_content text                    null comment '公告内容',
    status         smallint     default 0  not null comment '公告状态（1正常 0关闭）',
    creator_id     bigint                  not null comment '创建者ID',
    create_time    datetime                null comment '创建时间',
    updater_id     bigint                  null comment '更新者ID',
    update_time    datetime                null comment '更新时间',
    remark         varchar(255) default '' not null comment '备注',
    deleted        tinyint   default 0  not null comment '逻辑删除'
);

create sequence if not exists sys_operation_log_seq start with 1 increment by 1;
create table sys_operation_log
(
    operation_id      bigint default next value for sys_operation_log_seq,
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
    deleted           tinyint    default 0  not null comment '逻辑删除'
);

create sequence if not exists sys_post_seq start with 5 increment by 1;
create table sys_post
(
    post_id      bigint default next value for sys_post_seq,
    post_code    varchar(64)            not null comment '岗位编码',
    post_name    varchar(64)            not null comment '岗位名称',
    post_sort    int                    not null comment '显示顺序',
    status       smallint               not null comment '状态（1正常 0停用）',
    remark       varchar(512)           null comment '备注',
    creator_id   bigint                 null,
    create_time  datetime               null comment '创建时间',
    updater_id   bigint                 null,
    update_time  datetime               null comment '更新时间',
    deleted      tinyint  default 0  not null comment '逻辑删除'
);

create sequence if not exists sys_role_seq start with 4 increment by 1;
create table sys_role
(
    role_id      bigint default next value for sys_role_seq,
    role_name    varchar(32)              not null comment '角色名称',
    role_key     varchar(128)             not null comment '角色权限字符串',
    role_sort    int                      not null comment '显示顺序',
    data_scope   smallint      default 1  null comment '数据范围（1：全部数据权限 2：自定数据权限 3: 本部门数据权限 4: 本部门及以下数据权限 5: 本人权限）',
    dept_id_set  varchar(1024) default '' null comment '角色所拥有的部门数据权限',
    status       smallint                 not null comment '角色状态（1正常 0停用）',
    creator_id   bigint                   null comment '创建者ID',
    create_time  datetime                 null comment '创建时间',
    updater_id   bigint                   null comment '更新者ID',
    update_time  datetime                 null comment '更新时间',
    remark       varchar(512)             null comment '备注',
    deleted      tinyint    default 0  not null comment '删除标志（0代表存在 1代表删除）'
);

create table sys_role_menu
(
    role_id bigint auto_increment not null comment '角色ID',
    menu_id bigint auto_increment not null comment '菜单ID'
);

create sequence if not exists sys_user_seq start with 4 increment by 1;
create table sys_user
(
    user_id      bigint default next value for sys_user_seq,
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
    is_admin     tinyint   default 0  not null comment '超级管理员标志（1是，0否）',
    creator_id   bigint                  null comment '更新者ID',
    create_time  datetime                null comment '创建时间',
    updater_id   bigint                  null comment '更新者ID',
    update_time  datetime                null comment '更新时间',
    remark       varchar(512)            null comment '备注',
    deleted      tinyint   default 0  not null comment '删除标志（0代表存在 1代表删除）'
);

CREATE ALIAS FIND_IN_SET FOR "com.agileboot.infrastructure.mybatisplus.MySqlFunction.findInSet";
