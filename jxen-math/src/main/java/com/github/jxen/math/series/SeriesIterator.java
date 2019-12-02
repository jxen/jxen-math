package com.github.jxen.math.series;

/**
 * {@code SeriesIterator} interface describes iterator over numeric series.
 *
 * @author Denis Murashev
 *
 * @since Math 0.3
 */
public interface SeriesIterator {

	/**
	 * @return current item
	 */
	Number current();

	/**
	 * @return next item
	 */
	Number next();

	/**
	 * @return previous item
	 */
	Number previous();
}
