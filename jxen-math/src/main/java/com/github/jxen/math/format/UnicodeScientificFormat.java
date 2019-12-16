package com.github.jxen.math.format;

import static java.math.RoundingMode.UNNECESSARY;

import com.github.jxen.math.unicode.SuperscriptHelper;
import java.math.BigDecimal;
import java.text.FieldPosition;

/**
 * {@code UnicodeScientificFormat} class is responsible to scientific format using Unicode numbers.
 *
 * @author Denis Murashev
 *
 * @since Math 0.3
 */
public class UnicodeScientificFormat extends ScientificFormat {

	private static final long serialVersionUID = 9069918568699541555L;

	private static final SuperscriptHelper SUPERSCRIPT = new SuperscriptHelper();

	private static final String TIMES = "\u00D710";  // times symbol

	/**
	 * @param digits digits
	 */
	public UnicodeScientificFormat(int digits) {
		super(digits);
	}

	@Override
	public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos) {
		return format(number, TIMES, toAppendTo, pos);
	}

	@Override
	String toSup(String value) {
		return SUPERSCRIPT.to(value);
	}

	@Override
	public Number parse(String source) {
		int index = source.indexOf(TIMES);
		if (index == -1) {
			return new BigDecimal(source);
		}
		BigDecimal value = new BigDecimal(source.substring(0, index));
		int exponent = Integer.parseInt(SUPERSCRIPT.from(source.substring(index + TIMES.length())));
		if (exponent >= 0) {
			return value.multiply(BigDecimal.TEN.pow(exponent));
		}
		return value.setScale(-exponent, UNNECESSARY).divide(BigDecimal.TEN.pow(-exponent), UNNECESSARY);
	}
}
