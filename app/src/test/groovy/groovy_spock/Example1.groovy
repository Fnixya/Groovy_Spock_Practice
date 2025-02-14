package groovy_spock

import spock.lang.*

// Test indeterminate involving 0,1,+INF: https://mathworld.wolfram.com/Indeterminate.html

class FloatCalculusTests extends Specification {
   def o = new Operations()
  
   def "Test 0/0"() {
      expect:
      Float.NaN==o.Div(0.0,0.0)
   }

   def "Test 0*INF"() {
      expect:
      Float.NaN==o.Mult(0.0,Float.POSITIVE_INFINITY)
   }    

   def "Test INF/INF"() {
      expect:
      Float.NaN==o.Div(Float.POSITIVE_INFINITY,Float.POSITIVE_INFINITY)
   }

   def "Test INF-INF"() {
      expect:
      Float.NaN==o.Sum(Float.POSITIVE_INFINITY,Float.NEGATIVE_INFINITY)
   }

   def "Test 0^0"() {
      expect:
      1==o.Exp(0.0,0.0)
   }

   def "Test INF^0"() {
      expect:
      1==o.Exp(Float.POSITIVE_INFINITY,0.0)
   }

   def "Test 1.0^INF"() {
      expect:
      Float.NaN==o.Exp(1.0, Float.POSITIVE_INFINITY)
   }

   def "Test +INF"() {
      expect:
      Float.POSITIVE_INFINITY==o.Div(1.0,0.0)
   }
  
   def "Test -INF"() {
      expect:
      Float.NEGATIVE_INFINITY==o.Div(-1.0,0.0)
   }

   def "Test -0,0 - False Negative"() {
      expect:
      // new Float (-0.0) == o.Div(0.0,-1.0)
      o.Div(0.0,-1.0) == -0.0
   }

   def "Test 0,0"() {
      expect:
      Float.compare(o.Div(a, b), 0.0) == expected

      where:
      a | b | expected
      0.0 | 1.0 | 0
      0.0 | -1.0 | -1
      -0.0 | 1.0 | -1
      -0.0 | -1.0 | 0
   }

   def "Test sqrt -1"() {
      expect:
      Float.NaN==o.SR(-1.0)
   }

   def "INF/-INF"() {
      expect:
      o.Div(a, b) == expected

      where:
      a | b | expected
      Float.POSITIVE_INFINITY | Float.POSITIVE_INFINITY | Float.NaN
      Float.NEGATIVE_INFINITY | Float.POSITIVE_INFINITY | Float.NaN
      Float.POSITIVE_INFINITY | Float.NEGATIVE_INFINITY | Float.NaN
      Float.NEGATIVE_INFINITY | Float.NEGATIVE_INFINITY | Float.NaN
   }

   def "Test sqrt -1"() {
      expect:
      Float.NaN==o.SR(-1.0)
   }

}

// SUT: Operations

class Operations {

   float Div (float a=0.0, float b=0.0) {
      float res=a/b
      return res
   }

   float Sum (float a=0.0, float b=0.0) {
      float res=a+b
      return res
   }

   float Mult (float a=0.0, float b=0.0) {
      float res=a*b
      return res
   }

   float Exp (float a=0.0, float b=0.0) {
      float res=a**b
      return res
   }

   float SR (float a=0.0) {
      float res=Math.sqrt(a)
      return res
   }

}