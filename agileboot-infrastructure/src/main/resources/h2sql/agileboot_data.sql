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

-- SET FOREIGN_KEY_CHECKS=0;


-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', '主框架页-默认皮肤样式名称', 'sys.index.skinName', '["skin-blue","skin-green","skin-purple","skin-red","skin-yellow"]', 'skin-blue', '1', null, null,  '2022-08-28 22:12:19', '2022-05-21 08:30:55', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow', '0');
INSERT INTO `sys_config` VALUES ('2', '用户管理-账号初始密码', 'sys.user.initPassword', '', '1234567', '1', null, null,  '2022-08-28 21:54:19', '2022-05-21 08:30:55', '初始化密码 123456', '0');
INSERT INTO `sys_config` VALUES ('3', '主框架页-侧边栏主题', 'sys.index.sideTheme', '["theme-dark","theme-light"]', 'theme-dark', '1', null,  null,  '2022-08-28 22:12:15', '2022-08-20 08:30:55', '深色主题theme-dark，浅色主题theme-light', '0');
INSERT INTO `sys_config` VALUES ('4', '账号自助-验证码开关', 'sys.account.captchaOnOff', '["true","false"]', 'false', '0', null,  null,  '2022-08-28 22:03:37', '2022-05-21 08:30:55', '是否开启验证码功能（true开启，false关闭）', '0');
INSERT INTO `sys_config` VALUES ('5', '账号自助-是否开启用户注册功能', 'sys.account.registerUser', '["true","false"]', 'true', '0', null,  '1',  '2022-10-05 22:18:57', '2022-05-21 08:30:55', '是否开启注册用户功能（true开启，false关闭）', '0');


-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '0', '0', 'AgileBoot科技', '0', null, 'valarchie', '15888888888', 'valarchie@163.com', '1', null,  '2022-05-21 08:30:54', null,  null, '0');
INSERT INTO `sys_dept` VALUES ('2', '1', '0,1', '深圳总公司', '1', null, 'valarchie', '15888888888', 'valarchie@163.com', '1', null, '2022-05-21 08:30:54', null, null, '0');
INSERT INTO `sys_dept` VALUES ('3', '1', '0,1', '长沙分公司', '2', null, 'valarchie', '15888888888', 'valarchie@163.com', '1', null, '2022-05-21 08:30:54', null, null, '0');
INSERT INTO `sys_dept` VALUES ('4', '2', '0,1,2', '研发部门', '1', null, 'valarchie', '15888888888', 'valarchie@163.com', '1', null, '2022-05-21 08:30:54', null, null, '0');
INSERT INTO `sys_dept` VALUES ('5', '2', '0,1,2', '市场部门', '2', null, 'valarchie', '15888888888', 'valarchie@163.com', '1', null, '2022-05-21 08:30:54', null, null, '0');
INSERT INTO `sys_dept` VALUES ('6', '2', '0,1,2', '测试部门', '3', null, 'valarchie', '15888888888', 'valarchie@163.com', '1', null, '2022-05-21 08:30:54', null, null, '0');
INSERT INTO `sys_dept` VALUES ('7', '2', '0,1,2', '财务部门', '4', null, 'valarchie', '15888888888', 'valarchie@163.com', '1', null, '2022-05-21 08:30:54', null, null, '0');
INSERT INTO `sys_dept` VALUES ('8', '2', '0,1,2', '运维部门', '5', null, 'valarchie', '15888888888', 'valarchie@163.com', '1', null, '2022-05-21 08:30:54', null, null, '0');
INSERT INTO `sys_dept` VALUES ('9', '3', '0,1,3', '市场部门', '1', null, 'valarchie', '15888888888', 'valarchie@163.com', '1', null, '2022-05-21 08:30:54', null, null, '0');
INSERT INTO `sys_dept` VALUES ('10', '3', '0,1,3', '财务部门', '2', null, 'valarchie', '15888888888', 'valarchie@163.com', '0', null,  '2022-05-21 08:30:54', null,  null, '0');



-- ----------------------------
-- Records of sys_login_info
-- ----------------------------

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '0', '1', 'system', null, '', '0', '1', '1', '1', '1', '', 'system', '0',  '2022-05-21 08:30:54', null,  null, '系统管理目录', '0');
INSERT INTO `sys_menu` VALUES ('2', '系统监控', '0', '2', 'monitor', null, '', '0', '1', '1', '1', '1', '', 'monitor', '0',  '2022-05-21 08:30:54', null,  null, '系统监控目录', '0');
INSERT INTO `sys_menu` VALUES ('3', '系统工具', '0', '3', 'tool', null, '', '0', '1', '1', '1', '1', '', 'tool', '0',  '2022-05-21 08:30:54', null,  null, '系统工具目录', '0');
INSERT INTO `sys_menu` VALUES ('4', 'AgileBoot官网', '0', '4', 'https://juejin.cn/column/7159946528827080734', null, '', '1', '1', '1', '1', '1', '', 'guide', '0',  '2022-05-21 08:30:54', null,  null, 'Agileboot官网地址', '0');
INSERT INTO `sys_menu` VALUES ('5', '用户管理', '1', '1', 'user', 'system/user/index', '', '0', '1', '2', '1', '1', 'system:user:list', 'user', '0',  '2022-05-21 08:30:54', null,  null, '用户管理菜单', '0');
INSERT INTO `sys_menu` VALUES ('6', '角色管理', '1', '2', 'role', 'system/role/index', '', '0', '1', '2', '1', '1', 'system:role:list', 'peoples', '0',  '2022-05-21 08:30:54', null,  null, '角色管理菜单', '0');
INSERT INTO `sys_menu` VALUES ('7', '菜单管理', '1', '3', 'menu', 'system/menu/index', '', '0', '1', '2', '1', '1', 'system:menu:list', 'tree-table', '0',  '2022-05-21 08:30:54', null,  null, '菜单管理菜单', '0');
INSERT INTO `sys_menu` VALUES ('8', '部门管理', '1', '4', 'dept', 'system/dept/index', '', '0', '1', '2', '1', '1', 'system:dept:list', 'tree', '0',  '2022-05-21 08:30:54', null,  null, '部门管理菜单', '0');
INSERT INTO `sys_menu` VALUES ('9', '岗位管理', '1', '5', 'post', 'system/post/index', '', '0', '1', '2', '1', '1', 'system:post:list', 'post', '0',  '2022-05-21 08:30:54', null,  null, '岗位管理菜单', '0');
INSERT INTO `sys_menu` VALUES ('10', '参数设置', '1', '7', 'config', 'system/config/index', '', '0', '1', '2', '1', '1', 'system:config:list', 'edit', '0',  '2022-05-21 08:30:54', null,  null, '参数设置菜单', '0');
INSERT INTO `sys_menu` VALUES ('11', '通知公告', '1', '8', 'notice', 'system/notice/index', '', '0', '1', '2', '1', '1', 'system:notice:list', 'message', '0',  '2022-05-21 08:30:54', null,  null, '通知公告菜单', '0');
INSERT INTO `sys_menu` VALUES ('12', '日志管理', '1', '9', 'log', '', '', '0', '1', '1', '1', '1', '', 'log', '0',  '2022-05-21 08:30:54', null,  null, '日志管理菜单', '0');
INSERT INTO `sys_menu` VALUES ('13', '在线用户', '2', '1', 'online', 'monitor/online/index', '', '0', '1', '2', '1', '1', 'monitor:online:list', 'online', '0',  '2022-05-21 08:30:54', null,  null, '在线用户菜单', '0');
INSERT INTO `sys_menu` VALUES ('14', '数据监控', '2', '3', 'druid', 'monitor/druid/index', '', '0', '1', '2', '1', '1', 'monitor:druid:list', 'druid', '0',  '2022-05-21 08:30:54', null,  null, '数据监控菜单', '0');
INSERT INTO `sys_menu` VALUES ('15', '服务监控', '2', '4', 'server', 'monitor/server/index', '', '0', '1', '2', '1', '1', 'monitor:server:list', 'server', '0',  '2022-05-21 08:30:54', null,  null, '服务监控菜单', '0');
INSERT INTO `sys_menu` VALUES ('16', '缓存监控', '2', '5', 'cache', 'monitor/cache/index', '', '0', '1', '2', '1', '1', 'monitor:cache:list', 'redis', '0',  '2022-05-21 08:30:54', null,  null, '缓存监控菜单', '0');
INSERT INTO `sys_menu` VALUES ('17', '系统接口', '3', '3', 'swagger', 'tool/swagger/index', '', '0', '1', '2', '1', '1', 'tool:swagger:list', 'swagger', '0',  '2022-05-21 08:30:54', null,  null, '系统接口菜单', '0');
INSERT INTO `sys_menu` VALUES ('18', '操作日志', '12', '1', 'operlog', 'monitor/operlog/index', '', '0', '1', '2', '1', '1', 'monitor:operlog:list', 'form', '0',  '2022-05-21 08:30:54', null,  null, '操作日志菜单', '0');
INSERT INTO `sys_menu` VALUES ('19', '登录日志', '12', '2', 'logininfor', 'monitor/logininfor/index', '', '0', '1', '2', '1', '1', 'monitor:logininfor:list', 'logininfor', '0',  '2022-05-21 08:30:54', null,  null, '登录日志菜单', '0');
INSERT INTO `sys_menu` VALUES ('20', '用户查询', '5', '1', '', '', '', '0', '1', '3', '1', '1', 'system:user:query', '#', '0',  '2022-05-21 08:30:54', null, null, '', '0');
INSERT INTO `sys_menu` VALUES ('21', '用户新增', '5', '2', '', '', '', '0', '1', '3', '1', '1', 'system:user:add', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('22', '用户修改', '5', '3', '', '', '', '0', '1', '3', '1', '1', 'system:user:edit', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('23', '用户删除', '5', '4', '', '', '', '0', '1', '3', '1', '1', 'system:user:remove', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('24', '用户导出', '5', '5', '', '', '', '0', '1', '3', '1', '1', 'system:user:export', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('25', '用户导入', '5', '6', '', '', '', '0', '1', '3', '1', '1', 'system:user:import', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('26', '重置密码', '5', '7', '', '', '', '0', '1', '3', '1', '1', 'system:user:resetPwd', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('27', '角色查询', '6', '1', '', '', '', '0', '1', '3', '1', '1', 'system:role:query', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('28', '角色新增', '6', '2', '', '', '', '0', '1', '3', '1', '1', 'system:role:add', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('29', '角色修改', '6', '3', '', '', '', '0', '1', '3', '1', '1', 'system:role:edit', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('30', '角色删除', '6', '4', '', '', '', '0', '1', '3', '1', '1', 'system:role:remove', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('31', '角色导出', '6', '5', '', '', '', '0', '1', '3', '1', '1', 'system:role:export', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('32', '菜单查询', '7', '1', '', '', '', '0', '1', '3', '1', '1', 'system:menu:query', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('33', '菜单新增', '7', '2', '', '', '', '0', '1', '3', '1', '1', 'system:menu:add', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('34', '菜单修改', '7', '3', '', '', '', '0', '1', '3', '1', '1', 'system:menu:edit', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('35', '菜单删除', '7', '4', '', '', '', '0', '1', '3', '1', '1', 'system:menu:remove', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('36', '部门查询', '8', '1', '', '', '', '0', '1', '3', '1', '1', 'system:dept:query', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('37', '部门新增', '8', '2', '', '', '', '0', '1', '3', '1', '1', 'system:dept:add', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('38', '部门修改', '8', '3', '', '', '', '0', '1', '3', '1', '1', 'system:dept:edit', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('39', '部门删除', '8', '4', '', '', '', '0', '1', '3', '1', '1', 'system:dept:remove', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('40', '岗位查询', '9', '1', '', '', '', '0', '1', '3', '1', '1', 'system:post:query', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('41', '岗位新增', '9', '2', '', '', '', '0', '1', '3', '1', '1', 'system:post:add', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('42', '岗位修改', '9', '3', '', '', '', '0', '1', '3', '1', '1', 'system:post:edit', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('43', '岗位删除', '9', '4', '', '', '', '0', '1', '3', '1', '1', 'system:post:remove', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('44', '岗位导出', '9', '5', '', '', '', '0', '1', '3', '1', '1', 'system:post:export', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('45', '参数查询', '10', '1', '#', '', '', '0', '1', '3', '1', '1', 'system:config:query', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('46', '参数新增', '10', '2', '#', '', '', '0', '1', '3', '1', '1', 'system:config:add', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('47', '参数修改', '10', '3', '#', '', '', '0', '1', '3', '1', '1', 'system:config:edit', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('48', '参数删除', '10', '4', '#', '', '', '0', '1', '3', '1', '1', 'system:config:remove', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('49', '参数导出', '10', '5', '#', '', '', '0', '1', '3', '1', '1', 'system:config:export', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('50', '公告查询', '11', '1', '#', '', '', '0', '1', '3', '1', '1', 'system:notice:query', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('51', '公告新增', '11', '2', '#', '', '', '0', '1', '3', '1', '1', 'system:notice:add', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('52', '公告修改', '11', '3', '#', '', '', '0', '1', '3', '1', '1', 'system:notice:edit', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('53', '公告删除', '11', '4', '#', '', '', '0', '1', '3', '1', '1', 'system:notice:remove', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('54', '操作查询', '18', '1', '#', '', '', '0', '1', '3', '1', '1', 'monitor:operlog:query', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('55', '操作删除', '18', '2', '#', '', '', '0', '1', '3', '1', '1', 'monitor:operlog:remove', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('56', '日志导出', '18', '4', '#', '', '', '0', '1', '3', '1', '1', 'monitor:operlog:export', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('57', '登录查询', '19', '1', '#', '', '', '0', '1', '3', '1', '1', 'monitor:logininfor:query', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('58', '登录删除', '19', '2', '#', '', '', '0', '1', '3', '1', '1', 'monitor:logininfor:remove', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('59', '日志导出', '19', '3', '#', '', '', '0', '1', '3', '1', '1', 'monitor:logininfor:export', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('60', '在线查询', '13', '1', '#', '', '', '0', '1', '3', '1', '1', 'monitor:online:query', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('61', '批量强退', '13', '2', '#', '', '', '0', '1', '3', '1', '1', 'monitor:online:batchLogout', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');
INSERT INTO `sys_menu` VALUES ('62', '单条强退', '13', '3', '#', '', '', '0', '1', '3', '1', '1', 'monitor:online:forceLogout', '#', '0',  '2022-05-21 08:30:54', null,  null, '', '0');


-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES ('1', '温馨提醒：2018-07-01 AgileBoot新版本发布啦', '2', '新版本内容~~~~~~~~~~', '1', '1',  '2022-05-21 08:30:55', '1', '2022-08-29 20:12:37', '管理员', '0');
INSERT INTO `sys_notice` VALUES ('2', '维护通知：2018-07-01 AgileBoot系统凌晨维护', '1', '维护内容', '1', '1',  '2022-05-21 08:30:55', null,  null, '管理员', '0');


-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------



-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('1', 'ceo', '董事长', '1', '1', '', null,  '2022-05-21 08:30:54', null,  null, '0');
INSERT INTO `sys_post` VALUES ('2', 'se', '项目经理', '2', '1', '', null,  '2022-05-21 08:30:54', null,  null, '0');
INSERT INTO `sys_post` VALUES ('3', 'hr', '人力资源', '3', '1', '', null,  '2022-05-21 08:30:54', null,  null, '0');
INSERT INTO `sys_post` VALUES ('4', 'user', '普通员工', '5', '0', '', null,  '2022-05-21 08:30:54', null,  null, '0');


-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'admin', '1', '1', '', '1', null,  '2022-05-21 08:30:54', null,  null, '超级管理员', '0');
INSERT INTO `sys_role` VALUES ('2', '普通角色', 'common', '3', '2', '', '1', null,  '2022-05-21 08:30:54', null,  null, '普通角色', '0');
INSERT INTO `sys_role` VALUES ('3', '闲置角色', 'unused', '4', '2', '', '0', null,  '2022-05-21 08:30:54', null,  null, '未使用的角色', '0');



-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('2', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '2');
INSERT INTO `sys_role_menu` VALUES ('2', '3');
INSERT INTO `sys_role_menu` VALUES ('2', '4');
INSERT INTO `sys_role_menu` VALUES ('2', '5');
INSERT INTO `sys_role_menu` VALUES ('2', '6');
INSERT INTO `sys_role_menu` VALUES ('2', '7');
INSERT INTO `sys_role_menu` VALUES ('2', '8');
INSERT INTO `sys_role_menu` VALUES ('2', '9');
INSERT INTO `sys_role_menu` VALUES ('2', '10');
INSERT INTO `sys_role_menu` VALUES ('2', '11');
INSERT INTO `sys_role_menu` VALUES ('2', '12');
INSERT INTO `sys_role_menu` VALUES ('2', '13');
INSERT INTO `sys_role_menu` VALUES ('2', '14');
INSERT INTO `sys_role_menu` VALUES ('2', '15');
INSERT INTO `sys_role_menu` VALUES ('2', '16');
INSERT INTO `sys_role_menu` VALUES ('2', '17');
INSERT INTO `sys_role_menu` VALUES ('2', '18');
INSERT INTO `sys_role_menu` VALUES ('2', '19');
INSERT INTO `sys_role_menu` VALUES ('2', '20');
INSERT INTO `sys_role_menu` VALUES ('2', '21');
INSERT INTO `sys_role_menu` VALUES ('2', '22');
INSERT INTO `sys_role_menu` VALUES ('2', '23');
INSERT INTO `sys_role_menu` VALUES ('2', '24');
INSERT INTO `sys_role_menu` VALUES ('2', '25');
INSERT INTO `sys_role_menu` VALUES ('2', '26');
INSERT INTO `sys_role_menu` VALUES ('2', '27');
INSERT INTO `sys_role_menu` VALUES ('2', '28');
INSERT INTO `sys_role_menu` VALUES ('2', '29');
INSERT INTO `sys_role_menu` VALUES ('2', '30');
INSERT INTO `sys_role_menu` VALUES ('2', '31');
INSERT INTO `sys_role_menu` VALUES ('2', '32');
INSERT INTO `sys_role_menu` VALUES ('2', '33');
INSERT INTO `sys_role_menu` VALUES ('2', '34');
INSERT INTO `sys_role_menu` VALUES ('2', '35');
INSERT INTO `sys_role_menu` VALUES ('2', '36');
INSERT INTO `sys_role_menu` VALUES ('2', '37');
INSERT INTO `sys_role_menu` VALUES ('2', '38');
INSERT INTO `sys_role_menu` VALUES ('2', '39');
INSERT INTO `sys_role_menu` VALUES ('2', '40');
INSERT INTO `sys_role_menu` VALUES ('2', '41');
INSERT INTO `sys_role_menu` VALUES ('2', '42');
INSERT INTO `sys_role_menu` VALUES ('2', '43');
INSERT INTO `sys_role_menu` VALUES ('2', '44');
INSERT INTO `sys_role_menu` VALUES ('2', '45');
INSERT INTO `sys_role_menu` VALUES ('2', '46');
INSERT INTO `sys_role_menu` VALUES ('2', '47');
INSERT INTO `sys_role_menu` VALUES ('2', '48');
INSERT INTO `sys_role_menu` VALUES ('2', '49');
INSERT INTO `sys_role_menu` VALUES ('2', '50');
INSERT INTO `sys_role_menu` VALUES ('2', '51');
INSERT INTO `sys_role_menu` VALUES ('2', '52');
INSERT INTO `sys_role_menu` VALUES ('2', '53');
INSERT INTO `sys_role_menu` VALUES ('2', '54');
INSERT INTO `sys_role_menu` VALUES ('2', '55');
INSERT INTO `sys_role_menu` VALUES ('2', '56');
INSERT INTO `sys_role_menu` VALUES ('2', '57');
INSERT INTO `sys_role_menu` VALUES ('2', '58');
INSERT INTO `sys_role_menu` VALUES ('2', '59');
INSERT INTO `sys_role_menu` VALUES ('2', '60');
INSERT INTO `sys_role_menu` VALUES ('2', '61');
-- roleId = 2的权限 特地少一个 方便测试
INSERT INTO `sys_role_menu` VALUES ('3', '1');




-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '1', '1', '4', 'admin', 'valarchie1', '0', 'agileboot@163.com', '15888888889', '0', '', '$2a$10$rb1wRoEIkLbIknREEN1LH.FGs4g0oOS5t6l5LQ793nRaFO.SPHDHy', '1', '127.0.0.1', '2022-10-06 17:00:06', 1, null,  '2022-05-21 08:30:54', '1',  '2022-10-06 17:00:06', '管理员', '0');
INSERT INTO `sys_user` VALUES ('2', '2', '2', '5', 'ag1', 'valarchie2', '0', 'agileboot1@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '1', '127.0.0.1', '2022-05-21 08:30:54', 0, null,  '2022-05-21 08:30:54', null,  null, '测试员1', '0');
INSERT INTO `sys_user` VALUES ('3', '2', '0', '5', 'ag2', 'valarchie3', '0', 'agileboot2@qq.com', '15666666667', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '1', '127.0.0.1', '2022-05-21 08:30:54', 0, null,  '2022-05-21 08:30:54', null,  null, '测试员2', '0');
