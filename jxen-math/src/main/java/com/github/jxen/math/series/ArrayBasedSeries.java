package com.github.jxen.math.series;

import com.github.jxen.math.rational.Rational;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * {@code ArrayBasedSeries} class is {@link Series} implementation for array of integer based series.
 *
 * @author Denis Murashev
 *
 * @since Math 0.3
 */
public class ArrayBasedSeries implements Series {

  private final NavigableSet<Integer> items;
  private final int base;

  /**
   * Initializes with given values.
   *
   * @param items items
   * @param base  base
   */
  public ArrayBasedSeries(int[] items, int base) {
    this.items = new TreeSet<>();
    this.base = base;
    for (int i : items) {
      this.items.add(i);
    }
    if (this.items.last() % base == 0) {
      this.items.remove(this.items.last());
    }
    Set<Integer> additional = new HashSet<>();
    for (int i : this.items) {
      int a = i * base;
      while (a < this.items.last()) {
        additional.add(a);
        a *= base;
      }
    }
    this.items.addAll(additional);
    this.items.removeIf(v -> v <= this.items.last() / base);
  }

  @Override
  public SeriesIterator iterator(Number start) {
    return new ArrayBasedIterator(start);
  }

  private class ArrayBasedIterator implements SeriesIterator {

    private int value;
    private int power;

    ArrayBasedIterator(Number current) {
      int p = getPower(current.doubleValue());
      int v = (int) Math.round(current.doubleValue() / new Rational(base).power(p).doubleValue());
      Integer min = items.floor(v);
      Integer max = items.ceiling(v);
      if (min == null) {
        v *= base;
        if (v > items.last()) {
          v = items.last();
        }
        p--;
      } else if (max == null) {
        if (base * items.first() - v > v - items.last()) {
          v = items.last();
        } else {
          v /= base;
          if (v < items.first()) {
            v = items.first();
          }
          p++;
        }
      } else {
        v = max - v > v - min ? min : max;
      }
      value = v;
      power = p;
    }

    @Override
    public Number current() {
      return new Rational(value).multiply(new Rational(base).power(power));
    }

    @Override
    public Number next() {
      Integer v = items.higher(value);
      if (Objects.nonNull(v)) {
        value = v;
      } else {
        v = items.lower((items.last() + base - 1) / base);
        value = Objects.nonNull(v) ? v : items.first();
        power++;
      }
      return current();
    }

    @Override
    public Number previous() {
      Integer v = items.lower(value);
      if (Objects.nonNull(v)) {
        value = v;
      } else {
        value = items.last();
        power--;
      }
      return current();
    }

    private int getPower(double value) {
      double lb = Math.log(base);
      return (int) Math.floor(Math.log(value) / lb) - (int) Math.floor(Math.log(items.last()) / lb);
    }

    @Override
    public String toString() {
      return value + "*" + base + "^" + power;
    }
  }
}
