package se

import se.model.Player

object TheGame {
  def main(args: Array[String]): Unit = {
    println("Hello, my game is THE game")

    val student = Player("Your Name")
    println("Hello, " + student.name)
  }
}