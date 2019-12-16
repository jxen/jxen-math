package com.github.jxen.math.format;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Locale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnicodeScientificFormatTest {

	@BeforeEach
	void setUp() {
		Locale.setDefault(Locale.US);
	}

	@Test
	void testFormatCase1() {
		String actual = new UnicodeScientificFormat(4).format(1);
		assertEquals("1", actual);
	}

	@Test
	void testFormatCase2() {
		String actual = new UnicodeScientificFormat(4).format(10.5);
		assertEquals("1.05\u00D710\u00B9", actual);
	}

	@Test
	void testFormatCase3() {
		String actual = new UnicodeScientificFormat(4).format(0.01057501);
		assertEquals("1.0575\u00D710\u207B\u00B2", actual);
	}

	@Test
	void testParseCase1() {
		Number actual = new UnicodeScientificFormat(4).parse("1");
		assertEquals(BigDecimal.ONE, actual);
	}

	@Test
	void testParseCase2() {
		Number actual = new UnicodeScientificFormat(4).parse("1\u00D710\u00B9");
		assertEquals(BigDecimal.TEN, actual);
	}

	@Test
	void testParseCase3() {
		Number actual = new UnicodeScientificFormat(4).parse("1\u00D710\u207B\u00B9");
		assertEquals(new BigDecimal("0.1"), actual);
	}

	@Test
	void testParseFailure() {
		assertThrows(NumberFormatException.class, () -> new UnicodeScientificFormat(4).parse("1\u00D7100"));
	}
}
