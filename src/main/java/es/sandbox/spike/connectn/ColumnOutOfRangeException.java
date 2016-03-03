package es.sandbox.spike.connectn;

/**
 * Created by jeslopalo on 3/3/16.
 */
public class ColumnOutOfRangeException extends IllegalArgumentException {

    public ColumnOutOfRangeException(int column, Dimensions dimensions) {
        super(message(column, dimensions));
    }

    private static String message(int column, Dimensions dimensions) {
        return String.format("The column '%d' is out of range [%s]", column, dimensions);
    }
}
