package es.sandbox.spike.connectn;

import static es.sandbox.spike.connectn.Position.position;

/**
 * Created by jeslopalo on 29/2/16.
 */
enum Direction {
    TOP {
        @Override
        Position from(Position position) {
            return position(position.column(), position.row() + 1);
        }
    },
    TOP_RIGHT {
        @Override
        Position from(Position position) {
            return TOP.from(RIGHT.from(position));
        }
    },
    RIGHT {
        @Override
        Position from(Position position) {
            return position(position.column() + 1, position.row());
        }
    },
    BOTTOM_RIGHT {
        @Override
        Position from(Position position) {
            return BOTTOM.from(RIGHT.from(position));
        }
    },
    BOTTOM {
        @Override
        Position from(Position position) {
            return position(position.column(), position.row() - 1);
        }
    },
    BOTTOM_LEFT {
        @Override
        Position from(Position position) {
            return BOTTOM.from(LEFT.from(position));
        }
    },
    LEFT {
        @Override
        Position from(Position position) {
            return position(position.column() - 1, position.row());
        }
    },
    TOP_LEFT {
        @Override
        Position from(Position position) {
            return TOP.from(LEFT.from(position));
        }
    };

    abstract Position from(Position position);
}
