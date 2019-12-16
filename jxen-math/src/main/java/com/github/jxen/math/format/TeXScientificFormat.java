package com.github.jxen.math.format;

import java.math.BigDecimal;
import java.text.FieldPosition;

/**
 * {@code TeXScientificFormat} class is responsible to scientific format for TeX.
 *
 * @author Denis Murashev
 *
 * @since Math 0.3
 */
public class TeXScientificFormat extends ScientificFormat {

	private static final long serialVersionUID = -5080572483615858963L;

	private static final String TIMES = "\\times10^";

	/**
	 * @param digits digits
	 */
	public TeXScientificFormat(int digits) {
		super(digits);
	}

	@Override
	public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos) {
		return format(number, TIMES, toAppendTo, pos);
	}

	@Override
	public Number parse(String source) {
		int index = source.indexOf(TIMES);
		if (index == -1) {
			return new BigDecimal(source);
		}
		BigDecimal value = new BigDecimal(source.substring(0, index));
		int exponent = Integer.parseInt(source.substring(index + TIMES.length()));
		return value.multiply(BigDecimal.TEN.pow(exponent));
	}
}
