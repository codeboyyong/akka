package com.codeboyyong.akkasample.remote.parallel

import akka.actor.UntypedActor
import akka.actor.Actor
import com.codeboyyong.akkasample.util.AkkaUtil
import akka.actor.Address

case class SUM(start: Int, end: Int, taskId: String)

case class SUMResult(sum: Double, id: String)

class SumActor extends  Actor {
  def receive: Actor.Receive = {
    case SUM(start: Int, end: Int, taskId: String) => {
      var result: Double = 0;
      for (i <- start until end) {
        result = result + i
         for (j <- 1 until 1000) {
       //wast tile
           val x = (j*2+3)/6
            for (k <- 1 until 1000) {
       //wast tile
           val y =( (k+j)*2+3)/6
           
         }
         }
      }
       
      println("result:" + result)
      sender ! SUMResult(result, taskId)
    }

  }
}
 