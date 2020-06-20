package br.com.domingos.juan.model

import org.apache.spark.sql.SparkSession

case class ProcessInput(sparkSession: SparkSession,
                        configuration: ApplicationConfig) extends Serializable {}
