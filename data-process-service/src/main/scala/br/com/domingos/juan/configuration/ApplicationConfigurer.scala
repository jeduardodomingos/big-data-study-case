package br.com.domingos.juan.configuration

import br.com.domingos.juan.Utils.Utils.getResource
import br.com.domingos.juan.model.ApplicationConfig
import org.slf4j.{Logger, LoggerFactory}
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor

object ApplicationConfigurer {

  private val Logger: Logger = LoggerFactory.getLogger(this.getClass)

  def configure(arguments: Array[String]): ApplicationConfig = {
    Logger.info("Getting Application Configuration ...")

    val YamlFileName = arguments(0)
    val ResourceStream = getResource(YamlFileName)
    val YamlLoader = new Yaml(new Constructor(classOf[ApplicationConfig]))

    YamlLoader.load(ResourceStream).asInstanceOf[ApplicationConfig]
  }

}
