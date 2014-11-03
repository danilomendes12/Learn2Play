name := """learn2play"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean
)

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "5.1.27"
)