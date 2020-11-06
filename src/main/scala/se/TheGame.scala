package se

import se.model._

object TheGame {
  def main(args: Array[String]): Unit = {
    println("Hello, my game is THE game")

    val player1 = Player("Player 1")
    println("Hello, " + player1.name)

  }
}