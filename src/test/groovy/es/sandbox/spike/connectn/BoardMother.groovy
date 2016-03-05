package es.sandbox.spike.connectn

/**
 * Created by jeslopalo on 29/2/16.
 */
final class BoardMother {

    public static final SIMPLEST_BOARD_COLUMNS = 2;
    public static final SIMPLEST_BOARD_ROWS = 2;
    public static final SIMPLEST_BOARD_CHIPS_TO_WIN = 2;

    public static final MEDIUM_BOARD_COLUMNS = 10;
    public static final MEDIUM_BOARD_ROWS = 10;
    public static final MEDIUM_BOARD_CHIPS_TO_WIN = 2;

    static Board simplestBoard() {
        return new Board(SIMPLEST_BOARD_COLUMNS, SIMPLEST_BOARD_ROWS, SIMPLEST_BOARD_CHIPS_TO_WIN)
    }

    static Board mediumSizedBoard() {
        return new Board(MEDIUM_BOARD_COLUMNS, MEDIUM_BOARD_ROWS, MEDIUM_BOARD_CHIPS_TO_WIN);
    }

    private BoardMother() {
        throw new UnsupportedOperationException()
    }
}
