package se.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DeckSpec extends AnyWordSpec with Matchers {
  "The size of the deck" when {
    "new" should {
      val deck = Deck().createDeck()
      "size should be" in {
        deck.length should be(196)
      }
      "contain a number twice" in {
        deck.count(_ == 2) should be(2)
      }
    }
  }
}