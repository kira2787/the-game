package se.model

import scala.util.Random

case class Deck(deck: List[Int]) {
  def this() = this(Random.shuffle(List.range(2, 100) ::: List.range(2, 100)))

  def recreateDeck(firstN: Int) = {
    copy(deck.drop(firstN))
  }

  def redoDeck(value: Int) = {
    copy(value :: deck)
  }
}