package scr

import sbt._
import sbt.Keys._
import com.typesafe.sbt.osgi.SbtOsgi

object SbtScrPlugin extends AutoPlugin {

  object autoImport {
    lazy val scrAnnotations = TaskKey[Unit]("scrAnnotations", "Process SCR annotations")
  }
  import autoImport._

  override def trigger = allRequirements

  override val requires: Plugins = SbtOsgi

  override lazy val projectSettings = Seq(
    scrAnnotations <<= (
      classDirectory in Compile,
      fullClasspath in Compile,
      streams
    ) map ScrAnnotations.processTask
  )
}
