package es.sandbox.spike.connectn;

/**
 * Created by jeslopalo on 25/2/16.
 */
final class GameRules {

    static void validateDimensions(int columns, int rows) {
        assertThatNumberOfColumnsIsGreaterOrEqualThanTwo(columns);
        assertThatNumberOfRowsIsGreaterOrEqualThanTwo(rows);
    }

    static void validateChipsToWin(int chipsToWin, int columns, int rows) {
        assertThatChipsToWinIsGreaterThanOne(chipsToWin);
        assertThatChipsToWinIsLowerOrEqualThanNumberOfColumns(chipsToWin, columns);
        assertThatChipsToWinIsLowerOrEqualThanNumberOfRows(chipsToWin, rows);
    }

    private static void assertThatNumberOfColumnsIsGreaterOrEqualThanTwo(int columns) {
        if (columns < 2) {
            throw new IllegalArgumentException("The number of columns must be greater or equal than 2");
        }
    }

    private static void assertThatChipsToWinIsLowerOrEqualThanNumberOfColumns(int chipsToWin, int columns) {
        if (chipsToWin > columns) {
            throw new IllegalArgumentException("Chips to win must be lower or equal than the number of columns");
        }
    }

    private static void assertThatNumberOfRowsIsGreaterOrEqualThanTwo(int rows) {
        if (rows < 2) {
            throw new IllegalArgumentException("The number of rows must be greater or equal than 2");
        }
    }

    private static void assertThatChipsToWinIsLowerOrEqualThanNumberOfRows(int chipsToWin, int rows) {
        if (chipsToWin > rows) {
            throw new IllegalArgumentException("Chips to win must be lower or equal than the number of rows");
        }
    }

    private static void assertThatChipsToWinIsGreaterThanOne(int chipsToWin) {
        if (chipsToWin < 2) {
            throw new IllegalArgumentException("Chips to win must be greater than 1");
        }
    }

    /**
     * @throws UnsupportedOperationException
     */
    private GameRules() {
        throw new UnsupportedOperationException("uninstantiable class");
    }
}
