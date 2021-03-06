package es.sandbox.spike.connectn

import es.sandbox.test.utils.ReflectionInvoker
import spock.lang.Specification

/**
 * Created by jeslopalo on 26/2/16.
 */
class GameRulesSpec extends Specification {

    def "should not be instantiable"() {

        when:
        ReflectionInvoker.privateDefaultConstructor(GameRules.class, UnsupportedOperationException)

        then:
        UnsupportedOperationException exception = thrown()
        exception.message == "uninstantiable class"
    }

    def "should fail when chips to win is lower than two"() {

        when:
        GameRules.validateChipsToWin(chipsToWin, Dimensions.dimensions(5, 5));

        then:
        IllegalArgumentException exception = thrown()
        exception.message == "Chips to win must be greater than 1"

        where:
        chipsToWin << [-1, 0, 1]
    }

    def "should fail when chips to win is greater than the number of columns"() {

        when:
        GameRules.validateChipsToWin(chipsToWin, Dimensions.dimensions(5, 15));

        then:
        IllegalArgumentException exception = thrown()
        exception.message == "Chips to win '" + chipsToWin + "' must fit the board dimensions '5x15'"

        where:
        chipsToWin << [6, 7, 8]
    }

    def "should fail when chips to win is greater than the number of rows"() {

        when:
        GameRules.validateChipsToWin(chipsToWin, Dimensions.dimensions(15, 5));

        then:
        IllegalArgumentException exception = thrown()
        exception.message == "Chips to win '" + chipsToWin + "' must fit the board dimensions '15x5'"

        where:
        chipsToWin << [6, 7, 8]
    }

    def "should validate chips to win when are lower or equal than columns and rows"() {

        when:
        GameRules.validateChipsToWin(chipsToWin, Dimensions.dimensions(columns, rows))

        then:
        noExceptionThrown()

        where:
        chipsToWin | columns | rows
        2          | 2       | 10
        2          | 3       | 10
        2          | 4       | 10
        2          | 10      | 2
        2          | 10      | 3
        2          | 10      | 4
        3          | 3       | 10
        3          | 4       | 10
        3          | 5       | 10
        3          | 10      | 3
        3          | 10      | 4
        3          | 5       | 10
    }

    def "should fail when dimensions are null"() {
        when:
        GameRules.validateChipsToWin(3, null)

        then:
        NullPointerException exception = thrown()
        exception.message == "Dimensions must not be null"
    }
}
