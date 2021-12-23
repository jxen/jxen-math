package com.github.jxen.math.expression;

/**
 * {@code ExpressionException} class is general exception of Math Expression Parser module.
 *
 * @author Denis Murashev
 *
 * @since Math Expression Parser 0.1
 */
public final class ExpressionException extends RuntimeException {

  private static final long serialVersionUID = -8940415667910458702L;

  private final String expression;
  private final int position;
  private final int length;

  /**
   * Initializes with given values.
   *
   * @param message  message
   * @param position position
   * @param length   length
   */
  public ExpressionException(String message, int position, int length) {
    this(message, null, position, length);
  }

  /**
   * Initializes with given values.
   *
   * @param message  message
   * @param position position
   * @param length   length
   * @param cause      cause
   */
  public ExpressionException(String message, int position, int length, Throwable cause) {
    this(message, null, position, length, cause);
  }

  /**
   * Initializes with given values.
   *
   * @param message    message
   * @param expression expression
   * @param position   position
   * @param length     length
   */
  public ExpressionException(String message, String expression, int position, int length) {
    super(message);
    this.expression = expression;
    this.position = position;
    this.length = length;
  }

  /**
   * Initializes with given values.
   *
   * @param message    message
   * @param expression expression
   * @param position   position
   * @param length     length
   * @param cause      cause
   */
  public ExpressionException(String message, String expression, int position, int length, Throwable cause) {
    super(message, cause);
    this.expression = expression;
    this.position = position;
    this.length = length;
  }

  /**
   * Provides expression.
   *
   * @return expression
   */
  public String getExpression() {
    return expression;
  }

  /**
   * Provides position.
   *
   * @return position
   */
  public int getPosition() {
    return position;
  }

  /**
   * Provides length.
   *
   * @return length
   */
  public int getLength() {
    return length;
  }

  /**
   * Provides marker.
   *
   * @return marker
   */
  public String getMarker() {
    final char c = '\0';
    return new String(new char[position]).replace(c, ' ') + new String(new char[length]).replace(c, '^');
  }
}
