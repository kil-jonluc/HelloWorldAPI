import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import scala.concurrent.ExecutionContext

import scala.util.{ Failure, Success }
//import akka.actor.Status.Success
//import akka.http.scaladsl.model.StatusCodes.Success
import akka.http.scaladsl.model._

object Server extends App {

  val host = "0.0.0.0"
  val port = 9000;
  //used to manage actors. It is used for creating and looking them up
  implicit val system: ActorSystem = ActorSystem("helloworld");
  //in charge of executing Futures
  implicit val executor: ExecutionContext = system.dispatcher
  // in charge of running streams
  implicit val materializer: ActorMaterializer = ActorMaterializer();

  //Route
  def route = path("hello") {  get {    complete("Hello, World!")  }};

  //Start the server
  //Http().bindAndHandle(route, host, port);

  //logging
  val bindingFuture = Http().bindAndHandle(route, host, port);
  bindingFuture.onComplete {
    case Success(serverBinding) => println(s"listening to ${serverBinding.localAddress}")
    case Failure(error) => println(s"error: ${error.getMessage}")
  }
}
