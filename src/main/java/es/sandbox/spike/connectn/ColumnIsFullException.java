package es.sandbox.spike.connectn;

/**
 * Created by jeslopalo on 24/2/16.
 */
public class ColumnIsFullException extends RuntimeException {

    public ColumnIsFullException(int column) {
        super(message(column));
    }

    private static String message(int column) {
        return String.format("Column <%d> is full", column);
    }
}
