## 手把手 Mysql Docker 安装


### 创建本地数据存储目录

比如你想把docker内的Mysql数据，存在你的宿主机的话，需要在你宿主机创建一个目录。  
假设你的工作目录是 D:/workspace 那就创建一个 ***D:/workspace/mysql/data*** 目录。

注意上面的***D:/workspace/mysql/data*** 将作为启动docker的命名参数。

### 拉取Mysql的镜像

docker pull mysql:版本号

```
docker pull mysql:8.0
```

### 启动Mysql容器

```
docker run -d -v D:/workspace/mysql/data:/var/lib/mysql -it --name mysql8 -p 33067:3306 -e MYSQL_ROOT_PASSWORD=12345  mysql:8.0
```

注意 以上
1. -v 参数后面   ***D:/workspace/mysql/data***:/var/lib/mysql  斜体部分是你刚才创建的本地数据目录  
2. -p ***33067***:3306  斜体部分是你宿主机映射的端口，也就是你访问docker时所用的端口  
3. -e MYSQL_ROOT_PASSWORD=***12345*** 斜体部门是你的初始密码  对应的账号是root
4. --name ***mysql8*** 斜体是你给容器的命名

### 进入Mysql容器内

```
docker exec -it mysql8 /bin/bash
```

### 执行mysql命令

```
mysql -uroot- p 
```
填入我们刚才的初始密码 12345


由于Mysql8出于安全考虑 默认不允许外部连接直接访问。所以需要打开权限。

```
use mysql
```
```
alter user 'root'@'%' identified by '12345';
```
```
flush privileges;
```


### 创建数据库agileboot

```
drop database if exists `agileboot`;
create database `agileboot` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
```


### 导入sql文件

navicat直接导入 本项目sql文件夹下的数据库脚本 agileboot_xxxxxx.sql文件  
或者mysql命令打开agileboot库，复制脚本文件内容直接执行即可。
