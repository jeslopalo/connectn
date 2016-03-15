package es.sandbox.spike.connectn

import spock.lang.Specification

/**
 * Created by jeslopalo on 11/3/16.
 */
class BoardPrinterSpec extends Specification {

    def "should fail without board"() {
        when:
        new BoardPrinter(null)

        then:
        NullPointerException exception = thrown();
        exception.message == "Board may be not null"
    }

    def "should print empty board"() {
        given:
        def board = BoardMother.simplestBoard()
        def printer = new BoardPrinter(board)
        def outputStream = new ByteArrayOutputStream()

        when:
        printer.printTo(outputStream)

        then:
        outputStream.toString("UTF-8") ==
                """╔════════╗
                  |║  -  -  ║
                  |║  -  -  ║
                  |╚════════╝
                  |draw! The game is on going
                  |""".stripMargin()

    }

    def "should print empty boards"() {
        given:
        def board = new Board(Dimensions.dimensions(columns, rows), 2, Color.RED)
        def printer = new BoardPrinter(board)
        def outputStream = new ByteArrayOutputStream()

        when:
        printer.printTo(outputStream)

        then:
        outputStream.toString("UTF-8").length() == boardLength(columns, rows, Result.draw())

        where:
        columns << [2, 3, 4, 2, 3, 4]
        rows << [2, 3, 4, 4, 3, 2]
    }

    def "should print a board with finshed game"() {
        given:
        def board = BoardMother.connect4Board(Color.RED)
        def result = BoardMother.playTheGame(board, Color.RED, [0, 1, 2, 3, 4, 4, 4], [0, 1, 3, 2, 1, 0, 0])
        def printer = new BoardPrinter(board)
        def outputStream = new ByteArrayOutputStream()

        when:
        printer.printTo(outputStream)

        then:
        outputStream.toString("UTF-8").length() == boardLength(BoardMother.CONNECT4_BOARD_COLUMNS, BoardMother.CONNECT4_BOARD_ROWS, result)
        outputStream.toString("UTF-8") ==
                """╔═══════════════════════╗
                  |║  -  -  -  -  -  -  -  ║
                  |║  -  -  -  -  -  -  -  ║
                  |║  Y  -  -  -  -  -  -  ║
                  |║  Y  Y  -  -  R  -  -  ║
                  |║  Y  Y  Y  R  R  -  -  ║
                  |║  R  R  R  Y  R  -  -  ║
                  |╚═══════════════════════╝
                  |YELLOW win! positions: {[1, 2], [0, 3], [3, 0], [2, 1]}
                  |""".stripMargin()
    }

    def int boardLength(int columns, int rows, Result result) {

        def borderLength = (3 * columns) + 4 + 1;
        def rowLength = (3 * columns) + 4 + 1
        def resultLength = result.toString().length() + 1
        return (2 * borderLength) + (rows * rowLength) + resultLength
    }
}
