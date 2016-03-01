package es.sandbox.spike.connectn

import spock.lang.Specification

/**
 * Created by jeslopalo on 1/3/16.
 */
class ResultSpec extends Specification {

    def "should return empty optional when draw"() {
        when:
        def result = Result.draw()

        then:
        !result.winner().isPresent()
    }
}
