package br.com.domingos.juan.service.stream

import br.com.domingos.juan.model.SparkConfig
import org.apache.spark.SparkContext
import org.apache.spark.streaming.receiver.Receiver
import org.apache.spark.streaming.{Milliseconds, StreamingContext}
import org.slf4j.{Logger, LoggerFactory}

class MessageReceiverStreamService[TYPE](sparkConfig: SparkConfig,
                                         sparkContext: SparkContext,
                                         receiver: Receiver[TYPE],
                                         processCallback: () => Unit) {

  private val Logger: Logger = LoggerFactory.getLogger(this.getClass)

  def stream(): Unit = {
      streamingContext.receiverStream(receiver).foreachRDD(message => {
          message.collect()
          .foreach(_ => processCallback())
      })
  }

  private def streamingContext: StreamingContext = {
    new StreamingContext(sparkContext, Milliseconds(sparkConfig.streamInterval))
  }

}
