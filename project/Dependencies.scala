import sbt._

object Dependencies {
  val akka_version = "2.4.10"
  val akka_stream = "com.typesafe.akka" %% "akka-stream" % akka_version
  val akka_persistence = "com.typesafe.akka" %% "akka-persistence" % akka_version
  val akka = "com.typesafe.akka" %% "akka-actor" % akka_version
}