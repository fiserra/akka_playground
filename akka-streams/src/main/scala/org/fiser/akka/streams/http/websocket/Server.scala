package org.fiser.akka.streams.http.websocket

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl._
import akka.http.scaladsl.model.ws.{Message, TextMessage}
import akka.http.scaladsl.server.Directives._
import akka.stream.scaladsl.{BroadcastHub, Flow, Keep, MergeHub, RunnableGraph, Sink, Source}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.io.StdIn

/**
  * https://markatta.com/codemonkey/blog/2016/10/02/chat-with-akka-http-websockets/
  */
object Server {

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()

    val (chatSink, chatSource) = MergeHub.source[String].toMat(BroadcastHub.sink[String])(Keep.both).run()

    val userFlow: Flow[Message, Message, NotUsed] =
      Flow[Message].mapAsync(1) {
        case TextMessage.Strict(text) =>       Future.successful(text)
        case streamed: TextMessage.Streamed => streamed.textStream.runFold("")(_ ++ _)
      }.via(Flow.fromSinkAndSource(chatSink, chatSource))
        .map[Message](string => TextMessage(string))

    val route =
      path("chat") {
        get {
          handleWebSocketMessages(userFlow)
        }
      }

    val binding =  Await.result(Http().bindAndHandle(route, "127.0.0.1",8080), 3 seconds)

    val sink: Sink[String, NotUsed] = MergeHub.source[String].to(Sink.foreach(println)).run()
    Source.single("Hello world 1").runWith(sink)
    Source.single("Hello world 2").runWith(sink)

    val runnableGraph: RunnableGraph[Source[Int, NotUsed]] = Source(0 to 2000).toMat(BroadcastHub.sink[Int])(Keep.right)
    val source: Source[Int, NotUsed] = runnableGraph.run()

    source.runForeach(n => println(s"source1: $n"))
    source.runForeach(n => println(s"source2: $n"))

    println("Started server at 127.0.0.1:8080, press enter to kill server")
    StdIn.readLine()
    system.terminate()
  }
}
