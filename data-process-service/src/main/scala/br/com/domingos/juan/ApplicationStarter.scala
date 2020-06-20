package br.com.domingos.juan

import br.com.domingos.juan.configuration.{ApplicationConfigurer, SparkConfigurer}
import br.com.domingos.juan.model.{ApplicationConfig, ProcessInput, StreamParameters}
import br.com.domingos.juan.receiver.SqsStreamReceiver
import br.com.domingos.juan.service.aws.SqsService
import br.com.domingos.juan.service.process.DataProcessService
import br.com.domingos.juan.service.stream.MessageReceiverStreamService
import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel

object ApplicationStarter extends App {

  override def main(args: Array[String]) = {

    val Input = processInput(args)
    val SqsServiceInstance = new SqsService(Input)
    val SqsStreamReceiverInstance: SqsStreamReceiver = new SqsStreamReceiver(streamParameters(Input), SqsServiceInstance)
    val MessageReceiverStreamServiceInstance: MessageReceiverStreamService = new MessageReceiverStreamService(Input, SqsStreamReceiverInstance)

    DataProcessService.apply(Input, MessageReceiverStreamServiceInstance)
  }

  private def processInput(args: Array[String]): ProcessInput = {
    val Configuration: ApplicationConfig = ApplicationConfigurer.configure(args)
    val ApplicationSparkSession: SparkSession = SparkConfigurer.configure(Configuration)

    ProcessInput(ApplicationSparkSession, Configuration)
  }

  private def streamParameters(processInput: ProcessInput): StreamParameters = {
    StreamParameters(
      processInput.configuration.aws.sqs.consumeQueue.address,
      StorageLevel.MEMORY_ONLY,
      processInput.configuration.spark.streamInterval,
      processInput.configuration.aws.sqs.consumeQueue.maxMessages
    )
  }

}
