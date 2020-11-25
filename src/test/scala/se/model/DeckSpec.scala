package se.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DeckSpec extends AnyWordSpec with Matchers {

  "The size of the deck" when {
    "new" should {
      val deck = new Deck()
      "size should be" in {
        deck.deck.length should be(196)
      }
      "contain a number twice" in {
        deck.deck.count(_ == 2) should be(2)
      }
    }
  }
}
