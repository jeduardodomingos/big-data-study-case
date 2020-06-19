package br.com.domingos.juan.runner

import br.com.domingos.juan.model.StreamParameters
import br.com.domingos.juan.service.aws.SqsService
import com.amazonaws.services.sqs.model.{Message, ReceiveMessageRequest}
import org.slf4j.{Logger, LoggerFactory}

import scala.annotation.tailrec

class SqsReceiverRunner(sqsService: SqsService, parameters: StreamParameters, storeCallback: (Message) => Unit) extends Runnable {

  private val Logger: Logger = LoggerFactory.getLogger(this.getClass)

  def run(): Unit = {
    Logger.info("Starting message receiver thread")
    receiveMessages()
  }

  private def receiveMessages(): Unit = {
    Logger.info(s"Getting messages from ${parameters.sourceQueue}")

    val StartIndex: Int = 0
    val ReceiveRequest: ReceiveMessageRequest = buildReceiveMessageRequest
    val StreamMessages: List[Message] = sqsService.getMessages(ReceiveRequest)

    @tailrec
    def iterate(index: Int, messages: List[Message]): Unit = {
        Logger.info(s"Getting ${(index + 1).toString} of ${messages.size} messages")

        if(index < messages.size) {
          storeCallback(messages(index))
          iterate(index + 1, messages)
        }
    }

    iterate(StartIndex, StreamMessages)
  }

  private def buildReceiveMessageRequest: ReceiveMessageRequest = {
    Logger.info("Building receive message request ...")

    new ReceiveMessageRequest(parameters.sourceQueue)
      .withMaxNumberOfMessages(parameters.messageFlood)
      .withWaitTimeSeconds(parameters.queueWaitTime)
  }

}
