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

  def draw(handIndex: Int, pileIndex: Int): Unit = {
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
    var bool: Boolean = true

    for (x <- hand.hand) {
      for (p <- pile if pile.indexOf(p) < 2) {
        if (x > p) bool = false
        if ((x + 10) == p) bool = false
      }
      for (p <- pile if pile.indexOf(p) >= 2) {
        if (x < p) bool = false
        if ((x - 10) == p) bool = false
      }
    }
    bool
  }

  def checkMove(handIndex: Int, pileIndex: Int): Boolean = {
    var handVal = 0
    var pileVal = 0
    var bool: Boolean = true

    if (handIndex > 4 || pileIndex > 3) bool = false
    else {
      handVal = hand.hand(handIndex)
      pileVal = discardPile.discardPile(pileIndex)

      if (pileIndex < 2) {
        if ((handVal < pileVal) && (!((handVal + 10) == pileVal))) bool = false
      } else {
        if ((handVal > pileVal) && (!((handVal - 10) == pileVal))) bool = false
      }
    }
    bool
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
