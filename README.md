## requirements

* sbt 0.13.5
* activator 1.2.12
* play 2.3.7

## how to use

* `activator new` create a new project
* `activator run` run the service
* `activator test` run all the test cases

## how to deploy

* realize configuration files

```
cd conf/
cp redis.conf.example redis.conf
cp backend.conf.example backend.conf
cd ..
activator start # for production deployment
```

* realize the mysql configuration in $PATH

```
export DATABASE_URL_DB=mysql://host:port/database
export DATABASE_USERNAME_DB=username
export DATABASE_PASSWORD_DB=password
```

## notice!!!

部署的时候backend和service两个项目中的配置文件一定不要忘了cp出来

## Architecture

* backendserver nginx + spring + mysql
* serviceserver nginx + play + redis

aliyun上在一个地区可以直接用内网ip连接redis

## todo

* use async controller integrate with akka
