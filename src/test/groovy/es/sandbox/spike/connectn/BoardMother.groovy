package es.sandbox.spike.connectn

/**
 * Created by jeslopalo on 29/2/16.
 */
final class BoardMother {

    public static final SIMPLEST_BOARD_COLUMNS = 2;
    public static final SIMPLEST_BOARD_ROWS = 2;
    public static final SIMPLEST_BOARD_CHIPS_TO_WIN = 2;

    public static final MEDIUM_BOARD_COLUMNS = 10;
    public static final MEDIUM_BOARD_ROWS = 10;
    public static final MEDIUM_BOARD_CHIPS_TO_WIN = 2;

    static Board simplestBoard() {
        return new Board(SIMPLEST_BOARD_COLUMNS, SIMPLEST_BOARD_ROWS, SIMPLEST_BOARD_CHIPS_TO_WIN)
    }

    static Board simplestBoard(List<Integer> redPlays, List<Integer> yellowPlays) {
        final Board board = simplestBoard();

        playTheGame(board, redPlays, yellowPlays)

        return board;
    }

    static Board mediumSizedBoard() {
        return new Board(MEDIUM_BOARD_CHIPS_TO_WIN, MEDIUM_BOARD_COLUMNS, MEDIUM_BOARD_ROWS);
    }

    static Board mediumSizedBard(List<Integer> redPlays, List<Integer> yellowPlays) {
        final Board board = mediumSizedBoard();

        playTheGame(board, redPlays, yellowPlays);

        return board;
    }

    public static void playTheGame(Board board, List<Integer> redPlays, List<Integer> yellowPlays) {
        final Queue<Integer> reds = new LinkedList<>(redPlays);
        final Queue<Integer> yellows = new LinkedList<>(yellowPlays);

        for (int plays = 0; plays < Math.max(redPlays.size(), yellowPlays.size()); plays++) {

            if (reds.peek() != null) {
                board.put(Color.RED, reds.poll());
            }

            if (yellows.peek() != null) {
                board.put(Color.RED, yellows.poll());
            }
        }
    }

    private BoardMother() {
        throw new UnsupportedOperationException()
    }
}
