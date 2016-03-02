package es.sandbox.spike.connectn

import spock.lang.Specification

import static es.sandbox.spike.connectn.Position.position

/**
 * Created by jeslopalo on 1/3/16.
 */
class ResultSpec extends Specification {

    def "should return empty optional when draw"() {
        when:
        def result = Result.draw()

        then:
        !result.winner().isPresent()
    }

    def "should print out 'draw' when game is on going"() {
        when:
        def result = Result.draw()

        then:
        result.toString() == "draw! The game is on going"
    }

    def "should print out '{color} win!' when game is over"() {
        when:
        def result = Result.winner(Color.RED, chips(Color.RED, position(0, 0), position(0, 1)));

        then:
        result.toString() == "RED win! positions: {[0, 0], [0, 1]}"
    }

    private Set<Chip> chips(Color color, Position... positions) {

        final Set<Chip> chips = new HashSet<>()
        for (Position position : positions) {
            chips.add(new Chip(color, position));
        }
        return chips;
    }
}
