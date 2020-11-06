package se.model

import scala.util.Random

case class Deck() {

  def createDeck(): List[Int] = {
    Random.shuffle(List.range(2, 100) ::: List.range(2, 100))
  }
}