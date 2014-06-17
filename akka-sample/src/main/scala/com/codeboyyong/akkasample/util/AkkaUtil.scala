package com.codeboyyong.akkasample.util

import java.util.HashMap

import com.typesafe.config.ConfigFactory

import akka.actor.ActorSystem

object AkkaUtil {
  def startAkkaRemoteSystem(host: String, port: String, name: String = "myRemoteActorSystem"): ActorSystem = {

    val configureMap = new HashMap[String, Object]
    configureMap.put("akka.actor.provider", "akka.remote.RemoteActorRefProvider")

    //note: value is a list
   // configureMap.put("akka.remote.enabled-transports",
    //  List("akka.remote.netty.tcp"));
    configureMap.put("akka.remote.netty.tcp.port",new Integer(port));
    configureMap.put("akka.remote.netty.tcp.hostname", host);

    val akkaConfig = ConfigFactory.parseMap(configureMap);

    val actorSystem = ActorSystem.create(name, akkaConfig);
    return actorSystem;

  }
}