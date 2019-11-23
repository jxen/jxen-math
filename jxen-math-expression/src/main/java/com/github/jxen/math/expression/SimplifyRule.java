package com.github.jxen.math.expression;

interface SimplifyRule {

	AbstractNode simplify(BinaryNode node);
}
