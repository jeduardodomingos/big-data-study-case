package br.com.domingos.juan.utils

import java.io.InputStream

import org.slf4j.{Logger, LoggerFactory}

object Utils {

  private val Logger: Logger = LoggerFactory.getLogger(this.getClass)

  def getResource(resource: String): InputStream = {
    Logger.info(s"Getting $resource resource stream data")
    getClass.getResourceAsStream(s"/$resource");
  }

}
