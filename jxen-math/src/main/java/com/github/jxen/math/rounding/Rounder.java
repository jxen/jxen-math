package com.github.jxen.math.rounding;

import com.github.jxen.math.common.Adapters;

/**
 * {@code Rounder} class is responsible for values rounding.
 * The rounding can be done not only for the given value itself, but also for some fraction of the value.
 *
 * @author Denis Murashev
 *
 * @since Math 0.3
 */
public class Rounder {

	private final RoundingRule rule;
	private final Number ratio;

	/**
	 * @param ratio ratio
	 * @param rule  rule
	 */
	public Rounder(RoundingRule rule, Number ratio) {
		this.rule = rule;
		this.ratio = ratio;
	}

	/**
	 * @param rule  rule
	 */
	public Rounder(RoundingRule rule) {
		this(rule, 1);
	}

	/**
	 * @param value value
	 * @return rounded value
	 */
	public Number round(Number value) {
		Number min = rule.round(Adapters.lookup(value).div(ratio));
		long n = Math.round(Adapters.lookup(value).div(min).doubleValue());
		return Adapters.lookup(min).multiply(n);
	}
}
