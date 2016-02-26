package es.sandbox.spike.connectn

import es.sandbox.test.utils.ReflectionInvoker
import spock.lang.Specification

/**
 * Created by jeslopalo on 26/2/16.
 */
class GameRulesSpec extends Specification {

    def "it should not be instantiable"() {

        when:
        ReflectionInvoker.privateDefaultConstructor(GameRules.class, UnsupportedOperationException)

        then:
        UnsupportedOperationException exception = thrown()
        exception.message == "uninstantiable class"
    }
}
