package com.github.jxen.math.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.jxen.math.rational.Rational;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class DecimalAdaptersTest {

  @Test
  void testDoubleAbs() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(BigDecimal.ONE, value.abs());
  }

  @Test
  void testDoubleNegate() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(new BigDecimal("-1"), value.negate());
  }

  @Test
  void testDoubleReciprocal() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(BigDecimal.ONE, value.reciprocal());
  }

  @Test
  void testDoublePlusDouble() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(new BigDecimal("2.0"), value.plus(1.0));
  }

  @Test
  void testDoublePlusLong() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(new BigDecimal("2"), value.plus(1));
  }

  @Test
  void testDoublePlusDecimal() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(new BigDecimal("2"), value.plus(BigDecimal.ONE));
  }

  @Test
  void testDoublePlusRational() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(new BigDecimal("2.0"), value.plus(Rational.ONE));
  }

  @Test
  void testDoubleMinusDouble() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(new BigDecimal("0.0"), value.minus(1.0));
  }

  @Test
  void testDoubleMinusLong() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(BigDecimal.ZERO, value.minus(1));
  }

  @Test
  void testDoubleMinusDecimal() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(BigDecimal.ZERO, value.minus(BigDecimal.ONE));
  }

  @Test
  void testDoubleMinusRational() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(new BigDecimal("0.0"), value.minus(Rational.ONE));
  }

  @Test
  void testDoubleMultiplyDouble() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(new BigDecimal("1.0"), value.multiply(1.0));
  }

  @Test
  void testDoubleMultiplyLong() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(BigDecimal.ONE, value.multiply(1));
  }

  @Test
  void testDoubleMultiplyDecimal() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(BigDecimal.ONE, value.multiply(BigDecimal.ONE));
  }

  @Test
  void testDoubleMultiplyRational() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(new BigDecimal("1.0"), value.multiply(Rational.ONE));
  }

  @Test
  void testDoubleDivDouble() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(BigDecimal.ONE, value.div(1.0));
  }

  @Test
  void testDoubleDivLong() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(BigDecimal.ONE, value.div(1));
  }

  @Test
  void testDoubleDivDecimal() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(BigDecimal.ONE, value.div(BigDecimal.ONE));
  }

  @Test
  void testDoubleDivRational() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(BigDecimal.ONE, value.div(Rational.ONE));
  }

  @Test
  void testDoubleModDouble() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(new BigDecimal("0.0"), value.mod(1.0));
  }

  @Test
  void testDoubleModLong() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(BigDecimal.ZERO, value.mod(1));
  }

  @Test
  void testDoubleModDecimal() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(BigDecimal.ZERO, value.mod(BigDecimal.ONE));
  }

  @Test
  void testDoubleModRational() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(new BigDecimal("0.0"), value.mod(Rational.ONE));
  }

  @Test
  void testPower() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(BigDecimal.ONE, value.power(1));
  }

  @Test
  void testRound() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.valueOf(1.5));
    assertEquals(new BigDecimal("2"), value.round());
  }

  @Test
  void testFloor() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.valueOf(1.5));
    assertEquals(BigDecimal.ONE, value.floor());
  }

  @Test
  void testCeil() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.valueOf(1.5));
    assertEquals(new BigDecimal("2"), value.ceil());
  }

  @Test
  void testIntegralPositive() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertTrue(value.isIntegral());
  }

  @Test
  void testIntegralNegative() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.valueOf(1.5));
    assertFalse(value.isIntegral());
  }

  @Test
  void testCompareTo() {
    @SuppressWarnings("unchecked")
    ArithmeticAware<BigDecimal> value = (ArithmeticAware<BigDecimal>) Adapters.lookup(BigDecimal.ONE);
    assertEquals(0, value.compareTo(BigDecimal.ONE));
  }

  @Test
  void testHashCode() {
    Map<ArithmeticAware<?>, Integer> map = new HashMap<>();
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    map.put(value, 1);
    assertEquals(1, map.get(value));
    assertEquals(1, map.get(Adapters.lookup(BigDecimal.ONE)));
  }

  @Test
  void testEquals() {
    ArithmeticAware<?> value = Adapters.lookup(BigDecimal.ONE);
    assertEquals(value, value);
    assertEquals(value, Adapters.lookup(BigDecimal.ONE));
    assertNotEquals(value, Adapters.lookup(BigDecimal.ZERO));
    assertNotEquals(value, Adapters.lookup(1.0));
  }
}
