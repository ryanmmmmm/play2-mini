import sbt._
import Keys._

object MinimalBuild extends Build {
  
  lazy val buildVersion =  "2.0-RC1-SNAPSHOT"
  
  lazy val typesafeVersion = if (buildVersion.endsWith("SNAPSHOT")) "snapshots" else "releases"
  lazy val typesafe = "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
  lazy val typesafeSnapshot = "Typesafe Snapshots Repository" at "http://repo.typesafe.com/typesafe/ivy-snapshots/"
  lazy val repo = if (buildVersion.endsWith("SNAPSHOT")) typesafeSnapshot else typesafe  

  lazy val root = Project(id = "play-mini", base = file("."), settings = Project.defaultSettings).settings(
    version := buildVersion,
    publishTo := Some(repo),
    organization := "com.typesafe",
    resolvers += repo,
    libraryDependencies += "play" %% "play" % buildVersion,
    mainClass in (Compile, run) := Some("play.core.server.NettyServer")
  )
}
