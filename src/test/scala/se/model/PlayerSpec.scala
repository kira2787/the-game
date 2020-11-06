package se.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlayerSpec extends AnyWordSpec with Matchers {
  "A Player" when {
    "new" should {
      val player = Player("Kira")
      "have a name" in {
        player.name should be("Kira")
      }
      "have a nice String representation" in {
        player.toString should be("Kira")
      }
    }
  }
}