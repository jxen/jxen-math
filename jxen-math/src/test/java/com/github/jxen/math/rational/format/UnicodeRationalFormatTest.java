package com.github.jxen.math.rational.format;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.jxen.math.common.MathException;
import com.github.jxen.math.rational.BigRational;
import com.github.jxen.math.rational.Rational;
import java.math.BigInteger;
import java.text.DecimalFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UnicodeRationalFormatTest {

	@Test
	void testFormatDoublePrimitive() {
		Assertions.assertThrows(UnsupportedOperationException.class, () -> new UnicodeRationalFormat().format(1.5));
	}

	@Test
	void testFormatLongPrimitive() {
		Assertions.assertThrows(UnsupportedOperationException.class, () -> new UnicodeRationalFormat().format(2));
	}

	@Test
	void testFormatDouble() {
		String actual = new UnicodeRationalFormat().format(Double.valueOf(1.5));
		assertEquals(DecimalFormat.getInstance().format(1.5), actual);
	}

	@Test
	void testFormatLong() {
		String actual = new UnicodeRationalFormat().format(Long.valueOf(2));
		assertEquals("2", actual);
	}

	@Test
	void testFormatRational() {
		String actual = new UnicodeRationalFormat().format(Rational.valueOf(1.5));
		assertEquals("1\u00B9\u2044\u2082", actual);
	}

	@Test
	void testFormatRational2() {
		String actual = new UnicodeRationalFormat().format(Rational.valueOf(2));
		assertEquals("2", actual);
	}

	@Test
	void testFormatRationalCompact() {
		String actual = new UnicodeRationalFormat(true).format(Rational.valueOf(1.5));
		assertEquals("1\u00BD", actual);
	}

	@Test
	void testFormatBigRational() {
		String actual = new UnicodeRationalFormat().format(BigRational.valueOf(1.5));
		assertEquals("1\u00B9\u2044\u2082", actual);
	}

	@Test
	void testFormatBigRational2() {
		String actual = new UnicodeRationalFormat().format(BigRational.valueOf(2));
		assertEquals("2", actual);
	}

	@Test
	void testFormatBigRationalCompact() {
		String actual = new UnicodeRationalFormat(true).format(BigRational.valueOf(1.5));
		assertEquals("1\u00BD", actual);
	}

	@Test
	void testParseFailure() {
		assertThrows(MathException.class, () -> new UnicodeRationalFormat().parse("a"));
	}

	@Test
	void testParse1() {
		Number actual = new UnicodeRationalFormat().parse("1");
		assertTrue(actual instanceof Rational);
		assertEquals(Rational.ONE, actual);
	}

	@Test
	void testParse2() {
		Number actual = new UnicodeRationalFormat().parse("\u00B9\u2044\u2082");
		assertTrue(actual instanceof Rational);
		assertEquals(Rational.HALF, actual);
	}

	@Test
	void testParse3() {
		Number actual = new UnicodeRationalFormat().parse("1\u00B9\u2044\u2082");
		assertTrue(actual instanceof Rational);
		assertEquals(new Rational(3, 2), actual);
	}

	@Test
	void testParse4() {
		Number actual = new UnicodeRationalFormat().parse("\u00BD");
		assertTrue(actual instanceof Rational);
		assertEquals(Rational.HALF, actual);
	}

	@Test
	void testParse5() {
		Number actual = new UnicodeRationalFormat().parse("1\u00BD");
		assertTrue(actual instanceof Rational);
		assertEquals(new Rational(3, 2), actual);
	}

	@Test
	void testParseLongFailure() {
		String source = "1\u2044\u2082\u2080\u2080\u2080\u2080\u2080\u2080\u2080\u2080\u2080\u2080\u2080\u2080";
		assertThrows(MathException.class, () -> new UnicodeRationalFormat().parse(source));
	}

	@Test
	void testParseBigRational1() {
		String source = "\u00B9\u2044\u2082\u2080\u2080\u2080\u2080\u2080\u2080\u2080\u2080\u2080\u2080\u2080\u2080"
				+ "\u2080\u2080\u2080\u2080\u2080\u2080\u2080";
		Number actual = new UnicodeRationalFormat().parse(source);
		assertEquals(new BigRational(BigInteger.ONE, new BigInteger("20000000000000000000")), actual);
	}

	@Test
	void testParseBigRational2() {
		String source = "20000000000000000000\u00BD";
		Number actual = new UnicodeRationalFormat().parse(source);
		BigRational expected = new BigRational(new BigInteger("20000000000000000000"), BigInteger.ONE,
				BigInteger.valueOf(2));
		assertEquals(expected, actual);
	}

	@Test
	void testParseBigRationalFailure() {
		String source = "1\u2044\u2082\u2080\u2080\u2080\u2080\u2080\u2080\u2080\u2080\u2080\u2080\u2080\u2080"
				+ "\u2080\u2080\u2080\u2080\u2080\u2080\u2080";
		assertThrows(MathException.class, () -> new UnicodeRationalFormat().parse(source));
	}
}
