package com.github.jxen.math.common;

/**
 * {@code MathException} class is general exception for Math related project.
 *
 * @author Denis Murashev
 * @since Math 0.1
 */
public class MathException extends RuntimeException {

  private static final long serialVersionUID = -5517734766962936031L;

  /**
   * Initializes with given value.
   *
   * @param message message
   */
  public MathException(String message) {
    super(message);
  }

  /**
   * Initializes with given values.
   *
   * @param message message
   * @param cause   cause
   */
  public MathException(String message, Throwable cause) {
    super(message, cause);
  }
}
