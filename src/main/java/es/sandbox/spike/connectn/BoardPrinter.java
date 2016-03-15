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

    private static final char VERTICAL = '║';
    private static final char HORIZONTAL = '═';
    private static final char TOP_LEFT_CORNER = '╔';
    private static final char TOP_RIGHT_CORNER = '╗';
    private static final char BOTTOM_LEFT_CORNER = '╚';
    private static final char BOTTOM_RIGHT_CORNER = '╝';

    private final Board board;

    BoardPrinter(Board board) {
        Objects.requireNonNull(board, "Board may be not null");

        this.board = board;
    }

    public void printTo(OutputStream outputStream) {

        final Deque<String> rows = calculateRows();

        final String topBorder = border(rows.peek().length(), TOP_LEFT_CORNER, TOP_RIGHT_CORNER);
        final String bottomBorder = border(rows.peek().length(), BOTTOM_LEFT_CORNER, BOTTOM_RIGHT_CORNER);
        final PrintWriter writer = new PrintWriter(outputStream, true);

        writer.println(topBorder);
        rows.stream().forEach(row -> writer.format("%1$c%2$s  %1$c\n", VERTICAL, row));
        writer.println(bottomBorder);
        writer.println(this.board.getResult());
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

    private String border(int length, char left, char right) {
        final char[] border = new char[length + 2];
        Arrays.fill(border, HORIZONTAL);
        return String.format("%c%s%c", left, new String(border), right);
    }
}
