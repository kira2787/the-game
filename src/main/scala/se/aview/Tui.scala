package se.aview

import se.controller.Controller
import se.util.Observer

class Tui(controller: Controller) extends Observer {

  controller.add(this)

  def processInputLine(input: String): Unit = {
    input match {
      case "q" =>
      case "i" => invalidInputMsg() // invalid input
      case "h" => printGameRules() //help - game rules
      case "o" => controller.sortHand()
      case "n" => controller.createGame()
      case _ => input.split(" ").toList match {
        case value :: toIndex :: Nil => if (controller.checkMove(value.toInt, toIndex.toInt)) {
          controller.draw(value.toInt, toIndex.toInt)
        } else invalidInputMsg()
        case _ => invalidInputMsg()
      }
    }
  }

  override def update: Unit = println(controller.gameToString)

  def invalidInputMsg() = {
    println("Your input was invalid. Please enter h for help and read the game rules again.")
  }

  def printGameRules() = {
    println("\nA Player in The Game tries to discard all 98 cards in the deck onto four discard piles in order to win. " +
      "Four discard pile prompt cards are on the table: two showing \"1\" and an up arrow and " +
      "two showing \"100\" and a down arrow. \nOn a turn, a player must discard a card from the hand onto " +
      "one discard piles, with cards on the 1 piles being placed in ascending order and cards on the 100 piles " +
      "being placed in descending order. \nOne tricky aspect of the game is that you can play a card exactly 10 higher/lower " +
      "than the top card of a discard pile even when you would normally have to play in a descending/ascending order, \ne.g., " +
      "if a 100 discard pile is topped with an 87, you can play any card lower than 87 or you can play the 97.\nAfter " +
      "a player finishes a turn, they refill their hand from the deck." +
      "\nIf you play all 98 cards, you win!\n")

  }
}
