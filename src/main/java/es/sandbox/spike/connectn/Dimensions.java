package es.sandbox.spike.connectn;

import java.util.ArrayList;
import java.util.List;
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

    Chip[][] createBoard() {
        return new Chip[this.columns][this.rows];
    }

    private List<Position> positions() {
        final List<Position> positions = new ArrayList<>();
        IntStream.range(0, this.columns)
                .mapToObj(this::positionsAtColumn)
                .forEach(positions::addAll);
        return positions;
    }

    void forEachPosition(PositionClosure positionClosure) {
        positions().forEach(positionClosure::execute);
    }

    void forEachPositionInRows(PositionsClosure positionsClosure) {
        IntStream.range(0, this.rows)
                .mapToObj(this::positionsAtRow)
                .forEach(positionsClosure::execute);
    }

    List<Position> positionsAtColumn(int column) {
        validateColumn(column);

        return IntStream.range(0, this.rows)
                .mapToObj(row -> position(column, row))
                .collect(Collectors.toList());
    }

    List<Position> positionsAtRow(int row) {
        validateRow(row);

        return IntStream.range(0, this.columns)
                .mapToObj(column -> position(column, row))
                .collect(Collectors.toList());
    }

    boolean contains(Position position) {
        return columnIsInRange(position.column()) && rowIsInRange(position.row());
    }

    boolean fitsOn(int magnitude) {

        if (magnitude < 1) {
            throw new IllegalArgumentException("Magnitude must be greater than zero");
        }

        return (Math.max(this.columns, magnitude) == this.columns) &&
                (Math.max(this.rows, magnitude) == this.rows);
    }

    private void validateColumn(int column) {
        if (!columnIsInRange(column)) {
            throw new ColumnOutOfRangeException(column, this);
        }
    }

    private void validateRow(int row) {
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

    private static void validateDimensions(int columns, int rows) {

        if (columns < 2) {
            throw new IllegalArgumentException("The number of columns must be greater or equal than 2");
        }

        if (rows < 2) {
            throw new IllegalArgumentException("The number of rows must be greater or equal than 2");
        }
    }

    @Override
    public String toString() {
        return String.format("%dx%d", this.columns, this.rows);
    }
}
