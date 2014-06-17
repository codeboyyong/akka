package com.codeboyyong.akkasample.remote.parallel

import com.codeboyyong.akkasample.util.AkkaUtil
import akka.actor.Address
import akka.actor.Props
import akka.actor.Deploy
import akka.remote.RemoteScope
import akka.actor.ActorSystem
/***
 * This demo is quite easy to be run on differetn remote mathine
 * kind of like hadoop map reduce cluster !
 * So we can create kind of hadoop simulator!!!
 */
object RemoteParalleDemos extends App{
//  val system1 = AkkaUtil.startAkkaRemoteSystem("localhost", "2557", "system1")
//  
//  val system2 = AkkaUtil.startAkkaRemoteSystem("localhost", "2558", "system2")
val PARALLEL = 3;
/***
 * workload = 50000
 * 1-> 17 s
 * 2->  9 s
 * 2 and 2*workload ->18
 * 3->  6
 * 3 and 3*workload -> 18
 * 4->  5
 * ----------------------------
 * 
 * 8->  4  s
 * 8  and 2*workload ->9 
 * 16-> 4
 * 29 ->5
 */
for(i<-0 until PARALLEL){
   AkkaUtil.startAkkaRemoteSystem("localhost", 2557+i+"", "system"+i)
}
   val localSystem = ActorSystem("mylocalsystem")
   val parallelSum =localSystem.actorOf(Props[ParallelSumActor])
  
   parallelSum !  CalculteSUM(1, 50000*3, PARALLEL)
  
}