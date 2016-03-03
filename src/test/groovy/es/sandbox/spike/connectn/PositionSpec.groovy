package es.sandbox.spike.connectn

import nl.jqno.equalsverifier.EqualsVerifier
import spock.lang.Specification

/**
 * Created by jeslopalo on 2/3/16.
 */
class PositionSpec extends Specification {

    def "should print '[column, row]' for a position"() {
        expect:
        Position.position(column, row).toString() == stringify

        where:
        column | row || stringify
        5      | 0   || "[5, 0]"
        4      | 1   || "[4, 1]"
        3      | 2   || "[3, 2]"
        2      | 3   || "[2, 3]"
        1      | 4   || "[1, 4]"
        0      | 5   || "[0, 5]"
    }

    def "should verify equals & hashcode contract"() {

        when:
        EqualsVerifier.forClass(Position.class).verify();

        then:
        noExceptionThrown()
    }
}
