package es.sandbox.spike.connectn;

import java.util.Objects;

/**
 * Created by jeslopalo on 25/2/16.
 */
final class GameRules {

    static void validateChipsToWin(int chipsToWin, Dimensions dimensions) {
        Objects.requireNonNull(dimensions, "Dimensions must not be null");
        assertThatChipsToWinIsGreaterThanOne(chipsToWin);
        assertThatChipsToWinIsLowerOrEqualThanNumberOfColumns(chipsToWin, dimensions.getColumns());
        assertThatChipsToWinIsLowerOrEqualThanNumberOfRows(chipsToWin, dimensions.getRows());
    }

    private static void assertThatChipsToWinIsLowerOrEqualThanNumberOfColumns(int chipsToWin, int columns) {
        if (chipsToWin > columns) {
            throw new IllegalArgumentException("Chips to win must be lower or equal than the number of columns");
        }
    }

    private static void assertThatChipsToWinIsLowerOrEqualThanNumberOfRows(int chipsToWin, int rows) {
        if (chipsToWin > rows) {
            throw new IllegalArgumentException("Chips to win must be lower or equal than the number of rows");
        }
    }

    static void assertThatChipsToWinIsGreaterThanOne(int chipsToWin) {
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
