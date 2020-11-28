package se.aview

import se.controller.{Controller, GameState}
import se.util.Observer

class Tui(controller: Controller) extends Observer {

  controller.add(this)

  def processInputLine(input: String): Unit = {
    input match {
      case "q" =>
      case "h" => printGameRules() //TODO: print msg
      case "o" => controller.sortHand()
      case "n" => controller.createGame()
      case "z" => controller.undo()
      case "y" => controller.redo()
      case _ => input.split(" ").toList match {
        case handIndex :: pileIndex :: Nil => controller.draw(handIndex.toInt, pileIndex.toInt)
        case _ => //TODO: print msg
      }
    }
  }

  override def update: Boolean = {
    println(controller.gameToString)
    println(GameState.message(controller.gameState))
    controller.gameState = GameState.IDLE
    true
  }

  def printGameRules(): String = {
    "\nA Player in The Game tries to discard all 98 cards in the deck onto four discard piles in order to win. " +
      "Four discard pile prompt cards are on the table: two showing \"1\" and an up arrow and " +
      "two showing \"100\" and a down arrow. \nOn a turn, a player must discard a card from the hand onto " +
      "one discard piles, with cards on the 1 piles being placed in ascending order and cards on the 100 piles " +
      "being placed in descending order. \nOne tricky aspect of the game is that you can play a card exactly 10 higher/lower " +
      "than the top card of a discard pile even when you would normally have to play in a descending/ascending order, \ne.g., " +
      "if a 100 discard pile is topped with an 87, you can play any card lower than 87 or you can play the 97.\nAfter " +
      "a player finishes a turn, they refill their hand from the deck." +
      "\nIf you play all 98 cards, you win!\n"
  }
}
