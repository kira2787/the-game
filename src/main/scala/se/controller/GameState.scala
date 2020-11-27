package se.controller

object GameState extends Enumeration {
  type GameState = Value
  val WAITING, VALID, INVALID, WON = Value

  private val map = Map[GameState, String](
    WAITING -> "Please place a card from your hand on the discard pile.",
    VALID -> "Move is valid",
    INVALID -> "Move is not valid",
    WON -> "You won!",
  )

  def message(gameStatus: GameState) = {
    map(gameStatus)
  }
}