package com.github.jxen.math.expression;

import java.util.function.DoubleUnaryOperator;

/**
 * {@code MathFunction} class represents mathematical function.
 *
 * @author Denis Murashev
 * @since Math Expression Parser 0.1
 */
public final class MathFunction {

  private final String name;
  private final DoubleUnaryOperator operator;

  /**
   * Initializes with given values.
   *
   * @param name     name
   * @param operator operator
   */
  public MathFunction(String name, DoubleUnaryOperator operator) {
    this.name = name;
    this.operator = operator;
  }

  /**
   * Provides function name.
   *
   * @return function name
   */
  public String getName() {
    return name;
  }

  /**
   * Evaluates function value.
   *
   * @param arg argument
   * @return function result
   */
  public double value(double arg) {
    return operator.applyAsDouble(arg);
  }
}
