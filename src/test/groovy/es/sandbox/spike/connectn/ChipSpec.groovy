package es.sandbox.spike.connectn

import nl.jqno.equalsverifier.EqualsVerifier
import spock.lang.Specification

/**
 * Created by jeslopalo on 3/3/16.
 */
class ChipSpec extends Specification {

    def "should verify equals & hashcode contract"() {

        when:
        EqualsVerifier.forClass(Chip.class).verify();

        then:
        noExceptionThrown()
    }

    def "should print out chip information"() {

        given:
        def chip = new Chip(Color.RED, Position.position(0, 1))

        when:
        def toString = chip.toString()

        then:
        toString == "Chip{color=RED, position=[0, 1]}"
    }
}
