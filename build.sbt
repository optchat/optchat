name := """OptChat"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

resolvers += "Atilika Open Source repository" at "http://www.atilika.org/nexus/content/repositories/atilika"

resolvers += Resolver.url("Edulify Repository", url("http://edulify.github.io/modules/releases/"))(Resolver.ivyStylePatterns)

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  filters,
  javaWs,
  "mysql" % "mysql-connector-java" % "5.1.33",
  "org.webjars" %% "webjars-play" % "2.3.0",
  "org.webjars" % "font-awesome" % "4.2.0",
  "org.webjars" % "ionicons" % "1.5.2",
  "org.webjars" % "html5shiv" % "3.7.2",
  "org.webjars" % "respond" % "1.4.2",
  "com.adrianhurt" %% "play-bootstrap3" % "0.1.1",
  "org.webjars" % "jquery" % "2.1.1",
  "org.webjars" % "jquery-ui" % "1.11.1",
  "com.edulify" %% "play-hikaricp" % "1.5.0",
  "org.mindrot" % "jbcrypt" % "0.3m"
)
