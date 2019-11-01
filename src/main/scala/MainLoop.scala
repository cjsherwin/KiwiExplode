import CardType.Explode
import GameFns.draw

import scala.annotation.tailrec

/** Runs the main game loop */
class MainLoop(ioUtils: IoUtils) {
  import ioUtils._

  @tailrec
  final def runLoop(state: State): Any = {

    showPrompt()
    val userIn = ioUtils.getUserInput

    userIn match {
      case "D" =>
        val stateAfterDraw = draw(state)
        showState(stateAfterDraw)

        if (stateAfterDraw.gameIsOver) {
          if (stateAfterDraw.currentPlayer.lastDrawnCard.contains(Explode))
            showLoser()
          showGameOver()
        }
        else
          runLoop(stateAfterDraw)
      case "Q" =>
        showGameOver()
      case _ =>
        showUnrecognised()
        runLoop(state)

    }
  }

}
