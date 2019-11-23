package com.github.jxen.math.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import org.junit.jupiter.api.Test;

class ExpressionTest {

	@Test
	void testSimplifyCase1() {
		Expression expression = new Parser().compile("0+x", Collections.singleton("x"));
		assertEquals("x", expression.simplify().toString());
	}

	@Test
	void testSimplifyCase2() {
		Expression expression = new Parser().compile("x-0", Collections.singleton("x"));
		assertEquals("x", expression.simplify().toString());
	}

	@Test
	void testSimplifyCase3() {
		Expression expression = new Parser().compile("0*x", Collections.singleton("x"));
		assertEquals("0", expression.simplify().toString());
	}

	@Test
	void testSimplifyCase4() {
		Expression expression = new Parser().compile("1*x", Collections.singleton("x"));
		assertEquals("x", expression.simplify().toString());
	}

	@Test
	void testDerivativeCase1() {
		Expression expression = new Parser().compile("1", Collections.singleton("x"));
		assertEquals("0", expression.derivative("x").toString());
	}

	@Test
	void testDerivativeCase2() {
		Expression expression = new Parser().compile("2*x", Collections.singleton("x"));
		assertEquals("2.0", expression.derivative("x").toString());
	}

	@Test
	void testDerivativeCase3() {
		Expression expression = new Parser().compile("x^3+x^2-x+1", Collections.singleton("x"));
		assertEquals("(((3.0*(x^2.0))+(2.0*(x^1.0)))-1)", expression.derivative("x").toString());
	}

	@Test
	void testDerivativeCase4() {
		Expression expression = new Parser().compile("sin(x/2)", Collections.singleton("x"));
		assertEquals("(cos((x/2.0))*(2.0/4.0))", expression.derivative("x").toString());
	}

	@Test
	void testDerivativeCase5() {
		Expression expression = new Parser().compile("e^x", Collections.singleton("x"));
		assertEquals("(e^x)", expression.derivative("x").toString());
	}

	@Test
	void testDerivativeSqrt() {
		Expression expression = new Parser().compile("sqrt(x)", Collections.singleton("x"));
		assertEquals("(0.5/sqrt(x))", expression.derivative("x").toString());
	}

	@Test
	void testDerivativeExp() {
		Expression expression = new Parser().compile("exp(x)", Collections.singleton("x"));
		assertEquals("exp(x)", expression.derivative("x").toString());
	}

	@Test
	void testDerivativeLn() {
		Expression expression = new Parser().compile("ln(x)", Collections.singleton("x"));
		assertEquals("(1/x)", expression.derivative("x").toString());
	}

	@Test
	void testDerivativeLog() {
		Expression expression = new Parser().compile("log(x)", Collections.singleton("x"));
		assertEquals("(2.302585092994046*x)", expression.derivative("x").toString());
	}

	@Test
	void testDerivativeSin() {
		Expression expression = new Parser().compile("sin(x)", Collections.singleton("x"));
		assertEquals("cos(x)", expression.derivative("x").toString());
	}

	@Test
	void testDerivativeCos() {
		Expression expression = new Parser().compile("cos(x)", Collections.singleton("x"));
		assertEquals("(-1*sin(x))", expression.derivative("x").toString());
	}

	@Test
	void testDerivativeTg() {
		Expression expression = new Parser().compile("tg(x)", Collections.singleton("x"));
		assertEquals("(1/(cos(x)^2))", expression.derivative("x").toString());
	}

	@Test
	void testDerivativeCtg() {
		Expression expression = new Parser().compile("ctg(x)", Collections.singleton("x"));
		assertEquals("(-1/(sin(x)^2))", expression.derivative("x").toString());
	}

	@Test
	void testDerivativeArcsin() {
		Expression expression = new Parser().compile("arcsin(x)", Collections.singleton("x"));
		assertEquals("(1/sqrt((1-(x*x))))", expression.derivative("x").toString());
	}

	@Test
	void testDerivativeArccos() {
		Expression expression = new Parser().compile("arccos(x)", Collections.singleton("x"));
		assertEquals("(-1/sqrt((1-(x*x))))", expression.derivative("x").toString());
	}

	@Test
	void testDerivativeArctg() {
		Expression expression = new Parser().compile("arctg(x)", Collections.singleton("x"));
		assertEquals("(1/(1+(x*x)))", expression.derivative("x").toString());
	}

	@Test
	void testDerivativeSh() {
		Expression expression = new Parser().compile("sh(x)", Collections.singleton("x"));
		assertEquals("ch(x)", expression.derivative("x").toString());
	}

	@Test
	void testDerivativeCh() {
		Expression expression = new Parser().compile("ch(x)", Collections.singleton("x"));
		assertEquals("sh(x)", expression.derivative("x").toString());
	}

	@Test
	void testDerivativeTh() {
		Expression expression = new Parser().compile("th(x)", Collections.singleton("x"));
		assertEquals("(1/(ch(x)^2))", expression.derivative("x").toString());
	}

	@Test
	void testDerivativeAbs() {
		Expression expression = new Parser().compile("abs(x)", Collections.singleton("x"));
		assertEquals("sign(x)", expression.derivative("x").toString());
	}

	@Test
	void testDerivativeSign() {
		Expression expression = new Parser().compile("sign(x)", Collections.singleton("x"));
		assertEquals("0.0", expression.derivative("x").toString());
	}
}
