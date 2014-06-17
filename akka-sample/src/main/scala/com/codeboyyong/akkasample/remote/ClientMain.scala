package com.codeboyyong.akkasample.remote

import com.typesafe.config.ConfigFactory
import akka.actor.ActorSystem
import akka.actor.ActorSelection.toScala
 
object ClientMain extends App {
  val serverUrl = "akka.tcp://myRemoteActorSystem@127.0.0.1:2553"

  val clientSystem = ActorSystem("client", ConfigFactory.load(ConfigFactory.parseString( """
  akka {
    actor {
      provider = "akka.remote.RemoteActorRefProvider"
    }
     remote {
      transport = "akka.remote.netty.tcp"
      }
  }""")));

   //remember , "user" in the path is hardcoded.                                                                                        """)))
  val remoteServer = clientSystem.actorSelection(serverUrl + "/user/myRemoteActor")

  //client even dont need an actor. if this what's is the send in the remote actor?
  // it is a dead letter -> MyRemoteActor:get a string :HEY sender=Actor[akka://myRemoteActorSystem/deadLetters]
  remoteServer ! "HEY"
  remoteServer ! 123
}