package es.sandbox.spike.connectn;

import java.util.stream.Stream;

import static es.sandbox.spike.connectn.Position.position;

/**
 * Created by jeslopalo on 24/2/16.
 */
public class Board {
    private int chipsToWin;

    private Chip[][] chips;

    public Board(int chipsToWin, int columns, int rows) {
        assertThatNumberOfColumnsIsGreaterThanTwo(columns);
        assertThatNumberOfRowsIsGreaterThanTwo(rows);
        assertThatChipsToWinIsGreaterThanOne(chipsToWin);
        assertThatChipsToWinIsLowerOrEqualThanNumberOfColumns(chipsToWin, columns);
        assertThatChipsToWinIsLowerOrEqualThanNumberOfRows(chipsToWin, rows);

        this.chipsToWin = chipsToWin;
        this.chips = new Chip[columns][rows];
    }

    private static void assertThatNumberOfColumnsIsGreaterThanTwo(int columns) {
        if (columns < 2) {
            throw new IllegalArgumentException("The number of columns must be greater than 2");
        }
    }

    private static void assertThatChipsToWinIsLowerOrEqualThanNumberOfColumns(int chipsToWin, int columns) {
        if (chipsToWin > columns) {
            throw new IllegalArgumentException("Chips to win must be lower or equal than the number of columns");
        }
    }

    private static void assertThatNumberOfRowsIsGreaterThanTwo(int rows) {
        if (rows < 2) {
            throw new IllegalArgumentException("The number of rows must be greater than 2");
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


    void put(Color color, int column) {
        final int firstEmptyRow = findFirstEmptyRowInColumn(column);

        this.chips[column][firstEmptyRow] = new Chip(color, position(column, firstEmptyRow));
    }

    private int findFirstEmptyRowInColumn(int column) {

        for (int row = 0; row < this.chips[column].length; row++) {
            if (this.chips[column][row] == null) {
                return row;
            }
        }
        throw new ColumnIsFullException(column);
    }
}
