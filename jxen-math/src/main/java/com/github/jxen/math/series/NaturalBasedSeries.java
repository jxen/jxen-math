package com.github.jxen.math.series;

import java.util.stream.IntStream;

/**
 * {@code NaturalBasedSeries} class is {@link Series} implementation based on array of natural integer values.
 *
 * @author Denis Murashev
 *
 * @since Math 0.3
 */
public class NaturalBasedSeries extends ArrayBasedSeries {

  /**
   * Initializes with given values.
   *
   * @param max  max
   * @param base base
   */
  public NaturalBasedSeries(int max, int base) {
    super(IntStream.range(0, max).map(i -> i + 1).toArray(), base);
  }
}
