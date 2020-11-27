package se.aview

import se.controller.Controller
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import se.model.{Deck, DiscardPile, Hand}

class TuiSpec extends AnyWordSpec with Matchers {
  "The Game controller" should {
    var discardPile = new DiscardPile()
    var hand = new Hand()
    var deck = new Deck()
    val controller = new Controller(discardPile, hand, deck)
    val tui = new Tui(controller)
    "do nothing on input 'q'" in {
      tui.processInputLine("q")
    }
    "print game rules on input 'h'" in {
      tui.processInputLine("h")
    }
    "print invalid message on input 'i'" in {
      tui.processInputLine("i")
    }
    "create new game on input 'n'" in {
      tui.processInputLine("n")
    }
    "sort hand on input 'o'" in {
      tui.processInputLine("o")
    }
    "check move and draw on valid input" in {
      tui.processInputLine(controller.hand.hand(1) + " 0")
      controller.checkMove(controller.hand.hand(1), 0) should be(true)
    }
    "send invalid message on invalid input" in {
      tui.processInputLine("abc")
      controller.checkMove(111, 0) should be false
    }
  }
}