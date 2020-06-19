package br.com.domingos.juan.model

import org.apache.spark.storage.StorageLevel

case class StreamParameters(sourceQueue: String,
                            sparkStorageLevel: StorageLevel,
                            queueWaitTime: Int,
                            messageFlood: Int) extends Serializable {}
