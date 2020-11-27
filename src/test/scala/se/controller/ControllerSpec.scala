package se.controller

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import se.TheGame.controller
import se.util.Observer

import scala.language.reflectiveCalls

class ControllerSpec extends AnyWordSpec with Matchers {
  "A controller" when {
    "observed by an Observer" should {
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Boolean = {
          updated = true;
          updated
        }
      }
      controller.add(observer)
      "notify its Observer after creation" in {
        controller.createGame()
        observer.updated should be(true)
        controller.discardPile should be(1,1,100,100)
      }
      "notify its Observer after drawing" in {
        controller.createGame()
        observer.updated should be(true)
      }
      "notify its Observer after sorting cards" in {
        controller.sortHand()
        observer.updated should be(true)
      }
    }
  }
}