package se.controller

object GameState extends Enumeration {
  type GameState = Value
  val IDLE, INVALID, WON, LOST = Value

  val map = Map[GameState, String](
    IDLE -> "",
    INVALID -> "The move is not valid",
    WON -> "You won :)",
    LOST -> "You lost :("
  )

  def message(gameState: GameState): String = {
    map(gameState)
  }
}
