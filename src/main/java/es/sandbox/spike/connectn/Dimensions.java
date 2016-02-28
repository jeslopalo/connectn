package es.sandbox.spike.connectn;

/**
 * Created by jeslopalo on 27/2/16.
 */
class Dimensions {

    private final int columns;
    private final int rows;

    public Dimensions(int columns, int rows) {
        GameRules.validateDimensions(columns, rows);

        this.columns = columns;
        this.rows = rows;
    }

    @Override
    public String toString() {
        return String.format("%dx%d", this.columns, this.rows);
    }
}
