package se.controller

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import se.model.{Deck, DiscardPile, Hand}
import se.util.Observer

import scala.language.reflectiveCalls

class ControllerSpec extends AnyWordSpec with Matchers {
  "A controller" when {
    var discardPile = new DiscardPile()
    var hand = new Hand()
    var deck = new Deck()
    val controller = new Controller(discardPile, hand, deck)
    "observed by an Observer" should {
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated

        override def update: Boolean = {
          updated = true
          updated
        }
      }
      controller.add(observer)
      "notify its Observer after creation" in {
        controller.createGame()
        observer.updated should be(true)
        controller.discardPile should be(DiscardPile(Vector(1, 1, 100, 100)))
        controller.hand.hand.length should be(5)
        controller.deck.deck.length should be(191)
      }
      "notify its Observer after drawing" in {
        val newValue = controller.hand.hand(0)
        controller.draw(0, 0)
        observer.updated should be(true)
        controller.discardPile.discardPile(0) should be(newValue)
      }
      "notify its Observer after sorting cards" in {
        controller.sortHand()
        observer.updated should be(true)
        // a test?
      }
      "notify its Observer after check win" in {
        observer.updated should be(true)
        controller.checkWin() should be(false)
      }
      "notify its Observer after check a move" in {
        controller.checkMove(0, 2) should be(true)
        controller.checkMove(111, 0) should be(false)
        controller.checkMove(0, 5) should be(false)
        observer.updated should be(true)
      }
      "notify its Observer after check loose" in {
        controller.checkLoose() should be(false)
        controller.hand = Hand(Vector(9, 13, 54, 69, 76))
        controller.discardPile = DiscardPile(Vector(99,99,1,1))
        controller.checkLoose() should be(true)
        observer.updated should be(true)
      }
    }
  }
}