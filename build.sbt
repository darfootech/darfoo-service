name := """darfoo-play"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "redis.clients" % "jedis" % "2.6.1",
  "mysql" % "mysql-connector-java" % "5.1.18",
  "com.alibaba" % "fastjson" % "1.2.3"
)