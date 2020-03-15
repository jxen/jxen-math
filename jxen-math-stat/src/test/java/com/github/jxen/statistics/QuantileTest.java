package com.github.jxen.statistics;

import static org.junit.jupiter.api.Assertions.*;

import com.github.jxen.math.common.MathException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class QuantileTest {

	@Test
	void testAdd() {
		Quantile quantile = Stream.of(1.0, 2.0, 3.0).collect(SampleCollectors.quantile());
		quantile.add(new Quantile());
		assertEquals(3, quantile.size());
	}

	@Test
	void testMedian() {
		Quantile quantile = new Quantile();
		quantile.add(1, 2, 3);
		assertEquals(2, quantile.median());
	}

	@Test
	void testMedianFailed() {
		Quantile quantile = new Quantile();
		assertThrows(MathException.class, quantile::median);
	}

	@Test
	void testQuartile() {
		Quantile quantile = new Quantile();
		quantile.add(1, 2, 3);
		assertEquals(1, quantile.quartile(0));
	}

	@Test
	void testQuartileFailed() {
		Quantile quantile = new Quantile();
		quantile.add(1, 2, 3);
		assertThrows(IllegalArgumentException.class, () -> quantile.quartile(5));
	}

	@Test
	void testPercentileCase1() {
		Quantile quantile = new Quantile();
		quantile.add(1, 2, 3);
		assertEquals(3, quantile.percentile(100));
	}

	@Test
	void testPercentileCase2() {
		Quantile quantile = new Quantile();
		quantile.add(1, 2, 3);
		assertEquals(1.5, quantile.percentile(25));
	}

	@Test
	void testPercentileFailed() {
		Quantile quantile = new Quantile();
		quantile.add(1, 2, 3);
		assertThrows(IllegalArgumentException.class, () -> quantile.percentile(-1));
	}
}
