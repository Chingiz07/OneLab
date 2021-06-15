package kz.one.lab.actors

import akka.actor.{Actor, ActorLogging, Props}
import kz.one.lab.domain.Book

object Handler {

  case class CreateBook(book: Book)

  def props(): Props = Props(new Handler())

}

class Handler extends Actor with ActorLogging {

  var books: Seq[Book] = Seq.empty[Book]

  def receive: Receive = {
    case Handler.CreateBook(book) =>
      books = books :+ book
      log.info(s"$books")
      context.parent ! book
  }

}
