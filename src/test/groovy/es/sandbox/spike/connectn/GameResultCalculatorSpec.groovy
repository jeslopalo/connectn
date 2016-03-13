package es.sandbox.spike.connectn

import spock.lang.Specification

import static es.sandbox.spike.connectn.BoardMother.SIMPLEST_BOARD_CHIPS_TO_WIN
import static es.sandbox.spike.connectn.BoardMother.simplestBoard
import static es.sandbox.spike.connectn.GamePlayer.play
import static es.sandbox.spike.connectn.Position.position

/**
 * Created by jeslopalo on 29/2/16.
 */
class GameResultCalculatorSpec extends Specification {

    def "should fail without board"() {

        when:
        new GameResultCalculator(null, SIMPLEST_BOARD_CHIPS_TO_WIN)

        then:
        NullPointerException exception = thrown()
        exception.message == "Board must be not null"
    }

    def "should fail with invalid chips to win"() {

        given:
        def board = simplestBoard()

        when:
        new GameResultCalculator(board, chipsToWin)

        then:
        IllegalArgumentException exception = thrown()
        exception.message == "Chips to win must be greater than 1"

        where:
        chipsToWin << [-2, -1, 0, 1]
    }

    def "should fail when calculate result with null position"() {

        given:
        def board = simplestBoard()
        def sut = new GameResultCalculator(board, SIMPLEST_BOARD_CHIPS_TO_WIN)

        when:
        sut.calculateFor(null);

        then:
        NullPointerException exception = thrown()
        exception.message == "Position must be not null"
    }

    def "should calculate draw when there is no winner"() {
        given:
        def board = simplestBoard()
        board.put(Color.RED, 0)
        def sut = new GameResultCalculator(board, SIMPLEST_BOARD_CHIPS_TO_WIN)

        when:
        def result = sut.calculateFor(position(0, 0))

        then:
        result == Result.draw()
    }

    def "should calculate draw when there is no chip at position"() {
        given:
        def board = simplestBoard()
        def sut = new GameResultCalculator(board, SIMPLEST_BOARD_CHIPS_TO_WIN)

        when:
        def result = sut.calculateFor(position(0, 0))

        then:
        result == Result.draw()
    }

    def "should calculate winner in vertical direction"() {

        given:
        def board = simplestBoard();
        board.put(Color.RED, 0)
        board.put(Color.YELLOW, 1)
        board.put(Color.RED, 0)
        def sut = new GameResultCalculator(board, SIMPLEST_BOARD_CHIPS_TO_WIN)

        when:
        def result = sut.calculateFor(position(0, 1));

        then:
        result.winner().isPresent()
        result.winner().get() == Color.RED
    }

    def "should calculate winner in horizontal direction"() {

        given:
        def board = simplestBoard();
        board.put(Color.RED, 0)
        board.put(Color.YELLOW, 0)
        board.put(Color.RED, 1)
        def sut = new GameResultCalculator(board, SIMPLEST_BOARD_CHIPS_TO_WIN)

        when:
        def result = sut.calculateFor(position(1, 0));

        then:
        result.winner().isPresent()
        result.winner().get() == Color.RED
    }

    def "should calculate winner in main diagonal direction"() {

        given:
        def board = simplestBoard();
        board.put(Color.RED, 1)
        board.put(Color.YELLOW, 0)
        board.put(Color.RED, 0)
        def sut = new GameResultCalculator(board, SIMPLEST_BOARD_CHIPS_TO_WIN)

        when:
        def result = sut.calculateFor(position(0, 1));

        then:
        result.winner().isPresent()
        result.winner().get() == Color.RED
    }

    def "should calculate winner in anti diagonal direction"() {

        given:
        def board = simplestBoard();
        board.put(Color.RED, 0)
        board.put(Color.YELLOW, 1)
        board.put(Color.RED, 1)
        def sut = new GameResultCalculator(board, SIMPLEST_BOARD_CHIPS_TO_WIN)

        when:
        def result = sut.calculateFor(position(1, 1));

        then:
        result.winner().isPresent()
        result.winner().get() == Color.RED
    }

    def "should fail when the game is over"() {

        given:
        def board = BoardMother.simplestBoard([0, 0], [1])
        def sut = new GameResultCalculator(board, BoardMother.SIMPLEST_BOARD_CHIPS_TO_WIN)
        sut.calculateFor(position(0, 1))

        when:
        sut.assertThatGameIsOnGoing()

        then:
        GameOverException exception = thrown()
        exception.message == "Sorry! The game is over. RED win! positions: {[0, 0], [0, 1]}"
    }

    def "should assert that game is on going"() {

        given:
        def board = BoardMother.simplestBoard();
        def sut = new GameResultCalculator(board, BoardMother.SIMPLEST_BOARD_CHIPS_TO_WIN)

        when:
        sut.assertThatGameIsOnGoing()

        then:
        noExceptionThrown()
    }

    def "should calculate board result for simplest board"() {

        given:
        def board = BoardMother.simplestBoard(redPlays, yellowPlays)
        def resultCalculator = play(board, BoardMother.SIMPLEST_BOARD_DIMENSIONS, BoardMother.SIMPLEST_BOARD_CHIPS_TO_WIN);

        when:
        def expectedResult = resultCalculator.getResult()

        then:
        expectedResult.isGameOver() == finished
        expectedResult.toString() == resultMessage

        where:
        redPlays | yellowPlays || finished | resultMessage
        []       | []          || false | "draw! The game is on going"
        [1]      | [0]         || false | "draw! The game is on going"
        [1, 0]   | [0]         || true | "RED win! positions: {[0, 1], [1, 0]}"
    }

    def "should calculate board result for medium baord"() {

        given:
        def board = BoardMother.mediumSizedBoard(redPlays, yellowPlays)
        def resultCalculator = play(board, BoardMother.MEDIUM_BOARD_DIMENSIONS, BoardMother.MEDIUM_BOARD_CHIPS_TO_WIN);

        when:
        def expectedResult = resultCalculator.getResult()

        then:
        expectedResult.isGameOver() == finished
        expectedResult.toString() == resultMessage

        where:
        redPlays        | yellowPlays  || finished | resultMessage
        []              | []           || false | "draw! The game is on going"
        [1]             | [0]          || false | "draw! The game is on going"
        [1, 0]          | [0]          || false | "draw! The game is on going"
        [1, 2, 3]       | [0, 1]       || true | "RED win! positions: {[3, 0], [2, 0], [1, 0]}"
        [0, 5, 3]       | [0, 0, 0]    || true | "YELLOW win! positions: {[0, 1], [0, 2], [0, 3]}"
        [0, 1, 0, 2, 2] | [1, 0, 2, 1] || true | "RED win! positions: {[0, 0], [1, 1], [2, 2]}"
    }
}
