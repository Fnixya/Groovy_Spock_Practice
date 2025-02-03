import spock.lang.Specification

class DataDrivenSpec1 extends Specification {
   def "Test Price Calculator"() {

   given: "Price calculator class"
    def c = new Price_Calculator()

   when: "Perform price computing"
   def comp_price=0.0
//       comp_price = c.Calc_Price (base, special_price, extra_price, num_extras, discount)
    try {
    comp_price = c.Calc_Price (base, special_price, extra_price, num_extras, discount)
       } catch(Exception e) {
    comp_price= "Exception: ${e}" as String}

   then: "Calculated price must be equal to expected price"
    comp_price == price

   where:"Test cases"
    base | special_price | extra_price | num_extras | discount || price
    20000.0 | 3450.0 |6000.0 | 1 | 10.0 || 27450.0
    20000.0 | 3450.0 |6000.0 | 3 | 10.0 || 26850.0
    20000.0 | 3450.0 |6000.0 | 20 | 10.0 || 26550.0
     -1 | 0.0 |500.0 | 5 | 10.0 || "Exception: java.lang.Exception: NOT_VALID"
   "abc" | 0.0 |500.0 | 5 | 10.0 || "Exception: java.lang.Exception: NOT_VALID"
    
  }
  
}

class Price_Calculator {

float Calc_Price (base=0.0, special=0.0, extra=0.0, numextras = 0, discount=0.0) {

float aditional_discount=0.0
float res=0.0
 if ((base instanceof String ||  special instanceof String || extra instanceof String || numextras instanceof String || discount instanceof String))
    {
        throw new Exception("NOT_VALID")
    }
 if (base < 0 ||  special < 0 || extra < 0 || numextras < 0 || discount < 0 ) {
        throw new Exception("NOT_VALID")
    }
if (numextras >= 3) aditional_discount = 10
if (numextras >= 5) aditional_discount = 15
// if (discount > aditional_discount) aditional_discount = discount
res = base*(1 - discount/100)+ special + extra*(1 - aditional_discount/100)
return res
}

}