package com.github.jxen.math.format;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TeXCompactFormatTest {

	@Test
	void testFormatCase1() {
		assertEquals("1\\times10^10", new TeXCompactFormat(4).format(1e10));
	}
}
