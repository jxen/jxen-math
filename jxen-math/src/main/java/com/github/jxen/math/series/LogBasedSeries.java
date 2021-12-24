package com.github.jxen.math.series;

import com.github.jxen.math.rational.Rational;

/**
 * {@code LogBasedSeries} class is {@link Series} implementation for logarithmic based series.
 *
 * @author Denis Murashev
 *
 * @since Math 0.3
 */
public class LogBasedSeries implements Series {

  private final int count;
  private final int base;

  /**
   * Initializes with given values.
   *
   * @param count count
   * @param base  base
   */
  public LogBasedSeries(int count, int base) {
    this.count = count;
    this.base = base;
  }

  @Override
  public SeriesIterator iterator(Number start) {
    return new LogBasedIterator(start);
  }

  private final class LogBasedIterator implements SeriesIterator {

    private int value;
    private int number;

    LogBasedIterator(Number current) {
      value = (int) Math.round(count * Math.log(current.doubleValue()) / Math.log(base));
      double x = 1;
      double y = Math.pow(base, 1.0 / count);
      double z = Math.pow(base, 2.0 / count);
      while (Math.round(x) == Math.round(y) || Math.round(y) == Math.round(z)) {
        number++;
        x *= base;
        y *= base;
        z *= base;
      }
    }

    @Override
    public Number current() {
      double x = Math.pow(base, value % count / (double) count);
      int d = 1;
      for (int i = 0; i < number; i++) {
        x *= base;
        d *= base;
      }
      while (Math.round(x) < 1) {
        x *= base;
        d *= base;
      }
      return new Rational((int) Math.round(x), d).multiply(new Rational(base).power(value / count));
    }

    @Override
    public Number next() {
      value++;
      return current();
    }

    @Override
    public Number previous() {
      value--;
      return current();
    }

    @Override
    public String toString() {
      return base + "^(" + value + "/" + count + ")";
    }
  }
}
