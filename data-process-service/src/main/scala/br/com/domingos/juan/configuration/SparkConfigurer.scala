package br.com.domingos.juan.configuration

import br.com.domingos.juan.constants.CassandraConstants.{CassandraDriverConnectionClasspath, CassandraDriverPortClasspath, CassandraDriverUsernameClasspath, CassandraDriverPasswordClasspath}
import br.com.domingos.juan.model.ApplicationConfig
import org.apache.spark.sql.SparkSession
import org.slf4j.{Logger, LoggerFactory}

object SparkConfigurer {

  private val Logger: Logger = LoggerFactory.getLogger(this.getClass)

  def configure(applicationConfig: ApplicationConfig): SparkSession = {
    Logger.info("Configuring Spark Session")

    SparkSession.builder()
      .appName(applicationConfig.applicationName)
      .master(applicationConfig.spark.master)
      .config(CassandraDriverConnectionClasspath, applicationConfig.cassandra.host)
      .config(CassandraDriverPortClasspath, applicationConfig.cassandra.port)
      .config(CassandraDriverUsernameClasspath, applicationConfig.cassandra.username)
      .config(CassandraDriverPasswordClasspath, applicationConfig.cassandra.password)
      .getOrCreate()
  }

}
