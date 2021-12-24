package com.github.jxen.math.rounding;

import com.github.jxen.math.series.Series;

/**
 * {@code SeriesRoundingRule} class is {@link RoundingRule} based on {@link Series}.
 *
 * @author Denis Murashev
 *
 * @since Math 0.3
 */
public class SeriesRoundingRule implements RoundingRule {

  private final Series series;

  /**
   * Initializes with given values.
   *
   * @param series series
   */
  public SeriesRoundingRule(Series series) {
    this.series = series;
  }

  @Override
  public Number round(Number value) {
    return series.iterator(value).current();
  }
}
