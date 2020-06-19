package br.com.domingos.juan.service.stream.runner

import br.com.domingos.juan.model.StreamParameters
import br.com.domingos.juan.service.aws.SqsService
import com.amazonaws.services.sqs.model.{Message, ReceiveMessageRequest}
import org.slf4j.{Logger, LoggerFactory}

class MessageReceiveRunner(sqsService: SqsService, parameters: StreamParameters, storeCallback: => Unit) extends Runnable {

  private val Logger: Logger = LoggerFactory.getLogger(this.getClass)

  def run(): Unit = {
    Logger.info("Starting message receiver thread")
  }

  private def receiveMessages() = {
    Logger.info(s"Getting messages from ${parameters.sourceQueue}")

    val ReceiveRequest: ReceiveMessageRequest = buildReceiveMessageRequest

    val StreamMessages: List[Message] = sqsService.getMessages(ReceiveRequest)

    StreamMessages.

    storeCallback(message)
  }

  private def buildReceiveMessageRequest: ReceiveMessageRequest = {
    Logger.info("Building receive message request ...")

    new ReceiveMessageRequest(parameters.sourceQueue)
      .withMaxNumberOfMessages(parameters.messageFlood)
      .withWaitTimeSeconds(parameters.queueWaitTime)
  }

}
