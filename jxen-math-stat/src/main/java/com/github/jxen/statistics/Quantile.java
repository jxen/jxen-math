package com.github.jxen.statistics;

import com.github.jxen.math.common.Adapters;
import com.github.jxen.math.common.MathException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * {@code Quantile} class represents percentile statistics for data sample.
 *
 * @author Denis Murashev
 *
 * @since Math Statistics 0.2
 */
public class Quantile implements Sample<Quantile> {

	private static final Comparator<Number> COMPARATOR = Comparator.comparingDouble(Number::doubleValue);

	private final List<Number> values = new ArrayList<>();

	@Override
	public Quantile add(Number value) {
		values.add(value);
		values.sort(COMPARATOR);
		return this;
	}

	@Override
	public Quantile add(Quantile sample) {
		values.addAll(sample.values);
		values.sort(COMPARATOR);
		return this;
	}

	/**
	 * @param values values
	 * @return current quantile
	 */
	public Quantile add(Number... values) {
		for (Number v : values) {
			add(v);
		}
		return this;
	}

	/**
	 * @return data sample size
	 */
	public int size() {
		return values.size();
	}

	/**
	 * @param quantile quantile number to find
	 * @return quantile value
	 */
	public Number quantile(double quantile) {
		if (values.isEmpty()) {
			throw new MathException("Unable to process empty data sample.");
		}
		if (quantile < 0 || quantile > 1) {
			throw new IllegalArgumentException("Illegal quantile: " + quantile + ". It should be between 0 and 1");
		}
		int floor = (int) Math.floor((values.size() - 1) * quantile);
		int ceil = (int) Math.ceil((values.size() - 1) * quantile);
		if (floor == ceil) {
			return values.get(floor);
		}
		Number number = Adapters.lookup(values.get(floor)).plus(values.get(ceil));
		return Adapters.lookup(number).div(2);
	}

	/**
	 * @return median vaues
	 */
	public Number median() {
		final double half = 0.5;
		return quantile(half);
	}

	/**
	 * @param index quartile index
	 * @return quartile value
	 */
	public Number quartile(int index) {
		final double quarter = 0.25;
		return quantile(quarter * index);
	}

	/**
	 * @param index percent index
	 * @return percent value
	 */
	public Number percentile(int index) {
		final double percent = 0.01;
		return quantile(percent * index);
	}
}
