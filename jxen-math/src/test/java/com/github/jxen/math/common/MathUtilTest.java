package com.github.jxen.math.common;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MathUtilTest {

  @Test
  void testRoots() {
    double[] actual = MathUtil.roots(1, -3, 2);
    assertArrayEquals(new double[] {1, 2}, actual);
  }

  @Test
  void testNoRoots() {
    double[] actual = MathUtil.roots(1, 1, 1);
    assertEquals(0, actual.length);
  }

  @Test
  void testToFraction() {
    double value = 1.33333;
    long[] actual = MathUtil.toFraction(value);
    long[] expected = {4, 3};
    assertArrayEquals(expected, actual);
  }

  @Test
  void testToFractionNegative() {
    double value = -1.33333;
    long[] actual = MathUtil.toFraction(value);
    long[] expected = {-4, 3};
    assertArrayEquals(expected, actual);
  }

  @Test
  void testToFractionOne() {
    double value = 1;
    long[] actual = MathUtil.toFraction(value);
    long[] expected = {1, 1};
    assertArrayEquals(expected, actual);
  }

  @Test
  void testToFractionEdgeCase1() {
    double value = 1.3333;
    long[] actual = MathUtil.toFraction(value, 0, 1);
    long[] expected = {1, 1};
    assertArrayEquals(expected, actual);
  }

  @Test
  void testToFractionEdgeCase2() {
    double value = 1.3333;
    long[] actual = MathUtil.toFraction(value, 1, 10000000);
    long[] expected = {4, 3};
    assertArrayEquals(expected, actual);
  }

  @Test
  void testToFractionEdgeCase3() {
    double value = 0.3;
    long[] actual = MathUtil.toFraction(value, 5);
    long[] expected = {1, 3};
    assertArrayEquals(expected, actual);
  }

  @Test
  void testToFractionEdgeCase4() {
    double value = 0.0001;
    long[] actual = MathUtil.toFraction(value);
    long[] expected = {1, 10000};
    assertArrayEquals(expected, actual);
  }
}
