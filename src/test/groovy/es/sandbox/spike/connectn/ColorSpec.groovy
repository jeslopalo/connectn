package es.sandbox.spike.connectn

import spock.lang.Specification

/**
 * Created by jeslopalo on 26/2/16.
 */
class ColorSpec extends Specification {

    def "should be RED or YELLOW"() {
        expect:
        Color.values().size() == 2
        Color.valueOf("RED") != null
        Color.valueOf("YELLOW") != null
    }

    def "should rotate colors"() {
        expect:
        Color.rotate(color) == rotated;

        where:
        color        || rotated
        Color.RED    || Color.YELLOW
        Color.YELLOW || Color.RED
    }

    def "RED should rotate to YELLOW"() {

        expect:
        Color.RED.rotate() == Color.YELLOW
    }

    def "YELLOW should rotate to RED"() {

        expect:
        Color.YELLOW.rotate() == Color.RED
    }
}
