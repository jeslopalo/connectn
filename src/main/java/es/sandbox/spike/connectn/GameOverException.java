package es.sandbox.spike.connectn;

/**
 * Created by jeslopalo on 6/3/16.
 */
public final class GameOverException extends IllegalStateException {

    GameOverException(Result result) {
        super(message(result));
    }

    private static String message(Result result) {
        return String.format("Sorry! The game is over. %s", result);
    }
}
