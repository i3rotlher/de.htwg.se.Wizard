package de.htwg.se.wizard.aview.gui

import de.htwg.se.wizard.model.gamestateComponent.GamestateInterface
import scala.swing._

//columns are moveable
class Table_panel(gamestate: GamestateInterface) extends BoxPanel(Orientation.Vertical) {
  var game_table = gamestate.getGame_table

  preferredSize = new Dimension(gamestate.getPlayers.size*50, (60/gamestate.getPlayers.size*18.5).toInt)


  var names = Array.fill(gamestate.getPlayers.size){""}
  for ((player,x) <- gamestate.getPlayers.zipWithIndex) {
    names(x) = player.getName
  }

  var rounds: Array[Array[Any]] = Array.fill(60/gamestate.getPlayers.size,gamestate.getPlayers.size){"        |  "}

  if (game_table.size -1 > 0) {
    for ((round, x) <- game_table.zipWithIndex) {
      if (x != game_table.size-1) {
        for (y <- gamestate.getPlayers.indices) {
          rounds(x)(y) = "  " + round.results(y) + "   | " + round.guessed_tricks(y)
        }
      }
    }
  }
  val table: Table = new Table(rounds ,names.toSeq) {
    enabled = false
  }

  val table_scroll_pane = new ScrollPane(table) {
    contents = table
  }
  contents+=table_scroll_pane
}
