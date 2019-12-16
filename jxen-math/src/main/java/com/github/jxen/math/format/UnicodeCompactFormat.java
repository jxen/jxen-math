package com.github.jxen.math.format;

/**
 * {@code UnicodeCompactFormat} class is responsible to scientific format using Unicode numbers.
 *
 * @author Denis Murashev
 *
 * @since Math 0.3
 */
public class UnicodeCompactFormat extends CompactFormat {

	private static final long serialVersionUID = 5582591146669400500L;

	/**
	 * @param digits number of digits to display
	 */
	public UnicodeCompactFormat(int digits) {
		super(digits);
	}

	@Override
	protected ScientificFormat getScientificFormat(int digitCount) {
		return new UnicodeScientificFormat(digitCount);
	}
}
