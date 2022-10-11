## 手把手 Redis Docker 安装



### 拉取Redis的镜像

docker pull redis:版本号

```
docker pull redis:7-alpine
```

如果版本号过期的话，可以置空，会拉取最新的镜像，下面的启动命令可以去除掉 :***7-alpine***

### 启动Redis容器

```
docker run -d --name myredis -p 36379:6379 redis:7-alpine --requirepass "12345"
```

注意 以上
1. -p ***36379***:6379  斜体部分是你宿主机映射的端口，也就是你访问docker时所用的端口
2. --requirepass "***12345***" 斜体部门是你的初始密码  
3. --name ***myredis*** 斜体是你给容器的命名

