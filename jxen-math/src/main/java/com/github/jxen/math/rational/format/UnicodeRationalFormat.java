package com.github.jxen.math.rational.format;

import com.github.jxen.math.common.MathException;
import com.github.jxen.math.rational.Rational;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * {@code UnicodeRationalFormat} class is responsible for rational (fractional) values formatting
 * using special Unicode symbols.
 *
 * @author Denis Murashev
 * @since Math 0.1
 */
public class UnicodeRationalFormat extends RationalFormat {

	private static final long serialVersionUID = 983461923548214606L;

	private static final char[] NUMERATORS = {
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

	private static final char[] DENOMINATORS = {
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
		s.put(Rational.ONE_EIGHTH, "\u215B");   // 1/8
		s.put(Rational.THREE_EIGHTH, "\u215C"); // 3/8
		s.put(Rational.FIVE_EIGHTH, "\u215D");  // 5/8
		s.put(Rational.SEVEN_EIGHTH, "\u215E"); // 7/8

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

		static final UnicodeHelper NUMERATOR_HELPER = new UnicodeHelper(NUMERATORS);
		static final UnicodeHelper DENOMINATOR_HELPER = new UnicodeHelper(DENOMINATORS);

		private static final long serialVersionUID = 3223211785737519327L;

		private static final String ERROR = "Unparsable entity: ";
		private static final long TEN_LONG = 10;

		private final char[] digits;

		private UnicodeHelper(char[] digits) {
			this.digits = digits.clone();
		}

		@Override
		public String format(long value) {
			Deque<Integer> stack = new ArrayDeque<>();
			for (long n = value; n > 0; n /= TEN_LONG) {
				stack.push((int) (n % TEN_LONG));
			}
			StringBuilder builder = new StringBuilder();
			while (!stack.isEmpty()) {
				builder.append(digits[stack.pop()]);
			}
			return builder.toString();
		}

		@Override
		public String format(BigInteger value) {
			Deque<Integer> stack = new ArrayDeque<>();
			for (BigInteger n = value; n.signum() > 0; n = n.divide(BigInteger.TEN)) {
				stack.push(n.mod(BigInteger.TEN).intValue());
			}
			StringBuilder builder = new StringBuilder();
			while (!stack.isEmpty()) {
				builder.append(digits[stack.pop()]);
			}
			return builder.toString();
		}

		@Override
		public long parseLong(String value) {
			String text = new String(digits);
			long result = 0;
			for (char c : value.toCharArray()) {
				int index = text.indexOf(c);
				if (index == -1) {
					throw new MathException(ERROR + value);
				}
				result = result * TEN_LONG + index;
			}
			return result;
		}

		@Override
		public BigInteger parseBigInteger(String value) {
			String text = new String(digits);
			BigInteger result = BigInteger.ZERO;
			for (char c : value.toCharArray()) {
				int index = text.indexOf(c);
				if (index == -1) {
					throw new MathException(ERROR + value);
				}
				result = result.multiply(BigInteger.TEN).add(BigInteger.valueOf(index));
			}
			return result;
		}
	}
}
