package com.github.jxen.math.common;

/**
 * {@code NumberAdapter} interface represents general contract of numeric adapter from general {@link Number}
 * to {@link ArithmeticAware} interface.
 *
 * @author Denis Murashev
 * @since Measure 0.1
 */
@FunctionalInterface
public interface NumberAdapter {

	/**
	 * Provides adapter for given {@link Number}.
	 *
	 * @param value value to be adapted
	 * @return adapter able to be used in arithmetic operations
	 */
	ArithmeticAware<?> adapt(Number value);
}
