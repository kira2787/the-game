package se.controller

import se.controller.GameState._
import se.util.Command

class DrawCommand(handIndex: Int, pileIndex: Int, controller: Controller) extends Command {

  var memento: List[Int] = Nil

  var oldPile: Int = 0

  override def doStep(): Unit = {

    if (!controller.checkMove(handIndex, pileIndex)) controller.gameState = INVALID
    else {
      oldPile = controller.discardPile.discardPile(pileIndex)
      controller.discardPile = controller.discardPile.draw(pileIndex, controller.hand.hand(handIndex))
      controller.hand = controller.hand.draw(controller.deck.deck.head, handIndex)
      controller.deck = controller.deck.recreateDeck(1)
      controller.gameState = IDLE

      if(controller.checkWin()) controller.gameState = WON
      if(controller.checkLoose()) controller.gameState = LOST

    }
    memento = handIndex :: pileIndex :: oldPile :: Nil
  }

  override def undoStep(): Unit = {
    controller.deck = controller.deck.redoDeck(controller.hand.hand(handIndex))
    controller.hand = controller.hand.redoHand(handIndex, controller.discardPile.discardPile(pileIndex))
    controller.discardPile = controller.discardPile.redoPile(pileIndex, oldPile)

    memento = handIndex :: pileIndex :: oldPile :: Nil
  }

  override def redoStep(): Unit = {

    if (!controller.checkMove(handIndex, pileIndex)) controller.gameState = INVALID
    else {
      oldPile = controller.discardPile.discardPile(pileIndex)
      controller.discardPile = controller.discardPile.draw(pileIndex, controller.hand.hand(handIndex))
      controller.hand = controller.hand.draw(controller.deck.deck.head, handIndex)
      controller.deck = controller.deck.recreateDeck(1)
      controller.gameState = IDLE
    }
    memento = handIndex :: pileIndex :: oldPile :: Nil
  }
}