package com.codeboyyong.akkasample.remote

 import akka.actor.{Props, ActorSystem}
import akka.actor.actorRef2Scala
import com.typesafe.config.ConfigFactory
 


object ServerMainConfigString extends App {
  import akka.actor.ActorSystem
  import com.typesafe.config.ConfigFactory
  val customConf = ConfigFactory.parseString("""
      akka{
        actor{
          provider ="akka.remote.RemoteActorRefProvider"
         }
        remote{
              enabled-transports=["akka.remote.netty.tcp"]
              netty.tcp{
                      port = 2553
                      hostname="127.0.0.1"
                      }

               }
      }""")
  // ConfigFactory.load sandwiches customConfig between default reference
  // config and default overrides, and then resolves it.
  val system = ActorSystem("myRemoteActorSystem", ConfigFactory.load(customConf))
  val actor =  system.actorOf(Props[MyRemoteActor],"myRemoteActor")
  actor ! "the remote is up"
 }