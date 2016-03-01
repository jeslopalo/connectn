package es.sandbox.spike.connectn;

import java.util.Objects;

/**
 * Created by jeslopalo on 24/2/16.
 */
public class Chip {
    private Color color;
    private Position position;

    Chip(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public Color color() {
        return this.color;
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
        final StringBuilder sb = new StringBuilder("Chip{");
        sb.append("color=").append(this.color);
        sb.append(", position=").append(this.position);
        sb.append('}');
        return sb.toString();
    }
}
