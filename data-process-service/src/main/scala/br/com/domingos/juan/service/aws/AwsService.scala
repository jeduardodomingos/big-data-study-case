package br.com.domingos.juan.service.aws

import br.com.domingos.juan.model.ProcessInput
import com.amazonaws.auth.{AWSStaticCredentialsProvider, BasicAWSCredentials}
import org.slf4j.{Logger, LoggerFactory}


abstract class AwsService(processInput: ProcessInput) {

  private val Logger: Logger = LoggerFactory.getLogger(this.getClass);

  private def credentials: BasicAWSCredentials = {
    Logger.info("Getting AWS credentials ...")
    new BasicAWSCredentials(processInput.configuration.aws.accessKey, processInput.configuration.aws.secretKey)
  }

  def credentialProvider: AWSStaticCredentialsProvider = {
    Logger.info("Getting AWS credential provider ...")
    new AWSStaticCredentialsProvider(this.credentials)
  }

}
