package calculator;

/**
 * represents the methods any calculator can implement. The purpose of
 * this interface is to define a specification for all calculator types.
 */
public interface Calculator {

  /**
   * represents how to handle various inputs that a user can make. It also checks the
   * bounds and limitations of improper sequences and/or inputs.
   * @param input Character that a user inputs
   * @return a Calculator with corresponding fields
   */
  public Calculator input(Character input);

  /**
   * To get the result after an inputted sequence, whether the result be
   * an error or a numeric result.
   * @return a String as a sequence or a singular  numeric number.
   */
  public String getResult();
}