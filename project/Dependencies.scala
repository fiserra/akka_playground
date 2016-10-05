import sbt._

object Dependencies {
  val akkaVersion = "2.4.11"
  val akka_stream_dep = "com.typesafe.akka" %% "akka-stream" % akkaVersion
  val akka_http_core = "com.typesafe.akka" %% "akka-http-core" % akkaVersion
  val akka_http_core_experimental = "com.typesafe.akka" %% "akka-http-experimental" % akkaVersion
  val akka_persistence_dep = "com.typesafe.akka" %% "akka-persistence" % akkaVersion
  val akka_dep = "com.typesafe.akka" %% "akka-actor" % akkaVersion
  val akka_stream_kafka_dep = "com.typesafe.akka" %% "akka-stream-kafka" % "0.11"
  val logback = "ch.qos.logback" % "logback-classic" % "1.1.7"
}