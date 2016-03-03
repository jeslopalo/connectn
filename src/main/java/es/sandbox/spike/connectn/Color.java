package es.sandbox.spike.connectn;

/**
 * Created by jeslopalo on 24/2/16.
 */
public enum Color {
    RED, YELLOW;

    Color rotate() {
        return rotate(this);
    }

    private static Color rotate(Color color) {
        return color == Color.RED ? Color.YELLOW : Color.RED;
    }
}
