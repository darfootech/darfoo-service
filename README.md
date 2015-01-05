## requirements

* sbt 0.13.5
* activator 1.2.12
* play 2.3.7

## how to use

* `activator new` create a new project
* `activator run` run the service
* `activator test` run all the test cases

## how to deploy

```
cd conf/
cp redis.conf.example redis.conf
cp backend.conf.example backend.conf
cd ..
activator start
```

## notice!!!

部署的时候backend和service两个项目中的配置文件一定不要忘了cp出来

## Architecture

* backendserver nginx + spring + mysql
* serviceserver nginx + play + redis

aliyun上在一个地区可以直接用内网ip连接redis

## todo

* use async controller integrate with akka
