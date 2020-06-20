package br.com.domingos.juan.service.aws

import br.com.domingos.juan.model.ProcessInput
import com.amazonaws.services.sqs.model.{Message, ReceiveMessageRequest, SendMessageResult}
import com.amazonaws.services.sqs.{AmazonSQS, AmazonSQSClientBuilder}
import org.slf4j.{Logger, LoggerFactory}

import scala.collection.JavaConverters._

class SqsService(processInput: ProcessInput) extends AwsService(processInput) {

  private val Logger: Logger = LoggerFactory.getLogger(this.getClass);

  def sqsClient: AmazonSQS = {
    Logger.info("Building AWS SQS client ...")

    AmazonSQSClientBuilder
      .standard()
      .withCredentials(this.credentialProvider)
      .withRegion(this.processInput.configuration.aws.region)
      .build()
  }

  def sendMessage(queue: String, message: String): SendMessageResult = {
    Logger.info(s"Sending SQS message: ${message} to queue: ${queue}")
    this.sqsClient.sendMessage(queue, message)
  }

  def getMessages(receiveMessageRequest: ReceiveMessageRequest): List[Message] = {
    Logger.info(s"Getting SQS messages from queue: ${receiveMessageRequest.getQueueUrl}")
    sqsClient.receiveMessage(receiveMessageRequest).getMessages.asScala.toList
  }

}
