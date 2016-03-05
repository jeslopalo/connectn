package es.sandbox.spike.connectn;

/**
 * Created by jeslopalo on 4/3/16.
 */
public enum Line {
    /* (|) */
    VERTICAL {
        @Override
        Direction from() {
            return Direction.TOP;
        }

        @Override
        Direction to() {
            return Direction.BOTTOM;
        }
    },
    /* (\) */
    MAIN_DIAGONAL {
        @Override
        Direction from() {
            return Direction.TOP_LEFT;
        }

        @Override
        Direction to() {
            return Direction.BOTTOM_RIGHT;
        }
    },
    /* (-) */
    HORIZONTAL {
        @Override
        Direction from() {
            return Direction.LEFT;
        }

        @Override
        Direction to() {
            return Direction.RIGHT;
        }
    },
    /* (/) */
    ANTI_DIAGONAL {
        @Override
        Direction from() {
            return Direction.TOP_RIGHT;
        }

        @Override
        Direction to() {
            return Direction.BOTTOM_LEFT;
        }
    },;

    abstract Direction from();

    abstract Direction to();
}
