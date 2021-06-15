package kz.one.lab.route

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{Route, RouteResult}
import akka.http.scaladsl.server.directives.BasicDirectives
import akka.util.Timeout
import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import kz.one.lab.actors.Handler
import kz.one.lab.domain.Book
import kz.one.lab.serializer.Serializer

import scala.concurrent.Promise


trait Routes extends PerRequestCreator with Serializer with BasicDirectives with Json4sSupport {

  implicit val actorSystem: ActorSystem
  implicit val timeout: Timeout

  def handlerProps: Props

  val routes: Route =
    pathPrefix("library") { //POST localhost:8080/ping/start
      concat(
        path("create-book") {
          post {
            entity(as[Book]) { entity =>
              handle(handlerProps, Handler.CreateBook(entity))
            }
          }
        }
      )
    }

  def handle(props: Props, message: Any): Route = ctx => {
    val p = Promise[RouteResult]
    handleRequest(ctx, props, message, p)
    p.future
  }

}
