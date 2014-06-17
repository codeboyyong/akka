package com.codeboyyong.akkasample.remote.parallel

import akka.actor.UntypedActor
import akka.actor.Actor
import com.codeboyyong.akkasample.util.AkkaUtil
import akka.actor.Address
import akka.actor.Props
import akka.actor.Deploy
import akka.remote.RemoteScope
case class CalculteSUM(start: Int, end: Int, numOfTasks: Int)

class ParallelSumActor() extends Actor {

  var startTime: Long = 0
  var numOfTasks = 0
  var numOfFinishedTasks = 0
  var totalSum :Double= 0
  def receive: Actor.Receive = {

    case CalculteSUM(start: Int, end: Int, nTasks: Int) => {
      numOfTasks = nTasks
      startTime = System.currentTimeMillis()
      if (numOfTasks > 30) {
        throw new Exception("task number can not bigger than 30")
      }
      val step = (end - start) / numOfTasks

      for (i <- 0 until numOfTasks) {
        val address = Address("akka.tcp", "system" + i, "localhost", 2557 + i)

        val actor = context.actorOf((Props[SumActor]).withDeploy(Deploy(scope = RemoteScope(address))))
        actor ! SUM(i * step, (i + 1) * step, "task_" + i)
      }

    }
    case SUMResult(sum: Double, id: String) => {
      println("task[" + id + "] finished, sum =" + sum)
      numOfFinishedTasks = numOfFinishedTasks + 1
      totalSum = totalSum + sum
      if (numOfFinishedTasks == numOfTasks) {
        println("All task finished: total Sum=" + totalSum)
        println("All task finished: total time=" + (System.currentTimeMillis() - startTime) / 1000 +" seconds")
      }
    }

  }
}
 