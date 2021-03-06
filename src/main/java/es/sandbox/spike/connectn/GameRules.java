package es.sandbox.spike.connectn;

import java.util.Objects;

/**
 * Created by jeslopalo on 25/2/16.
 */
final class GameRules {

    static void validateChipsToWin(int chipsToWin, Dimensions dimensions) {
        Objects.requireNonNull(dimensions, "Dimensions must not be null");
        assertThatChipsToWinIsGreaterThanOne(chipsToWin);
        assertThatChipsToWinFitsOnDimensions(chipsToWin, dimensions);
    }

    private static void assertThatChipsToWinFitsOnDimensions(int chipsToWin, Dimensions dimensions) {
        if (!dimensions.fitsOn(chipsToWin)) {
            throw new IllegalArgumentException(String.format("Chips to win '%d' must fit the board dimensions '%s'", chipsToWin, dimensions));
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
