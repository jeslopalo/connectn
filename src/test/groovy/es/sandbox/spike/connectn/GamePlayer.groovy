package es.sandbox.spike.connectn

/**
 * Created by jeslopalo on 13/3/16.
 */
class GamePlayer {

    static GameResultCalculator play(Board board, Dimensions dimensions, int chipsToWin) {

        def gameResultCalculator = new GameResultCalculator(board, chipsToWin);

        dimensions.positions()
                .stream()
                .forEach {
            position -> gameResultCalculator.calculateFor(position)
        }

        return gameResultCalculator;
    }

}
