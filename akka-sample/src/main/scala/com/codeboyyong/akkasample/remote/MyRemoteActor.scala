package com.codeboyyong.akkasample.remote

import akka.actor.Actor


class MyRemoteActor( )
    extends Actor  {


  def receive: Actor.Receive = {
    case str:String  => println("MyRemoteActor:get a string :" +str +" sender=" + sender)
    case intValue:Integer  => println("MyRemoteActor:get a int :" +intValue)
    case obj:Object => println("MyRemoteActor:get a message :" +obj.toString)


  }


}
