package br.com.domingos.juan.model

case class EventMessage(uuid: String,
                        keyTag: String,
                        source: String) extends Serializable {}
