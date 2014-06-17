package com.codeboy.akkasample.server;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.Creator;

import com.codeboyyong.akkasample.remote.MyRemoteActor;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Excample of using a java API to start the actor system in a programmatic way
 * User: John Zhao Date: 5/20/14 Time: 9:07 PM
 */
public class ServerMainProgrammatic {
	public static void main(String[] args) {
		Map<String, Object> configureMap = new HashMap<String, Object>() {
			{
				put("akka.actor.provider", "akka.remote.RemoteActorRefProvider");

				put("akka.actor.deployment./mypath.remote",
						"akka.tcp://myRemoteActorSystem@127.0.0.1:2553");

				//note: value is a list
				put("akka.remote.enabled-transports",
						Arrays.asList(new String[] { "akka.remote.netty.tcp" }));
				put("akka.remote.netty.tcp.port", 2553);
				put("akka.remote.netty.tcp.hostname", "127.0.0.1");

			}
		};

		Config akkaConfig = ConfigFactory.parseMap(configureMap);

		ActorSystem actorSystem = ActorSystem.create("myRemoteActorSystem",akkaConfig);

		ActorRef actor = actorSystem.actorOf(
				Props.create(new MyActorCreator()), "myRemoteActor");
		actor.tell("the remote is up", actor);

	}

	static class MyActorCreator implements Creator<MyRemoteActor> {
		public MyRemoteActor create() throws Exception {
			return new MyRemoteActor();
		}
	}

}
