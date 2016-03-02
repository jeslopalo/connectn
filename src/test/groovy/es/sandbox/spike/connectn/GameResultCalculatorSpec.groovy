package es.sandbox.spike.connectn

import spock.lang.Specification

/**
 * Created by jeslopalo on 29/2/16.
 */
class GameResultCalculatorSpec extends Specification {

    def "should fail without board"() {

        when:
        new GameResultCalculator(null)

        then:
        NullPointerException exception = thrown()
        exception.message == "Board must be not null"
    }

    def "should fail when calculate result with null position"() {

        given:
        def board = BoardMother.simplestBoard()
        def sut = new GameResultCalculator(board)

        when:
        sut.calculateFor(null);

        then:
        NullPointerException exception = thrown()
        exception.message == "Position must be not null"
    }

    def "should calculate winner in vertical direction"() {

        given:
        def board = BoardMother.simplestBoard();
        board.put(Color.RED, 0)
        board.put(Color.YELLOW, 1)
        board.put(Color.RED, 0)

        when:
        def result = new GameResultCalculator(board).calculateFor(Position.position(0, 1));

        then:
        result.winner().isPresent()
        result.winner().get() == Color.RED
    }

    def "should calculate winner in horizontal direction"() {

        given:
        def board = BoardMother.simplestBoard();
        board.put(Color.RED, 0)
        board.put(Color.YELLOW, 0)
        board.put(Color.RED, 1)

        when:
        def result = new GameResultCalculator(board).calculateFor(Position.position(1, 0));

        then:
        result.winner().isPresent()
        result.winner().get() == Color.RED
    }

    def "should calculate winner in main diagonal direction"() {

        given:
        def board = BoardMother.simplestBoard();
        board.put(Color.RED, 1)
        board.put(Color.YELLOW, 0)
        board.put(Color.RED, 0)

        when:
        def result = new GameResultCalculator(board).calculateFor(Position.position(0, 1));

        then:
        result.winner().isPresent()
        result.winner().get() == Color.RED
    }

    def "should calculate winner in anti diagonal direction"() {

        given:
        def board = BoardMother.simplestBoard();
        board.put(Color.RED, 0)
        board.put(Color.YELLOW, 1)
        board.put(Color.RED, 1)

        when:
        def result = new GameResultCalculator(board).calculateFor(Position.position(1, 1));

        then:
        result.winner().isPresent()
        result.winner().get() == Color.RED
    }
}
