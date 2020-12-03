package se.model

case class DiscardPile(discardPile: Vector[Int]) {
  def this() = this(Vector(1, 1, 100, 100))

  def draw(index: Int, value: Int): DiscardPile = {
    copy(discardPile.updated(index, value))
  }

  def redoPile(index: Int, value: Int): DiscardPile = {
    copy(discardPile.updated(index, value))
  }
}
