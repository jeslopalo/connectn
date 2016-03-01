package es.sandbox.spike.connectn

import spock.lang.Specification

import static es.sandbox.spike.connectn.Position.position

/**
 * Created by jeslopalo on 24/2/16.
 */
class BoardSpec extends Specification {

    private static final int ROWS = 10
    private static final int COLUMNS = 10
    private static final int CHIPS_TO_WIN = 2;

    def "should fail when chips to win is lower than 2"() {

        when:
        new Board(chipsToWin, COLUMNS, ROWS);

        then:
        IllegalArgumentException exception = thrown();
        exception.message == "Chips to win must be greater than 1"

        where:
        chipsToWin << [-3, -2, -1, 0, 1]
    }

    def "should fail when number of columns is lower than 2"() {

        when:
        new Board(CHIPS_TO_WIN, numberOfColumns, ROWS)

        then:
        IllegalArgumentException exception = thrown()
        exception.message == "The number of columns must be greater or equal than 2"

        where:
        numberOfColumns << [-1, 0, 1]
    }

    def "should fail when number of columns is lower than chips to win"() {

        when:
        new Board(6, numberOfColumns, ROWS)

        then:
        IllegalArgumentException exception = thrown()
        exception.message == "Chips to win must be lower or equal than the number of columns"

        where:
        numberOfColumns << [3, 4, 5]
    }

    def "should fail when number of rows is lower than 2"() {

        when:
        new Board(CHIPS_TO_WIN, COLUMNS, numberOfRows);

        then:
        IllegalArgumentException exception = thrown();
        exception.message == "The number of rows must be greater or equal than 2"

        where:
        numberOfRows << [-1, 0, 1]
    }


    def "should fail when number of rows is lower than chips to win"() {

        when:
        new Board(6, COLUMNS, numberOfRows)

        then:
        IllegalArgumentException exception = thrown()
        exception.message == "Chips to win must be lower or equal than the number of rows"

        where:
        numberOfRows << [3, 4, 5]
    }

    def "should put the first chip in a column"() {

        given:
        def sut = new Board(CHIPS_TO_WIN, COLUMNS, ROWS)

        when:
        sut.put(Color.RED, 0)

        then:
        sut.colorAt(position(0, 0)).get() == Color.RED
    }

    def "should fill a column"() {

        given:
        def sut = new Board(CHIPS_TO_WIN, COLUMNS, ROWS)

        when:
        putChipsInColumnWithRotatingColors(sut, ROWS, 0, Color.RED);

        then:
        def color = Color.RED
        for (int row = 0; row < ROWS; row++) {
            color = color.rotate()

            sut.colorAt(position(0, row)) == color
        }
    }

    def "should fail when put a chip in a full column"() {

        given:
        def sut = new Board(CHIPS_TO_WIN, COLUMNS, ROWS)
        putChipsInColumnWithRotatingColors(sut, ROWS, 0, Color.RED)

        when:
        sut.put(Color.RED, 0)

        then:
        ColumnIsFullException exception = thrown()
        exception.message == "Column <0> is full"
    }

    void putChipsInColumnWithRotatingColors(Board board, int max, int column, Color starting) {
        def color = starting
        for (int row = 0; row < max; row++) {
            board.put(color, column);
            color = color.rotate();
        }
    }

    def "should fail with null position when look for a chip"() {
        given:
        def sut = new Board(CHIPS_TO_WIN, COLUMNS, ROWS)

        when:
        sut.chipAt(null)

        then:
        NullPointerException exception = thrown()
        exception.message == "Position must be non null"
    }

    def "should get chips at known positions"() {
        given:
        def sut = new Board(CHIPS_TO_WIN, COLUMNS, ROWS);
        sut.put(Color.RED, 0)
        sut.put(Color.YELLOW, 1)

        expect:
        sut.chipAt(position(0, 0)).get() == new Chip(Color.RED, position(0, 0))
        sut.chipAt(position(1, 0)).get() == new Chip(Color.YELLOW, position(1, 0))
    }

    def "should fail with null position when look for a color"() {
        given:
        def sut = new Board(CHIPS_TO_WIN, COLUMNS, ROWS);

        when:
        sut.colorAt(null);

        then:
        NullPointerException exception = thrown()
        exception.message == "Position must be non null"
    }

    def "should get colors at known positions"() {
        given:
        def sut = new Board(CHIPS_TO_WIN, COLUMNS, ROWS);
        sut.put(Color.RED, 0);
        sut.put(Color.YELLOW, 0);
        sut.put(Color.YELLOW, 1);
        sut.put(Color.RED, 1);

        expect:
        sut.colorAt(position(0, 0)).get() == Color.RED;
        sut.colorAt(position(0, 1)).get() == Color.YELLOW;
        sut.colorAt(position(1, 0)).get() == Color.YELLOW;
        sut.colorAt(position(1, 1)).get() == Color.RED;
    }

    def "should result in draw when put a chip in a non winner position"() {
        given:
        def sut = new Board(2, 2, 2);

        when:
        def result = sut.put(Color.RED, 0);

        then:
        result.isGameOver() == false
        result.winner().isPresent() == false
    }

    def "should finish the game with 2 chips to win and 2 consecutive Red chips"() {

        given:
        def sut = new Board(2, 2, 2)
        sut.put(Color.RED, 0)
        sut.put(Color.YELLOW, 1)

        when:
        def result = sut.put(Color.RED, 0)

        then:
        result.isGameOver() == true
        result.winner().get() == Color.RED
    }
}
