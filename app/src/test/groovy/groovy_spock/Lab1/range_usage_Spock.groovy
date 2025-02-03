import spock.lang.Specification

class TestRange extends Specification {

    def "Test 1"() {

        given:
        def x = 1..10

        expect:
        x.contains(10)
    }
        
    def "Test 2"() {

        given:
        def x = 1..10

        expect:
        !x.contains(15)
    }
        
    def "Test 3"() {

        given:
        def x = 1..10

        expect:
        x.size() == 10
        and:
        x.from == 1
        and:
        x.to == 10
        and:
        x.reverse() == 10..1
    }

}