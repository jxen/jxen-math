package com.github.jxen.math.series;

/**
 * {@code Series} interface represents numeric series.
 *
 * @author Denis Murashev
 *
 * @since Math 0.3
 */
public interface Series {

  /**
   * Provides iterator.
   *
   * @param start start value
   * @return iterator
   */
  SeriesIterator iterator(Number start);

  /**
   * Provides default iterator.
   *
   * @return default iterator
   */
  default SeriesIterator iterator() {
    return iterator(1);
  }
}
