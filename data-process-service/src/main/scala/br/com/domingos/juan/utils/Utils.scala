package br.com.domingos.juan.utils

import java.io.InputStream

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.slf4j.{Logger, LoggerFactory}

object Utils {

  private val Logger: Logger = LoggerFactory.getLogger(this.getClass)

  def getResource(resource: String): InputStream = {
    Logger.info(s"Getting $resource resource stream data")
    getClass.getResourceAsStream(s"/$resource");
  }

  def deserialize[T](json: String, clazz: Class[T]): T = {
    Logger.info(s"Parsing json message: ${json} to type ${clazz.getName}")

    def objectMapper: ObjectMapper = {
      new ObjectMapper()
        .registerModule(DefaultScalaModule)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, false)
        .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false)
    }

    objectMapper.readValue(json, clazz)
  }

}
