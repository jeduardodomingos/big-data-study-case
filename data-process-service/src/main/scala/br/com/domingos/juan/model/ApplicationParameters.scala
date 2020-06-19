package br.com.domingos.juan.model

import scala.beans.BeanProperty

class ApplicationConfig {
  @BeanProperty var applicationName: String = ""
  @BeanProperty var spark: SparkConfig = new SparkConfig()
  @BeanProperty var cassandra: CassandraConfig = new CassandraConfig()
  @BeanProperty var aws: AwsConfig = new AwsConfig()
}

class SparkConfig {
  @BeanProperty var master: String = ""
  @BeanProperty var streamInterval: Int = 0
}

class CassandraConfig {
  @BeanProperty var host: String = ""
  @BeanProperty var port: String = ""
  @BeanProperty var keyspace: String = ""
}

class AwsConfig {
  @BeanProperty var region: String = ""
  @BeanProperty var accessKey: String = ""
  @BeanProperty var secretKey: String = ""
  @BeanProperty var sqs: SqsConfig = new SqsConfig()
}

class SqsConfig {
  @BeanProperty var consumeQueue: String = ""
  @BeanProperty var produceQueue: String = ""
}
