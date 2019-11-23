package com.github.jxen.math.expression;

class Token {

	private int priority = Integer.MAX_VALUE;
	private final String value;
	private final int position;
	private AbstractNode node;

	Token(String value, int position) {
		this.value = value;
		this.position = position;
	}

	String getValue() {
		return value;
	}

	int getPosition() {
		return position;
	}

	int getPriority() {
		return priority;
	}

	void setPriority(Integer priority) {
		this.priority = priority;
	}

	AbstractNode getNode() {
		return node;
	}

	void setNode(AbstractNode node) {
		this.node = node;
	}

	@Override
	public String toString() {
		return value;
	}
}
