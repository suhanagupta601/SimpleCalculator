import org.junit.Test;

import calculator.Calculator;
import calculator.SimpleCalculator;
import calculator.SmartCalculator;
import static org.junit.Assert.assertEquals;

/**
 * represents a smart calculator that handles consecutive inputs
 * by a user, accumulating each operation and operand with
 * restrictions.
 */

public class SmartCalculatorTest {

  @Test
  public void testCase5Simple() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('5');
    calc = calc.input('5');
    calc = calc.input('-');
    calc = calc.input('5');
    calc = calc.input('+');
    calc = calc.input('5');
    calc = calc.input('=');
    assertEquals("55", calc.getResult());
  }

  @Test
  public void testCase5Simple1() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('5');
    calc = calc.input('*');
    calc = calc.input('5');
    calc = calc.input('+');
    calc = calc.input('5');
    calc = calc.input('=');
    assertEquals("30", calc.getResult());
  }

  @Test
  public void testCase5Simple2() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('5');
    calc = calc.input('*');
    calc = calc.input('5');
    calc = calc.input('=');
    assertEquals("25", calc.getResult());
  }

  @Test
  public void testCase5Simple3() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('5');
    calc = calc.input('+');
    calc = calc.input('5');
    calc = calc.input('=');
    assertEquals("10", calc.getResult());
  }

  @Test
  public void testCase5Simple4() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('5');
    calc = calc.input('-');
    calc = calc.input('5');
    calc = calc.input('=');
    assertEquals("0", calc.getResult());
  }


  //case5
  @Test
  public void testCase5MulOverflow() {
    Calculator calc = new SmartCalculator();
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
    calc = calc.input('5'); // overflows (if 2 operands result in an overflow, then = 0
    calc = calc.input('*');
    calc = calc.input('1');
    calc = calc.input('0');
    calc = calc.input('=');
    assertEquals("0", calc.getResult()); // 0 * 10
  }

  @Test
  public void testCase5AddOverflow() {
    Calculator calc = new SmartCalculator();
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
    calc = calc.input('5'); // overflows (if 2 operands result in an overflow, then = 0
    calc = calc.input('+');
    calc = calc.input('1');
    calc = calc.input('0');
    calc = calc.input('=');
    assertEquals("10", calc.getResult()); // 0 + 10
  }

  // case4 - begins with an operator
  @Test
  public void testCase4Zero() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('+');
    calc = calc.input('0');
    assertEquals("0", calc.getResult());
    calc = calc.input('=');
    assertEquals("0", calc.getResult());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testCase4Zero1() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('+');
    calc = calc.input('-'); //ignores he +, therefore considers - as the first things
    calc = calc.input('0');
    //    assertEquals("-0", calc.getResult());
    //    calc = calc.input('=');
    //    assertEquals("0", calc.getResult());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testCase4Zero2() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('+');
    calc = calc.input('*'); //ignores he +, therefore considers * as the first things
    calc = calc.input('3');
  }

  // case 3 - consecutive operators
  @Test
  public void testCase3Add() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('3');
    calc = calc.input('2');
    calc = calc.input('+');
    calc = calc.input('-');
    calc = calc.input('2');
    assertEquals("32-2", calc.getResult()); // + sign does not show up
    calc = calc.input('=');
    assertEquals("30", calc.getResult());
  }

  @Test
  public void testCase3Sub1() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('3');
    calc = calc.input('2');
    calc = calc.input('-');
    calc = calc.input('+');
    calc = calc.input('2');
    assertEquals("32+2", calc.getResult()); // + sign does not show up
    calc = calc.input('=');
    assertEquals("34", calc.getResult());
  }

  @Test
  public void testCase3SameAdd() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('3');
    calc = calc.input('2');
    calc = calc.input('+');
    calc = calc.input('+');
    calc = calc.input('2');
    assertEquals("32+2", calc.getResult()); // + sign does not show up
    calc = calc.input('=');
    assertEquals("34", calc.getResult());
  }

  @Test
  public void testCase3SameSub() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('3');
    calc = calc.input('2');
    calc = calc.input('-');
    calc = calc.input('-');
    calc = calc.input('2');
    assertEquals("32-2", calc.getResult()); // + sign does not show up
    calc = calc.input('=');
    assertEquals("30", calc.getResult());
  }

  @Test
  public void testCase3SameMul() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('3');
    calc = calc.input('2');
    calc = calc.input('*');
    calc = calc.input('*');
    calc = calc.input('2');
    assertEquals("32*2", calc.getResult()); // + sign does not show up
    calc = calc.input('=');
    assertEquals("64", calc.getResult());
  }

  @Test
  public void testCase3SameMul1() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('3');
    calc = calc.input('2');
    calc = calc.input('-');
    calc = calc.input('*');
    calc = calc.input('2');
    assertEquals("32*2", calc.getResult()); // + sign does not show up
    calc = calc.input('=');
    assertEquals("64", calc.getResult());
  }

  // case2 - slipping second operand
  @Test (expected = IllegalArgumentException.class)
  public void testZeros() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('=');
    calc = calc.input('0');
    // assertEquals("0", calc.getResult());
  }

  @Test
  public void testZeros1() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('0');
    calc = calc.input('+');
    calc = calc.input('0');
    calc = calc.input('=');
    assertEquals("0", calc.getResult());
    calc = calc.input('=');
    calc = calc.input('=');
    assertEquals("0", calc.getResult());
  }

  @Test
  public void testCase2Add() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('3');
    calc = calc.input('0');
    calc = calc.input('+');
    calc = calc.input('=');
    assertEquals("60", calc.getResult());
    calc = calc.input('=');
    assertEquals("90", calc.getResult());
    calc = calc.input('=');
    assertEquals("120", calc.getResult());
  }

  @Test
  public void testCase2Mul() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('3');
    calc = calc.input('0');
    calc = calc.input('*');
    calc = calc.input('=');
    assertEquals("900", calc.getResult());
    calc = calc.input('=');
    assertEquals("27000", calc.getResult());
    calc = calc.input('=');
    assertEquals("810000", calc.getResult());

  }

  @Test
  public void testCase2Sub() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('3');
    calc = calc.input('0');
    calc = calc.input('-');
    calc = calc.input('=');
    assertEquals("0", calc.getResult());
    calc = calc.input('=');
    assertEquals("-30", calc.getResult());
    calc = calc.input('=');
    assertEquals("-60", calc.getResult());
  }




  // throwing exceptions
  @Test (expected = IllegalArgumentException.class)
  public void testINVOpt() {
    Calculator calc = new SmartCalculator();
    calc.input('/');
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIAE1() {
    Calculator calc = new SmartCalculator();
    calc.input('-');
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIAE2() {
    Calculator calc = new SmartCalculator();
    calc.input('*');
  }

  @Test
  public void testIAENOT2() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('+');
    assertEquals("", calc.getResult());
    calc = calc.input('2');
    assertEquals("2", calc.getResult());
    calc = calc.input('+');
    calc = calc.input('3');
    assertEquals("2+3", calc.getResult());
    calc = calc.input('=');
    assertEquals("5", calc.getResult());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIAENOTSUBADD() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('+');
    assertEquals("", calc.getResult());
    calc = calc.input('-');
    assertEquals("+-", calc.getResult());
    calc.input('=');
  }

  @Test
  public void testChange1Mul() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('1');
    calc = calc.input('1');
    calc = calc.input('*');
    calc = calc.input('2');
    calc = calc.input('=');
    assertEquals("22", calc.getResult());
    calc = calc.input('=');
    assertEquals("44", calc.getResult());
    calc = calc.input('=');
    assertEquals("88", calc.getResult());
  }

  @Test
  public void testChange1Sub() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('1');
    calc = calc.input('0');
    calc = calc.input('-');
    calc = calc.input('5');
    calc = calc.input('=');
    assertEquals("5", calc.getResult());
    calc = calc.input('=');
    assertEquals("0", calc.getResult());
    calc = calc.input('=');
    assertEquals("-5", calc.getResult());
  }

  // wrong?
  @Test
  public void testChange1PosOverflow() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('1');
    calc = calc.input('+');
    calc = calc.input('2');
    calc = calc.input('1');
    calc = calc.input('4');
    calc = calc.input('7');
    calc = calc.input('4');
    calc = calc.input('8');
    calc = calc.input('3');
    calc = calc.input('6');
    calc = calc.input('4');
    calc = calc.input('6');
    calc = calc.input('=');
    assertEquals("2147483647", calc.getResult());
    calc = calc.input('=');
    assertEquals("2147483647", calc.getResult()); // return same thing if overflows
    // (as in return last result after second =)
  }

  @Test
  public void testChange1NegOverflow() {
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
    calc = calc.input('-');
    calc = calc.input('1');
    calc = calc.input('=');
    assertEquals("-2147483648", calc.getResult());
    calc = calc.input('=');
    assertEquals("-2147483648", calc.getResult()); // return the same thing if result overflows?
  }

  @Test
  public void testSimpleCalculatorOverflowMAX() {
    Calculator calc = new SmartCalculator();
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
    assertEquals("0", calc.getResult());
  }

  @Test
  public void testIgnoreSecondOp() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('3');
    calc = calc.input('2');
    calc = calc.input('+');
    calc = calc.input('-');
    assertEquals("32-", calc.getResult());
  }

  @Test
  public void testIgnoreSecondO1p() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('3');
    calc = calc.input('2');
    calc = calc.input('-');
    calc = calc.input('-');
    assertEquals("32-", calc.getResult());
  }

  @Test
  public void testIgnoreSecondOp2() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('3');
    calc = calc.input('2');
    calc = calc.input('-');
    calc = calc.input('*');
    assertEquals("32*", calc.getResult());
  }

  @Test
  public void testPosOverflowAdd() {
    Calculator calc = new SmartCalculator();
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
    assertEquals("2147483647", calc.getResult());
    calc = calc.input('+');
    calc = calc.input('=');
    assertEquals("0", calc.getResult());
  }

  @Test
  public void testNegOverflowWithOperands() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('0');
    calc = calc.input('-');
    calc = calc.input('1');
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
    assertEquals("-2147483648", calc.getResult());
    calc = calc.input('+');
    calc = calc.input('=');
    assertEquals("0", calc.getResult());
  }

  @Test
  public void testNegOverflowWithOperands1() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('0');
    calc = calc.input('-');
    calc = calc.input('1');
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
    assertEquals("-2147483648", calc.getResult());
    calc = calc.input('-');
    calc = calc.input('=');
    assertEquals("0", calc.getResult());
  }

  @Test
  public void testIgnoreOperatorButExponentiallyGet1() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('+');
    calc = calc.input('1');
    calc = calc.input('=');
    assertEquals("1", calc.getResult());
    calc = calc.input('=');
    assertEquals("1", calc.getResult());
  }

  // TESTS THAT ARE PASSING
  @Test
  public void testNewOverflow() {
    Calculator calc = new SmartCalculator();
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
    calc = calc.input('5'); // overflows (if 2 operands result in an overflow, then = 0
    calc = calc.input('-');
    calc = calc.input('1');
    calc = calc.input('0');
    calc = calc.input('=');
    assertEquals("-10", calc.getResult());
  }

  @Test
  public void testPlus() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('+');
    assertEquals("", calc.getResult());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testPlusEqual() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('+');
    calc = calc.input('='); // ignores the + and goes straight to = as the first input
    assertEquals("0", calc.getResult());
  }

  // operator then operand test
  @Test
  public void testOperatorOperand() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('+');
    assertEquals("", calc.getResult());
    calc = calc.input('2');
    calc = calc.input('-');
    calc = calc.input('1');
    assertEquals("2-1", calc.getResult());
    calc = calc.input('=');
    assertEquals("1", calc.getResult());
  }

  @Test
  public void testOperatorOperand1() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('+');
    assertEquals("", calc.getResult());
    calc = calc.input('2');
    assertEquals("2", calc.getResult());
    calc = calc.input('=');
    assertEquals("2", calc.getResult());
  }

  // 3 2 + 2 4 = + 1 0 =
  @Test
  public void testOperatorOperandAfter() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('3');
    calc = calc.input('2');
    calc = calc.input('+');
    calc = calc.input('2');
    calc = calc.input('4');
    calc = calc.input('=');
    calc = calc.input('+');
    calc = calc.input('1');
    calc = calc.input('0');
    calc = calc.input('=');
    assertEquals("66", calc.getResult());
  }

  @Test
  public void testMissingSecondOperand() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('1');
    calc = calc.input('+');
    calc = calc.input('=');
    assertEquals("2", calc.getResult());
  }

  @Test
  public void testMultipleEquals() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('1');
    calc = calc.input('+');
    calc = calc.input('2');
    calc = calc.input('=');
    assertEquals("3", calc.getResult());
    calc = calc.input('='); // 3+2
    assertEquals("5", calc.getResult());
  }

  // multiple equal signs
  @Test
  public void MultipleEqualsAfterThreeValidInputs() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('0');
    calc = calc.input('+');

    calc = calc.input('1');
    calc = calc.input('2');
    calc = calc.input('3');
    calc = calc.input('8');
    calc = calc.input('+');
    calc = calc.input('1');
    calc = calc.input('9');
    calc = calc.input('4');
    calc = calc.input('9');
    calc = calc.input('=');
    assertEquals("3187", calc.getResult());
    calc = calc.input('=');
    assertEquals("5136", calc.getResult()); // 3187 + 1949
    calc = calc.input('=');
    assertEquals("7085", calc.getResult()); // 5136 + 1949
  }

  // test exponentially (ie. missing second operand)
  @Test
  public void testExponentialOneOperand() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('3');
    calc = calc.input('2');
    calc = calc.input('+');
    calc = calc.input('=');
    assertEquals("64", calc.getResult());
  }

  @Test
  public void testExponentialOneOperand1() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('3');
    calc = calc.input('2');
    calc = calc.input('+');
    calc = calc.input('=');
    calc = calc.input('=');
    assertEquals("96", calc.getResult());
  }

  @Test
  public void testExponentialOneOperand2() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('2');
    calc = calc.input('*');
    calc = calc.input('=');
    assertEquals("4", calc.getResult());
    calc = calc.input('=');
    assertEquals("8", calc.getResult());
  }

  // where the first input is +
  @Test
  public void testOpFirstAdd() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('+');
    calc = calc.input('1');
    calc = calc.input('+');
    calc = calc.input('2');
    assertEquals("1+2", calc.getResult());
    calc = calc.input('=');
    assertEquals("3", calc.getResult());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testOpFirstSub() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('-');
    calc = calc.input('1');
    calc = calc.input('+');
    calc.input('2');
  }

  @Test (expected = IllegalArgumentException.class)
  public void testOpFirstMul() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('*');
    calc = calc.input('1');
    calc = calc.input('+');
    calc.input('2');
  }

  @Test
  public void testIgnoreFirstOP() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('1');
    calc = calc.input('+');
    calc = calc.input('-');
    calc = calc.input('2');
    calc = calc.input('=');
    assertEquals("-1", calc.getResult());

  }

  // overlapping tests
  // - testing C
  @Test
  public void testValidAfterAddC() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('1');
    calc = calc.input('+');
    assertEquals("1+", calc.getResult());
    calc = calc.input('2');
    assertEquals("1+2", calc.getResult());
    calc = calc.input('=');
    assertEquals("3", calc.getResult());
    calc = calc.input('C');
    assertEquals("", calc.getResult());
  }

  @Test
  public void testValidAfterSubtrC() {
    Calculator calc = new SmartCalculator();
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
    Calculator calc = new SmartCalculator();
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

  @Test
  public void testOnlyAddC() {
    Calculator calc = new SmartCalculator();
    calc = calc.input('+');
    assertEquals("", calc.getResult());
    calc = calc.input('C');
    assertEquals("", calc.getResult());
  }
}
