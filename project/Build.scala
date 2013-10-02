import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {
	val appName         = "config"
	val appVersion      = "1.0-SNAPSHOT"

	val appDependencies = Seq(
		"net.codingwell" %% "scala-guice" % "3.0.2",
		"commons-codec" % "commons-codec" % "1.6",
		"org.apache.commons" % "commons-lang3" % "3.1",
		"com.typesafe" % "config" % "1.0.0",
		"org.codehaus.jackson" % "jackson-mapper-asl" % "1.9.11",
		"org.codehaus.jackson" % "jackson-core-asl" % "1.9.11",
		"joda-time" % "joda-time" % "2.2",
		"org.joda" % "joda-convert" % "1.2",
		"com.typesafe.slick" %% "slick" % "1.0.0",
		"org.postgresql" % "postgresql" % "9.2-1003-jdbc4",
		"c3p0" % "c3p0" % "0.9.1.2",
		"org.specs2" %% "specs2" % "1.14" % "test",
		"junit" % "junit" % "4.7" % "test",
		jdbc,
		anorm
	)

	val main = play.Project(appName, appVersion, appDependencies).settings(
		resolvers += "Type Safe" at "http://repo.typesafe.com/typesafe/releases/",
		resolvers += "Type Safe Repo" at "http://repo.typesafe.com/typesafe/repo"      
	)

}
