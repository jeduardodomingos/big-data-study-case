package br.com.domingos.juan.service

import br.com.domingos.juan.model.AwsConfig
import com.amazonaws.auth.{AWSStaticCredentialsProvider, BasicAWSCredentials}
import org.slf4j.{Logger, LoggerFactory}


class AwsService(awsConfig: AwsConfig) {

  private val Logger: Logger = LoggerFactory.getLogger(this.getClass);

  private def credentials: BasicAWSCredentials = {
    Logger.info("Getting AWS credentials ...")
    new BasicAWSCredentials(awsConfig.accessKey, awsConfig.secretKey)
  }

  def credentialProvider: AWSStaticCredentialsProvider = {
    Logger.info("Getting AWS credential provider ...")
    new AWSStaticCredentialsProvider(this.credentials)
  }

}
