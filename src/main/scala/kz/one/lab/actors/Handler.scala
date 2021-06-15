package kz.one.lab.actors

import akka.actor.{Actor, ActorLogging, Props}

object Handler {

  case class Start()

  def props(): Props = Props(new Handler())

}

class Handler extends Actor with ActorLogging {

  def receive: Receive = {
    case Handler.Start() =>
      log.info("Started")
      context.parent ! "Started"
  }

}
