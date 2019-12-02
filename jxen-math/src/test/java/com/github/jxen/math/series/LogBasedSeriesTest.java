package com.github.jxen.math.series;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.jxen.math.rational.Rational;
import org.junit.jupiter.api.Test;

class LogBasedSeriesTest {

	@Test
	void testCurrentCase1() {
		SeriesIterator iterator = new LogBasedSeries(3, 10).iterator();
		assertEquals(Rational.ONE, iterator.current());
	}

	@Test
	void testCurrentCase2() {
		SeriesIterator iterator = new LogBasedSeries(10, 10).iterator();
		assertEquals(Rational.ONE, iterator.current());
	}

	@Test
	void testNext() {
		SeriesIterator iterator = new LogBasedSeries(3, 10).iterator();
		assertEquals(Rational.TWO, iterator.next());
	}

	@Test
	void testPrevious() {
		SeriesIterator iterator = new LogBasedSeries(3, 10).iterator();
		assertEquals(Rational.HALF, iterator.previous());
	}

	@Test
	void testToString() {
		SeriesIterator iterator = new LogBasedSeries(3, 10).iterator();
		assertEquals("10^(0/3)", iterator.toString());
	}
}
