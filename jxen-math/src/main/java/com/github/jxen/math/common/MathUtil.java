package com.github.jxen.math.common;

import java.util.Arrays;

/**
 * {@code MathUtil} class provides several utility methods.
 *
 * @author Denis Murashev
 * @since Math 0.1
 */
public final class MathUtil {

	private static final long RATIONAL_PRECISION = 1000;

	private MathUtil() {
	}

	/**
	 * Calculates roots of square equation.
	 *
	 * @param a a
	 * @param b b
	 * @param c c
	 * @return roots if exists or empty array otherwise
	 */
	public static double[] roots(double a, double b, double c) {
		double b2 = b / 2;
		double d2 = b2 * b2 - a * c;
		if (d2 < 0) {
			return new double[0];
		}
		double d = Math.sqrt(d2);
		return new double[]{(-b2 - d) / a, (-b2 + d) / a};
	}

	/**
	 * Creates chain fraction from given value.
	 *
	 * @param value     value
	 * @param maxCount  maxCount
	 * @param precision precision (longest denominator)
	 * @return chain fraction as list of {@code long}s
	 */
	public static long[] toFraction(double value, int maxCount, long precision) {
		int sign = (int) Math.signum(value);
		long[] chain = toChainFraction(sign * value, maxCount, precision);
		if (chain.length == 1) {
			return new long[] {sign * chain[0], 1};
		}
		int maxIndex = chain.length - 1;
		long n = 1;
		long d = chain[maxIndex];
		for (int i = maxIndex - 1; i > 0; i--) {
			long tmp = d;
			d = d * chain[i] + n;
			n = tmp;
		}
		return new long[] {sign * (chain[0] * d + n), d};
	}

	/**
	 * Creates chain fraction from given value.
	 *
	 * @param value     value
	 * @param precision precision (longest denominator)
	 * @return chain fraction as list of {@code long}s
	 */
	public static long[] toFraction(double value, long precision) {
		final int maxCount = 32;
		return toFraction(value, maxCount, precision);
	}

	/**
	 * Creates chain fraction from given value.
	 *
	 * @param value     value
	 * @return chain fraction as list of {@code long}s
	 */
	public static long[] toFraction(double value) {
		return toFraction(value, RATIONAL_PRECISION);
	}

	/**
	 * Creates chain fraction from given value.
	 *
	 * @param value     value
	 * @param maxCount  maxCount
	 * @param precision precision (longest denominator)
	 * @return chain fraction as list of {@code long}s
	 */
	private static long[] toChainFraction(double value, int maxCount, long precision) {
		long[] values = new long[maxCount + 1];
		values[0] = (long) value;
		double x = 1.0 / (value - values[0]);
		if (Double.isInfinite(x)) {
			return new long[]{values[0]};
		}
		long max = values[0] == 0 ? 1 : values[0];
		for (int i = 1; i <= maxCount; i++) {
			values[i] = (long) x;
			if (values[i] == 0L || values[i] > precision) {
				return Arrays.copyOf(values, i);
			}
			max *= values[i];
			if (max > precision) {
				return Arrays.copyOf(values, i);
			}
			x = 1.0 / (x - values[i]);
		}
		return values;
	}
}
