package br.com.domingos.juan.service.process

import br.com.domingos.juan.constants.CassandraConstants.{CassandraSqlClasspath, TweetsTable}
import br.com.domingos.juan.model.{ApplicationConfig, ProcessInput}
import br.com.domingos.juan.service.stream.MessageReceiverStreamService
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.slf4j.{Logger, LoggerFactory}

object DataProcessService {

  private val Logger: Logger = LoggerFactory.getLogger(this.getClass)

  def apply[T](processInput: ProcessInput, receiver: MessageReceiverStreamService[T]): Unit = {

  }

  def process(processInput: ProcessInput, tag: String) = {
    val df = getData(processInput.sparkSession, processInput.configuration, tag)
  }

  def getData(sparkSession: SparkSession, applicationConfig: ApplicationConfig, tag: String): DataFrame = {
    Logger.info(s"Getting data from cassandra for tag: ${tag}")

    sparkSession.sqlContext.read
      .format(CassandraSqlClasspath)
      .option("keyspace", applicationConfig.cassandra.keyspace)
      .option("table", TweetsTable)
      .load()
      .filter(s"key_tag = $tag")
      .toDF
  }

}
