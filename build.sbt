name := "BigDataa"

version := "1.0"

lazy val `bigdataa` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"

scalaVersion := "2.12.10"

libraryDependencies ++= Seq(jdbc, ehcache, ws, specs2 % Test, guice)
libraryDependencies += "org.apache.spark" %% "spark-core" % "3.1.2"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.1.2"
libraryDependencies += "org.apache.spark" %% "spark-mllib" % "3.1.2"
