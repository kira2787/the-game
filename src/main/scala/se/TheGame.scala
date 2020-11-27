package se

import se.aview.Tui
import se.controller.Controller
import se.model.{Deck, DiscardPile, Hand}

import scala.io.StdIn.readLine

object TheGame {
  val controller = new Controller(new DiscardPile, new Hand, new Deck)
  val tui = new Tui(controller)
  controller.notifyObservers

  var player: List[String] = Nil

  println("Welcome at THE GAME")

  println("Please enter the names of the players (only separated by a space)")
  player = readLine().split(" ").toList

  println("Enter h for help or n for new Game. " +
    "With entering o after starting the game, you are able to order the cards on your hand.")

  def main(args: Array[String]): Unit = {
    var input: String = args(0)
    if(!input.isEmpty) tui.processInputLine(input)
    else do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}