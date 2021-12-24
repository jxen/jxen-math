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
   * Current item.
   *
   * @return current item
   */
  Number current();

  /**
   * Next item.
   *
   * @return next item
   */
  Number next();

  /**
   * Previous item.
   *
   * @return previous item
   */
  Number previous();
}
