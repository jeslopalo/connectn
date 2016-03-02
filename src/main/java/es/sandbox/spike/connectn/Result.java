package es.sandbox.spike.connectn;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by jeslopalo on 29/2/16.
 */
class Result {

    private Color color;
    private Set<Chip> chips;

    private Result(Color color, Set<Chip> chips) {
        this.color = color;
        this.chips = chips;
    }

    private Result() {

    }

    public static Result draw() {
        return new Result();
    }

    public static Result winner(Color color, Set<Chip> chips) {
        return new Result(color, chips);
    }

    public Optional<Color> winner() {
        return this.color == null ? Optional.empty() : Optional.of(this.color);
    }

    private boolean isGameOver() {
        return this.color != null;
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
