package com.github.jxen.math.series;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NaturalBasedSeriesTest {

  @Test
  void testNaturalBasedSeries() {
    NaturalBasedSeries series = new NaturalBasedSeries(3, 10);
    assertEquals(1, series.iterator().current().intValue());
  }
}
