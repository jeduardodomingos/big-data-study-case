package br.com.domingos.juan.receiver

import br.com.domingos.juan.model.StreamParameters
import br.com.domingos.juan.service.aws.SqsService
import br.com.domingos.juan.runner.SqsReceiverRunner
import com.amazonaws.services.sqs.model.Message
import org.apache.spark.streaming.receiver.Receiver
import org.slf4j.{Logger, LoggerFactory}

class SqsStreamReceiver(parameters: StreamParameters, sqsService: SqsService) extends Receiver[Message](parameters.sparkStorageLevel){
  private val Logger: Logger = LoggerFactory.getLogger(this.getClass())

  private val Runner: SqsReceiverRunner = new SqsReceiverRunner(sqsService, parameters, store)

  override def onStart(): Unit = {
    Logger.info("Starting stream receiver")
    Runner.run()
  }

  override def onStop(): Unit = {}
}
