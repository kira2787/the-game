
case class Cell(x:Int, y:Int)

val cell1 = Cell(4,5)
cell1.x
cell1.y

case class Field(cells: Array[Cell])

val field1 = Field(Array.ofDim[Cell](1))
field1.cells(0)=cell1
field1.cells(0).x
field1.cells(0).y

case class Cell(value:Int) {
  def isSet:Boolean = value != 0
}

val cell1= Cell(2)
cell1.isSet

val cell2= Cell(0)
cell2.isSet

case class Field(cells: Array[Cell])

val field1 = Field(Array.ofDim[Cell](1))
field1.cells(0)=cell1

case class House(cells:Vector[Cell])

val house = House(Vector(cell1,cell2))

house.cells(0).value
house.cells(0).isSet

defined class Cell



cell1: Cell = Cell(2)
res0: Boolean = true

cell2: Cell = Cell(0)
res1: Boolean = false

defined class Field

field1: Field = Field([Lde.htwg.se.sudoku.model.A$...)
field1.cells(0): Cell = Cell(2)

defined class House

house: House = House(Vector(Cell(2), Cell(0)))

res2: Int = 2
res3: Boolean = true
