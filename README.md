<p align="center">

[//]: # (	<img alt="logo" src="https://oscimg.oschina.net/oscnet/up-d3d0a9303e11d522a06cd263f3079027715.png">)
</p>
<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">AgileBoot v1.0.0</h1>
<h4 align="center">基于SpringBoot+Vue3前后端分离的Java快速开发框架</h4>
<p align="center">
</p>

## 平台简介

AgileBoot是一套全部开源的快速开发平台，毫无保留给个人及企业免费使用。

* 前端采用Vue3、Element UI。[AgileBoot-Front-End](https://github.com/valarchie/AgileBoot-Back-End) ，保持同步更新。
* 后端采用Spring Boot、Spring Security、Redis & Jwt、Mybatis Plus。
* 权限认证使用Jwt，支持多终端认证系统。
* 支持加载动态权限菜单，多方式轻松权限控制。

#### 由来
本项目基于Ruoyi项目进行完全重构改造。  
首先非常感谢Ruoyi作者。但是Ruoyi项目存在太多缺陷。
- 命名比较乱七八糟（很多很糟糕的命名，包括机翻英语乱用）
- 项目分包以及模块比较乱
- 比较原始的Controller > Service > DAO的开发模式。过于面向过程。
- 一大堆自己造的轮子，并且没有UT覆盖。
- 大量逻辑嵌套在if else块当中
- 值的前后不统一，比如有的地方1代表是，有的地方1代表否
- 很多很奇怪的代码写法（比如return result > 0 ? true:false..    一言难尽）


于是我做了大量的重构工作。

#### 规范：
- 切分不同环境的启动文件
- 统一设计异常类
- 统一设计错误码并集中管理
- 统一系统内的变量并集中管理
- 统一返回模型
- 引入Google代码格式化模板（Ruoyi的代码格式很另类....）
- 后端代码的命名基本都整改OK
- 前端代码的命名也非常混乱，进行了整改
#### 整改：
- 引入hutool包以及guava包去掉大量自己造的轮子（大量工作.....）
- 引入lombok去除大量getter setter代码
- 调整日志级别
- 字典类型数据完全用Enum进行代替
- 移除SQL注入的Filter，因为迁移到Mybatis Plus就不会有这个注入的问题
- XSS直接通过JSON拦截过滤。
- 替换掉很多Deprecated的类以及配置
#### 优化：
- 优化异步服务
- 优化Excel相关类的设计，采用hutool包成熟的轮子
- 权限判断使用缓存
- IP地址查询引入离线包
- 引入多级缓存体系
- 启动优化

#### 重构内容



--- 

* 特别鸣谢：[element](https://github.com/ElemeFE/element) ，[vue-element-admin](https://github.com/PanJiaChen/vue-element-admin) ，[eladmin-web](https://github.com/elunez/eladmin-web) 。
* 阿里云折扣场：https://cn.aliyun.com/minisite/goods?from_alibabacloud=&userCode=djbhhf1x
* 腾讯云秒杀场：[点我领取](https://url.cn/mKgcHVNb) &nbsp;&nbsp;
* 阿里云优惠券：https://cn.aliyun.com/minisite/goods?from_alibabacloud=&userCode=djbhhf1x
* 腾讯云优惠券：[点我领取](https://url.cn/mKgcHVNb) &nbsp;&nbsp;

## 内置功能

1.  用户管理：用户是系统操作者，该功能主要完成系统用户配置。
2.  部门管理：配置系统组织机构（公司、部门、小组），树结构展现支持数据权限。
3.  岗位管理：配置系统用户所属担任职务。
4.  菜单管理：配置系统菜单，操作权限，按钮权限标识等。
5.  角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分。
6.  参数管理：对系统动态配置常用参数。
7.  通知公告：系统通知公告信息发布维护。
8.  操作日志：系统正常操作日志记录和查询；系统异常信息日志记录和查询。
9.  登录日志：系统登录日志记录查询包含登录异常。
10. 在线用户：当前系统中活跃用户状态监控。
11. 系统接口：根据业务代码自动生成相关的api接口文档。
12. 服务监控：监视当前系统CPU、内存、磁盘、堆栈等相关信息。
13. 缓存监控：对系统的缓存信息查询，命令统计等。
14. 在线构建器：拖动表单元素生成相应的HTML代码。
15. 连接池监视：监视当前系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈。

## 在线体验

- admin/admin123
- 如果该项目对您有帮助，可以请作者喝个咖啡~
  ![扫一扫](https://oscimg.oschina.net/oscnet/up-261828407c9089ad1cc0ce3f41a0ef3fbc0.png)


演示地址：暂无， 可先参考Ruoyi的演示网站 http://vue.ruoyi.vip  
文档地址：TODO http://doc.ruoyi.vip

## 演示图

<table>
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/cd1f90be5f2684f4560c9519c0f2a232ee8.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/1cbcf0e6f257c7d3a063c0e3f2ff989e4b3.jpg"/></td>
    </tr>
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-8074972883b5ba0622e13246738ebba237a.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-9f88719cdfca9af2e58b352a20e23d43b12.png"/></td>
    </tr>
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-39bf2584ec3a529b0d5a3b70d15c9b37646.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-936ec82d1f4872e1bc980927654b6007307.png"/></td>
    </tr>
	<tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-b2d62ceb95d2dd9b3fbe157bb70d26001e9.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-d67451d308b7a79ad6819723396f7c3d77a.png"/></td>
    </tr>	 
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/5e8c387724954459291aafd5eb52b456f53.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/644e78da53c2e92a95dfda4f76e6d117c4b.jpg"/></td>
    </tr>
	<tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-8370a0d02977eebf6dbf854c8450293c937.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-49003ed83f60f633e7153609a53a2b644f7.png"/></td>
    </tr>
	<tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-d4fe726319ece268d4746602c39cffc0621.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-c195234bbcd30be6927f037a6755e6ab69c.png"/></td>
    </tr>

</table>


### 注意事项
- IDEA会自动将.properties文件的编码设置为ISO-8859-1,请在Settings > Editor > File Encodings > Properties Files > 设置为UTF-8
- 如需要生成新的表，请使用CodeGenerator进行生成。（大概看一下代码就知道怎么填啦）
- 项目操作说明：https://www.cnblogs.com/valarchie/p/16777336.html

## AgileBoot前后端分离交流群
<!-- TODO 整改 -->
QQ群：  [![加入QQ群](https://img.shields.io/badge/1398880-blue.svg)](https://qm.qq.com/cgi-bin/qm/qr?k=TR5guoXS0HssErVWefmdFRirJvfpEvp1&jump_from=webapi&authKey=VkWMmVhp/pNdWuRD8sqgM+Sv2+Vy2qCJQSeLmeXlLtfER2RJBi6zL56PdcRlCmTs) 点击按钮入群。
