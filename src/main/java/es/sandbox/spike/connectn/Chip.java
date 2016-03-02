package es.sandbox.spike.connectn;

import java.util.Objects;

/**
 * Created by jeslopalo on 24/2/16.
 */
class Chip {
    private final Color color;
    private final Position position;

    Chip(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public Color color() {
        return this.color;
    }

    public Position position() {
        return this.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Chip other = (Chip) obj;
        return Objects.equals(this.position, other.position);
    }

    @Override
    public String toString() {
        return "Chip{color=" + this.color + ", position=" + this.position + '}';
    }
}
