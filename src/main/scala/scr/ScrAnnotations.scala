package scr

import sbt.Attributed
import sbt.Keys._
import java.io.File
import scala.collection.JavaConverters._
import java.net.URLClassLoader
import org.apache.felix.scrplugin._

private object ScrAnnotations {

  def processTask(classDirectory: File, fullClasspath: Seq[Attributed[File]], streams: TaskStreams): Unit = {
    val logger = Logger(streams.log)
    logger.info("processing SCR annotations")
    logger.info(s"cd: ${classDirectory.getAbsolutePath}")

    val project = mkProject(classDirectory, fullClasspath)
    val options = mkOptions(classDirectory)
    val generator = new SCRDescriptorGenerator(logger)

    generator.setOptions(options)
    generator.setProject(project)
    val result = generator.execute()
    ()
  }

  def fileToSource(classDirectory: File)(file: File): Source = new Source {
    def getFile(): File = file
    def getClassName(): String = {
      val base = classDirectory.getAbsolutePath
      val name = file.getAbsolutePath.substring(base.length + 1)
      val clzz = name.substring(0, name.length - 6) // name[.class]
      clzz.replace(File.separatorChar, '/').replace('/', '.')
    }
  }
  def getFiles(f: File): Array[File] = {
    val files = f.listFiles
    files ++ files.filter(_.isDirectory).flatMap(getFiles)
  }
  def getSources(classesDirectory: File): List[Source] =
    getFiles(classesDirectory).
      filter (_.getName endsWith ".class").
      map (fileToSource(classesDirectory)).
      toList

  def mkProject(classDirectory: File, fullClasspath: Seq[Attributed[File]]): Project = {
    val deps = fullClasspath.files
    val urls = deps map (_.toURI.toURL) toArray
    val project = new Project
    project.setClassLoader(new URLClassLoader(urls, getClass.getClassLoader))
    project.setDependencies(deps.asJava)
    project.setSources(getSources(classDirectory).asJava)
    project.setClassesDirectory(classDirectory.getAbsolutePath)
    project
  }

  def mkOptions(classesDirectory: File): Options = {
    val options = new Options
    options.setOutputDirectory(classesDirectory)
    options.setGenerateAccessors(true)
    options.setStrictMode(false)
    // options.setProperties()
    // options.setSpecVersion()
    // options.setIncremental(false)
    // options.setSkipVolatileCheck(false)
    options
  }
}
