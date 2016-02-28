package es.sandbox.spike.connectn

import spock.lang.Specification

/**
 * Created by jeslopalo on 27/2/16.
 */
class DimensionsSpec extends Specification {

    def "should fail when columns size is lower than 2"() {

        when:
        new Dimensions(columns, 5)

        then:
        IllegalArgumentException exception = thrown()
        exception.message == "The number of columns must be greater or equal than 2"

        where:
        columns << [-2, -1, 0, 1]
    }

    def "should fail when rows size is lower than 2"() {

        when:
        new Dimensions(5, rows)

        then:
        IllegalArgumentException exception = thrown()
        exception.message == "The number of rows must be greater or equal than 2"

        where:
        rows << [-2, -1, 0, 1]
    }

    def "should return dimensions as a 'columnsxrows' string"() {

        expect:
        new Dimensions(columns, rows).toString() == stringify

        where:
        columns | rows || stringify
        2       | 2    || "2x2"
        2       | 3    || "2x3"
        3       | 2    || "3x2"
    }
}
