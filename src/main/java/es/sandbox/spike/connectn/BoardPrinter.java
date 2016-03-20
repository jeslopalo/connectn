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

        final PrintWriter writer = new PrintWriter(outputStream, true);
        final Deque<String> rows = stringifyRows();
        final int rowLength = rows.peek().length();

        writeBorderTop(writer, rowLength);
        writeRows(writer, rows);
        writeBorderBottom(writer, rowLength);
        writeResult(writer);
        writer.flush();
    }

    private Deque<String> stringifyRows() {
        final Deque<String> rows = new ArrayDeque<>();

        this.board.dimensions().forEachPositionInRows(positions -> {
            final StringBuilder row = new StringBuilder();

            positions.forEach(position -> {
                final String color = this.board.chipAt(position)
                        .map(chip -> chip.color().name())
                        .orElse("-");
                row.append(formatColor(color));
            });

            rows.push(row.toString());
        });
        return rows;
    }

    private String formatColor(String color) {
        return String.format("%3s", color.substring(0, 1).toUpperCase());
    }

    private void writeBorderTop(PrintWriter writer, int rowLength) {
        writer.println(border(rowLength, TOP_LEFT_CORNER, TOP_RIGHT_CORNER));
    }

    private void writeRows(PrintWriter writer, Deque<String> rows) {
        rows.stream().forEach(row -> writer.format("%1$c%2$s  %1$c\n", VERTICAL, row));
    }

    private void writeBorderBottom(PrintWriter writer, int rowLength) {
        writer.println(border(rowLength, BOTTOM_LEFT_CORNER, BOTTOM_RIGHT_CORNER));
    }

    private void writeResult(PrintWriter writer) {
        writer.println(this.board.getResult());
    }

    private String border(int length, char left, char right) {
        final char[] border = new char[length + 2];
        Arrays.fill(border, HORIZONTAL);
        return String.format("%c%s%c", left, new String(border), right);
    }
}
