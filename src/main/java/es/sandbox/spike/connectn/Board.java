package es.sandbox.spike.connectn;

import static es.sandbox.spike.connectn.Position.position;

/**
 * Created by jeslopalo on 24/2/16.
 */
public class Board {
    private int chipsToWin;

    private Chip[][] chips;

    public Board(int chipsToWin, int columns, int rows) {
        GameRules.validateDimensions(columns, rows);
        GameRules.validateChipsToWin(chipsToWin, columns, rows);

        this.chipsToWin = chipsToWin;
        this.chips = new Chip[columns][rows];
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
