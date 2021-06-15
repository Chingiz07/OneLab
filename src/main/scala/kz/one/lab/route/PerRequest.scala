package kz.one.lab.route

import akka.actor.{ActorLogging, ActorRef, ActorSystem, Props, Actor => AkkaActor}
import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.model.{StatusCode, StatusCodes}
import akka.http.scaladsl.server.{RequestContext, RouteResult}
import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import kz.one.lab.domain.Book
import kz.one.lab.serializer.{Serializer, SerializerWithoutTypeHints}

import scala.concurrent.Promise

trait PerRequest extends AkkaActor with SerializerWithoutTypeHints with ActorLogging with Json4sSupport {

  import context._

  def p: Promise[RouteResult]
  def target: ActorRef
  def message: Any
  def r: RequestContext

  target ! message

  def receive: Receive = {
    case a: Book => complete(StatusCodes.OK, a)
  }

  def complete(status: StatusCode, obj: => ToResponseMarshallable) = {

    val f = status match {
      case StatusCodes.NoContent =>
        r.complete(None)

      case _ =>
        r.complete(obj)

    }

    f.onComplete(p.complete(_))

    stop(self)
  }

}

object PerRequest {

  case class WithActorRef(r: RequestContext, target: ActorRef, message: Any, p: Promise[RouteResult]) extends PerRequest

  case class WithProps(r: RequestContext, props: Props, message: Any, p: Promise[RouteResult], actorName: Option[String] = None) extends PerRequest {
    lazy val target = actorName match {
      case Some(name) => context.actorOf(props, name)
      case _ => context.actorOf(props)
    }
  }

}

trait PerRequestCreator {

  def handleRequest(r: RequestContext, props: Props, message: Any, p: Promise[RouteResult])(implicit system: ActorSystem): ActorRef = {
    system.actorOf(Props(PerRequest.WithProps(r, props, message, p)))
  }

}
