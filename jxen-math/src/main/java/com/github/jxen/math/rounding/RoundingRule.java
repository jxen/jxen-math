package com.github.jxen.math.rounding;

/**
 * {@code RoundingRule} interface defines contract of rounding rule.
 *
 * @author Denis Murashev
 *
 * @since Math 0.3
 */
public interface RoundingRule {

	/**
	 * @param value value
	 * @return rounded value
	 */
	Number round(Number value);
}
