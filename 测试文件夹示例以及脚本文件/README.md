> 本文件夹目的是为了演示，如果别人想要进行本地测试的话，可以参考本指导文档

# 1. 存储系统中交互数据

- 用户上传的图书数据存储在文件夹`auditbooks`中；
- 管理员审核通过的书籍信息存储在文件夹`books`当中；
- `files`文件夹使用来别的用途；

# 2. 脚本文件介绍

## 2.1 `dockerfile`文件介绍

```dockerfile

From openjdk:8-jdk-alpine

VOLUME /root/ebookshare/files
VOLUME /root/ebookshare/books
VOLUME /root/ebookshare/books/bookcoverimage
VOLUME /root/ebookshare/auditbooks
VOLUME /root/ebookshare/auditbooks/bookcoverimage


WORKDIR /app

COPY eBookShare-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]


```

本文件在容器中定义了5个虚拟存储，用来和我们的服务器进行一个映射，做一个永久保存，而不是删除容器之后就会消失。

## 2.2 `shell`脚本文件介绍

```shell
#!/bin/bash

docker stop ebookshare

docker container rm ebookshare

docker rmi ebookshare

docker build -t ebookshare .

docker run --name ebookshare -d -p 9091:9091 \
 -v /root/ebookshare/files:/root/ebookshare/files  \
 -v /root/ebookshare/books:/root/ebookshare/books \
 -v /root/ebookshare/books/bookcoverimage:/root/ebookshare/books/bookcoverimage \
 -v /root/ebookshare/auditbooks:/root/ebookshare/auditbooks \
 -v /root/ebookshare/auditbooks/bookcoverimage:/root/ebookshare/auditbooks/bookcoverimage  \
 ebookshare

```

即先停止当前的容器并删除容器，以及删除相应的镜像，接着根据`Dockerfile`文件进行构建镜像，最后创建相应的容器。

# 3. `nginx-docker`文件夹介绍

该部分文件夹是为了存储我们打包之后的前端代码：`ebooksharebak`、 `ebooksharefont`.

`default.conf`文件是配置nginx的配置文件：

```shell
server {
    listen       80;
    server_name  localhost;

    #charset koi8-r;
    access_log  /var/log/nginx/host.access.log  main;
    error_log  /var/log/nginx/error.log  error;

    location /api/ {
        proxy_pass http://124.71.166.37:9091;
    }
    
    location / {
    	try_files $uri $uri/ /index.html;
        root   /usr/share/nginx/html;
        index  index.html index.htm;
    }


    #error_page  404              /404.html;
    # redirect server error pages to the static page /50x.html
    #
    error_page   500 502 503 504  /50x.html;
    location = /50x.html {
        root   /usr/share/nginx/html;
    }
}
```

`Dockerfile`文件

```Dockfile
FROM nginx
COPY ebooksharefont/ /usr/share/nginx/html/
COPY default.conf /etc/nginx/conf.d/default.conf
```

