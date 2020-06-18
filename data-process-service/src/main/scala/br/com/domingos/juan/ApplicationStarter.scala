package br.com.domingos.juan

import br.com.domingos.juan.configuration.{ApplicationConfigurer, SparkConfigurer}
import br.com.domingos.juan.model.{ApplicationConfig, SparkConfig}
import org.apache.spark.SparkContext

object ApplicationStarter extends App {

  override def main(args: Array[String]) = {
    val Configuration: ApplicationConfig = ApplicationConfigurer.configure(args)
    val ApplicationContext: SparkContext = SparkConfigurer.configure(Configuration)
  }

}
