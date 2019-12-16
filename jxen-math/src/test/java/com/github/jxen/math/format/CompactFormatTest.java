package com.github.jxen.math.format;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CompactFormatTest {

	@Test
	void testFormatCase1() {
		assertEquals("1", new CompactFormat(4).format(1));
	}

	@Test
	void testFormatCase2() {
		assertEquals("1", new CompactFormat(0).format(1));
	}

	@Test
	void testFormatCase3() {
		assertEquals("1E6", new CompactFormat(2).format(1000000));
	}

	@Test
	void testFormatCase4() {
		assertEquals("1E-6", new CompactFormat(2).format(0.000001));
	}
}
