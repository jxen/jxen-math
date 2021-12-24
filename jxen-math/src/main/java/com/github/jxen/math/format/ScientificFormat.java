package com.github.jxen.math.format;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

/**
 * {@code ScientificFormat} class is responsible to scientific format.
 *
 * @author Denis Murashev
 *
 * @since Math 0.3
 */
public class ScientificFormat extends NumberFormat {

  private static final long serialVersionUID = 757666263979902559L;

  private static final String E_ZERO = "E0";

  private final String format;

  /**
   * Initializes with given value.
   *
   * @param digits digits
   */
  public ScientificFormat(int digits) {
    if (digits <= 0) {
      format = "0E0";
    } else {
      StringBuilder builder = new StringBuilder("0.");
      builder.append("#".repeat(digits));
      format = builder.append(E_ZERO).toString();
    }
  }

  @Override
  public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos) {
    return new DecimalFormat(format).format(number, toAppendTo, pos);
  }

  @Override
  public StringBuffer format(long number, StringBuffer toAppendTo, FieldPosition pos) {
    return format((double) number, toAppendTo, pos);
  }

  final StringBuffer format(double number, String times, StringBuffer toAppendTo, FieldPosition pos) {
    String value = new DecimalFormat(format).format(number);
    if (value.endsWith(E_ZERO)) {
      return toAppendTo.append(value, 0, value.length() - 2);
    }
    int index = value.indexOf('E');
    return toAppendTo.append(value, 0, index)
        .append(times)
        .append(toSup(value.substring(index + 1)));
  }

  /**
   * The implementation should provide given value representation as superscript.
   *
   * @param value value
   * @return representation
   */
  String toSup(String value) {
    return value;
  }

  @Override
  public Number parse(String source, ParsePosition parsePosition) {
    return parse(source);
  }

  @Override
  public Number parse(String source) {
    return new BigDecimal(source);
  }
}
