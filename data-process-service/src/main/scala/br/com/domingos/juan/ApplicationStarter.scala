package br.com.domingos.juan

import br.com.domingos.juan.configuration.{ApplicationConfigurer, SparkConfigurer}
import br.com.domingos.juan.model.{ApplicationConfig, ProcessInput, StreamParameters}
import br.com.domingos.juan.receiver.SqsStreamReceiver
import br.com.domingos.juan.service.aws.SqsService
import br.com.domingos.juan.service.process.DataProcessService
import com.amazonaws.services.sqs.model.Message
import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel
import org.slf4j.{Logger, LoggerFactory}

object ApplicationStarter extends App {

  private val Logger: Logger = LoggerFactory.getLogger(this.getClass)

  override def main(args: Array[String]) = {
    Logger.info("Initializing Data Process Service")

    val Configuration: ApplicationConfig = ApplicationConfigurer.configure(args)
    val ApplicationSparkSession: SparkSession = SparkConfigurer.configure(Configuration)
    val Input = ProcessInput(ApplicationSparkSession, Configuration)

    val SqsServiceInstance = new SqsService(Input)
    val SqsStreamReceiverInstance: SqsStreamReceiver = new SqsStreamReceiver(streamParameters(Input), SqsServiceInstance)

    DataProcessService.apply(Input, SqsStreamReceiverInstance[Message])[Message]
  }

  private def streamParameters(processInput: ProcessInput): StreamParameters = {
    Logger.info("Initializing stream parameters")

    StreamParameters(
      processInput.configuration.aws.sqs.consumeQueue.address,
      StorageLevel.MEMORY_ONLY,
      processInput.configuration.spark.streamInterval,
      processInput.configuration.aws.sqs.consumeQueue.maxMessages
    )
  }

}
