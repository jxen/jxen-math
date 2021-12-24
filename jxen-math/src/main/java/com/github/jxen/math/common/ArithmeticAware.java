package com.github.jxen.math.common;

/**
 * {@code ArithmeticAware} interface represents general contract of numeric values.
 *
 * @author Denis Murashev
 *
 * @param <T> number type
 *
 * @since Math 0.1
 */
public interface ArithmeticAware<T extends Number> extends Comparable<T> {

  /**
   * Provides absolute value.
   *
   * @return absolute value
   */
  T abs();

  /**
   * Provides negated value.
   *
   * @return negated value
   */
  T negate();

  /**
   * Provides reciprocal value.
   *
   * @return reciprocal value
   */
  T reciprocal();

  /**
   * Adds.
   *
   * @param value argument
   * @return sum
   */
  T plus(double value);

  /**
   * Adds.
   *
   * @param value argument
   * @return sum
   */
  T plus(long value);

  /**
   * Adds.
   *
   * @param value argument
   * @return sum
   */
  default T plus(Number value) {
    return plus(value.doubleValue());
  }

  /**
   * Subtracts.
   *
   * @param value argument
   * @return difference
   */
  T minus(double value);

  /**
   * Subtracts.
   *
   * @param value argument
   * @return difference
   */
  T minus(long value);

  /**
   * Subtracts.
   *
   * @param value argument
   * @return difference
   */
  default T minus(Number value) {
    return minus(value.doubleValue());
  }

  /**
   * Multiplies.
   *
   * @param value argument
   * @return product
   */
  T multiply(double value);

  /**
   * Multiplies.
   *
   * @param value argument
   * @return product
   */
  T multiply(long value);

  /**
   * Multiplies.
   *
   * @param value argument
   * @return product
   */
  default T multiply(Number value) {
    return multiply(value.doubleValue());
  }

  /**
   * Divides.
   *
   * @param value argument
   * @return ratio
   */
  T div(double value);

  /**
   * Divides.
   *
   * @param value argument
   * @return ratio
   */
  T div(long value);

  /**
   * Divides.
   *
   * @param value argument
   * @return ratio
   */
  default T div(Number value) {
    return div(value.doubleValue());
  }

  /**
   * Calculates modulo.
   *
   * @param value argument
   * @return modulo
   */
  T mod(double value);

  /**
   * Calculates modulo.
   *
   * @param value argument
   * @return modulo
   */
  T mod(long value);

  /**
   * Calculates modulo.
   *
   * @param value argument
   * @return modulo
   */
  default T mod(Number value) {
    return mod(value.doubleValue());
  }

  /**
   * Calculates power.
   *
   * @param value argument
   * @return value to the given power
   */
  T power(int value);

  /**
   * Provides rounded value.
   *
   * @return rounded value
   */
  T round();

  /**
   * Provides floor value.
   *
   * @return floor value
   */
  T floor();

  /**
   * Provides ceiling value.
   *
   * @return ceiling value
   */
  T ceil();

  /**
   * Checks if value is integral.
   *
   * @return {@code true} if value is integral
   */
  boolean isIntegral();
}
