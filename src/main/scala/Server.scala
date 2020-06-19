import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import scala.concurrent.ExecutionContext

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
  Http().bindAndHandle(route, host, port);
}
