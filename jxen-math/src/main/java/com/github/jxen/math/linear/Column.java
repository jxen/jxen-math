package com.github.jxen.math.linear;

import com.github.jxen.math.common.MathException;
import java.util.Arrays;

/**
 * {@code Column} class represents column (vector).
 *
 * @author Denis Murashev
 * @since Math 0.1
 */
public final class Column {

  private static final String ERROR_SIZE = "The columns have different sizes";

  private final double[] v;

  private Column(double[] v) {
    this.v = v.clone();
  }

  /**
   * Creates vector using given elements.
   *
   * @param v vector elements
   * @return vector
   */
  public static Column of(double... v) {
    return new Column(v);
  }

  /**
   * Provides size.
   *
   * @return matrix size
   */
  public int size() {
    return v.length;
  }

  /**
   * Provides element.
   *
   * @param i row
   * @return element
   */
  public double get(int i) {
    return v[i];
  }

  /**
   * Adds given column to current.
   *
   * @param column column
   * @return sum
   */
  public Column plus(Column column) {
    if (v.length != column.v.length) {
      throw new MathException(ERROR_SIZE);
    }
    double[] values = new double[v.length];
    for (int i = 0; i < v.length; i++) {
      values[i] = v[i] + column.v[i];
    }
    return new Column(values);
  }

  /**
   * Subtracts given column to current.
   *
   * @param column column
   * @return difference
   */
  public Column minus(Column column) {
    if (v.length != column.v.length) {
      throw new MathException(ERROR_SIZE);
    }
    double[] values = new double[v.length];
    for (int i = 0; i < v.length; i++) {
      values[i] = v[i] - column.v[i];
    }
    return new Column(values);
  }

  /**
   * Multiplies given value to current matrix.
   *
   * @param value value
   * @return product
   */
  public Column multiply(double value) {
    double[] values = new double[v.length];
    for (int i = 0; i < v.length; i++) {
      values[i] = v[i] * value;
    }
    return new Column(values);
  }

  @Override
  public String toString() {
    return Arrays.toString(v);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    return prime + Arrays.hashCode(v);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Column other = (Column) obj;
    return Arrays.equals(v, other.v);
  }
}
