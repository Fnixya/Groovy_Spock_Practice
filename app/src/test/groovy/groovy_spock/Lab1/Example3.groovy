/* Code for Spock Web Console */

import spock.lang.Specification 
import spock.lang.Unroll

class DataDrivenSpec2 extends Specification {
  // @Shared, no funciona
  // @Use(calc) //no funciona

  // @Unroll //no funciona, si en Spock Web Console, verbose
  @Unroll
  def "Using Sum w/ a: #a b: #b  , the result has to be:  #expected_sum"() {

    given: "Compute Sum"
      def s = new Operations()

    when: "Compute "
      float computed_sum = s.Sum (a, b)

    then: "has to be ..."
      computed_sum == expected_sum

    where:"Data Set"
      a | b  || expected_sum
      20.0 | 30.0  || 50.0
      0.0 | 0.0  || 0.0
      100.0 | 200.0  || 300.0
      100.0 | -100.0  || 0.0
      -10.0 | 20.0  || 10.0
      300.0 | -100.0  || 200.0

  }
  
}

class Operations {

  float Sum (float a=0.0, float b=0.0) {

    float res=a+b
    return res
  }
}
