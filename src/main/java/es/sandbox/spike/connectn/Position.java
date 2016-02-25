package es.sandbox.spike.connectn;

/**
 * Created by jeslopalo on 24/2/16.
 */
public class Position {
    private int column;
    private int row;

    private Position(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public static Position position(int column, int row) {
        return new Position(column, row);
    }
}
