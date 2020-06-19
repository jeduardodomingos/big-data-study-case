package br.com.domingos.juan.service.stream.receiver

import br.com.domingos.juan.model.StreamParameters
import com.amazonaws.services.sqs.model.Message
import org.apache.spark.streaming.receiver.Receiver

class SqsStreamReceiver(parameters: StreamParameters) extends Receiver[Message](parameters.sparkStorageLevel){
  override def onStart(): Unit = ???

  override def onStop(): Unit = ???
}
