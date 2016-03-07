package es.sandbox.spike.connectn;

/**
 * Created by jeslopalo on 7/3/16.
 */
public class WrongTurnException extends IllegalStateException {

    WrongTurnException(Color color) {
        super(message(color));
    }

    private static String message(Color color) {
        return String.format("Cannot put a %s chip. It's the %s turn!", color, color.rotate());
    }
}
