import org.junit.Test;

import calculator.Calculator;
import calculator.SimpleCalculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * this class represents tests for each public method
 * written for interface.
 */
public class SimpleCalculatorTest {

  // the type of tests that are gonna change from asg2
  //- handline multiple = in a row

  // assignment 2 tests
  @Test
  public void testSimplicity() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('1');
    assertEquals("1", calc.getResult());
  }

  // "Overall invalid input char"
  @Test
  public void testInvalidChars() {
    Calculator calc = new SimpleCalculator();
    assertThrows(IllegalArgumentException.class, () -> calc.input('A'));
  }

  @Test
  public void testInvalidChars1() {
    Calculator calc = new SimpleCalculator();
    assertThrows(IllegalArgumentException.class, () -> {
      calc.input('1');
      calc.input('/');
    });
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidChars1_1() {
    Calculator calc = new SimpleCalculator();
    calc.input('/');
  }


  @Test
  public void testNumWNoOps() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('1');
    calc = calc.input('2');
    calc = calc.input('=');
    assertEquals("12", calc.getResult());
  }

  @Test
  public void testValidAdd() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('1');
    calc = calc.input('+');
    calc = calc.input('2');

    calc = calc.input('=');
    assertEquals("3", calc.getResult());
  }

  // first input is an operator or '='
  @Test (expected = IllegalArgumentException.class)
  public void testFirstInputMuultiply() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('*');
  }

  @Test (expected = IllegalArgumentException.class)
  public void testFirstInputAdd() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('+');
  }

  @Test (expected = IllegalArgumentException.class)
  public void testFirstInputSubtract() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('-');
  }

  @Test (expected = IllegalArgumentException.class)
  public void testFirstInputEqual() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('=');
  }

  @Test
  public void testValidAfterAddC() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('1');
    calc = calc.input('+');
    assertEquals("1+", calc.getResult());
    calc = calc.input('2');
    assertEquals("1+2", calc.getResult());
    calc = calc.input('=');
    assertEquals("3", calc.getResult());
    calc = calc.input('C');

    //calc = new SimpleCalculator();
    assertEquals("", calc.getResult());
  }

  @Test
  public void testValidAfterSubtrC() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('5');
    assertEquals("5", calc.getResult());
    calc = calc.input('-');
    assertEquals("5-", calc.getResult());
    calc = calc.input('6');
    assertEquals("5-6", calc.getResult());
    calc = calc.input('=');
    assertEquals("-1", calc.getResult());

    calc = calc.input('C');
    assertEquals("", calc.getResult());
  }

  @Test
  public void testValidAfterMultiplyC() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('5');
    assertEquals("5", calc.getResult());
    calc = calc.input('*');
    assertEquals("5*", calc.getResult());
    calc = calc.input('9');
    assertEquals("5*9", calc.getResult());
    calc = calc.input('=');
    assertEquals("45", calc.getResult());

    calc = calc.input('C');
    assertEquals("", calc.getResult());
  }

  // ILLEGAL APPLICATIONS OF OPERATIONS -->
  // test different variations of += (with all types of operators)
  @Test (expected = IllegalArgumentException.class)
  public void testINVEAdd() {
    Calculator calc = new SimpleCalculator();
    calc.input('+');
  }

  @Test (expected = IllegalArgumentException.class)
  public void testINVEAdd_1() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('+');
    calc = calc.input('=');

  }

  @Test (expected = IllegalArgumentException.class)
  public void testINVESubtract() {
    Calculator calc = new SimpleCalculator();
    calc.input('-');
  }

  @Test (expected = IllegalArgumentException.class)
  public void testINVESubtract_1() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('-');
    calc = calc.input('=');

  }

  @Test (expected = IllegalArgumentException.class)
  public void testINVEMul() {
    Calculator calc = new SimpleCalculator();
    calc.input('*');
  }

  @Test (expected = IllegalArgumentException.class)
  public void testINVEMul_1() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('*');
    calc = calc.input('=');
  }

  @Test (expected = IllegalArgumentException.class)
  public void testINVEAdd1() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('+');
    calc.input('=');
  }

  @Test (expected = IllegalArgumentException.class)
  public void testINVESub1() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('-');
    calc.input('=');
  }

  @Test (expected = IllegalArgumentException.class)
  public void testINVEMul1() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('*');
    calc.input('=');
  }

  @Test
  public void testValAdd() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('+');
    calc = calc.input('2');
    calc = calc.input('=');
    assertEquals("4", calc.getResult());
  }

  @Test
  public void testValSub() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('-');
    calc = calc.input('2');
    calc = calc.input('=');
    assertEquals("0", calc.getResult());
  }

  @Test
  public void testValSubNeg() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('-');
    calc = calc.input('2');
    calc = calc.input('=');
    calc = calc.input('-');
    calc = calc.input('2');
    calc = calc.input('=');
    assertEquals("-2", calc.getResult());
  }

  @Test
  public void testValMul() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('*');
    calc = calc.input('2');
    calc = calc.input('=');
    assertEquals("4", calc.getResult());
  }

  @Test
  public void testValMulNeg() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('0');
    calc = calc.input('-');
    calc = calc.input('2');
    calc = calc.input('=');
    calc = calc.input('*');
    calc = calc.input('2');
    calc = calc.input('=');
    assertEquals("-4", calc.getResult());
  }

  @Test
  public void testValidEAdd2() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('+');
    assertEquals("2+", calc.getResult());
  }

  @Test
  public void testValidESub2() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('-');
    assertEquals("2-", calc.getResult());
  }

  @Test
  public void testValidEMul2() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('*');
    assertEquals("2*", calc.getResult());
  }

  // to clear with all operations + multiple operators
  @Test
  public void testValidCAdd() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('+');
    calc = calc.input('2');
    calc = calc.input('=');
    assertEquals("4", calc.getResult());
    calc = calc.input('C');
    assertEquals("", calc.getResult());
  }

  @Test
  public void testValidCSub() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('-');
    calc = calc.input('6');
    calc = calc.input('=');
    assertEquals("-4", calc.getResult());
    calc = calc.input('C');
    assertEquals("", calc.getResult());
  }

  @Test
  public void testValidCMul() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('*');
    calc = calc.input('6');
    calc = calc.input('=');
    assertEquals("12", calc.getResult());
    calc = calc.input('C');
    assertEquals("", calc.getResult());
  }

  @Test
  public void testValidCALL() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('-');
    calc = calc.input('6');
    calc = calc.input('*');
    calc = calc.input('1');
    calc = calc.input('0');
    calc = calc.input('+');
    calc = calc.input('2');
    calc = calc.input('=');
    assertEquals("12", calc.getResult());

    calc = calc.input('C');
    assertEquals("", calc.getResult());
  }

  @Test
  public void testValidALLOps() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('-');
    calc = calc.input('6');
    calc = calc.input('*');
    calc = calc.input('1');
    calc = calc.input('0');
    calc = calc.input('+');
    calc = calc.input('2');
    calc = calc.input('=');
    assertEquals("12", calc.getResult());
  }

  @Test
  public void testValidALLOpsBigNums() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('0');
    calc = calc.input('-');
    calc = calc.input('6');
    calc = calc.input('2');
    calc = calc.input('*');
    calc = calc.input('1');
    calc = calc.input('0');
    calc = calc.input('+');
    calc = calc.input('2');
    calc = calc.input('0');
    calc = calc.input('=');
    assertEquals("30", calc.getResult());
  }

  // "first input must be C or digit"
  @Test (expected = IllegalArgumentException.class)
  public void testINVEAdd2() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('+');
    calc = calc.input('2');
  }

  @Test (expected = IllegalArgumentException.class)
  public void testINVEAdd3() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('+');
    calc = calc.input('2');
    calc = calc.input('+');
    assertEquals("2+2+", calc.getResult());
    calc.input('=');
  }

  @Test (expected = IllegalArgumentException.class)
  public void testINVESub() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('-');
    calc = calc.input('9');
    calc = calc.input('-');
    assertEquals("2-9-", calc.getResult());
    calc.input('=');
  }

  @Test (expected = IllegalArgumentException.class)
  public void testINVESubAddMul() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('-');
    calc = calc.input('9');
    calc = calc.input('+');
    calc = calc.input('9');
    calc = calc.input('*');
    assertEquals("2-9+9*", calc.getResult());
    calc.input('=');
  }

  //"cannot have 2 operators in a row, must follow with an ="
  @Test (expected = IllegalArgumentException.class)
  public void testINVEAdd4() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('+');
    calc.input('+');
  }

  // The result of 7113+12380 is not as expected, expected:<[19493]> but was:<[0]>
  @Test
  public void testAG() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('7');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('3');
    assertEquals("7113", calc.getResult());
    calc = calc.input('+');
    assertEquals("7113+", calc.getResult());
    calc = calc.input('1');
    assertEquals("7113+1", calc.getResult());
    calc = calc.input('2');
    calc = calc.input('3');
    calc = calc.input('8');
    calc = calc.input('0');
    assertEquals("7113+12380", calc.getResult());
    calc = calc.input('=');
    assertEquals("19493", calc.getResult());
  }

  @Test
  public void testAG2() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('1');
    calc = calc.input('0');
    calc = calc.input('*');
    calc = calc.input('2');
    calc = calc.input('=');
    assertEquals("20", calc.getResult());
    calc = calc.input('+');
    assertEquals("20+", calc.getResult()); // how result should be displayed
    calc = calc.input('1');
    calc = calc.input('0');
    assertEquals("20+10", calc.getResult());
    calc = calc.input('=');
    assertEquals("30", calc.getResult());
    calc = calc.input('=');
    assertEquals("30", calc.getResult());
  }

  @Test
  public void testAGDoubleEq() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('1');
    calc = calc.input('0');
    calc = calc.input('*');
    calc = calc.input('2');
    calc = calc.input('=');
    assertEquals("20", calc.getResult());
    calc = calc.input('+');
    assertEquals("20+", calc.getResult()); // how result should be displayed
    calc = calc.input('1');
    calc = calc.input('0');
    assertEquals("20+10", calc.getResult());
    calc = calc.input('=');
    calc = calc.input('=');
    assertEquals("30", calc.getResult());
  }

  @Test
  public void testAG3() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('1');
    calc = calc.input('0');
    calc = calc.input('0');
    calc = calc.input('=');
    assertEquals("100", calc.getResult());
  }

  // 2147483647
  @Test
  public void testAG4() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('9');
    calc = calc.input('-');
    calc = calc.input('1');
    calc = calc.input('=');
    assertEquals("8", calc.getResult());
  }

  // checking negative overflow
  @Test
  public void testAG6() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('0');
    calc = calc.input('-');
    calc = calc.input('2');
    calc = calc.input('1');
    calc = calc.input('4');
    calc = calc.input('7');
    calc = calc.input('4');
    calc = calc.input('8');
    calc = calc.input('3');
    calc = calc.input('6');
    calc = calc.input('4');
    calc = calc.input('7');
    calc = calc.input('=');
    // -2147483647
    assertEquals("-2147483647", calc.getResult());
    calc = calc.input('-');
    calc = calc.input('1');
    calc = calc.input('=');
    // is an overflow num, this result = 0
    assertEquals("-2147483648", calc.getResult());
    calc = calc.input('-');
    calc = calc.input('1');
    calc = calc.input('=');
    assertEquals("0", calc.getResult());
  }

  // checking positive overflow
  @Test
  public void testAG7() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('1');
    calc = calc.input('0');
    calc = calc.input('7');
    calc = calc.input('3');
    calc = calc.input('7');
    calc = calc.input('4');
    calc = calc.input('1');
    calc = calc.input('8');
    calc = calc.input('2');
    calc = calc.input('4');
    calc = calc.input('+');
    calc = calc.input('1');
    calc = calc.input('0');
    calc = calc.input('7');
    calc = calc.input('3');
    calc = calc.input('7');
    calc = calc.input('4');
    calc = calc.input('1');
    calc = calc.input('8');
    calc = calc.input('2');
    calc = calc.input('5');
    calc = calc.input('=');
    assertEquals("0", calc.getResult());
  }

  @Test
  public void testAG5() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('0');
    calc = calc.input('-');
    calc = calc.input('1');
    calc = calc.input('=');
    assertEquals("-1", calc.getResult());
  }

  // operand overflow
  @Test (expected = IllegalArgumentException.class)
  public void testAG8() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    //assertEquals("11111111111", calc.getResult());
  }

  // checking negative overflow operand
  @Test (expected = IllegalArgumentException.class)
  public void testAG8_1() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('0');
    calc = calc.input('-');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc.input('=');
  }

  // checking negative overflow result
  @Test
  public void testAG9() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('0');
    calc = calc.input('-');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('=');
    assertEquals("-1111111111", calc.getResult());
    calc = calc.input('-');
    calc = calc.input('1');
    assertEquals("-1111111111-1", calc.getResult());
    calc = calc.input('1');
    assertEquals("-1111111111-11", calc.getResult());
    calc = calc.input('1');
    assertEquals("-1111111111-111", calc.getResult());
    calc = calc.input('1');
    assertEquals("-1111111111-1111", calc.getResult());
    calc = calc.input('1');
    assertEquals("-1111111111-11111", calc.getResult());
    calc = calc.input('1');
    assertEquals("-1111111111-111111", calc.getResult());
    calc = calc.input('1');
    assertEquals("-1111111111-1111111", calc.getResult());
    calc = calc.input('1');
    assertEquals("-1111111111-11111111", calc.getResult());
    calc = calc.input('1');
    assertEquals("-1111111111-111111111", calc.getResult());
    calc = calc.input('1');
    assertEquals("-1111111111-1111111111", calc.getResult());
    calc = calc.input('=');
    assertEquals("0", calc.getResult());
  }

  // multiplication positive overflow
  @Test
  public void testMultOverflow() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('9');
    calc = calc.input('9');
    calc = calc.input('9');
    calc = calc.input('9');
    calc = calc.input('9');
    calc = calc.input('9');
    calc = calc.input('*');
    calc = calc.input('9');
    calc = calc.input('9');
    calc = calc.input('9');
    calc = calc.input('9');
    calc = calc.input('9');
    calc = calc.input('9');
    calc = calc.input('=');
    assertEquals("0", calc.getResult());
  }

  // multiply and get negative
  @Test
  public void testMultOverflow1() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('9');
    calc = calc.input('9');
    calc = calc.input('9');
    calc = calc.input('9');
    calc = calc.input('9');
    calc = calc.input('9');
    calc = calc.input('*');
    calc = calc.input('9');
    calc = calc.input('9');
    calc = calc.input('9');
    calc = calc.input('9');
    calc = calc.input('9');
    calc = calc.input('9');
    calc = calc.input('=');
    assertEquals("0", calc.getResult());
  }

  @Test
  public void testAG10() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('=');
    assertEquals("2", calc.getResult());
  }

  @Test
  public void testInputZero() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('0');
    calc = calc.input('1');
    calc = calc.input('=');
    assertEquals("1", calc.getResult());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSimpleCalculatorOverflowSubtract() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('0');
    calc = calc.input('-');
    // the following operand overflows
    calc = calc.input('2');
    calc = calc.input('1');
    calc = calc.input('4');
    calc = calc.input('7');
    calc = calc.input('4');
    calc = calc.input('8');
    calc = calc.input('3');
    calc = calc.input('6');
    calc = calc.input('4');
    calc = calc.input('8');
    calc = calc.input('='); // -2147483648 (Min_VALUE)
    //assertEquals("-2147483648", calc.getResult());
  }

  @Test
  public void testSimpleCalculatorOverflowSubtract1() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('0');
    calc = calc.input('-');
    calc = calc.input('2');
    calc = calc.input('1');
    calc = calc.input('4');
    calc = calc.input('7');
    calc = calc.input('4');
    calc = calc.input('8');
    calc = calc.input('3');
    calc = calc.input('6');
    calc = calc.input('4');
    calc = calc.input('7');
    calc = calc.input('='); // -2147483648 (Min_VALUE)
    assertEquals("-2147483647", calc.getResult());
    calc = calc.input('-');
    calc = calc.input('1');
    calc = calc.input('=');
    assertEquals("-2147483648", calc.getResult());

  }

  @Test
  public void testSimpleCalculatorOverflowMAX() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('1');
    calc = calc.input('4');
    calc = calc.input('7');
    calc = calc.input('4');
    calc = calc.input('8');
    calc = calc.input('3');
    calc = calc.input('6');
    calc = calc.input('4');
    calc = calc.input('7'); // 2,147,483,647 (MAX_VALUE)
    calc = calc.input('+');
    calc = calc.input('1');
    calc = calc.input('=');
    assertEquals("0", calc.getResult()); // expecting overflow -> "0"
  }

  @Test
  public void testSimpleCalculatorOverflowMAXOperand() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('1');
    calc = calc.input('4');
    calc = calc.input('7');
    calc = calc.input('4');
    calc = calc.input('8');
    calc = calc.input('3');
    calc = calc.input('6');
    calc = calc.input('4');
    calc = calc.input('7'); // 2,147,483,647 (MAX_VALUE)
    calc = calc.input('=');
    assertEquals("2147483647", calc.getResult());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSimpleCalculatorOverflowMAXOperand1() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('2');
    calc = calc.input('1');
    calc = calc.input('4');
    calc = calc.input('7');
    calc = calc.input('4');
    calc = calc.input('8');
    calc = calc.input('3');
    calc = calc.input('6');
    calc = calc.input('4');
    calc = calc.input('8');
    calc.input('=');
  }

  @Test (expected = IllegalArgumentException.class)
  public void testEquals() {
    Calculator calc = new SimpleCalculator();
    calc.input('=');
  }

  @Test
  public void testEquals1() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('0');
    calc.input('=');
    assertEquals("0", calc.getResult());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testEquals2() {
    Calculator calc = new SimpleCalculator();
    assertEquals("", calc.getResult());
    calc.input('=');
  }

  @Test
  public void testEquals3() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('0');
    calc = calc.input('0');
    calc = calc.input('2');
    assertEquals("000", calc.getResult());
    calc.input('=');
    assertEquals("0", calc.getResult());
  }

  @Test
  public void testEqualsNeutralMTStr() {
    Calculator calc = new SimpleCalculator();
    assertEquals("", calc.getResult());
    calc = calc.input('0');
    calc = calc.input('=');
    assertEquals("0", calc.getResult());
    calc = calc.input('=');
    assertEquals("0", calc.getResult());
  }

  @Test
  public void testNeg() {
    Calculator calc = new SimpleCalculator();
    assertEquals("", calc.getResult());
    calc = calc.input('0');
    calc = calc.input('-');
    calc = calc.input('0');
    calc = calc.input('2');
    calc = calc.input('=');
    assertEquals("-2", calc.getResult());
  }

  @Test
  public void testovfl() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('1');
    calc = calc.input('0');
    calc = calc.input('0');
    calc = calc.input('0');
    calc = calc.input('0');
    calc = calc.input('0');
    calc = calc.input('0');
    calc = calc.input('0');
    calc = calc.input('0');
    calc = calc.input('0');
    calc = calc.input('+');
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('4');
    calc = calc.input('7');
    calc = calc.input('4');
    calc = calc.input('8');
    calc = calc.input('3');
    calc = calc.input('6');
    calc = calc.input('4');
    calc = calc.input('9');
    calc = calc.input('=');
    assertEquals("0", calc.getResult());
  }
}
