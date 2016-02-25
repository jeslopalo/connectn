package es.sandbox.spike.connectn

import spock.lang.Specification

/**
 * Created by jeslopalo on 24/2/16.
 */
class BoardSpec extends Specification {

    private static final int ROWS = 10
    private static final int COLUMNS = 10
    private static final int CHIPS_TO_WIN = 2;

    def "it should fail when chips to win is lower than 2"() {

        when:
        new Board(chipsToWin, COLUMNS, ROWS);

        then:
        IllegalArgumentException exception = thrown();
        exception.message == "Chips to win must be greater than 1"

        where:
        chipsToWin << [-3, -2, -1, 0, 1]
    }

    def "it should fail when number of columns is lower than 2"() {

        when:
        new Board(CHIPS_TO_WIN, numberOfColumns, ROWS)

        then:
        IllegalArgumentException exception = thrown()
        exception.message == "The number of columns must be greater than 2"

        where:
        numberOfColumns << [-1, 0, 1]
    }

    def "it should fail when number of columns is lower than chips to win"() {

        when:
        new Board(6, numberOfColumns, ROWS)

        then:
        IllegalArgumentException exception = thrown()
        exception.message == "Chips to win must be lower or equal than the number of columns"

        where:
        numberOfColumns << [3, 4, 5]
    }

    def "it should fail when number of rows is lower than 2"() {

        when:
        new Board(CHIPS_TO_WIN, COLUMNS, numberOfRows);

        then:
        IllegalArgumentException exception = thrown();
        exception.message == "The number of rows must be greater than 2"

        where:
        numberOfRows << [-1, 0, 1]
    }


    def "it should fail when number of rows is lower than chips to win"() {

        when:
        new Board(6, COLUMNS, numberOfRows)

        then:
        IllegalArgumentException exception = thrown()
        exception.message == "Chips to win must be lower or equal than the number of rows"

        where:
        numberOfRows << [3, 4, 5]
    }
}
