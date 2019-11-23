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
	 * @return absolute value
	 */
	T abs();

	/**
	 * @return negated value
	 */
	T negate();

	/**
	 * @return reciprocal value
	 */
	T reciprocal();

	/**
	 * @param value argument
	 * @return sum
	 */
	T plus(double value);

	/**
	 * @param value argument
	 * @return sum
	 */
	T plus(long value);

	/**
	 * @param value argument
	 * @return sum
	 */
	default T plus(Number value) {
		return plus(value.doubleValue());
	}

	/**
	 * @param value argument
	 * @return difference
	 */
	T minus(double value);

	/**
	 * @param value argument
	 * @return difference
	 */
	T minus(long value);

	/**
	 * @param value argument
	 * @return difference
	 */
	default T minus(Number value) {
		return minus(value.doubleValue());
	}

	/**
	 * @param value argument
	 * @return product
	 */
	T multiply(double value);

	/**
	 * @param value argument
	 * @return product
	 */
	T multiply(long value);

	/**
	 * @param value argument
	 * @return product
	 */
	default T multiply(Number value) {
		return multiply(value.doubleValue());
	}

	/**
	 * @param value argument
	 * @return ratio
	 */
	T div(double value);

	/**
	 * @param value argument
	 * @return ratio
	 */
	T div(long value);

	/**
	 * @param value argument
	 * @return ratio
	 */
	default T div(Number value) {
		return div(value.doubleValue());
	}

	/**
	 * @param value argument
	 * @return modulo
	 */
	T mod(double value);

	/**
	 * @param value argument
	 * @return modulo
	 */
	T mod(long value);

	/**
	 * @param value argument
	 * @return modulo
	 */
	default T mod(Number value) {
		return mod(value.doubleValue());
	}

	/**
	 * @param value argument
	 * @return value to the given power
	 */
	T power(int value);

	/**
	 * @return rounded value
	 */
	T round();

	/**
	 * @return floor value
	 */
	T floor();

	/**
	 * @return ceiling value
	 */
	T ceil();

	/**
	 * @return {@code true} if value is integral
	 */
	boolean isIntegral();
}
