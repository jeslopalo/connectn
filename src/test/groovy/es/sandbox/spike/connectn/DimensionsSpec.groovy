package es.sandbox.spike.connectn

import spock.lang.Specification

import static es.sandbox.spike.connectn.Position.position

/**
 * Created by jeslopalo on 27/2/16.
 */
class DimensionsSpec extends Specification {

    def "should fail when columns size is lower than 2"() {

        when:
        Dimensions.dimensions(columns, 5)

        then:
        IllegalArgumentException exception = thrown()
        exception.message == "The number of columns must be greater or equal than 2"

        where:
        columns << [-2, -1, 0, 1]
    }

    def "should fail when rows size is lower than 2"() {

        when:
        Dimensions.dimensions(5, rows)

        then:
        IllegalArgumentException exception = thrown()
        exception.message == "The number of rows must be greater or equal than 2"

        where:
        rows << [-2, -1, 0, 1]
    }

    def "should return dimensions as a 'columnsxrows' string"() {

        expect:
        Dimensions.dimensions(columns, rows).toString() == stringify

        where:
        columns | rows || stringify
        2       | 2    || "2x2"
        2       | 3    || "2x3"
        3       | 2    || "3x2"
    }

    def "should check whether a position is contained"() {

        given:
        def dimensions = Dimensions.dimensions(3, 3)

        expect:
        dimensions.contains(position) == contained

        where:
        position         || contained
        position(0, 0)   || true
        position(0, 1)   || true
        position(0, 2)   || true
        position(1, 0)   || true
        position(1, 1)   || true
        position(1, 2)   || true
        position(2, 0)   || true
        position(2, 1)   || true
        position(2, 2)   || true
        position(-2, -1) || false
        position(-1, 0)  || false
        position(3, 3)   || false
        position(4, 4)   || false
    }
}
