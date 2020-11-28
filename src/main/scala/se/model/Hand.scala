package se.model

case class Hand(hand: Vector[Int]) {
  def this() = this(Vector.empty)

  def draw(value: Int, index: Int): Hand = {
    copy(hand.updated(index, value))
  }

  def firstDraw(deck: List[Int]): Hand = {
    copy(hand ++ deck)
  }

  def sort(): Hand = {
    copy(hand.sorted)
  }

  def redoHand(index:Int, value:Int): Hand = {
    copy(hand.updated(index, value))
  }
}