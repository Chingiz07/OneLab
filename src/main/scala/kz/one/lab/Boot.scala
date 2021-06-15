package kz.one.lab

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.typesafe.config.{Config, ConfigFactory}
import kz.one.lab.actors.Handler
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

  def handlerProps: Props = Handler.props()

  Http().bindAndHandle(routes, host, port)

}
