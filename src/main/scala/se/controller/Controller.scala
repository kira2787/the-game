package se.controller

import se.controller.GameState._
import se.model.{Deck, DiscardPile, Hand}
import se.util.{Observable, UndoManager}


class Controller(var discardPile: DiscardPile, var hand: Hand, var deck: Deck) extends Observable {

  var gameState: GameState = IDLE
  private val undoManager = new UndoManager

  def createGame(): Unit = {

    hand = hand.firstDraw(deck.deck.slice(0, 5))
    deck = deck.recreateDeck(5)

    notifyObservers
  }

  def draw(handIndex:Int, pileIndex: Int): Unit = {
    undoManager.doStep(new DrawCommand(handIndex, pileIndex, this))
    notifyObservers
  }

  def gameToString(): String = discardPile.toString + "\n\nHand:" + hand.toString + "\n\n - - - -"

  def sortHand(): Unit = {
    hand = hand.sort()
    notifyObservers
  }

  def checkWin(): Boolean = deck.deck.isEmpty

  def checkLoose(): Boolean = {
    val pile = discardPile.discardPile
    var bool: Int = 0

    for (x <- hand.hand) {
      if ((x > pile(0) && x > pile(1)) || (x < pile(2) && x < pile(3))) bool = 1
      else if ((x + 10) == pile(0) || (x + 10) == pile(1) || (x - 10) == pile(2) || (x - 10) == pile(3)) bool = 1
    }

    bool match {
      case 1 => false
      case _ => true
    }
  }

  def checkMove(handIndex: Int, pileIndex: Int): Boolean = {
    var handVal = 0
    var pileVal = 0
    var bool: Int = 0

    if (handIndex > 4 || pileIndex > 3) bool = 1
    else {
      handVal = hand.hand(handIndex)
      pileVal = discardPile.discardPile(pileIndex)

      if (pileIndex < 2) {
        if ((handVal < pileVal) && (!((handVal + 10) == pileVal))) bool = 1
      } else {
        if ((handVal > pileVal) && (!((handVal - 10) == pileVal))) bool = 1
      }
    }
    bool match {
      case 1 => false
      case _ => true
    }
  }

  def undo(): Unit = {
    undoManager.undoStep()
    notifyObservers
  }

  def redo(): Unit = {
    undoManager.redoStep()
    notifyObservers
  }
}
