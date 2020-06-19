package br.com.domingos.juan.service.stream

import br.com.domingos.juan.model.SparkConfig
import br.com.domingos.juan.service.aws.SqsService
import org.apache.spark.SparkContext
import org.apache.spark.streaming.{Milliseconds, StreamingContext}
import org.slf4j.{Logger, LoggerFactory}

class MessageReceiverStream(sparkConfig: SparkConfig, sparkContext: SparkContext, sqsService: SqsService) {

  private val Logger: Logger = LoggerFactory.getLogger(this.getClass)

  private def buildStreamingContext: StreamingContext = {
    new StreamingContext(sparkContext, Milliseconds(sparkConfig.streamInterval))
  }

}
