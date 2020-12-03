import se.model.Player

def addInt(a: Int, b: Int): Int = a + b
addInt(17, 4)

def createGame(): String = {

  val player1 = Player("Spieler 1")

  val hand = Vector(71, 12, 3, 14, 55)
  val discardPiles = Vector(0, 0, 100, 100)

  "\nSTAPEL\n\n" + discardPiles.mkString(" - ") + "\n\n" + player1.name + "\n" + hand.mkString(", ")
}

println("The Game")
createGame()
