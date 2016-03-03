package es.sandbox.spike.connectn;

/**
 * Created by jeslopalo on 27/2/16.
 */
class Dimensions {

    private final int columns;
    private final int rows;

    public Dimensions(int columns, int rows) {
        GameRules.validateDimensions(columns, rows);

        this.columns = columns;
        this.rows = rows;
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
