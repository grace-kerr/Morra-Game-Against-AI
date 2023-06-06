package nz.ac.auckland.se281.datastructures;

/**
 * Thrown to indicate that an invalid position was encountered. This exception is typically used in
 * data structures to signal an error when accessing or manipulating elements at an invalid
 * position.
 */
public class InvalidPositionException extends Exception {

  private static final long serialVersionUID = 1L;

  /** Constructs a new InvalidPositionException with no message. */
  public InvalidPositionException() {}

  /**
   * Constructs a new InvalidPositionException with a message.
   *
   * <p>* @param message the message which will let the user know what went wrong.
   */
  public InvalidPositionException(String message) {
    super(message);
  }
}
