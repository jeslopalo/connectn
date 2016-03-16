package es.sandbox.spike.connectn;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by jeslopalo on 29/2/16.
 */
final class GameResultCalculator {

    private final Board board;
    private final int chipsToWin;
    private Result result;

    GameResultCalculator(Board board, int chipsToWin) {
        Objects.requireNonNull(board, "Board must be not null");
        GameRules.assertThatChipsToWinIsGreaterThanOne(chipsToWin);

        this.board = board;
        this.chipsToWin = chipsToWin;
        this.result = Result.draw();
    }

    public Result getResult() {
        return this.result;
    }

    public Result calculateFor(Position position) {
        Objects.requireNonNull(position, "Position must be not null");

        if (!this.result.isGameOver()) {
            this.result = resultFor(position);
        }
        return this.result;
    }

    private Result resultFor(Position position) {
        return chipAt(position)
                .map(chip ->
                        findFirstWinnerLineFrom(chip)
                                .map(chips -> Result.winner(chip.color(), chips))
                                .orElse(Result.draw()))
                .orElse(Result.draw());
    }

    private Optional<Set<Chip>> findFirstWinnerLineFrom(Chip chip) {
        return Stream.of(Line.values())
                .map(line -> findChipsInLine(chip, line))
                .filter(chips -> chips.size() >= this.chipsToWin)
                .findFirst();
    }

    private Set<Chip> findChipsInLine(Chip chip, Line line) {
        final Set<Chip> positions = new HashSet<>();
        positions.add(chip);
        positions.addAll(findFrom(chip, line.from()));
        positions.addAll(findFrom(chip, line.to()));
        return positions;
    }

    private Set<Chip> findFrom(Chip origin, Direction direction) {
        final Set<Chip> positions = new HashSet<>();

        origin.position().at(direction)
                .ifPresent(position ->
                        chipAt(position)
                                .filter(chip -> chip.color() == origin.color())
                                .ifPresent(chip -> {
                                    positions.add(chip);
                                    positions.addAll(findFrom(chip, direction));
                                }));

        return positions;
    }

    private Optional<Chip> chipAt(Position position) {
        return this.board.chipAt(position);
    }

    public void assertThatGameIsOnGoing() throws GameOverException {
        if (this.result.isGameOver()) {
            throw new GameOverException(this.result);
        }
    }
}
