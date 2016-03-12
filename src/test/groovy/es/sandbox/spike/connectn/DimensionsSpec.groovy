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

    def "should fail when dimensions (columns or rows) are lower than two"() {

        when:
        Dimensions.dimensions(columns, rows)

        then:
        IllegalArgumentException exception = thrown()
        exception.message == message

        where:
        columns | rows | message
        -1      | 3    | "The number of columns must be greater or equal than 2"
        0       | 3    | "The number of columns must be greater or equal than 2"
        1       | 3    | "The number of columns must be greater or equal than 2"
        3       | -1   | "The number of rows must be greater or equal than 2"
        3       | 0    | "The number of rows must be greater or equal than 2"
        3       | 1    | "The number of rows must be greater or equal than 2"
    }

    def "should validate dimensions (columns or rows) when are greater or equal than two"() {

        when:
        Dimensions.dimensions(columns, rows);

        then:
        noExceptionThrown()

        where:
        columns << [2, 3, 4, 5, 5, 4, 3, 2]
        rows << [2, 3, 4, 5, 2, 3, 4, 5]
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

    def "should fail getting column positions when column is out of range"() {
        given:
        def dimensions = Dimensions.dimensions(2, 2)

        when:
        dimensions.positionsAtColumn(column)

        then:
        ColumnOutOfRangeException exception = thrown()
        exception.message == "The column '" + column + "' is out of range [2x2]"

        where:
        column << [-2, -1, 2, 3]
    }

    def "should get column positions"() {
        given:
        def dimensions = Dimensions.dimensions(2, 2)

        when:
        def positions = dimensions.positionsAtColumn(column)

        then:
        positions.containsAll([position(column, 0), position(column, 1)])

        where:
        column << [0, 1]
    }

    def "should fail getting row positions when row is out of range"() {
        given:
        def dimensions = Dimensions.dimensions(2, 2)

        when:
        dimensions.positionsAtRow(row)

        then:
        RowOutOfRangeException exception = thrown()
        exception.message == "The row '" + row + "' is out of range [2x2]"

        where:
        row << [-2, -1, 2, 3]
    }

    def "should fail with negative magnitude when validate if it fits on"() {
        given:
        def dimensions = Dimensions.dimensions(2, 2)

        when:
        dimensions.fitsOn(magnitude)

        then:
        IllegalArgumentException exception = thrown()
        exception.message == "Magnitude must be greater than zero"

        where:
        magnitude << [-2, -1, 0]
    }

    def "should validate if a magnitude fits on square dimensions"() {

        given:
        def dimensions = Dimensions.dimensions(columns, rows)

        expect:
        dimensions.fitsOn(magnitude) == fits

        where:
        columns | rows | magnitude || fits
        2       | 2    | 1         || true
        2       | 2    | 2         || true
        2       | 2    | 3         || false

        3       | 3    | 1         || true
        3       | 3    | 2         || true
        3       | 3    | 3         || true
        3       | 3    | 4         || false
    }

    def "should validate if a magnitude fits on a rectangular dimensions"() {

        given:
        def dimensions = Dimensions.dimensions(columns, rows)

        expect:
        dimensions.fitsOn(magnitude) == fits

        where:
        columns | rows | magnitude || fits
        2       | 3    | 1         || true
        2       | 3    | 2         || true
        2       | 3    | 3         || true
        2       | 3    | 4         || false

        3       | 2    | 1         || true
        3       | 2    | 2         || true
        3       | 2    | 3         || true
        3       | 2    | 4         || false
    }

    def "should get rows positions"() {
        given:
        def dimensions = Dimensions.dimensions(2, 2)

        when:
        def positions = dimensions.positionsAtRow(row)

        then:
        positions.containsAll([position(0, row), position(1, row)])

        where:
        row << [0, 1]
    }

    def "should get columns"() {
        given:
        def dimensions = Dimensions.dimensions(columns, 3);

        expect:
        dimensions.getColumns() == columns

        where:
        columns << [2, 3, 4, 5, 10, 100]
    }

    def "should get rows"() {
        given:
        def dimensions = Dimensions.dimensions(3, rows);

        expect:
        dimensions.getRows() == rows

        where:
        rows << [2, 3, 4, 5, 10, 100]
    }
}
