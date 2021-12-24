package com.github.jxen.math.format;

import java.math.BigDecimal;
import java.util.Locale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TeXScientificFormatTest {

  @BeforeEach
  void setUp() {
    Locale.setDefault(Locale.US);
  }

  @Test
  void testFormatCase1() {
    String actual = new TeXScientificFormat(4).format(1);
    Assertions.assertEquals("1", actual);
  }

  @Test
  void testFormatCase2() {
    String actual = new TeXScientificFormat(4).format(10.5);
    Assertions.assertEquals("1.05\\times10^1", actual);
  }

  @Test
  void testFormatCase3() {
    String actual = new TeXScientificFormat(4).format(0.01057501);
    Assertions.assertEquals("1.0575\\times10^-2", actual);
  }

  @Test
  void testParseCase1() {
    Number actual = new TeXScientificFormat(4).parse("1");
    Assertions.assertEquals(BigDecimal.ONE, actual);
  }

  @Test
  void testParseCase2() {
    Number actual = new TeXScientificFormat(4).parse("1\\times10^1");
    Assertions.assertEquals(BigDecimal.TEN, actual);
  }
}
