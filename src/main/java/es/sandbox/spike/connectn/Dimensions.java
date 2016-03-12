package es.sandbox.spike.connectn;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static es.sandbox.spike.connectn.Position.position;

/**
 * Created by jeslopalo on 27/2/16.
 */
final class Dimensions {

    private final int columns;
    private final int rows;

    private Dimensions(int columns, int rows) {
        validateDimensions(columns, rows);

        this.columns = columns;
        this.rows = rows;
    }

    public static Dimensions dimensions(int columns, int rows) {
        return new Dimensions(columns, rows);
    }

    public Set<Position> positionsAtColumn(int column) {
        validateColumn(column);

        return IntStream.range(0, this.rows)
                .mapToObj(row -> position(column, row))
                .collect(Collectors.toSet());
    }

    public Set<Position> positionsAtRow(int row) {
        validateRow(row);

        return IntStream.range(0, this.columns)
                .mapToObj(column -> position(column, row))
                .collect(Collectors.toSet());
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

    boolean fitsOn(int magnitude) {
        
        if (magnitude < 1) {
            throw new IllegalArgumentException("Magnitude must be greater than zero");
        }

        return (Math.max(this.columns, magnitude) == this.columns) ||
                (Math.max(this.rows, magnitude) == this.rows);
    }

    void validateColumn(int column) {
        if (!columnIsInRange(column)) {
            throw new ColumnOutOfRangeException(column, this);
        }
    }

    void validateRow(int row) {
        if (!rowIsInRange(row)) {
            throw new RowOutOfRangeException(row, this);
        }
    }

    private boolean rowIsInRange(int row) {
        return 0 <= row && row < this.rows;
    }

    private boolean columnIsInRange(int column) {
        return 0 <= column && column < this.columns;
    }

    static void validateDimensions(int columns, int rows) {
        assertThatNumberOfColumnsIsGreaterOrEqualThanTwo(columns);
        assertThatNumberOfRowsIsGreaterOrEqualThanTwo(rows);
    }

    private static void assertThatNumberOfColumnsIsGreaterOrEqualThanTwo(int columns) {
        if (columns < 2) {
            throw new IllegalArgumentException("The number of columns must be greater or equal than 2");
        }
    }

    private static void assertThatNumberOfRowsIsGreaterOrEqualThanTwo(int rows) {
        if (rows < 2) {
            throw new IllegalArgumentException("The number of rows must be greater or equal than 2");
        }
    }

    @Override
    public String toString() {
        return String.format("%dx%d", this.columns, this.rows);
    }
}
