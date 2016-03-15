package es.sandbox.spike.connectn;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Objects;

/**
 * Created by jeslopalo on 11/3/16.
 */
public class BoardPrinter {

    private final Board board;

    BoardPrinter(Board board) {
        Objects.requireNonNull(board, "Board may be not null");

        this.board = board;
    }

    public void printTo(OutputStream outputStream) {

        final Deque<String> rows = calculateRows();

        final String border = border(rows.peek().length());
        final PrintWriter writer = new PrintWriter(outputStream, true);
        writer.format("%s\n", border);
        rows.stream().forEach(row -> writer.format("|%s  |\n", row));
        writer.format("%s\n", border);
        writer.format("%s\n", this.board.getResult());
        writer.flush();
    }

    private Deque<String> calculateRows() {
        final Deque<String> rows = new ArrayDeque<>();

        this.board.dimensions().forEachPositionInRows(positions -> {
            final StringBuilder row = new StringBuilder();

            positions.forEach(position -> {
                final String color = this.board.chipAt(position)
                        .map(chip -> chip.color().name())
                        .orElse("-");
                row.append(String.format("%3s", color.substring(0, 1).toUpperCase()));
            });

            rows.push(row.toString());
        });
        return rows;
    }

    private String border(int length) {
        final char[] border = new char[length];
        Arrays.fill(border, '-');
        return String.format(" %s-- ", new String(border));
    }
}
