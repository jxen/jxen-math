package com.github.jxen.math.format;

/**
 * {@code TeXCompactFormat} class is responsible to scientific format for TeX.
 *
 * @author Denis Murashev
 *
 * @since Math 0.3
 */
public class TeXCompactFormat extends CompactFormat {

	private static final long serialVersionUID = -3814614314718100565L;

	/**
	 * @param digits number of digits to display
	 */
	public TeXCompactFormat(int digits) {
		super(digits);
	}

	@Override
	protected ScientificFormat getScientificFormat(int digitCount) {
		return new TeXScientificFormat(digitCount);
	}
}
