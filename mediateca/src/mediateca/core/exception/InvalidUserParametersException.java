package mediateca.core.exception;

/**
 * Class for representing an invalid user's parameters.
 */
public class InvalidUserParametersException extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201901101348L;

  /**
   * Default constructor
   */
  public InvalidUserParametersException() {
    // do nothing
  }

  /**
   * @param description
   */
  public InvalidUserParametersException(String description) {
    super(description);
  }

  /**
   * @param cause
   */
  public InvalidUserParametersException(Exception cause) {
    super(cause);
  }

}
