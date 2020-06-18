package br.com.domingos.juan.model

import com.fasterxml.jackson.annotation.JsonProperty

class ApplicationConfig {
  @JsonProperty("applicationName") var applicationName: String = ""
  @JsonProperty("spark") var spark: SparkConfig = new SparkConfig()
  @JsonProperty("aws") var aws: AwsConfig = new AwsConfig()
}

class SparkConfig {
  @JsonProperty("master") var master: String = ""
}

class AwsConfig {
  @JsonProperty("region") var region: String = ""
  @JsonProperty("accessKey") var accessKey: String = ""
  @JsonProperty("secretKey") var secretKey: String = ""
  @JsonProperty("sqs") var sqs: SqsConfig = new SqsConfig()
}

class SqsConfig {
  @JsonProperty("consumeQueue") var receiveQueue: String = ""
  @JsonProperty("produceQueue") var produceQueue: String = ""
}
