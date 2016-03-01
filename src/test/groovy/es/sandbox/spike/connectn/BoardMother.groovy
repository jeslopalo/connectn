package es.sandbox.spike.connectn

/**
 * Created by jeslopalo on 29/2/16.
 */
final class BoardMother {

    static Board simplestBoard() {
        return new Board(2, 2, 2)
    }

    private BoardMother() {
        throw new UnsupportedOperationException()
    }
}
