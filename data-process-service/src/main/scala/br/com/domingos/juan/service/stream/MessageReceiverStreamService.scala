package br.com.domingos.juan.service.stream

import br.com.domingos.juan.model.ProcessInput
import com.amazonaws.services.sqs.model.Message
import org.apache.spark.streaming.receiver.Receiver
import org.apache.spark.streaming.{Milliseconds, StreamingContext}
import org.slf4j.{Logger, LoggerFactory}

class MessageReceiverStreamService[T](processInput: ProcessInput, receiver: Receiver[T]) {

  private val Logger: Logger = LoggerFactory.getLogger(this.getClass)

  def stream(processCallback: (ProcessInput, Message) => Unit): Unit = {
    Logger.info("Starting message streaming")

    streamingContext.receiverStream(receiver).foreachRDD(messageRDD => {
      messageRDD.collect()
        .foreach(_ => processCallback(processInput, _))
    })
  }

  private def streamingContext: StreamingContext = {
    Logger.info("Getting streaming context")

    new StreamingContext(
      processInput.sparkSession.sparkContext,
      Milliseconds(processInput.configuration.spark.streamInterval)
    )
  }

}
