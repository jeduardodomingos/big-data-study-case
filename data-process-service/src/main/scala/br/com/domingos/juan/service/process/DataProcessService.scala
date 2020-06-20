package br.com.domingos.juan.service.process

import br.com.domingos.juan.constants.CassandraConstants.{CassandraSqlClasspath, TweetsTable}
import br.com.domingos.juan.model.{ApplicationConfig, EventMessage, ProcessInput}
import br.com.domingos.juan.service.stream.MessageReceiverStreamService
import br.com.domingos.juan.utils.Utils.deserialize
import com.amazonaws.services.sqs.model.Message
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.slf4j.{Logger, LoggerFactory}

object DataProcessService {

  private val Logger: Logger = LoggerFactory.getLogger(this.getClass)

  def apply(processInput: ProcessInput, receiver: MessageReceiverStreamService) = {
      receiver.stream(process)
  }

  def process(processInput: ProcessInput, message: Message):Unit = {
    val DeserializeMessage: EventMessage = deserialize(message.getBody, classOf[EventMessage])

    Logger.info(s"Starting process for tag ${DeserializeMessage.keyTag}")

   // val df = getData(processInput.sparkSession, processInput.configuration, DeserializeMessage.keyTag)
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
