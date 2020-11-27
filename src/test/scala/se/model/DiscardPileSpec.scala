package se.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DiscardPileSpec extends AnyWordSpec with Matchers {

  "The Discard Pile" when {
    var discardPile = new DiscardPile()
    "new" should {
      "should be" in {
        discardPile.discardPile.mkString(",") should be("1,1,100,100")
      }
    }
    "after draw" should {
      "should be" in {
        discardPile = discardPile.draw(2, 0)
        discardPile should be(DiscardPile(Vector(2, 1, 100, 100)))
      }
    }
  }
}