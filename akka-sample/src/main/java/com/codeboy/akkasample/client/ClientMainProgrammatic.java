package com.codeboy.akkasample.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Address;
import akka.actor.Deploy;
import akka.actor.Props;
import akka.japi.Creator;
import akka.remote.RemoteScope;

import com.codeboyyong.akkasample.remote.MyRemoteActor;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ClientMainProgrammatic {
	public static void main(String[] args) {

		Map<String, Object> configureMap = new HashMap<String, Object>() {
			{
				put("akka.actor.provider", "akka.remote.RemoteActorRefProvider");

				// note: value is a list
				put("akka.remote.enabled-transports",
						Arrays.asList(new String[] { "akka.remote.netty.tcp" }));
			}
		};

		Config akkaConfig = ConfigFactory.parseMap(configureMap);
		ActorSystem actorSystem = ActorSystem.create("client", akkaConfig);

		// val serverUrl = "akka.tcp://myRemoteActorSystem@127.0.0.1:2553"
		Address addr = new Address("akka.tcp", "myRemoteActorSystem",
				"127.0.0.1", 2553);
		
		// remember , "user" in the path is hardcoded. """)))
		// val remoteServer = clientSystem.actorSelection(serverUrl +
		// "/user/myRemoteActor")
		final ActorRef remoteServer = //actorSystem.actorOf(new Props(new Deploy(new RemoteScope(addr)), MyRemoteActor.class, "name"));
				actorSystem.actorOf(  Props.create(new MyCreator()).withDeploy(new Deploy(new RemoteScope(addr))), "clientCalcA1");    
        
				// client even dont need an actor. if this what's is the send in the
		// remote actor?
		// it is a dead letter -> MyRemoteActor:get a string :HEY
		// sender=Actor[akka://myRemoteActorSystem/deadLetters]
		remoteServer.tell("HEY", null);
		remoteServer.tell(123, null);
	}
	
	static class MyCreator implements    Creator<Actor> {

		public Actor create() throws Exception {
			return new MyRemoteActor();
		}}; 
}