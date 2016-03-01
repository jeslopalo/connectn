package es.sandbox.spike.connectn;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by jeslopalo on 24/2/16.
 */
class Position {
    private final int column;
    private final int row;

    private Position(int column, int row) {
        this.column = column;
        this.row = row;
    }

    static Position position(int column, int row) {
        return new Position(column, row);
    }

    public int column() {
        return this.column;
    }

    public int row() {
        return this.row;
    }

    public Optional<Position> at(Direction direction) {

        if (direction == Direction.TOP) {
            return Optional.of(position(this.column, this.row + 1));
        }
        if (direction == Direction.TOP_RIGHT) {
            return Optional.of(position(this.column + 1, this.row + 1));
        }
        if (direction == Direction.RIGHT) {
            return Optional.of(position(this.column + 1, this.row));
        }
        if (direction == Direction.BOTTOM_RIGHT) {
            return Optional.of(position(this.column + 1, this.row - 1));
        }
        if (direction == Direction.BOTTOM) {
            return Optional.of(position(this.column, this.row - 1));
        }
        if (direction == Direction.BOTTOM_LEFT) {
            return Optional.of(position(this.column - 1, this.row - 1));
        }
        if (direction == Direction.LEFT) {
            return Optional.of(position(this.column - 1, this.row));
        }
        if (direction == Direction.TOP_LEFT) {
            return Optional.of(position(this.column - 1, this.row + 1));
        }

        return Optional.empty();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.column, this.row);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        return Objects.equals(this.column, other.column)
                && Objects.equals(this.row, other.row);
    }

    @Override
    public String toString() {
        return "Position{column=" + column + ", row=" + row + '}';
    }
}
