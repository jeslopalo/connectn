package es.sandbox.spike.connectn

/**
 * Created by jeslopalo on 29/2/16.
 */
final class BoardMother {

    public static final SIMPLEST_BOARD_COLUMNS = 2;
    public static final SIMPLEST_BOARD_ROWS = 2;
    public static final SIMPLEST_BOARD_DIMENSIONS = Dimensions.dimensions(SIMPLEST_BOARD_COLUMNS, SIMPLEST_BOARD_ROWS)
    public static final SIMPLEST_BOARD_CHIPS_TO_WIN = 2;
    public static final SIMPLEST_BOARD_STARTING_COLOR = Color.RED;

    public static final MEDIUM_BOARD_COLUMNS = 10;
    public static final MEDIUM_BOARD_ROWS = 10;
    public static final MEDIUM_BOARD_DIMENSIONS = Dimensions.dimensions(MEDIUM_BOARD_COLUMNS, MEDIUM_BOARD_ROWS)
    public static final MEDIUM_BOARD_CHIPS_TO_WIN = 3;
    public static final MEDIUM_BOARD_STARTING_COLOR = Color.RED;

    public static final CONNECT4_BOARD_COLUMNS = 7
    public static final CONNECT4_BOARD_ROWS = 6
    public static final CONNECT4_BOARD_DIMENSIONS = Dimensions.dimensions(CONNECT4_BOARD_COLUMNS, CONNECT4_BOARD_ROWS)
    public static final CONNECT4_BOARD_CHIPS_TO_WIN = 4

    static Board simplestBoard() {
        return new Board(SIMPLEST_BOARD_DIMENSIONS, SIMPLEST_BOARD_CHIPS_TO_WIN, SIMPLEST_BOARD_STARTING_COLOR)
    }

    static Board simplestBoard(List<Integer> redPlays, List<Integer> yellowPlays) {
        final Board board = simplestBoard();

        playTheGame(board, Color.RED, redPlays, yellowPlays)

        return board;
    }

    static Board mediumSizedBoard() {
        return new Board(MEDIUM_BOARD_DIMENSIONS, MEDIUM_BOARD_CHIPS_TO_WIN, MEDIUM_BOARD_STARTING_COLOR);
    }

    static Board mediumSizedBoard(List<Integer> redPlays, List<Integer> yellowPlays) {
        final Board board = mediumSizedBoard();

        playTheGame(board, Color.RED, redPlays, yellowPlays);

        return board;
    }


    static Board connect4Board(Color color) {
        return new Board(CONNECT4_BOARD_DIMENSIONS, CONNECT4_BOARD_CHIPS_TO_WIN, color);
    }

    public
    static Result playTheGame(Board board, Color firstPlayerColor, List<Integer> firstPlayerMoves, List<Integer> secondPlayerMoves) {
        final Queue<Integer> p1Moves = new LinkedList<>(firstPlayerMoves);
        final Queue<Integer> p2Moves = new LinkedList<>(secondPlayerMoves);

        def Result result = Result.draw()
        for (int plays = 0; plays < Math.max(firstPlayerMoves.size(), secondPlayerMoves.size()); plays++) {

            if (p1Moves.peek() != null) {
                result = board.put(firstPlayerColor, p1Moves.poll());
            }

            if (p2Moves.peek() != null) {
                result = board.put(firstPlayerColor.rotate(), p2Moves.poll());
            }
        }
        return result;
    }

    private BoardMother() {
        throw new UnsupportedOperationException()
    }
}
