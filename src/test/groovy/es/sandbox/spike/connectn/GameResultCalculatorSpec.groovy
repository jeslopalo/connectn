package es.sandbox.spike.connectn

import spock.lang.Specification

import static es.sandbox.spike.connectn.BoardMother.SIMPLEST_BOARD_CHIPS_TO_WIN
import static es.sandbox.spike.connectn.BoardMother.simplestBoard

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

    def "should calculate winner in vertical direction"() {

        given:
        def board = simplestBoard();
        board.put(Color.RED, 0)
        board.put(Color.YELLOW, 1)
        board.put(Color.RED, 0)
        def sut = new GameResultCalculator(board, SIMPLEST_BOARD_CHIPS_TO_WIN)

        when:
        def result = sut.calculateFor(Position.position(0, 1));

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
        def result = sut.calculateFor(Position.position(1, 0));

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
        def result = sut.calculateFor(Position.position(0, 1));

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
        def result = sut.calculateFor(Position.position(1, 1));

        then:
        result.winner().isPresent()
        result.winner().get() == Color.RED
    }
}
