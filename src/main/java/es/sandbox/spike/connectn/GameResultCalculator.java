package es.sandbox.spike.connectn;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Created by jeslopalo on 29/2/16.
 */
class GameResultCalculator {

    private final Board board;

    public GameResultCalculator(Board board) {
        Objects.requireNonNull(board, "Board must be not null");
        this.board = board;
    }

    public Result calculateFor(Position position) {
        Objects.requireNonNull(position, "Position must be not null");

        return chipAt(position)
                .map(chip -> {

                    /* Vertical positions (|) */
                    final Set<Chip> verticalChips = verticalChipsFrom(position);
                    if (verticalChips.size() == this.board.getChipsToWin()) {
                        return Result.winner(chip.color(), verticalChips);
                    }

                    /* Main diagonal positions (\) */
                    final Set<Chip> mainDiagonalChips = mainDiagonalChipsFrom(position);
                    if (mainDiagonalChips.size() == this.board.getChipsToWin()) {
                        return Result.winner(chip.color(), mainDiagonalChips);
                    }

                    /* Horizontal positions (-) */
                    final Set<Chip> horizontalChips = horizontalChipsFrom(position);
                    if (horizontalChips.size() == this.board.getChipsToWin()) {
                        return Result.winner(chip.color(), horizontalChips);
                    }

                    /* Anti diagonal positions (/) */
                    final Set<Chip> antiDiagonalChips = antiDiagonalChipsFrom(position);
                    if (antiDiagonalChips.size() == this.board.getChipsToWin()) {
                        return Result.winner(chip.color(), antiDiagonalChips);
                    }

                    return Result.draw();

                })
                .orElse(Result.draw());
    }

    private Set<Chip> findFrom(Position starting, Color color, Direction direction) {
        final Set<Chip> positions = new HashSet<>();

        starting.at(direction)
                .ifPresent(position ->
                        chipAt(position)
                                .filter(chip -> chip.color() == color)
                                .ifPresent(chip -> {
                                    positions.add(chip);
                                    positions.addAll(findFrom(position, color, direction));
                                }));

        return positions;
    }

    private Set<Chip> findChipsInDirections(Position position, Direction starting, Direction ending) {
        final Set<Chip> positions = new HashSet<>();
        chipAt(position)
                .ifPresent(chip -> {
                    positions.add(chip);
                    positions.addAll(findFrom(position, chip.color(), starting));
                    positions.addAll(findFrom(position, chip.color(), ending));
                });
        return positions;
    }

    private Set<Chip> horizontalChipsFrom(Position position) {
        return findChipsInDirections(position, Direction.LEFT, Direction.RIGHT);
    }

    private Set<Chip> mainDiagonalChipsFrom(Position position) {
        return findChipsInDirections(position, Direction.TOP_LEFT, Direction.BOTTOM_RIGHT);
    }

    private Set<Chip> verticalChipsFrom(Position position) {
        return findChipsInDirections(position, Direction.TOP, Direction.BOTTOM);
    }

    private Set<Chip> antiDiagonalChipsFrom(Position position) {
        return findChipsInDirections(position, Direction.TOP_RIGHT, Direction.BOTTOM_LEFT);
    }

    private Optional<Chip> chipAt(Position position) {
        return this.board.chipAt(position);
    }
}
