package com.github.jxen.math.series;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class IntegerSeriesTest {

	@Test
	void testCurrent() {
		SeriesIterator iterator = new IntegerSeries().iterator();
		assertEquals(1, iterator.current());
	}

	@Test
	void testNext() {
		SeriesIterator iterator = new IntegerSeries().iterator();
		assertEquals(2, iterator.next());
	}

	@Test
	void testPrevious() {
		SeriesIterator iterator = new IntegerSeries().iterator();
		assertEquals(0, iterator.previous());
	}
}
