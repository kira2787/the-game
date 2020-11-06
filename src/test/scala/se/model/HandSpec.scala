package se.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class HandSpec extends AnyWordSpec with Matchers {
  "The hand" when {
    "new" should {
      val hand = Hand().initializeHand()
      "should be" in {
        hand.length should be(0) //traversable shouldBe empty books should not be empty
      }
    }
  }
}