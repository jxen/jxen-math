package com.github.jxen.math.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ParserTest {

  @Test
  void testCompileFailureEmpty() {
    assertThrows(ExpressionException.class, () -> new Parser().compile(""));
  }

  @Test
  void testCompileFailureNull() {
    assertThrows(ExpressionException.class, () -> new Parser().compile(null));
  }

  @Test
  void testCompileFailureIncorrectToken() {
    assertThrows(ExpressionException.class, () -> new Parser().compile("a"));
  }

  @Test
  void testCompileFailureUnexpected() {
    assertThrows(ExpressionException.class, () -> new Parser().compile(")"));
  }

  @Test
  void testCompileFailureNoClosing() {
    assertThrows(ExpressionException.class, () -> new Parser().compile("("));
  }

  @Test
  void testCompileFailureMissingOperand() {
    assertThrows(ExpressionException.class, () -> new Parser().compile("*1"));
  }

  @Test
  void testCompileFailureMissingArg() {
    assertThrows(ExpressionException.class, () -> new Parser().compile("sin"));
  }

  @Test
  void testCompileConst() {
    double actual = new Parser().compile("e").evaluate(null);
    assertEquals(Math.E, actual);
  }

  @Test
  void testCompileCase1() {
    double actual = new Parser().compile("1+2-3").evaluate(null);
    assertEquals(0, actual);
  }

  @Test
  void testCompileCase2() {
    double actual = new Parser().compile("1-2+3").evaluate(null);
    assertEquals(2, actual);
  }

  @Test
  void testCompileCase3() {
    double actual = new Parser().compile("1+2*3").evaluate(null);
    assertEquals(7, actual);
  }

  @Test
  void testCompileCase4() {
    double actual = new Parser().compile("3^2").evaluate(null);
    assertEquals(9, actual);
  }

  @Test
  void testCompileCase5() {
    double actual = new Parser().compile("3%2").evaluate(null);
    assertEquals(1, actual);
  }

  @Test
  void testCompileCase6() {
    double actual = new Parser().compile("-1").evaluate(null);
    assertEquals(-1, actual);
  }

  @Test
  void testCompileCtg() {
    double actual = new Parser().compile("ctg(pi/4)").evaluate(null);
    assertEquals(1, actual, 1e-15);
  }
}
