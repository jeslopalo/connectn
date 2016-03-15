package es.sandbox.spike

import es.sandbox.spike.connectn.Color
import spock.lang.Specification

import static es.sandbox.spike.connectn.BoardMother.connect4Board
import static es.sandbox.spike.connectn.BoardMother.playTheGame

/**
 * Created by jeslopalo on 10/3/16.
 */
class Connect4GameSpec extends Specification {

    def "should play connect4"() {
        given:
        def board = connect4Board(Color.RED)

        when:
        def result = playTheGame(board, Color.RED, redPlays, yellowPlays);

        then:
        result.isGameOver()
        result.winner().get() == winner

        where:
        redPlays        | yellowPlays     || winner
        [0, 1, 2, 3]    | [0, 1, 2]       || Color.RED
        [0, 1, 2, 0, 1] | [0, 1, 2, 3, 3] || Color.YELLOW
        [0, 0, 0, 0]    | [1, 2, 3]       || Color.RED
    }
}
