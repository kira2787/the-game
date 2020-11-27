package se.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class HandSpec extends AnyWordSpec with Matchers {
  "The hand" when {
    var hand = Hand(Vector.empty)
    "new" should {
      "should be" in {
        hand.hand.length should be(0)
      }
    }
    "after first draw" should {
      "size should be" in {
        hand = hand.firstDraw(List[Int](5, 4, 3, 2, 1))
        hand.hand.length should be(5)
      }
    }
    "ordered" should {
      "ordered vector should be" in {
        hand = hand.sort()
        hand should be(Hand(Vector(1, 2, 3, 4, 5)))
      }
    }
    "after drawing during the game" should {
      "hand should be" in {
        hand = hand.firstDraw(List[Int](5, 4, 3, 2, 1))
        hand = hand.draw(6, 0) //new value index
        hand should be(Hand(Vector(6, 4, 3, 2, 1)))
      }
    }
  }
}