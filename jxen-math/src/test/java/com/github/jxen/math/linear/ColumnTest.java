package com.github.jxen.math.linear;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.jxen.math.common.MathException;
import org.junit.jupiter.api.Test;

class ColumnTest {

  @Test
  void testCreateFromArray() {
    double[] array = {1, 0, 0};
    Column actual = Column.of(array);
    assertEquals(3, actual.size());
    assertEquals(1, actual.get(0));
  }

  @Test
  void testPlusFail() {
    Column v = Column.of(1, 0, 0);
    assertThrows(MathException.class, () -> v.plus(Column.of(1)));
  }

  @Test
  void testPlus() {
    Column v = Column.of(1, 0, 0);
    Column actual = v.plus(Column.of(0, 1, 0));
    Column expected = Column.of(1, 1, 0);
    assertEquals(expected, actual);
  }

  @Test
  void testMinusFail() {
    Column v = Column.of(1, 0, 0);
    assertThrows(MathException.class, () -> v.minus(Column.of(1)));
  }

  @Test
  void testMinus() {
    Column v = Column.of(1, 0, 0);
    Column actual = v.minus(Column.of(0, 1, 0));
    Column expected = Column.of(1, -1, 0);
    assertEquals(expected, actual);
  }

  @Test
  void testMultiply() {
    Column v = Column.of(1, 0, 0);
    Column actual = v.multiply(2);
    Column expected = Column.of(2, 0, 0);
    assertEquals(expected, actual);
  }

  @Test
  void testToString() {
    assertEquals("[1.0, 1.0]", Column.of(1, 1).toString());
  }

  @Test
  void testEqualsSame() {
    Column column = Column.of(1, 1);
    assertEquals(column, column);
  }

  @Test
  void testNotEquals() {
    assertNotEquals(Column.of(1), 1);
  }
}
