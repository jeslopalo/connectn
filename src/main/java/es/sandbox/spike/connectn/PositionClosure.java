package es.sandbox.spike.connectn;

/**
 * Created by jeslopalo on 14/3/16.
 */
@FunctionalInterface
public interface PositionClosure {

    /**
     * @param position
     */
    void execute(Position position);
}
