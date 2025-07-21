package calculator;

/**
 * represents a simple calculator that handles consecutive inputs
 * by a user, accumulating each operation and operand with
 * restrictions.
 */
public class SimpleCalculator extends ACalculator {
//  private String result;
//  private int soFar;
//  private Character prevSymbol;
//  private int num1;
//  private PrevType prevType; // enum of all the types the user can input

  /**
   * this constructor represents the initial state of a new
   * calculator object with initialized fields when certain
   * occurrences (like input is 'C' or you want to start a
   * calculator happens).
   */
  public SimpleCalculator() {
    super();
//    this.result = "";
//    this.soFar = 0;
//    this.prevSymbol = ' ';
//    this.num1 = 0;
//    this.prevType = PrevType.EMPTY;
  }

  private SimpleCalculator(
          String result, int soFar, Character prevSymbol, int num1, PrevType prevType) {
    super(result, soFar, prevSymbol, num1, prevType);
//    this.result = result;
//    this.soFar = soFar;
//    this.prevSymbol = prevSymbol;
//    this.num1 = num1;
//    this.prevType = prevType;
  }

  private Calculator updateSoFar(Character charNum) {
    if (Character.isDigit(charNum)) {
      int digit = Character.getNumericValue(charNum);
      if (digit < 0 || digit > 9) {
        throw new IllegalArgumentException("Invalid digit");
      }

      if (soFar >= 0) {
        if (soFar > Integer.MAX_VALUE / 10
                || (soFar == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
          throw new IllegalArgumentException("Potential overflow: " + soFar);
        }
        int currentSoFar = soFar * 10 + digit;
        String currentResult = this.result + charNum;
        return new SimpleCalculator(currentResult, currentSoFar, prevSymbol, num1, PrevType.DIGIT);
      } else if (soFar < 0) {
        if (soFar < Integer.MIN_VALUE / 10
                || (soFar == Integer.MIN_VALUE / 10 && digit > -(Integer.MIN_VALUE % 10))) {
          throw new IllegalArgumentException("Potential underflow: " + soFar);
        }
        int currentSoFar = soFar * 10 - digit;
        String currentResult = this.result + charNum;
        return new SimpleCalculator(currentResult, currentSoFar, prevSymbol, num1, PrevType.DIGIT);
      }
    }
    throw new IllegalArgumentException("Invalid character: " + charNum);
  }


  // checking if the final result overflows num1 = 20, sofar = 10
  private boolean resultBounds(int num1, int num2, Character operator) {
    switch (operator) {
      case '+':
        // true = outofbounds
        try {
          Math.addExact(num1, num2);
        } catch (ArithmeticException e) {
          return true;
        }
        return false;

      // true = outofbounds
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
        throw new IllegalArgumentException("result overflows");
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

    if (operandOrOperator == 'C') {
      return new SimpleCalculator();
    }

    if (prevType == PrevType.EMPTY
            && (operandOrOperator != 'C'
            && !Character.isDigit(operandOrOperator))) {
      throw new IllegalArgumentException("first input must be C or digit");
    }

    if (Character.isDigit(operandOrOperator)) {
      if (prevType == PrevType.EQUAL) {
        return new SimpleCalculator(
                String.valueOf(operandOrOperator),
                Character.getNumericValue(operandOrOperator),
                ' ',
                0,
                PrevType.DIGIT);
      }
      return this.updateSoFar(operandOrOperator);
    }

    if ((operandOrOperator == '+'
            || operandOrOperator == '-'
            || operandOrOperator == '*')
            && (prevType != PrevType.EQUAL)) {
      if (prevType == PrevType.OPERATOR) {
        throw new IllegalArgumentException(
                "cannot have 2 operators in a row, must follow with an =");
      }
      String currentResult = this.result + operandOrOperator;
      return new SimpleCalculator(
              currentResult, 0,
              operandOrOperator, this.soFar,
              PrevType.OPERATOR);
    }

    if (prevType == PrevType.EQUAL) {
      if (operandOrOperator == '=') {
        return new SimpleCalculator(
                this.result,
                0,
                '=',
                this.num1,
                PrevType.EQUAL);
      } else if (operandOrOperator == '+'
              || operandOrOperator == '-'
              || operandOrOperator == '*') {
        String newResult = this.result + operandOrOperator;
        return new SimpleCalculator(
                newResult,
                0,
                operandOrOperator,
                this.num1,
                PrevType.OPERATOR);
      }
    }
    if (operandOrOperator == '=') {
      if (prevType == PrevType.OPERATOR) {
        throw new IllegalArgumentException("Need second operand to apply operator to");
      }

      if (prevSymbol == '+') {
        if (resultBounds(num1, soFar, '+')) {
          return new SimpleCalculator("0", 0, '=', 0, PrevType.EQUAL);
        } else {
          singleNumberResult = num1 + soFar;
        }
      } else if (prevSymbol == '-') {
        if (resultBounds(num1, soFar, '-')) {
          return new SimpleCalculator("0", 0, '=', 0, PrevType.EQUAL);
        } else {
          //singleNumberResult = num1 - soFar;
          singleNumberResult = Math.subtractExact(num1, soFar);

        }

      } else if (prevSymbol == '*') {
        if (resultBounds(num1, soFar, '*')) {
          return new SimpleCalculator("0", 0, '=', 0, PrevType.EQUAL);
        } else {
          singleNumberResult = num1 * soFar;
        }
      } else {
        singleNumberResult = soFar;
      }
      return new SimpleCalculator(
              String.valueOf(singleNumberResult),
              0,
              '=',
              singleNumberResult,
              PrevType.EQUAL);
    }
    throw new IllegalArgumentException("Overall invalid input char");
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
