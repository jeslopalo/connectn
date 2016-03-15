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

    def "should print an empty board"() {
        given:
        def board = new Board(Dimensions.dimensions(columns, rows), 2, Color.RED)
        def printer = new BoardPrinter(board)
        def outputStream = new ByteArrayOutputStream()

        when:
        printer.printTo(outputStream)
        printer.printTo(System.out)

        then:
        def borderLength = (3 * columns) + 4 + 1;
        def rowLength = (3 * columns) + 4 + 1
        def resultLength = Result.draw().toString().length() + 1
        outputStream.toString("UTF-8").length() == (2 * borderLength) + (rows * rowLength) + resultLength

        where:
        columns << [2, 3, 4, 2, 3, 4]
        rows << [2, 3, 4, 4, 3, 2]
    }
/*
    def "should print an empty board to output stream"() {
        given:
        def board = BoardMother.connect4Board(Color.RED)
        def outputStream = new ByteArrayOutputStream()
        BoardMother.playTheGame(board, Color.RED, [0, 1], [0, 1])

        when:
        board.printTo(outputStream)

        then:
        outputStream.toString("UTF-8") == ""
    }
*/

}
