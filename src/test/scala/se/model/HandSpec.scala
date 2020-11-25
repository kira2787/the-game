package se.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class HandSpec extends AnyWordSpec with Matchers {
  "The hand" when {
    "new" should {
      val hand = new Hand()
      "should be" in {
        hand.hand.length should be(0)
      }
    }
  }
}