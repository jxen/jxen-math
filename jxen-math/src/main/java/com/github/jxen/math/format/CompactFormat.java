package com.github.jxen.math.format;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

/**
 * {@code CompactFormat} class is responsible to compact numeric format.
 *
 * @author Denis Murashev
 *
 * @since Math 0.3
 */
public class CompactFormat extends NumberFormat {

  private static final long serialVersionUID = -4846937367902216199L;

  private static final double TEN = 10;
  private static final double MIN = 1 / TEN / TEN;

  private final int digits;
  private final double max;

  /**
   * Initializes with given value.
   *
   * @param digits number of digits to display
   */
  public CompactFormat(int digits) {
    this.digits = digits;
    max = Math.pow(TEN, digits + 2);
  }

  @Override
  public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos) {
    double abs = Math.abs(number);
    if (abs > max || abs < MIN) {
      return getScientificFormat(digits - 1).format(number, toAppendTo, pos);
    }
    int count = digits - (int) Math.floor(Math.log10(abs)) - 1;
    return new DecimalFormat(normal(count)).format(number, toAppendTo, pos);
  }

  @Override
  public StringBuffer format(long number, StringBuffer toAppendTo, FieldPosition pos) {
    return format((double) number, toAppendTo, pos);
  }

  /**
   * The implementation should provide scientific format.
   *
   * @param digitCount digit count
   * @return scientific format
   */
  protected ScientificFormat getScientificFormat(int digitCount) {
    return new ScientificFormat(digitCount);
  }

  private String normal(int digits) {
    if (digits <= 0) {
      return "0";
    }
    StringBuilder builder = new StringBuilder("0.");
    for (int i = 0; i < digits; i++) {
      builder.append('#');
    }
    return builder.toString();
  }

  @Override
  public Number parse(String source, ParsePosition parsePosition) {
    return new BigDecimal(source);
  }
}
