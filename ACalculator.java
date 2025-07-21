package calculator;

/**
 * represents an abstracted class in which many overlaps, such as fields and methods
 * are represented/implemented here.
 */
public abstract class ACalculator implements Calculator {
  protected String result;
  protected int soFar;
  protected Character prevSymbol;
  protected int num1;
  protected PrevType prevType;

  /**
   * represents a Calculator in which it takes inputs to keep track of what has
   * been inputted in the sequence.
   * @param result the result of a sequence or what is inputted in the sequence so far.
   * @param soFar what the user has been typing (accumulating the current inputs before an operator.
   * @param prevSymbol represents the previous operator or equal sign used.
   * @param num1 represents the first operand.
   * @param prevType represents if the previous input was a digit, equal, operator, or empty.
   */
  public ACalculator(
          String result, int soFar, Character prevSymbol, int num1, PrevType prevType) {
    this.result = result;
    this.soFar = soFar;
    this.prevSymbol = prevSymbol;
    this.num1 = num1;
    this.prevType = prevType;
  }

  /**
   * represents a standard constructor with all the fields initialized
   * for the calculator.
   */
  public ACalculator() {
    this.result = "";
    this.soFar = 0;
    this.prevSymbol = ' ';
    this.num1 = 0;
    this.prevType = PrevType.EMPTY;
  }

  /**
   * represents the result of a given sequence in a calculator object.
   * @return a string signifying the result of the sequence.
   */
  @Override
  public String getResult() {
    return this.result;
  }
}
