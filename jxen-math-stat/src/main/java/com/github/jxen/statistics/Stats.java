package com.github.jxen.statistics;

import static com.github.jxen.statistics.Combinatorics.combination;

/**
 * {@code Stats} class represents statistics for data sample.
 *
 * @author Denis Murashev
 *
 * @since Math Statistics 0.1
 */
public class Stats implements Sample<Stats> {

	private final double[] moments;

	/**
	 * @param size size of moments
	 */
	public Stats(int size) {
		moments = new double[size + 1];
	}

	/**
	 * Creates Stats of default size of 2.
	 */
	public Stats() {
		this(2);
	}

	@Override
	public Stats add(Number value) {
		add(value.doubleValue());
		return this;
	}

	@Override
	public Stats add(Stats sample) {
		if (moments.length != sample.moments.length) {
			throw new UnsupportedOperationException("Cannot combine samples of different sizes");
		}
		for (int i = 0; i < moments.length; i++) {
			moments[i] += sample.moments[i];
		}
		return this;
	}

	/**
	 * Adds value to stats.
	 *
	 * @param value value
	 * @return current stats
	 */
	public Stats add(double value) {
		double x = 1;
		moments[0]++;
		for (int i = 1; i < moments.length; i++) {
			x *= value;
			moments[i] += x;
		}
		return this;
	}

	/**
	 * Adds values to stats.
	 *
	 * @param values values
	 * @return current stats
	 */
	public Stats add(double... values) {
		for (double v : values) {
			add(v);
		}
		return this;
	}

	/**
	 * @return data sample size
	 */
	public int size() {
		return (int) moments[0];
	}

	/**
	 * @return mean value
	 */
	public double mean() {
		return rawMoment(1);
	}

	/**
	 * @return standard deviation
	 */
	public double sd() {
		return Math.sqrt(variance());
	}

	/**
	 * @return unbiased standard deviation
	 */
	public double sdUndiased() {
		return Math.sqrt(varianceUndiased());
	}

	/**
	 * @return variance
	 */
	public double variance() {
		return centralMoment(2);
	}

	/**
	 * @return unbiased variance
	 */
	public double varianceUndiased() {
		return centralMoment(2) * size() / (size() - 1);
	}

	/**
	 * @return skewness
	 */
	public double skewness() {
		final int ordinal = 3;
		return standardMoment(ordinal);
	}

	/**
	 * @return kurtosis
	 */
	public double kurtosis() {
		final int ordinal = 4;
		final int shift = 3;
		return standardMoment(ordinal) - shift;
	}

	/**
	 * @param ordinal ordinal
	 * @return raw moment
	 */
	public double rawMoment(int ordinal) {
		return moments[ordinal] / moments[0];
	}

	/**
	 * @param ordinal ordinal
	 * @return central moment
	 */
	public double centralMoment(int ordinal) {
		int sign = 1;
		double value = 0;
		double mean = rawMoment(1);
		for (int i = 0; i <= ordinal; i++) {
			value += sign * combination(ordinal, i) * rawMoment(ordinal - i) * Math.pow(mean, i);
			sign = -sign;
		}
		return value;
	}

	/**
	 * @param ordinal ordinal
	 * @return standard moment
	 */
	public double standardMoment(int ordinal) {
		return centralMoment(ordinal) / Math.pow(sd(), ordinal);
	}
}
