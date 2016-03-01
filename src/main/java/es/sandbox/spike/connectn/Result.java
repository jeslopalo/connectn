package es.sandbox.spike.connectn;

import java.util.Optional;
import java.util.Set;

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

    public Result() {

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

    public boolean isGameOver() {
        return this.color != null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Result{");
        sb.append("color=").append(this.color);
        if (isGameOver()) {
            sb.append(", chips=").append(this.chips);
        }
        sb.append('}');
        return sb.toString();
    }
}
