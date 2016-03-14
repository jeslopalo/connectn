package es.sandbox.spike.connectn;

import java.util.List;

/**
 * Created by jeslopalo on 14/3/16.
 */
@FunctionalInterface
public interface PositionsClosure {

    /**
     * @param positions
     */
    void execute(List<Position> positions);
}