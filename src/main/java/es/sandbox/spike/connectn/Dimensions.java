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
        return columnIsInRange(position) && rowIsInRange(position);
    }

/*
    void validate(Position position) {
        if (!contains(position)) {
            throw new PositionOutOfRangeException(position, this);
        }
    }
 */

    private boolean rowIsInRange(Position position) {
        return 0 <= position.row() && position.row() < this.rows;
    }

    private boolean columnIsInRange(Position position) {
        return 0 <= position.column() && position.column() < this.columns;
    }

    @Override
    public String toString() {
        return String.format("%dx%d", this.columns, this.rows);
    }
}
