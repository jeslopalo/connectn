package es.sandbox.spike.connectn;

/**
 * Created by jeslopalo on 24/2/16.
 */
public enum Color {
    RED, YELLOW;

    public Color rotate() {
        return rotate(this);
    }

    public static Color rotate(Color color) {
        return color == Color.RED ? Color.YELLOW : Color.RED;
    }
}
