package kz.one.lab.actors

import akka.actor.{Actor, ActorLogging, Props}
import com.sksamuel.elastic4s.ElasticClient
import com.typesafe.config.Config
import kz.one.lab.domain.Book
import kz.one.lab.service.BookService

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

object Handler {

  case class CreateBook(book: Book)

  def props(elasticClient: ElasticClient)(
    implicit ec: ExecutionContext,
    config: Config
  ): Props = Props(new Handler(elasticClient))

}

class Handler(elasticClient: ElasticClient)(
  implicit ec: ExecutionContext,
  config: Config
) extends Actor with ActorLogging {

  def bookService: BookService = new BookService(elasticClient)

  def receive: Receive = {
    case Handler.CreateBook(book) =>
      bookService.createBook(book).onComplete {
        case Success(book) =>
          context.parent ! book
        case Failure(exception) =>
          throw exception
      }
  }

}
