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

    public Result calculate(Position position) {
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


    private Set<Chip> horizontalChipsFrom(Position position) {
        final Set<Chip> positions = new HashSet<>();

        chipAt(position)
                .ifPresent(chip -> {
                    positions.add(chip);
                    positions.addAll(findFrom(position, chip.color(), Direction.LEFT));
                    positions.addAll(findFrom(position, chip.color(), Direction.RIGHT));
                });

        return positions;
    }


    private Set<Chip> mainDiagonalChipsFrom(Position position) {
        final Set<Chip> positions = new HashSet<>();

        chipAt(position)
                .ifPresent(chip -> {
                    positions.add(chip);
                    positions.addAll(findFrom(position, chip.color(), Direction.TOP_LEFT));
                    positions.addAll(findFrom(position, chip.color(), Direction.BOTTOM_RIGHT));
                });

        return positions;
    }

    private Set<Chip> verticalChipsFrom(Position position) {
        final Set<Chip> positions = new HashSet<>();

        chipAt(position)
                .ifPresent(chip -> {
                    positions.add(chip);
                    positions.addAll(findFrom(position, chip.color(), Direction.TOP));
                    positions.addAll(findFrom(position, chip.color(), Direction.BOTTOM));
                });

        return positions;
    }


    private Set<Chip> antiDiagonalChipsFrom(Position position) {
        final Set<Chip> positions = new HashSet<>();

        chipAt(position)
                .ifPresent(chip -> {
                    positions.add(chip);
                    positions.addAll(findFrom(position, chip.color(), Direction.TOP_RIGHT));
                    positions.addAll(findFrom(position, chip.color(), Direction.BOTTOM_LEFT));
                });

        return positions;
    }

    private Optional<Chip> chipAt(Position position) {
        return this.board.chipAt(position);
    }
}
