
<p align="center">
      <img src="https://img.shields.io/badge/Release-V1.8.0-green.svg" alt="Downloads">
      <img src="https://img.shields.io/badge/JDK-1.8+-green.svg" alt="Build Status">
  <img src="https://img.shields.io/badge/license-MIT-blue.svg" alt="Build Status">
   <img src="https://img.shields.io/badge/Spring%20Boot-2.7.1-blue.svg" alt="Downloads">
   <a target="_blank" href="https://bladex.vip">
   <img src="https://img.shields.io/badge/Author-valarchie-ff69b4.svg" alt="Downloads">
 </a>
 <a target="_blank" href="https://bladex.vip">
   <img src="https://img.shields.io/badge/Copyright%20-@Agileboot-%23ff3f59.svg" alt="Downloads">
 </a>
 </p>  
<p align="center">

<img alt="logo" height="200" src="https://oscimg.oschina.net/oscnet/up-eda2a402cc061f1f5f40d9ac4c084f4c98c.png">
</p>
<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">AgileBoot v1.8.0</h1>

<h4 align="center">基于SpringBoot+Vue3前后端分离的Java快速开发脚手架</h4>
<p align="center">
</p>

## ⚡平台简介⚡

AgileBoot是一套开源的全栈精简快速开发平台，毫无保留给个人及企业免费使用。本项目的目标是做一款精简可靠，代码风格优良，项目规范的小型开发脚手架。
适合个人开发者的小型项目或者公司内部项目使用。也可作为供初学者学习使用的案例。


* 前端采用Vue3、Element Plus。对应前端仓库 [AgileBoot-Front-End](https://github.com/valarchie/AgileBoot-Front-End) ，保持同步更新。
* 后端采用Spring Boot、Spring Security & Jwt、Redis & MySql、Mybatis Plus、Hutool工具包。
* 权限认证使用Jwt，支持多终端认证系统。
* 支持注解式主从数据库切换，注解式请求限流，注解式重复请求拦截。
* 支持注解式菜单权限拦截，注解式数据权限拦截。
* 支持加载动态权限菜单，实时权限控制。
* ***有大量的单元测试，集成测试覆盖确保业务逻辑正确***。

> 有任何问题或者建议，可以在 _Issues_ 中提给作者。  
> 
> 您的Issue比Star更重要
>
> 如果觉得项目对您有帮助，可以来个Star ⭐


## 💥 在线体验 💥
演示地址：
- www.agileboot.vip
- www.agileboot.cc
>  账号密码：admin/admin123


## 🌴 项目背景 🌴
业余时间想做一些个人小项目，一开始找了很多开源项目比如Ruoyi / Jeecg / ElAdmin / RenRen-Fast / Guns / EAdmin  
最后本项目选择基于Ruoyi项目进行完全重构改造。  
首先非常感谢Ruoyi作者。但是Ruoyi项目存在太多缺陷。
- 命名比较乱七八糟（很多很糟糕的命名，包括机翻英语乱用）
- 项目分包以及模块比较乱
- 比较原始的Controller > Service > DAO的开发模式。过于面向过程。
- 一大堆自己造的轮子，并且没有UT覆盖。
- 大量逻辑嵌套在if else块当中
- 值的前后不统一，比如有的地方1代表是，有的地方1代表否
- 很多很奇怪的代码写法（比如return result > 0 ? true:false..    一言难尽）
- 业务逻辑不集中，代码可读性较差。


于是我做了大量的重构工作。

### 重构内容

- 规范：
    - 切分不同环境的启动文件
    - 统一设计异常类
    - 统一设计错误码并集中处理异常
    - 统一系统内的变量并集中管理
    - 统一返回模型
    - 引入Google代码格式化模板
    - 后端代码的命名基本都整改OK
    - 前端代码的命名也非常混乱，进行了整改
    - 规范系统内的常量
- 整改：
    - 引入hutool包以及guava包去掉大量自己造的轮子，尽可能使用现成的轮子
    - 去除代码中大量的warning
    - 引入lombok去除大量getter setter代码
    - 调整日志级别
    - 字典类型数据完全用Enum进行代替
    - 移除SQL注入的Filter，因为迁移到Mybatis Plus就不会有这个注入的问题
    - XSS直接通过JSON序列化进行转义。
    - 替换掉很多Deprecated的类以及配置
    - 替换fastJson为Jackson
    - 数据库的整体重构设计，缩减至10张表。
    - 重新设计异步代码
    - 前后端密码加密传输（更严谨的话，还是需要HTTPS）
    - 重构权限校验和数据权限校验（直接都通过注解的形式）
- 优化：
    - 优化异步服务
    - 优化Redis缓存类，封装各个业务缓存，提供多级缓存实现（Redis+Guava）
    - 提供三个层级的缓存供使用者调用（Map,Guava,Redis使用者可依情况选择使用哪个缓存类）
    - 权限判断使用多级缓存
    - IP地址查询引入离线包
    - 前端优化字典数据缓存
    - 启动优化
    - i18n支持
    - 优化excel工具类，代码更加简洁
    - 将所有逻辑集中于Domain模块中
    - 切面记录修改者和创建者
    - 统一设置事务

## ✨ 使用 ✨


### 开发环境

- JDK
- Mysql
- Redis
- Node.js

### 技术栈

| 技术             | 说明              | 版本                |
|----------------|-----------------|-------------------|
| `springboot`   | Java项目必备框架      | 2.7               |
| `druid`        | alibaba数据库连接池   | 1.2.8             |
| `springdoc`    | 文档生成            | 3.0.0             |
| `mybatis-plus` | 数据库框架           | 3.5.2             |
| `hutool`       | 国产工具包（简单易用）     | 3.5.2             |
| `mockito`      | 单元测试模拟          | 1.10.19           |
| `guava`        | 谷歌工具包（提供简易缓存实现） | 31.0.1-jre        |
| `junit`        | 单元测试            | 1.10.19           |
| `h2`           | 内存数据库           | 1.10.19           |
| `jackson`      | 比较安全的Json框架     | follow springboot |
| `knife4j`      | 接口文档框架          | 3.0.3             |
| `Spring Task`  | 定时任务框架（适合小型项目）  | follow springboot |


### 启动说明

#### 前置准备： 下载前后端代码

```
git clone https://github.com/valarchie/AgileBoot-Back-End
git clone https://github.com/valarchie/AgileBoot-Front-End
```

#### 安装好Mysql和Redis


#### 后端启动
```
1. 生成所需的数据库表
找到后端项目根目录下的sql目录中的agileboot_xxxxx.sql脚本文件。 导入到你新建的数据库中。

2. 在infrastructure模块底下，找到resource目录下的application-dev.yml文件
配置数据库以及Redis的 地址、端口、账号密码

3. 在根目录执行mvn install

4. 找到agileboot-admin模块中的AgileBootAdminApplication启动类，直接启动即可

5. 当出现以下字样即为启动成功
  ____   _                _                                                           __         _  _ 
 / ___| | |_  __ _  _ __ | |_   _   _  _ __    ___  _   _   ___  ___  ___  ___  ___  / _| _   _ | || |
 \___ \ | __|/ _` || '__|| __| | | | || '_ \  / __|| | | | / __|/ __|/ _ \/ __|/ __|| |_ | | | || || |
  ___) || |_| (_| || |   | |_  | |_| || |_) | \__ \| |_| || (__| (__|  __/\__ \\__ \|  _|| |_| || ||_|
 |____/  \__|\__,_||_|    \__|  \__,_|| .__/  |___/ \__,_| \___|\___|\___||___/|___/|_|   \__,_||_|(_)
                                      |_|                             

```

#### 前端启动
```
1. npm install

2. npm run dev

3. 当出现以下字样时即为启动成功

vite v2.6.14 dev server running at:

> Local: http://127.0.0.1:3000/

ready in 4376ms.

```

详细过程在这个文章中：[AgileBoot - 手把手一步一步带你Run起全栈项目(SpringBoot+Vue3)](https://juejin.cn/post/7153812187834744845)


> 对于想要尝试全栈项目的前端人员，这边提供更简便的后端启动方式，无需配置Mysql和Redis直接启动
#### 无Mysql/Redis 后端启动
```
1. 找到agilboot-admin模块下的resource文件中的application.yml文件

2. 配置以下两个值
spring.profiles.active: basic,dev
改为
spring.profiles.active: basic,test

agileboot.embedded.mysql: false
agileboot.embedded.redis: false
改为
agileboot.embedded.mysql: true
agileboot.embedded.redis: true

请注意:高版本的MacOS系统，无法启动内置的Redis


3. 找到agileboot-admin模块中的AgileBootAdminApplication启动类，直接启动即可
```


## 🙊 系统内置功能 🙊  
  

🙂 大部分功能，均有通过 **单元测试** **集成测试** 保证质量。

|     | 功能    | 描述                              |
|-----|-------|---------------------------------|
|     | 用户管理  | 用户是系统操作者，该功能主要完成系统用户配置          |
| ⭐   | 部门管理  | 配置系统组织机构（公司、部门、小组），树结构展现支持数据权限  |
| ⭐   | 岗位管理  | 配置系统用户所属担任职务                    |
|     | 菜单管理  | 配置系统菜单、操作权限、按钮权限标识等，本地缓存提供性能    |
| ⭐   | 角色管理  | 角色菜单权限分配、设置角色按机构进行数据范围权限划分      |
|     | 参数管理  | 对系统动态配置常用参数                     |
|     | 通知公告  | 系统通知公告信息发布维护                    |
| 🚀  | 操作日志  | 系统正常操作日志记录和查询；系统异常信息日志记录和查询     |
|     | 登录日志  | 系统登录日志记录查询包含登录异常                |
|     | 在线用户  | 当前系统中活跃用户状态监控                   |
|     | 系统接口  | 根据业务代码自动生成相关的api接口文档            |
|     | 服务监控  | 监视当前系统CPU、内存、磁盘、堆栈等相关信息         |
|     | 缓存监控  | 对系统的缓存信息查询，命令统计等                |
|     | 连接池监视 | 监视当前系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈 |


目前版本是V1.8.0，将在2.0版本后陆续新增新功能。

## 🐯 工程结构 🐯

``` 
agileboot
├── agileboot-admin -- 管理后台接口模块（供后台调用）
│
├── agileboot-api -- 开放接口模块（供客户端调用）
│
├── agileboot-common -- 精简基础工具模块
│
├── agileboot-infrastructure -- 基础设施模块（主要是配置和集成）
│
├── agileboot-domain -- 业务模块
├    ├── user -- 用户模块（举例）
├         ├── command -- 命令参数接收模型（命令）
├         ├── dto -- 返回数据类
├         ├── model -- 领域模型类
├         ├── query -- 查询参数模型（查询）
│         ├────── UserApplicationService -- 应用服务（事务层，操作领域模型类完成业务逻辑）
│
├── agileboot-integration-test -- 集成测试模块
│
├── agileboot-orm -- 数据映射模块（仅包含数据相关逻辑）
├    ├── entiy -- 实体类
├    ├── enums -- 数据相关枚举 
├    ├── mapper -- DAO 
├    ├── query -- 封装查询对象 
├    ├── result -- 封装多表查询对象 
└──  └── service -- 服务层
```

### 代码流转

请求分为两类：一类是查询，一类是操作（即对数据有进行更新）。

**查询**：Controller > xxxQuery > xxxApplicationService > xxxService(Db) > xxxMapper  
**操作**：Controller > xxxCommand > xxxApplicationService > xxxModel(处理逻辑) > save 或者 update (本项目直接采用JPA的方式进行插入已经更新数据)

这是借鉴CQRS的开发理念，将查询和操作分开处理。操作类的业务实现借鉴了DDD战术设计的理念，使用领域类，工厂类更面向对象的实现逻辑。 
如果你不太适应这样的开发模式的话。可以在domain模块中按照你之前从Controller->Service->DAO的模式进行开发。it is up to you.



### 二次开发指南

假设你要新增一个会员member业务，可以在以下三个模块新增对应的包来实现你的业务
``` 
agileboot
├── agileboot-admin -- 
│                ├── member -- 会员模块
│
├── agileboot-domain -- 
├                ├── member -- 会员模块
│
├── agileboot-orm -- 
│              ├── member -- 会员模块
│    
└─
```



--- 

## 🎅 技术文档 🎅
* [AgileBoot - 基于SpringBoot + Vue3的前后端快速开发脚手架](https://juejin.cn/post/7152871067151777829)
* [AgileBoot - 手把手一步一步带你Run起全栈项目(SpringBoot+Vue3)](https://juejin.cn/post/7153812187834744845)
* [AgileBoot - 项目内统一的错误码设计](https://juejin.cn/post/7156062116712022023)
* [AgileBoot - 如何集成内置数据库H2和内置Redis](https://juejin.cn/post/7158793441198112781)
* [AgileBoot - Mybatis Plus 框架项目落地实践总结](https://juejin.cn/post/7202573260659195963)
* [AgileBoot - SpringBoot项目多层级多环境yml设计](https://juejin.cn/post/7205171975647215676)
* [AgileBoot - 项目中多级缓存设计实践总结](https://juejin.cn/post/7208112485764857914)
* 持续输出中



## 🌻 注意事项 🌻
- IDEA会自动将.properties文件的编码设置为ISO-8859-1,请在Settings > Editor > File Encodings > Properties Files > 设置为UTF-8
- 请导入统一的代码格式化模板（Google）: Settings > Editor > Code Style > Java > 设置按钮 > import schema > 选择项目根目录下的GoogleStyle.xml文件
- 如需要生成新的表，请使用CodeGenerator类进行生成。
  - 填入数据库地址，账号密码，库名。然后填入所需的表名执行代码即可。（大概看一下代码就知道怎么填啦）
  - 生成的类在orm模块下的target/classes目录下
  - 不同的数据库keywordsHandler方法请填入对应不同数据库handler。（搜索keywordsHandler关键字）
- 项目基础环境搭建，请参考docker目录下的指南搭建。保姆级启动说明：
  - [AgileBoot - 手把手一步一步带你Run起全栈项目(SpringBoot+Vue3)](https://juejin.cn/post/7153812187834744845)
- 注意：管理后台的后端启动类是AgileBoot**Admin**Application

## 🎬 AgileBoot全栈交流群 🎬

QQ群：  [![加入QQ群](https://img.shields.io/badge/1398880-blue.svg)](https://qm.qq.com/cgi-bin/qm/qr?k=TR5guoXS0HssErVWefmdFRirJvfpEvp1&jump_from=webapi&authKey=VkWMmVhp/pNdWuRD8sqgM+Sv2+Vy2qCJQSeLmeXlLtfER2RJBi6zL56PdcRlCmTs) 点击按钮入群。


如果觉得该项目对您有帮助，可以小额捐赠支持本项目演示网站服务器等费用~


<img alt="logo" height="200" src="https://oscimg.oschina.net/oscnet/up-28b63fdd7b3ce003bd30c25883f2276212b.png">

## 💕 特别鸣谢


- <a href="https://github.com/FerryboatSeranade" target="_blank">@pokr</a> 感谢提供ChatGpt账号助力本项目开发

## 💒 相关框架
- 基于node.js开发的后端 <a href="https://gitee.com/TsMask/mask_api_midwayjs" target="_blank">Midwayjs</a> 
