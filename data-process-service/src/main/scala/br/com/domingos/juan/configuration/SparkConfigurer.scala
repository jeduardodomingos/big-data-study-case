package br.com.domingos.juan.configuration

import br.com.domingos.juan.model.{ApplicationConfig}
import org.apache.spark.{SparkConf, SparkContext}

object SparkConfigurer {

  def sparkConf(applicationConfig: ApplicationConfig): SparkConf =
    new SparkConf()
      .setAppName(applicationConfig.applicationName)
      .setMaster(applicationConfig.spark.master)

  def configure(applicationConfig: ApplicationConfig): SparkContext = {
    new SparkContext(this.sparkConf(applicationConfig))
  }

}
