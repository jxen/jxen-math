package com.github.jxen.math.expression;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

final class Functions {

	static final MathFunction SQRT = new MathFunction("sqrt", Math::sqrt);

	static final MathFunction EXP = new MathFunction("exp", Math::exp);

	static final MathFunction LN = new MathFunction("ln", Math::log);

	static final MathFunction LOG = new MathFunction("log", Math::log10);

	static final MathFunction SIGN = new MathFunction("sign", Math::signum);

	static final MathFunction ABS = new MathFunction("abs", Math::abs);

	static final MathFunction SIN = new MathFunction("sin", Math::sin);

	static final MathFunction COS = new MathFunction("cos", Math::cos);

	static final MathFunction TG = new MathFunction("tg", Math::tan);

	static final MathFunction CTG = new MathFunction("ctg", x -> 1 / Math.tan(x));

	static final MathFunction ASIN = new MathFunction("arcsin", Math::asin);

	static final MathFunction ACOS = new MathFunction("arccos", Math::acos);

	static final MathFunction ATG = new MathFunction("arctg", Math::atan);

	static final MathFunction SH = new MathFunction("sh", Math::sinh);

	static final MathFunction CH = new MathFunction("ch", Math::cosh);

	static final MathFunction TH = new MathFunction("th", Math::tanh);

	private static final double HALF = 0.5;

	private static final Map<MathFunction, FunctionRule> RULES = new HashMap<>();

	private final Map<String, MathFunction> functionMap = new HashMap<>();

	static {
		RULES.put(SQRT, new SqrtRule());
		RULES.put(EXP, new ExpRule());
		RULES.put(LN, new LnRule());
		RULES.put(LOG, new LogRule());
		RULES.put(SIGN, new SignRule());
		RULES.put(ABS, new AbsRule());
		RULES.put(SIN, new SinRule());
		RULES.put(COS, new CosRule());
		RULES.put(TG, new TgRule());
		RULES.put(CTG, new CtgRule());
		RULES.put(ASIN, new ArcRule(ConstantNode.ONE));
		RULES.put(ACOS, new ArcRule(ConstantNode.MINUS_ONE));
		RULES.put(ATG, new AtgRule());
		RULES.put(SH, new ShRule());
		RULES.put(CH, new ChRule());
		RULES.put(TH, new ThRule());
	}

	Functions() {
		addFunction(SQRT);
		addFunction(EXP);
		addFunction(LN);
		addFunction(LOG);
		addFunction(SIGN);
		addFunction(ABS);
		addFunction(SIN);
		addFunction(COS);
		addFunction(TG);
		addFunction(CTG);
		addFunction(ASIN);
		addFunction(ACOS);
		addFunction(ATG);
		addFunction(SH);
		addFunction(CH);
		addFunction(TH);
	}

	FunctionNode getNode(Token token) {
		MathFunction function = functionMap.get(token.getValue());
		return Objects.nonNull(function) ? new FunctionNode(token, function) : null;
	}

	static DerivativeRule<FunctionNode> getRule(MathFunction function) {
		return RULES.get(function);
	}

	private void addFunction(MathFunction function) {
		functionMap.put(function.getName(), function);
	}

	private abstract static class FunctionRule implements DerivativeRule<FunctionNode> {

		@Override
		public final AbstractNode derivative(FunctionNode node, String arg) {
			BinaryNode result = new BinaryNode(Operators.MULTIPLY);
			result.setChildren(simpleDerivative(node), node.getNode().derivative(arg));
			return result;
		}

		abstract AbstractNode simpleDerivative(FunctionNode node);
	}

	private static class SqrtRule extends FunctionRule {

		@Override
		AbstractNode simpleDerivative(FunctionNode node) {
			FunctionNode function = new FunctionNode(SQRT);
			function.setChildren(null, node.getNode());
			BinaryNode result = new BinaryNode(Operators.DIVIDE);
			result.setChildren(new ConstantNode(HALF), function);
			return result;
		}
	}

	private static class ExpRule extends FunctionRule {

		@Override
		AbstractNode simpleDerivative(FunctionNode node) {
			FunctionNode result = new FunctionNode(EXP);
			result.setChildren(null, node.getNode());
			return result;
		}
	}

	private static class LnRule extends FunctionRule {

		@Override
		AbstractNode simpleDerivative(FunctionNode node) {
			BinaryNode result = new BinaryNode(Operators.DIVIDE);
			result.setChildren(ConstantNode.ONE, node.getNode());
			return result;
		}
	}

	private static class LogRule extends FunctionRule {

		@Override
		AbstractNode simpleDerivative(FunctionNode node) {
			final double ten = 10;
			BinaryNode result = new BinaryNode(Operators.MULTIPLY);
			result.setChildren(new ConstantNode(Math.log(ten)), node.getNode());
			return result;
		}
	}

	private static class SinRule extends FunctionRule {

		@Override
		AbstractNode simpleDerivative(FunctionNode node) {
			FunctionNode result = new FunctionNode(COS);
			result.setChildren(null, node.getNode());
			return result;
		}
	}

	private static class CosRule extends FunctionRule {

		@Override
		AbstractNode simpleDerivative(FunctionNode node) {
			AbstractNode left = ConstantNode.MINUS_ONE;
			FunctionNode right = new FunctionNode(SIN);
			right.setChildren(null, node.getNode());
			BinaryNode result = new BinaryNode(Operators.MULTIPLY);
			result.setChildren(left, right);
			return result;
		}
	}

	private static class TgRule extends FunctionRule {

		@Override
		AbstractNode simpleDerivative(FunctionNode node) {
			FunctionNode left = new FunctionNode(COS);
			left.setChildren(null, node.getNode());
			AbstractNode right = ConstantNode.TWO;
			BinaryNode bottom = new BinaryNode(Operators.POWER);
			bottom.setChildren(left, right);
			BinaryNode result = new BinaryNode(Operators.DIVIDE);
			result.setChildren(ConstantNode.ONE, bottom);
			return result;
		}
	}

	private static class CtgRule extends FunctionRule {

		@Override
		AbstractNode simpleDerivative(FunctionNode node) {
			FunctionNode left = new FunctionNode(SIN);
			left.setChildren(null, node.getNode());
			AbstractNode right = ConstantNode.TWO;
			BinaryNode bottom = new BinaryNode(Operators.POWER);
			bottom.setChildren(left, right);
			BinaryNode result = new BinaryNode(Operators.DIVIDE);
			result.setChildren(ConstantNode.MINUS_ONE, bottom);
			return result;
		}
	}

	private static class ArcRule extends FunctionRule {

		private final ConstantNode constantNode;

		ArcRule(ConstantNode constantNode) {
			this.constantNode = constantNode;
		}

		@Override
		AbstractNode simpleDerivative(FunctionNode node) {
			BinaryNode right = new BinaryNode(Operators.MULTIPLY);
			right.setChildren(node.getNode(), node.getNode());
			AbstractNode difference = new BinaryNode(Operators.MINUS);
			difference.setChildren(ConstantNode.ONE, right);
			AbstractNode bottom = new FunctionNode(SQRT);
			bottom.setChildren(null, difference);
			BinaryNode result = new BinaryNode(Operators.DIVIDE);
			result.setChildren(constantNode, bottom);
			return result;
		}
	}

	private static class AtgRule extends FunctionRule {

		@Override
		AbstractNode simpleDerivative(FunctionNode node) {
			AbstractNode left = ConstantNode.ONE;
			BinaryNode right = new BinaryNode(Operators.MULTIPLY);
			right.setChildren(node.getNode(), node.getNode());
			AbstractNode bottom = new BinaryNode(Operators.PLUS);
			bottom.setChildren(left, right);
			BinaryNode result = new BinaryNode(Operators.DIVIDE);
			result.setChildren(ConstantNode.ONE, bottom);
			return result;
		}
	}

	private static class ShRule extends FunctionRule {

		@Override
		AbstractNode simpleDerivative(FunctionNode node) {
			FunctionNode result = new FunctionNode(CH);
			result.setChildren(null, node.getNode());
			return result;
		}
	}

	private static class ChRule extends FunctionRule {

		@Override
		AbstractNode simpleDerivative(FunctionNode node) {
			FunctionNode result = new FunctionNode(SH);
			result.setChildren(null, node.getNode());
			return result;
		}
	}

	private static class ThRule extends FunctionRule {

		@Override
		AbstractNode simpleDerivative(FunctionNode node) {
			FunctionNode left = new FunctionNode(CH);
			left.setChildren(null, node.getNode());
			AbstractNode right = ConstantNode.TWO;
			BinaryNode bottom = new BinaryNode(Operators.POWER);
			bottom.setChildren(left, right);
			BinaryNode result = new BinaryNode(Operators.DIVIDE);
			result.setChildren(ConstantNode.ONE, bottom);
			return result;
		}
	}

	private static class AbsRule extends FunctionRule {

		@Override
		AbstractNode simpleDerivative(FunctionNode node) {
			FunctionNode result = new FunctionNode(SIGN);
			result.setChildren(null, node.getNode());
			return result;
		}
	}

	private static class SignRule extends FunctionRule {

		@Override
		AbstractNode simpleDerivative(FunctionNode node) {
			return ConstantNode.ZERO;
		}
	}
}
