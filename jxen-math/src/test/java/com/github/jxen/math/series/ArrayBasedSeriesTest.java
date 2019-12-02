package com.github.jxen.math.series;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.jxen.math.rational.Rational;
import org.junit.jupiter.api.Test;

class ArrayBasedSeriesTest {

	@Test
	void testCurrentCase1() {
		SeriesIterator iterator = new ArrayBasedSeries(new int[]{1, 2, 3, 5, 7}, 10).iterator();
		assertEquals(Rational.ONE, iterator.current());
	}

	@Test
	void testCurrentCase2() {
		SeriesIterator iterator = new ArrayBasedSeries(new int[]{1, 2, 3, 5, 7, 10}, 10).iterator();
		assertEquals(1, iterator.current().intValue());
	}

	@Test
	void testCurrentCase3() {
		SeriesIterator iterator = new ArrayBasedSeries(new int[]{1, 2, 3, 5, 7, 10}, 2).iterator();
		assertEquals(1, iterator.current().intValue());
	}

	@Test
	void testCurrentCase4() {
		SeriesIterator iterator = new ArrayBasedSeries(new int[]{3}, 4).iterator(1.5);
		assertEquals(Rational.THREE_FOURTH, iterator.current());
	}

	@Test
	void testCurrentCase5() {
		SeriesIterator iterator = new ArrayBasedSeries(new int[]{1, 4}, 5).iterator(1.5);
		assertEquals(Rational.ONE, iterator.current());
	}

	@Test
	void testCurrentCase6() {
		SeriesIterator iterator = new ArrayBasedSeries(new int[]{1}, 4).iterator(0.5);
		assertEquals(Rational.ONE_FOURTH, iterator.current());
	}

	@Test
	void testCurrentCase7() {
		SeriesIterator iterator = new ArrayBasedSeries(new int[]{1}, 4).iterator(3.5);
		assertEquals(4, iterator.current().intValue());
	}

	@Test
	void testNextCase1() {
		SeriesIterator iterator = new ArrayBasedSeries(new int[]{1, 2, 3, 5, 7}, 10).iterator();
		assertEquals(Rational.TWO, iterator.next());
	}

	@Test
	void testNextCase2() {
		SeriesIterator iterator = new ArrayBasedSeries(new int[]{1, 2, 3}, 10).iterator(5);
		assertEquals(10, iterator.next().intValue());
	}

	@Test
	void testPreviousCase1() {
		SeriesIterator iterator = new ArrayBasedSeries(new int[]{1, 2, 3, 5, 7}, 10).iterator();
		assertEquals(Rational.valueOf(0.7), iterator.previous());
	}

	@Test
	void testPreviousCase2() {
		SeriesIterator iterator = new ArrayBasedSeries(new int[]{1, 2, 3, 5, 7}, 10).iterator(1.5);
		assertEquals(Rational.ONE, iterator.previous());
	}

	@Test
	void testToString() {
		SeriesIterator iterator = new ArrayBasedSeries(new int[]{1, 2, 3, 5, 7}, 10).iterator();
		assertEquals("1*10^0", iterator.toString());
	}
}
