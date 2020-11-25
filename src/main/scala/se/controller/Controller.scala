package se.controller

import se.model.{Deck, DiscardPile, Hand}
import se.util.Observable


class Controller(var discardPile: DiscardPile, var hand: Hand, var deck: Deck) extends Observable {

  def createGame(): Unit = {

    discardPile = new DiscardPile()
    deck = new Deck()
    hand = new Hand()

    hand = hand.firstDraw(deck.deck.slice(0, 5))
    deck = deck.recreateDeck(5)

    notifyObservers
  }

  def draw(value: Int, toIndex: Int): Unit = {

    hand = hand.draw(deck.deck.head, hand.hand.indexOf(value))
    deck = deck.recreateDeck(1)
    discardPile = discardPile.draw(value, toIndex)

    notifyObservers
  }

  def gameToString: String = discardPile.toString + "\n\nHand:" + hand.toString + "\n\n - - - -"

  def sortHand(): Unit = {
    hand = hand.sort()
    notifyObservers
  }

  def checkWin(): Boolean = deck.deck.isEmpty

  def checkLoose(): Boolean = {
    val pile = discardPile.discardPile

    for (x <- hand.hand) {
      if ((x > pile(0) && x > pile(1)) || (x < pile(2) && x < pile(3))) false
      // not lost, if card on hand is exact 10 less then pile 0,1 oder 10 more than pile 2,3
      else if ((x + 10) == pile(0) || (x + 10) == pile(1) || (x - 10) == pile(2) || (x - 10) == pile(3)) false
    }
    true
  }

  def checkMove(value: Int, toIndex: Int): Boolean = {
    val pile = discardPile.discardPile

    if (!hand.hand.contains(value)) {
      return false
    } else if (toIndex > pile.length) {
      return false
    }
    if (toIndex < 2) {
      if ((value < pile(toIndex)) && (!((value + 10) == pile(toIndex)))) {
        return false
      }
    } else {
      if ((value > pile(toIndex)) && (!((value - 10) == pile(toIndex)))) {
        return false
      }
    }
    true
  }
}
