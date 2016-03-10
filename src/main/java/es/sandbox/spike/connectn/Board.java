package es.sandbox.spike.connectn;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import static es.sandbox.spike.connectn.Position.position;

/**
 * Created by jeslopalo on 24/2/16.
 */
public class Board {
    private final Dimensions dimensions;
    private final GameResultCalculator gameResultCalculator;
    private final Chip[][] chips;
    private Color nextTurn;

    /**
     * @param dimensions
     * @param chipsToWin
     * @param startingColor
     */
    public Board(Dimensions dimensions, int chipsToWin, Color startingColor) {
        Objects.requireNonNull(dimensions, "Dimensions may not be null");
        GameRules.validateChipsToWin(chipsToWin, dimensions);
        Objects.requireNonNull(startingColor, "Starting color may not be null");

        this.dimensions = dimensions;
        this.chips = new Chip[dimensions.getColumns()][dimensions.getRows()];
        this.gameResultCalculator = new GameResultCalculator(this, chipsToWin);
        this.nextTurn = startingColor;
    }

    /**
     * @param color
     * @param column
     * @return
     * @throws ColumnOutOfRangeException
     * @throws ColumnIsFullException
     * @throws WrongTurnException
     * @throws GameOverException
     */
    public Result put(Color color, int column)
            throws ColumnOutOfRangeException, ColumnIsFullException, WrongTurnException, GameOverException {

        this.gameResultCalculator.assertThatGameIsOnGoing();

        consumeTurnFor(color);
        final Position position = findFirstEmptyPositionInColumn(column);
        this.chips[position.column()][position.row()] = new Chip(color, position);

        return calculateResultFor(position);
    }

    private void consumeTurnFor(Color color) {
        if (this.nextTurn != color) {
            throw new WrongTurnException(color);
        }
        this.nextTurn = color.rotate();
    }

    private Position findFirstEmptyPositionInColumn(int column) throws ColumnOutOfRangeException, ColumnIsFullException {
        this.dimensions.validateColumn(column);

        return IntStream.range(0, this.chips[column].length)
                .filter(row -> this.chips[column][row] == null)
                .mapToObj(row -> position(column, row))
                .findFirst()
                .orElseThrow(() -> new ColumnIsFullException(column));
    }

    private Result calculateResultFor(Position position) {
        return this.gameResultCalculator.calculateFor(position);
    }

    /**
     * @param position
     * @return
     */
    public Optional<Color> colorAt(Position position) {
        Objects.requireNonNull(position, "Position must be non null");

        return chipAt(position)
                .map(Chip::color);
    }

    /**
     * @param position
     * @return
     */
    public Optional<Chip> chipAt(Position position) {
        Objects.requireNonNull(position, "Position must be non null");

        if (this.dimensions.contains(position)) {
            return Optional.ofNullable(this.chips[position.column()][position.row()]);
        }
        return Optional.empty();
    }
}
