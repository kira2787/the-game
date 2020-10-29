import se.model.Player

def addInt (a:Int, b:Int) : Int = a + b
addInt(17,4)

def createGame() : String = {

  val player1 = new Player("Spieler 1")

  val hand = Array(71, 12, 3, 14, 55)
  val discardPiles = Array(0, 0, 100, 100)

  "\nSTAPEL\n\n" + discardPiles.mkString(" - ") + "\n\n" + player1.name + "\n" + hand.mkString(", ")
}

println("The Game")
createGame()
