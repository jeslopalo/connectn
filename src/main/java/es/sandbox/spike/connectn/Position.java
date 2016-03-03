package es.sandbox.spike.connectn;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by jeslopalo on 24/2/16.
 */
final class Position {
    private final int column;
    private final int row;

    private Position(int column, int row) {
        this.column = column;
        this.row = row;
    }

    static Position position(int column, int row) {
        return new Position(column, row);
    }

    int column() {
        return this.column;
    }

    int row() {
        return this.row;
    }

    public Optional<Position> at(Direction direction) {
        return Optional.of(direction.from(this));
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
        return String.format("[%d, %d]", this.column, this.row);
    }
}
