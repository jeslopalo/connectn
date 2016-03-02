package es.sandbox.spike.connectn;

import java.util.Objects;
import java.util.Optional;

import static es.sandbox.spike.connectn.Position.position;

/**
 * Created by jeslopalo on 24/2/16.
 */
public class Board {
    private final int chipsToWin;
    private final Dimensions dimensions;
    private final GameResultCalculator gameResultCalculator;

    private final Chip[][] chips;

    public Board(int chipsToWin, int columns, int rows) {
        GameRules.validateDimensions(columns, rows);
        GameRules.validateChipsToWin(chipsToWin, columns, rows);

        this.dimensions = new Dimensions(columns, rows);
        this.chipsToWin = chipsToWin;
        this.chips = new Chip[columns][rows];
        this.gameResultCalculator = new GameResultCalculator(this);
    }

    int getChipsToWin() {
        return this.chipsToWin;
    }

    Dimensions getDimensions() {
        return this.dimensions;
    }

    Result put(Color color, int column) {
        final int row = findFirstEmptyRowInColumn(column);
        final Position position = position(column, row);

        this.chips[column][row] = new Chip(color, position);

        return calculateResult(position);
    }

    private Result calculateResult(Position position) {
        return this.gameResultCalculator.calculateFor(position);
    }

    private int findFirstEmptyRowInColumn(int column) {

        for (int row = 0; row < this.chips[column].length; row++) {
            if (this.chips[column][row] == null) {
                return row;
            }
        }
        throw new ColumnIsFullException(column);
    }

    public Optional<Color> colorAt(Position position) {
        Objects.requireNonNull(position, "Position must be non null");

        return chipAt(position)
                .map(Chip::color);
    }

    public Optional<Chip> chipAt(Position position) {
        Objects.requireNonNull(position, "Position must be non null");

        if (this.dimensions.contains(position)) {
            return Optional.ofNullable(this.chips[position.column()][position.row()]);
        }
        return Optional.empty();
    }
}
