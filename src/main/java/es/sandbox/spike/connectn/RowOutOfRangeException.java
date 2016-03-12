package es.sandbox.spike.connectn;

/**
 * Created by jeslopalo on 12/3/16.
 */
public class RowOutOfRangeException extends IllegalArgumentException {

    public RowOutOfRangeException(int row, Dimensions dimensions) {
        super(message(row, dimensions));
    }

    private static String message(int row, Dimensions dimensions) {
        return String.format("The row '%d' is out of range [%s]", row, dimensions);
    }
}
