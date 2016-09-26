name := "akka_playground"
import Dependencies._

lazy val commonSettings = Seq(
  organization := "com.fiser",
  version := "1.0",
  scalaVersion := "2.11.8"
)

lazy val fp = (project in file("akka-streams")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= Seq(akka_stream)
  )

lazy val cats = (project in file("akka-persistence")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= Seq(akka_persistence)
  )

lazy val type_classes = (project in file("akka")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= Seq(akka)
  )

    