package com.github.jxen.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class StatsTest {

  private static final double PRECISION = 1e-5;

  @Test
  void testAdd() {
    Stats stats = Stream.of(1.0, 2.0, 3.0).collect(SampleCollectors.primitive(2));
    stats.add(new Stats());
    assertEquals(3, stats.size());
  }

  @Test
  void testAddFailed() {
    assertThrows(UnsupportedOperationException.class, () -> new Stats().add(new Stats(1)));
  }

  @Test
  void testSize() {
    Stats stats = new Stats();
    stats.add(1, 2, 3);
    assertEquals(3, stats.size());
  }

  @Test
  void testMean() {
    Stats stats = Stream.of(1.0, 2.0, 3.0).collect(SampleCollectors.primitive(2));
    assertEquals(2.0, stats.mean());
  }

  @Test
  void testSd() {
    Stats stats = Stream.of(1.0, 2.0, 3.0).collect(SampleCollectors.primitive(2));
    assertEquals(0.8165, stats.sd(), PRECISION);
  }

  @Test
  void testSdUnbiased() {
    Stats stats = Stream.of(1.0, 2.0, 3.0).collect(SampleCollectors.primitive(2));
    assertEquals(1.0, stats.sdUnbiased(), PRECISION);
  }

  @Test
  void testSkewness() {
    Stats stats = Stream.of(1.0, 2.0, 3.0).collect(SampleCollectors.primitive(3));
    assertEquals(0.0, stats.skewness(), PRECISION);
  }

  @Test
  void testKurtosis() {
    Stats stats = Stream.of(1.0, 2.0, 3.0).collect(SampleCollectors.primitive(4));
    assertEquals(-1.5, stats.kurtosis(), PRECISION);
  }
}
