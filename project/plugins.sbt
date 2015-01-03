//resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"
resolvers ++= Seq(
    //"snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
    //"staging" at "http://oss.sonatype.org/content/repositories/staging",
    //"releases" at "http://oss.sonatype.org/content/repositories/releases",
    "oschina" at "http://maven.oschina.net/content/groups/public",
    "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"
    //"mavenRepoJX" at "http://repo1.maven.org/maven2/"
)

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.3.7")

// web plugins

addSbtPlugin("com.typesafe.sbt" % "sbt-coffeescript" % "1.0.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-less" % "1.0.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-jshint" % "1.0.1")

addSbtPlugin("com.typesafe.sbt" % "sbt-rjs" % "1.0.1")

addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.0.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-mocha" % "1.0.0")