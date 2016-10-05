name := "akka_playground"

import Dependencies._

lazy val commonSettings = Seq(
  organization := "com.fiser",
  version := "1.0",
  scalaVersion := "2.11.8"
)

lazy val akka_streams = (project in file("akka-streams")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= Seq(akka_stream_dep, akka_http_core, akka_http_core_experimental, akka_stream_kafka_dep, logback)
  )

lazy val akka_persistence = (project in file("akka-persistence")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= Seq(akka_persistence_dep)
  )

lazy val akka = (project in file("akka")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= Seq(akka_dep)
  )