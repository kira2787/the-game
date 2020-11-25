package se.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DiscardPileSpec extends AnyWordSpec with Matchers {

  "The Discard Pile" when {
    "new" should {
      val discardPile = new DiscardPile()
      "should be" in {
        discardPile.discardPile.mkString(",") should be("1,1,100,100")
      }
    }
  }

}