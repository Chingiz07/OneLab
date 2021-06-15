package kz.one.lab.actors

import akka.actor.{Actor, ActorLogging, Props}
import kz.one.lab.domain.Book

object Handler {

  case class CreateBook(book: Book)

  def props(books: Seq[Book]): Props = Props(new Handler(books))

}

class Handler(books: Seq[Book]) extends Actor with ActorLogging {

  def receive: Receive = {
    case Handler.CreateBook(book) =>
      val localBooks = books :+ book
      log.info(s"$localBooks")
      context.parent ! book
  }

}
