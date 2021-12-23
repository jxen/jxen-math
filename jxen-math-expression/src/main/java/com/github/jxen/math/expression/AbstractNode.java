package com.github.jxen.math.expression;

import java.util.Map;
import java.util.Set;

abstract class AbstractNode {

  private final Token token;
  private final String name;

  AbstractNode(Token token) {
    this.token = token;
    this.name = token.getValue();
  }

  AbstractNode(String name) {
    this.token = new Token(name, -1);
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }

  Token getToken() {
    return token;
  }

  String getName() {
    return name;
  }

  int getPosition() {
    return token.getPosition();
  }

  abstract double evaluate(Map<String, ? extends Number> args);

  abstract AbstractNode simplify();

  abstract AbstractNode derivative(String arg);

  abstract void setChildren(AbstractNode left, AbstractNode right);

  abstract Set<String> getVariables();
}
