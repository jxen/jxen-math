package com.github.jxen.math.rounding;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.jxen.math.series.LogBasedSeries;
import org.junit.jupiter.api.Test;

public class RounderTest {

  @Test
  void testSeriesRoundingRule() {
    SeriesRoundingRule rule = new SeriesRoundingRule(new LogBasedSeries(3, 10));
    Rounder rounder = new Rounder(rule);
    assertEquals(4.0, rounder.round(3).doubleValue());
  }
}
