package scr

import org.apache.felix.scrplugin.Log

class Logger(val log: sbt.Logger) extends Log {
  def debug(e: Throwable): Unit =
    log.debug(e.getMessage)
  def debug(msg: String, e: Throwable): Unit =
    log.debug(msg)
  def debug(msg: String): Unit =
    log.debug(msg)

  def error(e: Throwable): Unit =
    log.error(e.getMessage)
  def error(msg: String, e: Throwable): Unit =
    log.error(msg)
  def error(msg: String, location: String, lineNumber: Int, columnNumber: Int): Unit =
    log.error(msg)
  def error(msg: String, location: String, lineNumber: Int): Unit =
    log.error(msg)
  def error(msg: String): Unit =
    log.error(msg)

  def info(e: Throwable): Unit =
    log.info(e.getMessage)
  def info(msg: String, e: Throwable): Unit =
    log.info(msg)
  def info(msg: String): Unit =
    log.info(msg)

  def isDebugEnabled(): Boolean = true
  def isErrorEnabled(): Boolean = true
  def isInfoEnabled(): Boolean = true
  def isWarnEnabled(): Boolean = true

  def warn(e: Throwable): Unit =
    log.warn(e.getMessage)
  def warn(msg: String, e: Throwable): Unit =
    log.warn(msg)
  def warn(msg: String, location: String, lineNumber: Int, columnNumber: Int): Unit =
    log.warn(msg)
  def warn(msg: String, location: String, lineNumber: Int): Unit =
    log.warn(msg)
  def warn(msg: String): Unit =
    log.warn(msg)
}

object Logger {
  def apply(log: sbt.Logger): Logger = new Logger(log)
}
