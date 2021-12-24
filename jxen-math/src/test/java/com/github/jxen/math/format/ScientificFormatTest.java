package com.github.jxen.math.format;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.jxen.math.common.MathException;
import java.math.BigDecimal;
import java.text.ParsePosition;
import org.junit.jupiter.api.Test;

class ScientificFormatTest {

  @Test
  void testFormatCase1() {
    String actual = new ScientificFormat(4).format(1);
    assertEquals("1E0", actual);
  }

  @Test
  void testFormatCase2() {
    String actual = new ScientificFormat(0).format(1);
    assertEquals("1E0", actual);
  }

  @Test
  void testParseCase1() {
    Number actual = new ScientificFormat(4).parse("1E0", new ParsePosition(0));
    assertEquals(BigDecimal.ONE, actual);
  }

  @Test
  void testParseFailure() {
    assertThrows(NumberFormatException.class, () -> new ScientificFormat(4).parse("A"));
  }
}
