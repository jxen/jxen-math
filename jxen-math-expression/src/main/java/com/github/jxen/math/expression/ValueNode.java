package com.github.jxen.math.expression;

abstract class ValueNode extends AbstractNode {

  ValueNode(Token token) {
    super(token);
  }

  ValueNode(String name) {
    super(name);
  }

  @Override
  AbstractNode simplify() {
    return this;
  }

  @Override
  void setChildren(AbstractNode left, AbstractNode right) {
    if (left != null && right != null) {
      throw new ExpressionException("Unexpected part: ", left.getToken().getPosition(), left.getName().length());
    }
  }
}
