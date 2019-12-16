package com.github.jxen.math.format;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UnicodeCompactFormatTest {

	@Test
	void testFormatCase1() {
		assertEquals("1\u00D710\u00B9\u2070", new UnicodeCompactFormat(4).format(1e10));
	}
}
