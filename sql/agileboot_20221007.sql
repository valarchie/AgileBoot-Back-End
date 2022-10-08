/*
Navicat MySQL Data Transfer

Source Server         : Local-Mysql
Source Server Version : 80029
Source Host           : localhost:33066
Source Database       : agileboot

Target Server Type    : MYSQL
Target Server Version : 80029
File Encoding         : 65001

Date: 2022-10-07 16:05:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `config_id` int NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '配置名称',
  `config_key` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '配置键名',
  `config_options` varchar(1024) NOT NULL DEFAULT '' COMMENT '可选的选项',
  `config_value` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '配置值',
  `is_allow_change` tinyint(1) NOT NULL COMMENT '是否允许修改',
  `creator_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `creator_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '创建者',
  `updater_id` bigint DEFAULT NULL COMMENT '更新者ID',
  `updater_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`config_id`),
  UNIQUE KEY `config_key_uniq_idx` (`config_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='参数配置表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', '主框架页-默认皮肤样式名称', 'sys.index.skinName', '[\"skin-blue\",\"skin-green\",\"skin-purple\",\"skin-red\",\"skin-yellow\"]', 'skin-blue', '1', null, 'admin', null, '', '2022-08-28 22:12:19', '2022-05-21 08:30:55', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow', '0');
INSERT INTO `sys_config` VALUES ('2', '用户管理-账号初始密码', 'sys.user.initPassword', '', '1234567', '1', null, 'admin', null, '', '2022-08-28 21:54:19', '2022-05-21 08:30:55', '初始化密码 123456', '0');
INSERT INTO `sys_config` VALUES ('3', '主框架页-侧边栏主题', 'sys.index.sideTheme', '[\"theme-dark\",\"theme-light\"]', 'theme-dark', '1', null, 'admin', null, '', '2022-08-28 22:12:15', '2022-08-20 08:30:55', '深色主题theme-dark，浅色主题theme-light', '0');
INSERT INTO `sys_config` VALUES ('4', '账号自助-验证码开关', 'sys.account.captchaOnOff', '[\"true\",\"false\"]', 'false', '0', null, 'admin', null, '', '2022-08-28 22:03:37', '2022-05-21 08:30:55', '是否开启验证码功能（true开启，false关闭）', '0');
INSERT INTO `sys_config` VALUES ('5', '账号自助-是否开启用户注册功能', 'sys.account.registerUser', '[\"true\",\"false\"]', 'true', '0', null, 'admin', '1', 'admin', '2022-10-05 22:18:57', '2022-05-21 08:30:55', '是否开启注册用户功能（true开启，false关闭）', '0');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint NOT NULL DEFAULT '0' COMMENT '父部门id',
  `ancestors` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '祖级列表',
  `dept_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int NOT NULL DEFAULT '0' COMMENT '显示顺序',
  `leader_id` bigint DEFAULT NULL,
  `leader_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '负责人',
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '联系电话',
  `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `status` smallint NOT NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `creator_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `creator_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新者ID',
  `updater_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('100', '0', '0', 'AgileBoot科技', '0', null, 'valarchie', '15888888888', 'valarchie@163.com', '1', null, 'admin', '2022-05-21 08:30:54', null, '', null, '0');
INSERT INTO `sys_dept` VALUES ('101', '100', '0,100', '深圳总公司', '1', null, 'valarchie', '15888888888', 'valarchie@163.com', '1', null, 'admin', '2022-05-21 08:30:54', null, '', null, '0');
INSERT INTO `sys_dept` VALUES ('102', '100', '0,100', '长沙分公司', '2', null, 'valarchie', '15888888888', 'valarchie@163.com', '1', null, 'admin', '2022-05-21 08:30:54', null, '', null, '0');
INSERT INTO `sys_dept` VALUES ('103', '101', '0,100,101', '研发部门', '1', null, 'valarchie', '15888888888', 'valarchie@163.com', '1', null, 'admin', '2022-05-21 08:30:54', null, '', null, '0');
INSERT INTO `sys_dept` VALUES ('104', '101', '0,100,101', '市场部门', '2', null, 'valarchie', '15888888888', 'valarchie@163.com', '1', null, 'admin', '2022-05-21 08:30:54', null, '', null, '0');
INSERT INTO `sys_dept` VALUES ('105', '101', '0,100,101', '测试部门', '3', null, 'valarchie', '15888888888', 'valarchie@163.com', '1', null, 'admin', '2022-05-21 08:30:54', null, '', null, '0');
INSERT INTO `sys_dept` VALUES ('106', '101', '0,100,101', '财务部门', '4', null, 'valarchie', '15888888888', 'valarchie@163.com', '1', null, 'admin', '2022-05-21 08:30:54', null, '', null, '0');
INSERT INTO `sys_dept` VALUES ('107', '101', '0,100,101', '运维部门', '5', null, 'valarchie', '15888888888', 'valarchie@163.com', '1', null, 'admin', '2022-05-21 08:30:54', null, '', null, '0');
INSERT INTO `sys_dept` VALUES ('108', '102', '0,100,102', '市场部门', '1', null, 'valarchie', '15888888888', 'valarchie@163.com', '1', null, 'admin', '2022-05-21 08:30:54', null, '', null, '0');
INSERT INTO `sys_dept` VALUES ('109', '102', '0,100,102', '财务部门', '2', null, 'valarchie', '15888888888', 'valarchie@163.com', '0', null, 'admin', '2022-05-21 08:30:54', '1', 'admin', '2022-10-05 22:16:49', '0');

-- ----------------------------
-- Table structure for sys_login_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_info`;
CREATE TABLE `sys_login_info` (
  `info_id` bigint NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '用户账号',
  `ip_address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '浏览器类型',
  `operation_system` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '操作系统',
  `status` smallint NOT NULL DEFAULT '0' COMMENT '登录状态（1成功 0失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=415 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统访问记录';

-- ----------------------------
-- Records of sys_login_info
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint NOT NULL DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int NOT NULL DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) DEFAULT NULL COMMENT '路由参数',
  `is_external` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否为外链（1是 0否）',
  `is_cache` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否缓存（1缓存 0不缓存）',
  `menu_type` smallint NOT NULL DEFAULT '0' COMMENT '菜单类型（M=1目录 C=2菜单 F=3按钮）',
  `is_visible` tinyint(1) NOT NULL DEFAULT '0' COMMENT '菜单状态（1显示 0隐藏）',
  `status` smallint NOT NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '#' COMMENT '菜单图标',
  `creator_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `creator_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新者ID',
  `updater_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '备注',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2007 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '0', '1', 'system', null, '', '0', '1', '1', '1', '1', '', 'system', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '系统管理目录', '0');
INSERT INTO `sys_menu` VALUES ('2', '系统监控', '0', '2', 'monitor', null, '', '0', '1', '1', '1', '1', '', 'monitor', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '系统监控目录', '0');
INSERT INTO `sys_menu` VALUES ('3', '系统工具', '0', '3', 'tool', null, '', '0', '1', '1', '1', '1', '', 'tool', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '系统工具目录', '0');
INSERT INTO `sys_menu` VALUES ('4', 'AgileBoot官网', '0', '4', 'http://ruoyi.vip', null, '', '1', '1', '1', '1', '1', '', 'guide', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '若依官网地址', '0');
INSERT INTO `sys_menu` VALUES ('100', '用户管理', '1', '1', 'user', 'system/user/index', '', '0', '1', '2', '1', '1', 'system:user:list', 'user', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '用户管理菜单', '0');
INSERT INTO `sys_menu` VALUES ('101', '角色管理', '1', '2', 'role', 'system/role/index', '', '0', '1', '2', '1', '1', 'system:role:list', 'peoples', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '角色管理菜单', '0');
INSERT INTO `sys_menu` VALUES ('102', '菜单管理', '1', '3', 'menu', 'system/menu/index', '', '0', '1', '2', '1', '1', 'system:menu:list', 'tree-table', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '菜单管理菜单', '0');
INSERT INTO `sys_menu` VALUES ('103', '部门管理', '1', '4', 'dept', 'system/dept/index', '', '0', '1', '2', '1', '1', 'system:dept:list', 'tree', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '部门管理菜单', '0');
INSERT INTO `sys_menu` VALUES ('104', '岗位管理', '1', '5', 'post', 'system/post/index', '', '0', '1', '2', '1', '1', 'system:post:list', 'post', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '岗位管理菜单', '0');
INSERT INTO `sys_menu` VALUES ('105', '字典管理', '1', '6', 'dict', 'system/dict/index', '', '0', '1', '2', '1', '1', 'system:dict:list', 'dict', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '字典管理菜单', '1');
INSERT INTO `sys_menu` VALUES ('106', '参数设置', '1', '7', 'config', 'system/config/index', '', '0', '1', '2', '1', '1', 'system:config:list', 'edit', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '参数设置菜单', '0');
INSERT INTO `sys_menu` VALUES ('107', '通知公告', '1', '8', 'notice', 'system/notice/index', '', '0', '1', '2', '1', '1', 'system:notice:list', 'message', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '通知公告菜单', '0');
INSERT INTO `sys_menu` VALUES ('108', '日志管理', '1', '9', 'log', '', '', '0', '1', '1', '1', '1', '', 'log', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '日志管理菜单', '0');
INSERT INTO `sys_menu` VALUES ('109', '在线用户', '2', '1', 'online', 'monitor/online/index', '', '0', '1', '2', '1', '1', 'monitor:online:list', 'online', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '在线用户菜单', '0');
INSERT INTO `sys_menu` VALUES ('110', '定时任务', '2', '2', 'job', 'monitor/job/index', '', '0', '1', '2', '1', '1', 'monitor:job:list', 'job', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '定时任务菜单', '1');
INSERT INTO `sys_menu` VALUES ('111', '数据监控', '2', '3', 'druid', 'monitor/druid/index', '', '0', '1', '2', '1', '1', 'monitor:druid:list', 'druid', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '数据监控菜单', '0');
INSERT INTO `sys_menu` VALUES ('112', '服务监控', '2', '4', 'server', 'monitor/server/index', '', '0', '1', '2', '1', '1', 'monitor:server:list', 'server', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '服务监控菜单', '0');
INSERT INTO `sys_menu` VALUES ('113', '缓存监控', '2', '5', 'cache', 'monitor/cache/index', '', '0', '1', '2', '1', '1', 'monitor:cache:list', 'redis', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '缓存监控菜单', '0');
INSERT INTO `sys_menu` VALUES ('114', '表单构建', '3', '1', 'build', 'tool/build/index', '', '0', '1', '2', '1', '1', 'tool:build:list', 'build', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '表单构建菜单', '1');
INSERT INTO `sys_menu` VALUES ('115', '代码生成', '3', '2', 'gen', 'tool/gen/index', '', '0', '1', '2', '1', '1', 'tool:gen:list', 'code', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '代码生成菜单', '1');
INSERT INTO `sys_menu` VALUES ('116', '系统接口', '3', '3', 'swagger', 'tool/swagger/index', '', '0', '1', '2', '1', '1', 'tool:swagger:list', 'swagger', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '系统接口菜单', '0');
INSERT INTO `sys_menu` VALUES ('500', '操作日志', '108', '1', 'operlog', 'monitor/operlog/index', '', '0', '1', '2', '1', '1', 'monitor:operlog:list', 'form', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '操作日志菜单', '0');
INSERT INTO `sys_menu` VALUES ('501', '登录日志', '108', '2', 'logininfor', 'monitor/logininfor/index', '', '0', '1', '2', '1', '1', 'monitor:logininfor:list', 'logininfor', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '登录日志菜单', '0');
INSERT INTO `sys_menu` VALUES ('1001', '用户查询', '100', '1', '', '', '', '0', '1', '3', '1', '1', 'system:user:query', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1002', '用户新增', '100', '2', '', '', '', '0', '1', '3', '1', '1', 'system:user:add', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1003', '用户修改', '100', '3', '', '', '', '0', '1', '3', '1', '1', 'system:user:edit', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1004', '用户删除', '100', '4', '', '', '', '0', '1', '3', '1', '1', 'system:user:remove', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1005', '用户导出', '100', '5', '', '', '', '0', '1', '3', '1', '1', 'system:user:export', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1006', '用户导入', '100', '6', '', '', '', '0', '1', '3', '1', '1', 'system:user:import', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1007', '重置密码', '100', '7', '', '', '', '0', '1', '3', '1', '1', 'system:user:resetPwd', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1008', '角色查询', '101', '1', '', '', '', '0', '1', '3', '1', '1', 'system:role:query', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1009', '角色新增', '101', '2', '', '', '', '0', '1', '3', '1', '1', 'system:role:add', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1010', '角色修改', '101', '3', '', '', '', '0', '1', '3', '1', '1', 'system:role:edit', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1011', '角色删除', '101', '4', '', '', '', '0', '1', '3', '1', '1', 'system:role:remove', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1012', '角色导出', '101', '5', '', '', '', '0', '1', '3', '1', '1', 'system:role:export', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1013', '菜单查询', '102', '1', '', '', '', '0', '1', '3', '1', '1', 'system:menu:query', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1014', '菜单新增', '102', '2', '', '', '', '0', '1', '3', '1', '1', 'system:menu:add', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1015', '菜单修改', '102', '3', '', '', '', '0', '1', '3', '1', '1', 'system:menu:edit', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1016', '菜单删除', '102', '4', '', '', '', '0', '1', '3', '1', '1', 'system:menu:remove', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1017', '部门查询', '103', '1', '', '', '', '0', '1', '3', '1', '1', 'system:dept:query', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1018', '部门新增', '103', '2', '', '', '', '0', '1', '3', '1', '1', 'system:dept:add', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1019', '部门修改', '103', '3', '', '', '', '0', '1', '3', '1', '1', 'system:dept:edit', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1020', '部门删除', '103', '4', '', '', '', '0', '1', '3', '1', '1', 'system:dept:remove', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1021', '岗位查询', '104', '1', '', '', '', '0', '1', '3', '1', '1', 'system:post:query', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1022', '岗位新增', '104', '2', '', '', '', '0', '1', '3', '1', '1', 'system:post:add', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1023', '岗位修改', '104', '3', '', '', '', '0', '1', '3', '1', '1', 'system:post:edit', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1024', '岗位删除', '104', '4', '', '', '', '0', '1', '3', '1', '1', 'system:post:remove', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1025', '岗位导出', '104', '5', '', '', '', '0', '1', '3', '1', '1', 'system:post:export', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1026', '字典查询', '105', '1', '#', '', '', '0', '1', '3', '1', '1', 'system:dict:query', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '1');
INSERT INTO `sys_menu` VALUES ('1027', '字典新增', '105', '2', '#', '', '', '0', '1', '3', '1', '1', 'system:dict:add', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '1');
INSERT INTO `sys_menu` VALUES ('1028', '字典修改', '105', '3', '#', '', '', '0', '1', '3', '1', '1', 'system:dict:edit', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '1');
INSERT INTO `sys_menu` VALUES ('1029', '字典删除', '105', '4', '#', '', '', '0', '1', '3', '1', '1', 'system:dict:remove', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '1');
INSERT INTO `sys_menu` VALUES ('1030', '字典导出', '105', '5', '#', '', '', '0', '1', '3', '1', '1', 'system:dict:export', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '1');
INSERT INTO `sys_menu` VALUES ('1031', '参数查询', '106', '1', '#', '', '', '0', '1', '3', '1', '1', 'system:config:query', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1032', '参数新增', '106', '2', '#', '', '', '0', '1', '3', '1', '1', 'system:config:add', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1033', '参数修改', '106', '3', '#', '', '', '0', '1', '3', '1', '1', 'system:config:edit', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1034', '参数删除', '106', '4', '#', '', '', '0', '1', '3', '1', '1', 'system:config:remove', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1035', '参数导出', '106', '5', '#', '', '', '0', '1', '3', '1', '1', 'system:config:export', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1036', '公告查询', '107', '1', '#', '', '', '0', '1', '3', '1', '1', 'system:notice:query', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1037', '公告新增', '107', '2', '#', '', '', '0', '1', '3', '1', '1', 'system:notice:add', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1038', '公告修改', '107', '3', '#', '', '', '0', '1', '3', '1', '1', 'system:notice:edit', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1039', '公告删除', '107', '4', '#', '', '', '0', '1', '3', '1', '1', 'system:notice:remove', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1040', '操作查询', '500', '1', '#', '', '', '0', '1', '3', '1', '1', 'monitor:operlog:query', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1041', '操作删除', '500', '2', '#', '', '', '0', '1', '3', '1', '1', 'monitor:operlog:remove', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1042', '日志导出', '500', '4', '#', '', '', '0', '1', '3', '1', '1', 'monitor:operlog:export', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1043', '登录查询', '501', '1', '#', '', '', '0', '1', '3', '1', '1', 'monitor:logininfor:query', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1044', '登录删除', '501', '2', '#', '', '', '0', '1', '3', '1', '1', 'monitor:logininfor:remove', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1045', '日志导出', '501', '3', '#', '', '', '0', '1', '3', '1', '1', 'monitor:logininfor:export', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1046', '在线查询', '109', '1', '#', '', '', '0', '1', '3', '1', '1', 'monitor:online:query', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1047', '批量强退', '109', '2', '#', '', '', '0', '1', '3', '1', '1', 'monitor:online:batchLogout', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1048', '单条强退', '109', '3', '#', '', '', '0', '1', '3', '1', '1', 'monitor:online:forceLogout', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1049', '任务查询', '110', '1', '#', '', '', '0', '1', '3', '1', '1', 'monitor:job:query', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '1');
INSERT INTO `sys_menu` VALUES ('1050', '任务新增', '110', '2', '#', '', '', '0', '1', '3', '1', '1', 'monitor:job:add', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '1');
INSERT INTO `sys_menu` VALUES ('1051', '任务修改', '110', '3', '#', '', '', '0', '1', '3', '1', '1', 'monitor:job:edit', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '1');
INSERT INTO `sys_menu` VALUES ('1052', '任务删除', '110', '4', '#', '', '', '0', '1', '3', '1', '1', 'monitor:job:remove', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '1');
INSERT INTO `sys_menu` VALUES ('1053', '状态修改', '110', '5', '#', '', '', '0', '1', '3', '1', '1', 'monitor:job:changeStatus', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '1');
INSERT INTO `sys_menu` VALUES ('1054', '任务导出', '110', '7', '#', '', '', '0', '1', '3', '1', '1', 'monitor:job:export', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '1');
INSERT INTO `sys_menu` VALUES ('1055', '生成查询', '115', '1', '#', '', '', '0', '1', '3', '1', '1', 'tool:gen:query', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '1');
INSERT INTO `sys_menu` VALUES ('1056', '生成修改', '115', '2', '#', '', '', '0', '1', '3', '1', '1', 'tool:gen:edit', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '1');
INSERT INTO `sys_menu` VALUES ('1057', '生成删除', '115', '3', '#', '', '', '0', '1', '3', '1', '1', 'tool:gen:remove', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '1');
INSERT INTO `sys_menu` VALUES ('1058', '导入代码', '115', '2', '#', '', '', '0', '1', '3', '1', '1', 'tool:gen:import', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '1');
INSERT INTO `sys_menu` VALUES ('1059', '预览代码', '115', '4', '#', '', '', '0', '1', '3', '1', '1', 'tool:gen:preview', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '1');
INSERT INTO `sys_menu` VALUES ('1060', '生成代码', '115', '5', '#', '', '', '0', '1', '3', '1', '1', 'tool:gen:code', '#', '0', 'admin', '2022-05-21 08:30:54', null, '', null, '', '1');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `notice_id` int NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告标题',
  `notice_type` smallint NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` text COMMENT '公告内容',
  `status` smallint NOT NULL DEFAULT '0' COMMENT '公告状态（1正常 0关闭）',
  `creator_id` bigint NOT NULL COMMENT '创建者ID',
  `creator_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新者ID',
  `updater_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '备注',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='通知公告表';

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES ('1', '温馨提醒：2018-07-01 AgileBoot新版本发布啦', '2', '新版本内容~~~~~~~~~~', '1', '1', 'admin', '2022-05-21 08:30:55', '1', 'admin', '2022-08-29 20:12:37', '管理员', '0');
INSERT INTO `sys_notice` VALUES ('2', '维护通知：2018-07-01 AgileBoot系统凌晨维护', '1', '维护内容', '1', '1', 'admin', '2022-05-21 08:30:55', null, '', null, '管理员', '0');

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log` (
  `operation_id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `business_type` smallint NOT NULL DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `request_method` smallint NOT NULL DEFAULT '0' COMMENT '请求方式',
  `request_module` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '请求模块',
  `request_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '请求URL',
  `called_method` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '调用方法',
  `operator_type` smallint NOT NULL DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `user_id` bigint DEFAULT '0' COMMENT '用户ID',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '操作人员',
  `operator_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '操作人员ip',
  `operator_location` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '操作地点',
  `dept_id` bigint DEFAULT '0' COMMENT '部门ID',
  `dept_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '部门名称',
  `operation_param` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '请求参数',
  `operation_result` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '返回参数',
  `status` smallint NOT NULL DEFAULT '1' COMMENT '操作状态（1正常 0异常）',
  `error_stack` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '错误消息',
  `operation_time` datetime NOT NULL COMMENT '操作时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`operation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=379 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志记录';

-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `post_id` bigint NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int NOT NULL COMMENT '显示顺序',
  `status` smallint NOT NULL COMMENT '状态（1正常 0停用）',
  `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `creator_id` bigint DEFAULT NULL,
  `creator_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater_id` bigint DEFAULT NULL,
  `updater_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='岗位信息表';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('1', 'ceo', '董事长', '1', '1', '', null, 'admin', '2022-05-21 08:30:54', null, '', null, '0');
INSERT INTO `sys_post` VALUES ('2', 'se', '项目经理', '2', '1', '', null, 'admin', '2022-05-21 08:30:54', null, '', null, '0');
INSERT INTO `sys_post` VALUES ('3', 'hr', '人力资源', '3', '1', '', null, 'admin', '2022-05-21 08:30:54', null, '', null, '0');
INSERT INTO `sys_post` VALUES ('4', 'user', '普通员工', '5', '0', '', null, 'admin', '2022-05-21 08:30:54', '1', 'admin', '2022-09-02 20:50:32', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `data_scope` smallint DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3: 本部门数据权限 4: 本部门及以下数据权限 5: 本人权限）',
  `dept_id_set` varchar(1024) DEFAULT '' COMMENT '角色所拥有的部门数据权限',
  `status` smallint NOT NULL COMMENT '角色状态（1正常 0停用）',
  `creator_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `creator_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新者ID',
  `updater_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'admin', '1', '1', '', '1', null, 'admin', '2022-05-21 08:30:54', null, '', null, '超级管理员', '0');
INSERT INTO `sys_role` VALUES ('2', '普通角色', 'common', '3', '2', '', '1', null, 'admin', '2022-05-21 08:30:54', '1', 'admin', '2022-09-22 19:38:45', '普通角色', '0');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('104', '1');
INSERT INTO `sys_role_menu` VALUES ('104', '103');
INSERT INTO `sys_role_menu` VALUES ('104', '104');
INSERT INTO `sys_role_menu` VALUES ('104', '1008');
INSERT INTO `sys_role_menu` VALUES ('104', '1010');
INSERT INTO `sys_role_menu` VALUES ('108', '1');
INSERT INTO `sys_role_menu` VALUES ('108', '2');
INSERT INTO `sys_role_menu` VALUES ('108', '3');
INSERT INTO `sys_role_menu` VALUES ('108', '100');
INSERT INTO `sys_role_menu` VALUES ('108', '101');
INSERT INTO `sys_role_menu` VALUES ('108', '102');
INSERT INTO `sys_role_menu` VALUES ('108', '103');
INSERT INTO `sys_role_menu` VALUES ('108', '104');
INSERT INTO `sys_role_menu` VALUES ('108', '106');
INSERT INTO `sys_role_menu` VALUES ('108', '107');
INSERT INTO `sys_role_menu` VALUES ('108', '108');
INSERT INTO `sys_role_menu` VALUES ('108', '109');
INSERT INTO `sys_role_menu` VALUES ('108', '111');
INSERT INTO `sys_role_menu` VALUES ('108', '112');
INSERT INTO `sys_role_menu` VALUES ('108', '113');
INSERT INTO `sys_role_menu` VALUES ('108', '116');
INSERT INTO `sys_role_menu` VALUES ('108', '500');
INSERT INTO `sys_role_menu` VALUES ('108', '501');
INSERT INTO `sys_role_menu` VALUES ('108', '1001');
INSERT INTO `sys_role_menu` VALUES ('108', '1002');
INSERT INTO `sys_role_menu` VALUES ('108', '1003');
INSERT INTO `sys_role_menu` VALUES ('108', '1004');
INSERT INTO `sys_role_menu` VALUES ('108', '1005');
INSERT INTO `sys_role_menu` VALUES ('108', '1006');
INSERT INTO `sys_role_menu` VALUES ('108', '1007');
INSERT INTO `sys_role_menu` VALUES ('108', '1008');
INSERT INTO `sys_role_menu` VALUES ('108', '1009');
INSERT INTO `sys_role_menu` VALUES ('108', '1010');
INSERT INTO `sys_role_menu` VALUES ('108', '1011');
INSERT INTO `sys_role_menu` VALUES ('108', '1012');
INSERT INTO `sys_role_menu` VALUES ('108', '1013');
INSERT INTO `sys_role_menu` VALUES ('108', '1014');
INSERT INTO `sys_role_menu` VALUES ('108', '1015');
INSERT INTO `sys_role_menu` VALUES ('108', '1016');
INSERT INTO `sys_role_menu` VALUES ('108', '1017');
INSERT INTO `sys_role_menu` VALUES ('108', '1018');
INSERT INTO `sys_role_menu` VALUES ('108', '1019');
INSERT INTO `sys_role_menu` VALUES ('108', '1020');
INSERT INTO `sys_role_menu` VALUES ('108', '1021');
INSERT INTO `sys_role_menu` VALUES ('108', '1022');
INSERT INTO `sys_role_menu` VALUES ('108', '1023');
INSERT INTO `sys_role_menu` VALUES ('108', '1024');
INSERT INTO `sys_role_menu` VALUES ('108', '1025');
INSERT INTO `sys_role_menu` VALUES ('108', '1031');
INSERT INTO `sys_role_menu` VALUES ('108', '1032');
INSERT INTO `sys_role_menu` VALUES ('108', '1033');
INSERT INTO `sys_role_menu` VALUES ('108', '1034');
INSERT INTO `sys_role_menu` VALUES ('108', '1035');
INSERT INTO `sys_role_menu` VALUES ('108', '1036');
INSERT INTO `sys_role_menu` VALUES ('108', '1037');
INSERT INTO `sys_role_menu` VALUES ('108', '1038');
INSERT INTO `sys_role_menu` VALUES ('108', '1039');
INSERT INTO `sys_role_menu` VALUES ('108', '1040');
INSERT INTO `sys_role_menu` VALUES ('108', '1041');
INSERT INTO `sys_role_menu` VALUES ('108', '1042');
INSERT INTO `sys_role_menu` VALUES ('108', '1043');
INSERT INTO `sys_role_menu` VALUES ('108', '1044');
INSERT INTO `sys_role_menu` VALUES ('108', '1045');
INSERT INTO `sys_role_menu` VALUES ('108', '1046');
INSERT INTO `sys_role_menu` VALUES ('108', '1047');
INSERT INTO `sys_role_menu` VALUES ('108', '1048');
INSERT INTO `sys_role_menu` VALUES ('108', '2000');
INSERT INTO `sys_role_menu` VALUES ('108', '2001');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `post_id` bigint DEFAULT NULL COMMENT '职位id',
  `role_id` bigint DEFAULT NULL COMMENT '角色id',
  `dept_id` bigint DEFAULT NULL COMMENT '部门ID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `user_type` smallint DEFAULT '0' COMMENT '用户类型（00系统用户）',
  `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '用户邮箱',
  `phone_number` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '手机号码',
  `sex` smallint DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '头像地址',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '密码',
  `status` smallint NOT NULL DEFAULT '0' COMMENT '帐号状态（1正常 2停用 3冻结）',
  `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `creator_id` bigint DEFAULT NULL COMMENT '更新者ID',
  `creator_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新者ID',
  `updater_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '1', '1', '103', 'admin', 'valarchie1', '0', 'agileboot@1631.com', '15888888889', '0', '/profile//avatar/20220814122428_blob_81ea3759fd45478ab0d875041afa5a28.jpeg', '$2a$10$rb1wRoEIkLbIknREEN1LH.FGs4g0oOS5t6l5LQ793nRaFO.SPHDHy', '1', '127.0.0.1', '2022-10-06 17:00:06', null, 'admin', '2022-05-21 08:30:54', '1', 'admin', '2022-10-06 17:00:06', '管理员', '0');
INSERT INTO `sys_user` VALUES ('2', '2', '2', '105', 'ag', 'valarchie', '0', 'agileboot@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '1', '127.0.0.1', '2022-05-21 08:30:54', null, 'admin', '2022-05-21 08:30:54', null, '', null, '测试员', '0');
INSERT INTO `sys_user` VALUES ('107', '3', '2', '104', 'ag12345', '测试', '0', '222@4234.com', '18859976673', '2', '', '12345678', '1', '', null, '1', 'admin', '2022-10-05 22:01:43', '1', 'admin', '2022-10-05 22:02:03', '12312312', '1');
INSERT INTO `sys_user` VALUES ('108', '1', '108', '101', 'ag888888', '23423423@42432.com', '0', '234452@42344aa.com', '18849976671', '2', '', '$2a$10$T/K2DSLtnr4yH9z98kx9AOUFlDf4QOBAwBjcY2WNFfDsNxR1NIv8u', '1', '127.0.0.1', '2022-10-05 22:54:41', '1', 'admin', '2022-10-05 22:26:31', '1', 'admin', '2022-10-05 22:54:41', '23424234234234', '0');
