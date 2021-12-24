package com.github.jxen.math.unicode;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * {@code NumberHelper} class is responsible for representing numbers with given Unicode symbols.
 *
 * @author Denis Murashev
 *
 * @since Math 0.3
 */
public class NumberHelper implements Serializable {

  private static final long serialVersionUID = 2207690332607493900L;

  private static final char MINUS = '-';
  private static final char ZERO = '0';

  private final char[] digits;
  private final char minus;
  private final Map<Character, Character> map = new HashMap<>();

  /**
   * Initializes with given values.
   *
   * @param digits digits
   * @param plus   plus sign
   * @param minus  minus sign
   */
  protected NumberHelper(char[] digits, char plus, char minus) {
    this.digits = digits.clone();
    this.minus = minus;
    int i = 0;
    for (char c : digits) {
      map.put(c, (char) (ZERO + i));
      i++;
    }
    map.put(minus, MINUS);
    map.put(plus, '+');
  }

  /**
   * Converts given value into Unicode representation.
   *
   * @param value value
   * @return Unicode representation
   */
  public final String to(String value) {
    char[] chars = value.toCharArray();
    int start = 0;
    if (chars[0] == '-') {
      chars[0] = minus;
      start++;
    }
    for (int i = start; i < value.length(); i++) {
      chars[i] = digits[chars[i] - ZERO];
    }
    return new String(chars);
  }

  /**
   * Converts given Unicode representation into regular String representation.
   *
   * @param value Unicode value representation
   * @return regular String representation
   */
  public final String from(String value) {
    char[] chars = value.toCharArray();
    for (int i = 0; i < value.length(); i++) {
      chars[i] = Optional.ofNullable(map.get(chars[i]))
          .orElseThrow(() -> new NumberFormatException("Unparseable value: " + value));
    }
    return new String(chars);
  }
}
