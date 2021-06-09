package kz.one.lab

import akka.actor.ActorSystem
import kz.one.lab.actors.Actor1

object Boot extends App {

  implicit val actorSystem: ActorSystem = ActorSystem("Lab2")

  val actor1 = actorSystem.actorOf(Actor1.props)
  val actor2 = actorSystem.actorOf(Actor1.props)

  actor1 ! "Ping"
  actor1 ! Actor1.Hello("Hello")

}