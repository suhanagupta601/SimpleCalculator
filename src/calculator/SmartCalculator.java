package calculator;

/**
 * represents a smart calculator for "just in time" computations
 * with limitations.
 */
public class SmartCalculator extends ACalculator {
  private String result;
  private int soFar;
  private Character prevSymbol;
  private int num1;
  private PrevType prevType;
  private int prevOperand;

  /**
   * initial state of the smart calculator with all fields initialized.
   */
  public SmartCalculator() {
    super();
//    this.result = "";
//    this.soFar = 0;
//    this.prevSymbol = ' ';
//    this.num1 = 0;
//    this.prevType = PrevType.EMPTY;
    this.prevOperand = 0;
  }

  private SmartCalculator(String result, int soFar, Character prevSymbol,
                          int num1, PrevType prevType, int prevOperand) {
//
    super();
    this.prevOperand = prevOperand;
  }

  private Calculator updateSoFar(Character charNum) {
    if (Character.isDigit(charNum)) {
      int digit = Character.getNumericValue(charNum);
      if (digit < 0
              || digit > 9) {
        throw new IllegalArgumentException("invalid digit");
      }
      if (soFar >= 0) {
        if (soFar > Integer.MAX_VALUE / 10
                || (soFar == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
          throw new IllegalArgumentException("Potential overflow: " + soFar);
        }
        int currentSoFar = soFar * 10 + digit;
        String currentResult = this.result + charNum;
        return new SmartCalculator(currentResult, currentSoFar,
                prevSymbol,
                num1, PrevType.DIGIT,
                currentSoFar);
      } else {
        if (soFar < Integer.MIN_VALUE / 10
                || (soFar == Integer.MIN_VALUE / 10 && digit > -(Integer.MIN_VALUE % 10))) {
          throw new IllegalArgumentException("Potential underflow: " + soFar);
        }
        int currentSoFar = soFar * 10 - digit;
        String currentResult = this.result + charNum;
        return new SmartCalculator(currentResult,
                currentSoFar, prevSymbol, num1,
                PrevType.DIGIT, currentSoFar);
      }
    }
    throw new IllegalArgumentException("Invalid char: " + charNum);
  }

  private boolean resultBounds(int num1, int num2, Character operator) {
    switch (operator) {
      case '+':
        try {
          Math.addExact(num1, num2);
        } catch (ArithmeticException e) {
          return true;
        }
        return false;
      case '-':
        try {
          Math.subtractExact(num1, num2);
        } catch (ArithmeticException e) {
          return true;
        }
        return false;
      case '*':
        try {
          Math.multiplyExact(num1, num2);
        } catch (ArithmeticException e) {
          return true;
        }
        return false;
      default:
        throw new IllegalArgumentException("invalid operator");
    }
  }

  /**
   * represents how to handle various inputs that a user can make.
   * it also checks the bounds and limitations of improper sequences
   * and/or inputs.
   * @param operandOrOperator Character that a user inputs
   * @return a Calculator with corresponding fields
   */
  @Override
  public Calculator input(Character operandOrOperator) {
    int singleNumberResult = 0;

    // Clear command resets the calculator.
    if (operandOrOperator == 'C') {
      return new SmartCalculator();
    }

    if (prevType == PrevType.EMPTY
            && (operandOrOperator != 'C'
            && !Character.isDigit(operandOrOperator))) {
      if (operandOrOperator == '+') {

        // ignore the first plus (as in do not show it in the result -
        // as a result of the autograder)
        return new SmartCalculator("", 0, ' ', 0, PrevType.EMPTY, 0);
      } else {
        throw new IllegalArgumentException("first input must be a digit, 'C', or '+'");
      }
    }

    if (Character.isDigit(operandOrOperator)) {
      if (prevType == PrevType.EQUAL) {
        return new SmartCalculator(
                String.valueOf(operandOrOperator),
                Character.getNumericValue(operandOrOperator),
                ' ',
                0,
                PrevType.DIGIT,
                Character.getNumericValue(operandOrOperator));
      }
      return updateSoFar(operandOrOperator);
    }

    // =+, =-, =* are all valid here
    if ((operandOrOperator == '+'
            || operandOrOperator == '-'
            || operandOrOperator == '*')
            && (prevType == PrevType.EQUAL)) {
      String currentResult = this.result + operandOrOperator;
      return new SmartCalculator(
              currentResult,
              0,
              operandOrOperator,
              num1,
              PrevType.OPERATOR,
              0);
    }

    // if input is operator and prev ≠ Equals
    if ((operandOrOperator == '+'
            || operandOrOperator == '-'
            || operandOrOperator == '*')
            && (prevType != PrevType.EQUAL)) {
      if (prevType == PrevType.OPERATOR) {

        ///  IGNORES THE FIRST OPERATOR IF 2 OPERATORS ARE INPUTTED CONSECUTIVELY
        String currentResult =
                this.result.substring(0, this.result.length() - 1) + operandOrOperator;

        return new SmartCalculator(
                currentResult,
                0,
                operandOrOperator,
                this.num1,
                PrevType.OPERATOR,
                0);
      }
      else if (prevType == PrevType.DIGIT) {

        ///  RESULT ACCUMULATES (KEEPS OPERATING ON ITSELF AGAIN) exponential stuff
        int applyOperandAgain = 0;
        if (prevSymbol == '+') {
          if (resultBounds(num1, soFar, '+')) {
            applyOperandAgain = 0;
          } else {
            applyOperandAgain = num1 + soFar;
          }
        } else if (prevSymbol == '-') {
          if (resultBounds(num1, soFar, '-')) {
            applyOperandAgain = 0;
          } else {
            applyOperandAgain = num1 - soFar;
          }
        } else if (prevSymbol == '*') {
          if (resultBounds(num1, soFar, '*')) {
            applyOperandAgain = 0;
          } else {
            applyOperandAgain = num1 * soFar;
          }
        } else {
          // No prior operator—simply take the current number.
          applyOperandAgain = soFar;
        }
        String currentResult = String.valueOf(applyOperandAgain) + operandOrOperator;
        return new SmartCalculator(
                currentResult,
                0,
                operandOrOperator,
                applyOperandAgain,
                PrevType.OPERATOR,
                0);
      }
    }

    /// = is pressed repeatedly
    if (prevType == PrevType.EQUAL && operandOrOperator == '=') {

      // if invalid operator, then just return itself
      if (prevSymbol != '+' && prevSymbol != '-' && prevSymbol != '*') {
        return new SmartCalculator(String.valueOf(num1), 0,
                prevSymbol, num1,
                PrevType.EQUAL, prevOperand);
      }
      int repeatedResult;
      if (prevSymbol == '+') {
        if (resultBounds(num1, prevOperand, '+')) {
          repeatedResult = 0;
        } else {
          repeatedResult = num1 + prevOperand;
        }
      } else if (prevSymbol == '-') {
        if (resultBounds(num1, prevOperand, '-')) {
          repeatedResult = 0;
        } else {
          repeatedResult = num1 - prevOperand;
        }
      } else if (prevSymbol == '*') {
        if (resultBounds(num1, prevOperand, '*')) {
          repeatedResult = 0;
        } else {
          repeatedResult = num1 * prevOperand;
        }
      } else {
        throw new IllegalArgumentException("not a valid operator");
      }
      return new SmartCalculator(
              String.valueOf(repeatedResult),
              0,
              prevSymbol,
              repeatedResult,
              PrevType.EQUAL,
              prevOperand);
    }

    // PENDING OPERATIONS (like normal from simpleCalc)
    if (operandOrOperator == '=') {
      // = pressed right after operator
      if (prevType == PrevType.OPERATOR) {
        soFar = num1;
      }
      if (prevSymbol == '+') {
        if (resultBounds(num1, soFar, '+')) {
          return new SmartCalculator("0", 0, prevSymbol, 0, PrevType.EQUAL, soFar);
        } else {
          singleNumberResult = num1 + soFar;
        }
      } else if (prevSymbol == '-') {
        if (resultBounds(num1, soFar, '-')) {
          return new SmartCalculator("0", 0, prevSymbol, 0, PrevType.EQUAL, soFar);
        } else {
          singleNumberResult = Math.subtractExact(num1, soFar);
        }
      } else if (prevSymbol == '*') {
        if (resultBounds(num1, soFar, '*')) {
          return new SmartCalculator("0", 0, prevSymbol, 0, PrevType.EQUAL, soFar);
        } else {
          singleNumberResult = num1 * soFar;
        }
      } else {
        singleNumberResult = soFar;
      }
      return new SmartCalculator(
              String.valueOf(singleNumberResult),
              0,
              prevSymbol,
              singleNumberResult,
              PrevType.EQUAL,
              soFar);
    }
    throw new IllegalArgumentException("overall invalid input char");
  }

//  /**
//   * represents the result of a given sequence in a calculator object.
//   * @return a string signifying the result of the sequence.
//   */
//  @Override
//  public String getResult() {
//    return this.result;
//  }
}
