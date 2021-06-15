package kz.one.lab

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.typesafe.config.{Config, ConfigFactory}
import kz.one.lab.actors.Handler
import kz.one.lab.domain.Book
import kz.one.lab.route.Routes

import java.util.concurrent.TimeUnit

object Boot extends App with Routes {
  //For formating code: Ctrl + Alt + L
  implicit val config: Config = ConfigFactory.load()
  val systemName = config.getString("akka.system")

  implicit val timeout: Timeout                = Timeout(60, TimeUnit.SECONDS)
  implicit val actorSystem: ActorSystem        = ActorSystem(systemName)
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val host: String = config.getString("akka.http.host")
  val port: Int = config.getInt("akka.http.port")

  val books: Seq[Book] = Seq(
    Book("1", "Chingiz", "Scala"),
    Book("2", "Maksat", "Swift"),
    Book("3", "Ameer", "Akka")
  )
  def handlerProps: Props = Handler.props(books)

  Http().bindAndHandle(routes, host, port)

}
