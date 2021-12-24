package com.github.jxen.math.rational.format;

import com.github.jxen.math.common.MathException;
import com.github.jxen.math.rational.BigRational;
import com.github.jxen.math.rational.Rational;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@code RationalFormat} class is responsible for rational (fractional) values formatting.
 *
 * @author Denis Murashev
 * @since Math 0.1
 */
public class RationalFormat extends NumberFormat {

  private static final long serialVersionUID = -360721434914020723L;

  private static final String ZERO = "0";

  private final Helper integralHelper = new DefaultHelper();
  private final Helper numeratorHelper;
  private final Helper denominatorHelper;

  /**
   * Initializes with default helpers.
   */
  public RationalFormat() {
    this(new DefaultHelper(), new DefaultHelper());
  }

  /**
   * Initializes with given values.
   *
   * @param numeratorHelper   numerator helper
   * @param denominatorHelper denominator helper
   */
  protected RationalFormat(Helper numeratorHelper, Helper denominatorHelper) {
    this.numeratorHelper = numeratorHelper;
    this.denominatorHelper = denominatorHelper;
  }

  @Override
  public StringBuffer format(Object number, StringBuffer toAppendTo, FieldPosition pos) {
    if (number instanceof Rational) {
      return format((Rational) number, toAppendTo);
    }
    if (number instanceof BigRational) {
      return format((BigRational) number, toAppendTo);
    }
    return DecimalFormat.getInstance().format(number, toAppendTo, pos);
  }

  @Override
  public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos) {
    throw new UnsupportedOperationException("RationalFormat cannot format floating point values");
  }

  @Override
  public StringBuffer format(long number, StringBuffer toAppendTo, FieldPosition pos) {
    throw new UnsupportedOperationException("RationalFormat cannot format integer values");
  }

  private StringBuffer format(Rational number, StringBuffer toAppendTo) {
    if (Rational.ZERO.equals(number)) {
      return toAppendTo.append(ZERO);
    }
    long integral = number.getIntegral();
    if (integral != 0) {
      toAppendTo.append(integralHelper.format(integral));
    }
    long numerator = number.getNumerator();
    if (numerator != 0) {
      if (integral != 0) {
        toAppendTo.append(getSeparator());
      }
      String s = getShort(numerator, number.getDenominator());
      if (Objects.nonNull(s)) {
        toAppendTo.append(s);
      } else {
        toAppendTo.append(numeratorHelper.format(numerator))
            .append(getFraction())
            .append(denominatorHelper.format(number.getDenominator()));
      }
    }
    return toAppendTo;
  }

  private StringBuffer format(BigRational number, StringBuffer toAppendTo) {
    if (BigRational.ZERO.equals(number)) {
      return toAppendTo.append(ZERO);
    }
    BigInteger integral = number.getIntegral();
    if (integral.compareTo(BigInteger.ZERO) != 0) {
      toAppendTo.append(integralHelper.format(integral));
    }
    BigInteger numerator = number.getNumerator();
    if (numerator.compareTo(BigInteger.ZERO) != 0) {
      if (integral.compareTo(BigInteger.ZERO) != 0) {
        toAppendTo.append(getSeparator());
      }
      String s = getShort(numerator.longValue(), number.getDenominator().longValue());
      if (Objects.nonNull(s)) {
        toAppendTo.append(s);
      } else {
        toAppendTo.append(numeratorHelper.format(numerator))
            .append(getFraction())
            .append(denominatorHelper.format(number.getDenominator()));
      }
    }
    return toAppendTo;
  }

  /**
   * The implementation should provide separator between integral and fractional parts.
   *
   * @return separator between integral and fractional parts
   */
  protected String getSeparator() {
    return " ";
  }

  /**
   * The implementation should provide fraction symbol.
   *
   * @return separator between numerator and denominator parts
   */
  protected String getFraction() {
    return "/";
  }

  /**
   * The implementation should provide short value representation.
   *
   * @param numerator   numerator
   * @param denominator denominator
   * @return short string representation
   */
  protected String getShort(long numerator, long denominator) {
    return null;
  }

  @Override
  public Number parse(String source, ParsePosition parsePosition) {
    return parse(source);
  }

  @Override
  public Number parse(String source) {
    final int maxLong = 17;
    final String empty = "";
    Pattern pattern = Pattern.compile("^[+-]?\\d+");
    Matcher matcher = pattern.matcher(source);
    String integral = matcher.find() ? matcher.group() : empty;
    String fraction = source.substring(integral.length()).trim();
    int index = fraction.indexOf(getFraction());
    if (index == -1 && !fraction.isEmpty()) {
      Rational rational = lookForShort(fraction);
      if (Objects.nonNull(rational)) {
        return integral.length() > maxLong
            ? new BigRational(integralHelper.parseBigInteger(integral)).plus(rational)
            : new Rational(integralHelper.parseLong(integral)).plus(rational);
      }
      throw new MathException("Unparsable source: " + source);
    }
    String numerator = index == -1 ? empty : fraction.substring(0, index).trim();
    String denominator = index == -1 ? empty : fraction.substring(index + 1).trim();
    if (index == 0) {
      numerator = integral;
      integral = empty;
    }
    int length = integral.length();
    length = Math.max(length, numerator.length());
    length = Math.max(length, denominator.length());
    if (length > maxLong) {
      return buildBigRational(integral, numerator, denominator);
    }
    return buildRational(integral, numerator, denominator);
  }

  /**
   * The implementation should provide short value representation.
   *
   * @param value value
   * @return Rational instance if exist or null otherwise
   */
  protected Rational lookForShort(String value) {
    return null;
  }

  private Number buildRational(String integral, String numerator, String denominator) {
    long x = integralHelper.parseLong(integral);
    return numerator.isEmpty() ? new Rational(x)
        : new Rational(x, numeratorHelper.parseLong(numerator), denominatorHelper.parseLong(denominator));
  }

  private Number buildBigRational(String integral, String numerator, String denominator) {
    BigInteger x = integralHelper.parseBigInteger(integral);
    return numerator.isEmpty() ? new BigRational(x)
        : new BigRational(x, numeratorHelper.parseBigInteger(numerator),
        denominatorHelper.parseBigInteger(denominator));
  }

  /**
   * {@code Helper} interface.
   */
  protected interface Helper extends Serializable {

    /**
     * Formats.
     *
     * @param value value
     * @return formatted value
     */
    String format(long value);

    /**
     * Formats.
     *
     * @param value value
     * @return formatted value
     */
    String format(BigInteger value);

    /**
     * Parses.
     *
     * @param value value
     * @return parsed value
     */
    long parseLong(String value);

    /**
     * Parses.
     *
     * @param value value
     * @return parsed value
     */
    BigInteger parseBigInteger(String value);
  }

  private static class DefaultHelper implements Helper {

    private static final long serialVersionUID = -1176076135554816664L;

    @Override
    public String format(long value) {
      return String.valueOf(value);
    }

    @Override
    public String format(BigInteger value) {
      return String.valueOf(value);
    }

    @Override
    public long parseLong(String value) {
      if (value.isEmpty()) {
        return 0;
      }
      return Long.parseLong(value);
    }

    @Override
    public BigInteger parseBigInteger(String value) {
      if (value.isEmpty()) {
        return BigInteger.ZERO;
      }
      return new BigInteger(value);
    }
  }
}
