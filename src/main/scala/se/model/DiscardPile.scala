package se.model

case class DiscardPile(discardPile: Vector[Int]) {
  def this() = this(Vector(1, 1, 100, 100))

  def draw(newValue: Int, index: Int): DiscardPile = {
    copy(discardPile.updated(index, newValue))
  }
}
