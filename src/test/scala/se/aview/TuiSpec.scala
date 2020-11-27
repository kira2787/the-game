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
 }
}