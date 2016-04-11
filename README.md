sbt-scr-plugin
==============

Plugin for [sbt](http://www.scala-sbt.org) to process [SCR annotations](http://felix.apache.org/documentation/subprojects/apache-felix-maven-scr-plugin/scr-annotations.html) and create the necessary descriptor files.

Installing sbt-scr-plugin
-------------------------

Add the following to `projects/plugins.sbt`:

```
addSbtPlugin("sbt-scr-plugin" % "sbt-scr-plugin" % "0.1.0")
```

Add the following to your `build.sbt` file:

```
lazy val foo = (project in file("."))
  .enablePlugins(SbtScr)
```

Tasks
-----
`genScrAnnotations`: scans the classes in the output directory to process SCR annotations and creates the necessary descriptor files.

Resources
---------
Other plugins for processing SCR annotations
- [Maven SCR Plugin](http://felix.apache.org/documentation/subprojects/apache-felix-maven-scr-plugin.html)
- [Gradle SCR Plugin](https://github.com/TWCable/gradle-plugin-scr)
