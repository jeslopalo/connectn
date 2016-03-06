package es.sandbox.spike.connectn;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by jeslopalo on 29/2/16.
 */
final class Result {

    private final Color color;
    private final Set<Chip> chips;

    private Result() {
        this(null, new HashSet<>());
    }

    private Result(Color color, Set<Chip> chips) {
        this.color = color;
        this.chips = chips;
    }

    public static Result draw() {
        return new Result();
    }

    public static Result winner(Color color, Set<Chip> chips) {
        return new Result(color, chips);
    }

    public Optional<Color> winner() {
        return Optional.ofNullable(this.color);
    }

    boolean isGameOver() {
        return this.color != null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, chips);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Result other = (Result) obj;
        return Objects.equals(this.color, other.color)
                && Objects.equals(this.chips, other.chips);
    }

    @Override
    public String toString() {
        if (!isGameOver()) {
            return "draw! The game is on going";
        }
        return String.format("%s win! positions: {%s}", this.color, positions(this.chips));
    }

    private String positions(Set<Chip> chips) {
        return chips
                .stream()
                .map(Chip::position)
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }
}
