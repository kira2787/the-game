package se.model

case class Hand(hand: Vector[Int]) {
  def this() = this(Vector.empty)

  def draw(newValue: Int, index: Int): Hand = {
    copy(hand.updated(index, newValue))
  }

  def firstDraw(deck: List[Int]): Hand = {
    copy(hand ++ deck)
  }

  def sort(): Hand = {
    copy(hand.sorted)
  }
}