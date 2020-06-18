package br.com.domingos.juan.configuration

import br.com.domingos.juan.model.ApplicationConfig
import org.apache.spark.{SparkConf, SparkContext}
import org.slf4j.{Logger, LoggerFactory}

object SparkConfigurer {

  private val Logger: Logger = LoggerFactory.getLogger(this.getClass)

  def sparkConf(applicationConfig: ApplicationConfig): SparkConf =
    new SparkConf()
      .setAppName(applicationConfig.applicationName)
      .setMaster(applicationConfig.spark.master)

  def configure(applicationConfig: ApplicationConfig): SparkContext = {
    Logger.info("Initializing Spark Context ...")
    new SparkContext(this.sparkConf(applicationConfig))
  }

}
