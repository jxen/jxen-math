package com.github.jxen.math.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.jxen.math.rational.Rational;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class DoubleAdaptersTest {

	@Test
	void testRational() {
		assertNotNull(Adapters.lookup(Rational.ONE));
	}

	@Test
	void testDoubleAbs() {
		ArithmeticAware<?> value = Adapters.lookup(1);
		assertEquals(1.0, value.abs());
	}

	@Test
	void testDoubleNegate() {
		ArithmeticAware<?> value = Adapters.lookup(1);
		assertEquals(-1.0, value.negate());
	}

	@Test
	void testDoubleReciprocal() {
		ArithmeticAware<?> value = Adapters.lookup(1);
		assertEquals(1.0, value.reciprocal());
	}

	@Test
	void testDoublePlusDouble() {
		ArithmeticAware<?> value = Adapters.lookup(1);
		assertEquals(2.0, value.plus(1.0));
	}

	@Test
	void testDoublePlusLong() {
		ArithmeticAware<?> value = Adapters.lookup(1);
		assertEquals(2.0, value.plus(1));
	}

	@Test
	void testDoubleMinusDouble() {
		ArithmeticAware<?> value = Adapters.lookup(1);
		assertEquals(0.0, value.minus(1.0));
	}

	@Test
	void testDoubleMinusLong() {
		ArithmeticAware<?> value = Adapters.lookup(1);
		assertEquals(0.0, value.minus(1));
	}

	@Test
	void testDoubleMultiplyDouble() {
		ArithmeticAware<?> value = Adapters.lookup(1);
		assertEquals(1.0, value.multiply(1.0));
	}

	@Test
	void testDoubleMultiplyLong() {
		ArithmeticAware<?> value = Adapters.lookup(1);
		assertEquals(1.0, value.multiply(1));
	}

	@Test
	void testDoubleDivDouble() {
		ArithmeticAware<?> value = Adapters.lookup(1);
		assertEquals(1.0, value.div(1.0));
	}

	@Test
	void testDoubleDivLong() {
		ArithmeticAware<?> value = Adapters.lookup(1);
		assertEquals(1.0, value.div(1));
	}

	@Test
	void testDoubleModDouble() {
		ArithmeticAware<?> value = Adapters.lookup(1);
		assertEquals(0.0, value.mod(1.0));
	}

	@Test
	void testDoubleModLong() {
		ArithmeticAware<?> value = Adapters.lookup(1);
		assertEquals(0.0, value.mod(1));
	}

	@Test
	void testPower() {
		ArithmeticAware<?> value = Adapters.lookup(1);
		assertEquals(1.0, value.power(1));
	}

	@Test
	void testRound() {
		ArithmeticAware<?> value = Adapters.lookup(1.5);
		assertEquals(2.0, value.round());
	}

	@Test
	void testFloor() {
		ArithmeticAware<?> value = Adapters.lookup(1.5);
		assertEquals(1.0, value.floor());
	}

	@Test
	void testCeil() {
		ArithmeticAware<?> value = Adapters.lookup(1.5);
		assertEquals(2.0, value.ceil());
	}

	@Test
	void testIntegralPositive() {
		ArithmeticAware<?> value = Adapters.lookup(1.0);
		assertTrue(value.isIntegral());
	}

	@Test
	void testIntegralNegative() {
		ArithmeticAware<?> value = Adapters.lookup(1.5);
		assertFalse(value.isIntegral());
	}

	@Test
	void testCompareTo() {
		@SuppressWarnings("unchecked")
		ArithmeticAware<Double> value = (ArithmeticAware<Double>) Adapters.lookup(1.0);
		assertEquals(0, value.compareTo(1.0));
	}

	@Test
	void testHashCode() {
		Map<ArithmeticAware<?>, Integer> map = new HashMap<>();
		ArithmeticAware<?> value = Adapters.lookup(1.0);
		map.put(value, 1);
		assertEquals(1, map.get(value));
		assertEquals(1, map.get(Adapters.lookup(1.0)));
	}

	@Test
	void testEquals() {
		ArithmeticAware<?> value = Adapters.lookup(1.0);
		assertEquals(value, value);
		assertEquals(value, Adapters.lookup(1.0));
		assertNotEquals(value, Adapters.lookup(2.0));
		assertNotEquals(value, Adapters.lookup(BigDecimal.ONE));
	}
}
