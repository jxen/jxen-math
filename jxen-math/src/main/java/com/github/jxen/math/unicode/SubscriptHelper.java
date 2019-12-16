package com.github.jxen.math.unicode;

/**
 * {@code SubscriptHelper} class is responsible for converting to and from subscript Unicode symbols.
 *
 * @author Denis Murashev
 *
 * @since Math 0.3
 */
public class SubscriptHelper extends NumberHelper {

	private static final long serialVersionUID = 340067492357713183L;

	private static final char[] DIGITS = {
			'\u2080', // 0
			'\u2081', // 1
			'\u2082', // 2
			'\u2083', // 3
			'\u2084', // 4
			'\u2085', // 5
			'\u2086', // 6
			'\u2087', // 7
			'\u2088', // 8
			'\u2089', // 9
	};

	private static final char PLUS = '\u208A'; // plus

	private static final char MINUS = '\u208B'; // minus

	/**
	 * Initializes instance.
	 */
	public SubscriptHelper() {
		super(DIGITS, PLUS, MINUS);
	}
}
