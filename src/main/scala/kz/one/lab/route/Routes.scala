package kz.one.lab.route

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.BasicDirectives
import akka.util.Timeout
import de.heikoseeberger.akkahttpjson4s.Json4sSupport


trait Routes extends BasicDirectives with Json4sSupport {

  implicit val actorSystem: ActorSystem
  implicit val timeout: Timeout

  def handlerProps: Props

  val routes: Route =
    pathPrefix("ping") { //POST localhost:8080/ping/start
      concat(
        path("start") {
          post {
            complete("OK")
          }
        }
      )
    }

}
