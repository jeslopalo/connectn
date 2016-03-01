package es.sandbox.spike.connectn;

/**
 * Created by jeslopalo on 1/3/16.
 */
public class PositionOutOfRangeException extends IllegalArgumentException {

    public PositionOutOfRangeException(Position position, Dimensions dimensions) {
        super(message(position, dimensions));
    }

    private static String message(Position position, Dimensions dimensions) {
        return "aaa";
    }

}
