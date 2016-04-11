name := "sbt-scr-plugin"

licenses := Seq("MIT License" -> url("http://www.opensource.org/licenses/mit-license.php"))

sbtPlugin := true

scalacOptions := Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-encoding", "UTF-8",
  "-language:postfixOps",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard"
)

libraryDependencies += "org.apache.felix" % "org.apache.felix.scr.generator" % "1.13.0"

addSbtPlugin("com.typesafe.sbt" % "sbt-osgi" % "0.8.0")

lazy val scrPlugin = project in file(".")
