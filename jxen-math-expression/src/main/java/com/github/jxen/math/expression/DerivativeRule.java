package com.github.jxen.math.expression;

interface DerivativeRule<T extends AbstractNode> {

	AbstractNode derivative(T node, String arg);
}
