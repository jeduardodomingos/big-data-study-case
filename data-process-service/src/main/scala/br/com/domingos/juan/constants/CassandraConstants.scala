package br.com.domingos.juan.constants

object CassandraConstants {

  val CassandraDriverConnectionClasspath: String = "spark.cassandra.connection.host"
  val CassandraDriverPortClasspath: String = "spark.cassandra.connection.port"
  val CassandraDriverUsernameClasspath: String = "spark.cassandra.auth.username"
  val CassandraDriverPasswordClasspath: String = "spark.cassandra.auth.password"
  val CassandraSqlClasspath: String = "org.apache.spark.sql.cassandra"
  val TweetsTable: String = "tweets"
}
