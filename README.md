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
activator # for production deployment
[darfoo-play] $ start port # default to 9000

cp product.conf.example product.conf
# 在production环境下 如果product.conf中的密码有@这样的特殊字符 整个密码需要用双引号包裹住
# for development (貌似下面run启动在ubuntu12.04LTS上ok 在maverick上就跪了 在mac上要run启动只能是把数据库配置写在application.conf文件里)
./activator "run -Dhttp.port=9003 -Dconfig.resource=conf/product.conf"
# for production (start启动在mac下是ok的而且也有实时log)
./activator "start -Dhttp.port=9003 -Dconfig.file=conf/product.conf"
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

## database migration

* rake db:new_migration name=migration_name -> db/migrate/migration_file.rb
* rake db:migrate (rake db:migrate RAILS_ENV=production)
* rake db:rollback (rake db:rollback RAILS_ENV=production)

## todo

* use async controller integrate with akka
