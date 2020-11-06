package se.model

case class DiscardPile() {

  def createDiscardPile(): Vector[Int] = {
    Vector(1, 1, 100, 100)
  }
}
