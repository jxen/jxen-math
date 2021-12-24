package com.github.jxen.math.series;

import com.github.jxen.math.common.Adapters;

/**
 * {@code IntegerSeries} class is {@link Series} implementation for simple integer series.
 *
 * @author Denis Murashev
 *
 * @since Math 0.3
 */
public class IntegerSeries implements Series {

  @Override
  public SeriesIterator iterator(Number start) {
    return new IntegerIterator(start);
  }

  private static final class IntegerIterator implements SeriesIterator {

    private int current;

    private IntegerIterator(Number start) {
      current = Adapters.lookup(start).round().intValue();
    }

    @Override
    public Integer current() {
      return current;
    }

    @Override
    public Integer next() {
      return ++current;
    }

    @Override
    public Integer previous() {
      return --current;
    }
  }
}
