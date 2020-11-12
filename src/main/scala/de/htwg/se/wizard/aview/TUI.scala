package de.htwg.se.wizard.aview

import de.htwg.se.wizard.model.{Card, Cards, Gamestate, Player}

import scala.util.{Failure, Success, Try}

case class TUI() {

  def next_player_Card(player: Player): Card = {
    println(player.name + " du bist dran!")
    println(player.showHand())
    println("Welche Karte soll gespielt werden ?")
    var fail = true
    var playcard = getNumber()
    while (fail) {
      if (!(playcard >= 1 && playcard <= player.hand.size)) {
        println("Bitte gebe eine gültige Karte ein!")
        println("Welche Karte soll gespielt werden ?")
        println(player.showHand())
        playcard = getNumber()
      } else {
        fail = false
      }
    }
    player.hand(playcard-1)
  }

  def getNumber(): Int = {
    var fail = true
    var input = toInt(scala.io.StdIn.readLine())
    while (fail) {
      input match {
        case Failure(f) => println("Bitte gebe eine gültige Zahl ein!")
          input = toInt(scala.io.StdIn.readLine())
        case Success(s) => fail = false
      }
    }
    input.get
  }

  def toInt(s: String): Try[Int] = Try(Integer.parseInt(s.trim))

  def createPlayers(): Gamestate = {
    val game = Gamestate()
    // abfrage nach spieleranzahl
    println("Wie viele Spieler wollen spielen ? [3,4,5 oder 6]")
    var playercount = scala.io.StdIn.readLine()
    while (!List("3", "4", "5", "6").contains(playercount)) {
      println("Es können nur 3,4,5 oder 6 Spieler spielen!")
      playercount = scala.io.StdIn.readLine()
    }

    var players = List[Player]()

    for (x <- 1 to playercount.toInt) {
      println("Bitte gib deinen Namen ein Spieler " + x + ":")
      players = players.appended(Player(scala.io.StdIn.readLine()))
    }
    game.init_Gamestate(players)
  }

  def get_guesses(game: Gamestate, round_counter: Int): List[Int] = {
    var guessed = List.fill(game.players.size){0}
    for (i <- game.players.indices) {
      val active_Player = (round_counter + i - 1) % game.players.size
      printf("\n\n\n\n\n")
      println(game.players(active_Player).showHand())
      println(game.players(active_Player).toString + " wie viele Stiche wirst du machen?")
      guessed = guessed.updated(active_Player, getNumber())
    }
    guessed
  }

  def show_Trumpcard(trumpcard : Card): Unit = {
    println("Trumpfkarte: " + trumpcard)
  }

  def mini_ended(winner : Player): Unit = println("\nStich gewonnen von " + winner.toString + "\n\n\n\n\n\n")

  def round_ended(gamestate: Gamestate): Unit = println("\n\nRunde vorbei!!!\n\n" +gamestate.game_table +"\n")

  def begin_round(roundnumber: Int): Unit = println("\n\n\nRunde "+roundnumber+" beginnt!\n\n")

  def card_not_playable(): Unit = println("Diese Karte ist momentan nicht spielbar!")
}
