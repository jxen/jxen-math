package com.github.jxen.math.unicode;

/**
 * {@code SuperscriptHelper} class is responsible for converting to and from superscript Unicode symbols.
 *
 * @author Denis Murashev
 *
 * @since Math 0.3
 */
public class SuperscriptHelper extends NumberHelper {

	private static final long serialVersionUID = -6586915411124075788L;

	private static final char[] DIGITS = {
			'\u2070', // 0
			'\u00B9', // 1
			'\u00B2', // 2
			'\u00B3', // 3
			'\u2074', // 4
			'\u2075', // 5
			'\u2076', // 6
			'\u2077', // 7
			'\u2078', // 8
			'\u2079', // 9
	};

	private static final char PLUS = '\u207A'; // plus

	private static final char MINUS = '\u207B'; // minus

	/**
	 * Initializes instance.
	 */
	public SuperscriptHelper() {
		super(DIGITS, PLUS, MINUS);
	}
}
