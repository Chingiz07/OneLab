package kz.one.lab.actors

import akka.actor.{Actor, ActorLogging, Props}
import Actor1.Hello

object Actor1 {

  case class Hello(name: String)

  def props: Props = Props(new Actor1)

}

class Actor1 extends Actor with ActorLogging {

  override def receive: Receive = {
    case a@Hello(string) =>
      log.info(s"Class: ${a.getClass.getSimpleName}, value: $string, sender: ${sender()}")

    case a@"Ping" =>
      log.info(s"$a")
  }

}
