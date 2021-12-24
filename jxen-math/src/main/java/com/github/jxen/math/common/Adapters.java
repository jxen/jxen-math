package com.github.jxen.math.common;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * {@code Adapters} class is responsible for {@link NumberAdapter} implementation management.
 *
 * @author Denis Murashev
 * @since Measure 0.1
 */
public final class Adapters {

  private static final Map<Class<? extends Number>, NumberAdapter> LOOKUP_TABLE = new HashMap<>();

  private static final NumberAdapter DEFAULT_ADAPTER = DefaultNumber::new;

  static {
    register(BigDecimal.class, v -> new DecimalNumber((BigDecimal) v));
  }

  private Adapters() {
  }

  /**
   * Registers adapter for given class.
   *
   * @param type    type
   * @param adapter adapter
   */
  public static void register(Class<? extends Number> type, NumberAdapter adapter) {
    LOOKUP_TABLE.put(type, adapter);
  }

  /**
   * Looks for appropriate adapter.
   *
   * @param value numeric value
   * @return value supporting arithmetic operations
   */
  public static ArithmeticAware<?> lookup(Number value) {
    if (value instanceof ArithmeticAware) {
      return (ArithmeticAware) value;
    }
    return Optional.ofNullable(LOOKUP_TABLE.get(value.getClass())).orElse(DEFAULT_ADAPTER).adapt(value);
  }

  private static final class DefaultNumber implements ArithmeticAware<Double> {

    private final double x;

    private DefaultNumber(Number value) {
      x = value.doubleValue();
    }

    @Override
    public Double abs() {
      return Math.abs(x);
    }

    @Override
    public Double negate() {
      return -x;
    }

    @Override
    public Double reciprocal() {
      return 1 / x;
    }

    @Override
    public Double plus(double value) {
      return x + value;
    }

    @Override
    public Double plus(long value) {
      return x + value;
    }

    @Override
    public Double minus(double value) {
      return x - value;
    }

    @Override
    public Double minus(long value) {
      return x - value;
    }

    @Override
    public Double multiply(double value) {
      return x * value;
    }

    @Override
    public Double multiply(long value) {
      return x * value;
    }

    @Override
    public Double div(double value) {
      return x / value;
    }

    @Override
    public Double div(long value) {
      return x / value;
    }

    @Override
    public Double mod(double value) {
      return x % value;
    }

    @Override
    public Double mod(long value) {
      return x % value;
    }

    @Override
    public Double power(int value) {
      return Math.pow(x, value);
    }

    @Override
    public Double round() {
      return Math.rint(x);
    }

    @Override
    public Double floor() {
      return Math.floor(x);
    }

    @Override
    public Double ceil() {
      return Math.ceil(x);
    }

    @Override
    public boolean isIntegral() {
      return x - (long) x == 0;
    }

    @Override
    public int compareTo(Double o) {
      return Double.compare(x, o);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null) {
        return false;
      }
      if (getClass() != o.getClass()) {
        return false;
      }
      DefaultNumber that = (DefaultNumber) o;
      return Double.compare(that.x, x) == 0;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x);
    }
  }

  private static final class DecimalNumber implements ArithmeticAware<BigDecimal> {

    private final BigDecimal x;
    private final MathContext mc;

    private DecimalNumber(BigDecimal value) {
      x = value;
      mc = new MathContext(value.precision());
    }

    @Override
    public BigDecimal abs() {
      return x.abs();
    }

    @Override
    public BigDecimal negate() {
      return x.negate();
    }

    @Override
    public BigDecimal reciprocal() {
      return BigDecimal.ONE.divide(x, mc);
    }

    @Override
    public BigDecimal plus(double value) {
      return x.add(BigDecimal.valueOf(value));
    }

    @Override
    public BigDecimal plus(long value) {
      return x.add(BigDecimal.valueOf(value));
    }

    @Override
    public BigDecimal plus(Number value) {
      if (value instanceof BigDecimal) {
        return x.add((BigDecimal) value);
      }
      return ArithmeticAware.super.plus(value);
    }

    @Override
    public BigDecimal minus(double value) {
      return x.subtract(BigDecimal.valueOf(value));
    }

    @Override
    public BigDecimal minus(long value) {
      return x.subtract(BigDecimal.valueOf(value));
    }

    @Override
    public BigDecimal minus(Number value) {
      if (value instanceof BigDecimal) {
        return x.subtract((BigDecimal) value);
      }
      return ArithmeticAware.super.minus(value);
    }

    @Override
    public BigDecimal multiply(double value) {
      return x.multiply(BigDecimal.valueOf(value));
    }

    @Override
    public BigDecimal multiply(long value) {
      return x.multiply(BigDecimal.valueOf(value));
    }

    @Override
    public BigDecimal multiply(Number value) {
      if (value instanceof BigDecimal) {
        return x.multiply((BigDecimal) value);
      }
      return ArithmeticAware.super.multiply(value);
    }

    @Override
    public BigDecimal div(double value) {
      return x.divide(BigDecimal.valueOf(value), mc);
    }

    @Override
    public BigDecimal div(long value) {
      return x.divide(BigDecimal.valueOf(value), mc);
    }

    @Override
    public BigDecimal div(Number value) {
      if (value instanceof BigDecimal) {
        return x.divide((BigDecimal) value, mc);
      }
      return ArithmeticAware.super.div(value);
    }

    @Override
    public BigDecimal mod(double value) {
      return x.remainder(BigDecimal.valueOf(value));
    }

    @Override
    public BigDecimal mod(long value) {
      return x.remainder(BigDecimal.valueOf(value));
    }

    @Override
    public BigDecimal mod(Number value) {
      if (value instanceof BigDecimal) {
        return x.remainder((BigDecimal) value, mc);
      }
      return ArithmeticAware.super.mod(value);
    }

    @Override
    public BigDecimal power(int value) {
      return x.pow(value);
    }

    @Override
    public BigDecimal round() {
      return x.setScale(0, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal floor() {
      return x.setScale(0, RoundingMode.DOWN);
    }

    @Override
    public BigDecimal ceil() {
      return x.setScale(0, RoundingMode.UP);
    }

    @Override
    public boolean isIntegral() {
      return x.compareTo(round()) == 0;
    }

    @Override
    public int compareTo(BigDecimal o) {
      return x.compareTo(o);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null) {
        return false;
      }
      if (getClass() != o.getClass()) {
        return false;
      }
      DecimalNumber that = (DecimalNumber) o;
      return x.equals(that.x);
    }

    @Override
    public int hashCode() {
      return Objects.hash(x);
    }
  }
}
