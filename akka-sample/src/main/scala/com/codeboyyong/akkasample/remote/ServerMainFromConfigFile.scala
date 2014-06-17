package com.codeboyyong.akkasample.remote


import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
 


object ServerMainConfigFile extends App {
  import akka.actor.ActorSystem
  import com.typesafe.config.ConfigFactory
  
  //auto found the ocnfig application.conf
  val system = ActorSystem("myRemoteActorSystem")
  val actor =  system.actorOf(Props[MyRemoteActor],"myRemoteActor")
  actor ! "the remote is up"
 }