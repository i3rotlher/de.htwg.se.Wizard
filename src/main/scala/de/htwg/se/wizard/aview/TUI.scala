package de.htwg.se.wizard.aview

import de.htwg.se.wizard.model.Round

class Tui {

//  def processInputLine(input: String, grid:Grid):Grid = {
//    input match {
//      case "q" => grid
//      case "n"=> new Grid(9)
//      case "r" => new GridCreator(9).createRandom(16)
//      case "s" =>
//        val (success, solvedGrid) = new Solver(grid).solve;
//        if (success) println("Puzzle solved")else println("This puzzle could not be solved!")
//        solvedGrid
//      case _ => {
//        input.toList.filter(c => c != ' ').map(c => c.toString.toInt) match {
//          case row :: column :: value :: Nil => grid.set(row, column, value)
//          case _ => grid
//        }
//      }
//    }
//  }
    def processInputLine(input: String) = {
      input match {
        case "np" =>
      }
  }




}