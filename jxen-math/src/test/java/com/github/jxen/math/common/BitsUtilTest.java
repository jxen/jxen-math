package com.github.jxen.math.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BitsUtilTest {

	private static final int SAMPLES = 20;

	@Test
	void testIsPowerOf2Int() {
		assertTrue(BitsUtil.isPowerOf2(0));
		assertTrue(BitsUtil.isPowerOf2(1));
		for (int i = 1; i < SAMPLES; i++) {
			int value = 1 << i;
			assertTrue(BitsUtil.isPowerOf2(value));
			value++;
			assertFalse(BitsUtil.isPowerOf2(value));
		}
	}

	@Test
	void testIsPowerOf2Long() {
		assertTrue(BitsUtil.isPowerOf2(0L));
		assertTrue(BitsUtil.isPowerOf2(1L));
		for (int i = 1; i < SAMPLES; i++) {
			long value = 1 << i;
			assertTrue(BitsUtil.isPowerOf2(value));
			value++;
			assertFalse(BitsUtil.isPowerOf2(value));
		}
	}

	@Test
	void testGetMostSignificantBitInt() {
		assertEquals(0, BitsUtil.getMostSignificantBit(0));
		assertEquals(Integer.MIN_VALUE, BitsUtil.getMostSignificantBit(-1));
		assertEquals(1, BitsUtil.getMostSignificantBit(1));
		for (int i = 1; i < SAMPLES; i++) {
			int expected = 1 << i;
			for (int value = expected; value < expected + SAMPLES && value < expected << 1; value++) {
				assertEquals(expected, BitsUtil.getMostSignificantBit(value));
			}
		}
	}

	@Test
	void testGetMostSignificantBitLong() {
		assertEquals(0, BitsUtil.getMostSignificantBit(0L));
		assertEquals(Long.MIN_VALUE, BitsUtil.getMostSignificantBit(-1L));
		assertEquals(1, BitsUtil.getMostSignificantBit(1L));
		for (int i = 1; i < SAMPLES; i++) {
			long expected = 1 << i;
			for (long value = expected; value < expected + SAMPLES && value < expected << 1; value++) {
				assertEquals(expected, BitsUtil.getMostSignificantBit(value));
			}
		}
	}

	@Test
	void testTestBitInt() {
		final int maxBit = 31;
		assertFalse(BitsUtil.testBit(0, 0));
		assertFalse(BitsUtil.testBit(1, 1));
		assertTrue(BitsUtil.testBit(1, 0));
		assertFalse(BitsUtil.testBit(2, 0));
		assertTrue(BitsUtil.testBit(2, 1));
		assertTrue(BitsUtil.testBit(-1, 0));
		assertTrue(BitsUtil.testBit(-1, maxBit));
		assertFalse(BitsUtil.testBit(Integer.MIN_VALUE, 0));
		assertTrue(BitsUtil.testBit(Integer.MIN_VALUE, maxBit));
	}

	@Test
	void testTestBitLong() {
		final int maxBit = 63;
		assertFalse(BitsUtil.testBit(0L, 0));
		assertFalse(BitsUtil.testBit(1L, 1));
		assertTrue(BitsUtil.testBit(1L, 0));
		assertFalse(BitsUtil.testBit(2L, 0));
		assertTrue(BitsUtil.testBit(2L, 1));
		assertTrue(BitsUtil.testBit(-1L, 0));
		assertTrue(BitsUtil.testBit(-1L, maxBit));
		assertFalse(BitsUtil.testBit(Long.MIN_VALUE, 0));
		assertTrue(BitsUtil.testBit(Long.MIN_VALUE, maxBit));
	}

	@Test
	void testClearLeadingBitsInt() {
		for (int base = 1; base < SAMPLES; base++) {
			for (int value = 1; value < SAMPLES; value++) {
				int expected = value % (1 << base);
				assertEquals(expected, BitsUtil.clearLeadingBits(value, base));
			}
		}
	}

	@Test
	void testClearLeadingBitsLong() {
		for (int base = 1; base < SAMPLES; base++) {
			for (long value = 1; value < SAMPLES; value++) {
				long expected = value % (1 << base);
				assertEquals(expected, BitsUtil.clearLeadingBits(value, base));
			}
		}
	}
}
