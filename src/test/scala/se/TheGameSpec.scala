package se

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TheGameSpec extends AnyWordSpec with Matchers {
  "The Game main class " should {
    "accept text input as argument" in {
      TheGame.main(Array[String]("h"))
    }
  }
}