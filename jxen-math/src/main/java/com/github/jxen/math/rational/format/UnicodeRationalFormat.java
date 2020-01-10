package com.github.jxen.math.rational.format;

import com.github.jxen.math.rational.Rational;
import com.github.jxen.math.unicode.NumberHelper;
import com.github.jxen.math.unicode.SubscriptHelper;
import com.github.jxen.math.unicode.SuperscriptHelper;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * {@code UnicodeRationalFormat} class is responsible for rational (fractional) values formatting
 * using special Unicode symbols.
 *
 * @author Denis Murashev
 *
 * @since Math 0.1
 */
public class UnicodeRationalFormat extends RationalFormat {

	private static final long serialVersionUID = 983461923548214606L;

	private static final Map<Rational, String> SHORTS;

	private static final Map<String, Rational> REVERSE;

	static {
		Map<Rational, String> s = new HashMap<>();
		s.put(Rational.ONE_FOURTH, "\u00BC");   // 1/4
		s.put(Rational.HALF, "\u00BD");         // 1/2
		s.put(Rational.THREE_FOURTH, "\u00BE"); // 3/4
		s.put(Rational.ONE_THIRD, "\u2153");    // 1/3
		s.put(Rational.TWO_THIRD, "\u2154");    // 2/3
		s.put(Rational.ONE_FIFTH, "\u2155");    // 1/5
		s.put(Rational.TWO_FIFTH, "\u2156");    // 2/5
		s.put(Rational.THREE_FIFTH, "\u2157");  // 3/5
		s.put(Rational.FOUR_FIFTH, "\u2158");   // 4/5
		s.put(Rational.ONE_SIXTH, "\u2159");    // 1/6
		s.put(Rational.FIVE_SIXTH, "\u215A");   // 5/6
		s.put(Rational.ONE_SEVENTH, "\u2150");  // 1/7
		s.put(Rational.ONE_EIGHTH, "\u215B");   // 1/8
		s.put(Rational.THREE_EIGHTH, "\u215C"); // 3/8
		s.put(Rational.FIVE_EIGHTH, "\u215D");  // 5/8
		s.put(Rational.SEVEN_EIGHTH, "\u215E"); // 7/8
		s.put(Rational.ONE_NINTH, "\u2151");    // 1/9
		s.put(Rational.ONE_TENTH, "\u2152");    // 1/10

		Map<String, Rational> r = new HashMap<>();
		s.forEach((key, value) -> r.put(value, key));

		SHORTS = Collections.unmodifiableMap(s);
		REVERSE = Collections.unmodifiableMap(r);
	}

	private final boolean compact;

	/**
	 * Initializes with default helpers.
	 */
	public UnicodeRationalFormat() {
		this(false);
	}

	/**
	 * @param compact compact
	 */
	public UnicodeRationalFormat(boolean compact) {
		super(UnicodeHelper.NUMERATOR_HELPER, UnicodeHelper.DENOMINATOR_HELPER);
		this.compact = compact;
	}

	@Override
	protected String getSeparator() {
		return "";
	}

	@Override
	protected String getFraction() {
		return "\u2044"; // Fraction
	}

	@Override
	protected String getShort(long numerator, long denominator) {
		if (compact) {
			return SHORTS.get(new Rational(numerator, denominator));
		}
		return null;
	}

	@Override
	protected Rational lookForShort(String value) {
		return REVERSE.get(value);
	}

	private static final class UnicodeHelper implements Helper {

		static final UnicodeHelper NUMERATOR_HELPER = new UnicodeHelper(new SuperscriptHelper());
		static final UnicodeHelper DENOMINATOR_HELPER = new UnicodeHelper(new SubscriptHelper());

		private static final long serialVersionUID = -8004187890154211343L;

		private final NumberHelper helper;

		private UnicodeHelper(NumberHelper helper) {
			this.helper = helper;
		}

		@Override
		public String format(long value) {
			return helper.to(String.valueOf(value));
		}

		@Override
		public String format(BigInteger value) {
			return helper.to(value.toString());
		}

		@Override
		public long parseLong(String value) {
			return Long.parseLong(helper.from(value));
		}

		@Override
		public BigInteger parseBigInteger(String value) {
			return new BigInteger(helper.from(value));
		}
	}
}
