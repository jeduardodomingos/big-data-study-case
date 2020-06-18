package br.com.domingos.juan.service

import br.com.domingos.juan.model.AwsConfig
import com.amazonaws.services.sqs.model.SendMessageResult
import com.amazonaws.services.sqs.{AmazonSQS, AmazonSQSClientBuilder}
import org.slf4j.{Logger, LoggerFactory}

class SqsService(awsConfig: AwsConfig) extends AwsService(awsConfig) {

  private val Logger: Logger = LoggerFactory.getLogger(this.getClass);

  def sqsClient: AmazonSQS = {
    Logger.info("Building AWS SQS client ...")

    AmazonSQSClientBuilder
      .standard()
      .withCredentials(this.credentialProvider)
      .withRegion(this.awsConfig.region)
      .build()
  }

  def sendMessage(queue: String, message: String): SendMessageResult = {
    Logger.info(s"Sending SQS message: ${message} to queue: ${queue}")
    this.sqsClient.sendMessage(queue, message)
  }



}
