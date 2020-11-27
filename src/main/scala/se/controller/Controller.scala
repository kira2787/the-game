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

  def checkMove(value: Int, toIndex: Int): Boolean = {
    val pile = discardPile.discardPile
    var bool: Int = 0

    if (!hand.hand.contains(value)) bool = 1
    else if (toIndex > pile.length) bool = 1

    if (toIndex < 2) {
      if ((value < pile(toIndex)) && (!((value + 10) == pile(toIndex)))) bool = 1
    } else {
      if ((value > pile(toIndex)) && (!((value - 10) == pile(toIndex)))) bool = 1
    }

    bool match {
      case 1 => false
      case _ => true
    }
  }
}
