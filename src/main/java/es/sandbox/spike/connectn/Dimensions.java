package es.sandbox.spike.connectn;

/**
 * Created by jeslopalo on 27/2/16.
 */
final class Dimensions {

    private final int columns;
    private final int rows;

    private Dimensions(int columns, int rows) {
        GameRules.validateDimensions(columns, rows);

        this.columns = columns;
        this.rows = rows;
    }

    public static Dimensions dimensions(int columns, int rows) {
        return new Dimensions(columns, rows);
    }

    int getColumns() {
        return this.columns;
    }

    int getRows() {
        return this.rows;
    }

    boolean contains(Position position) {
        return columnIsInRange(position.column()) && rowIsInRange(position.row());
    }

    void validateColumn(int column) {
        if (!columnIsInRange(column)) {
            throw new ColumnOutOfRangeException(column, this);
        }
    }

    private boolean rowIsInRange(int row) {
        return 0 <= row && row < this.rows;
    }

    private boolean columnIsInRange(int column) {
        return 0 <= column && column < this.columns;
    }

    @Override
    public String toString() {
        return String.format("%dx%d", this.columns, this.rows);
    }
}
