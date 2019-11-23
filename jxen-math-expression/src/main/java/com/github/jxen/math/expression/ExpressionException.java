package com.github.jxen.math.expression;

/**
 * {@code ExpressionException} class is general exception of Math Expression Parser module.
 *
 * @author Denis Murashev
 * @since Math Expression Parser 0.1
 */
public class ExpressionException extends RuntimeException {

	private static final long serialVersionUID = -6658285966320098533L;

	private final String expression;
	private final int position;
	private final int length;

	/**
	 * @param message  message
	 * @param position position
	 * @param length   length
	 */
	public ExpressionException(String message, int position, int length) {
		this(message, null, position, length);
	}

	/**
	 * @param message  message
	 * @param position position
	 * @param length   length
	 * @param cause      cause
	 */
	public ExpressionException(String message, int position, int length, Throwable cause) {
		this(message, null, position, length, cause);
	}

	/**
	 * @param message    message
	 * @param expression expression
	 * @param position   position
	 * @param length     length
	 */
	public ExpressionException(String message, String expression, int position, int length) {
		super(message);
		this.expression = expression;
		this.position = position;
		this.length = length;
	}

	/**
	 * @param message    message
	 * @param expression expression
	 * @param position   position
	 * @param length     length
	 * @param cause      cause
	 */
	public ExpressionException(String message, String expression, int position, int length, Throwable cause) {
		super(message, cause);
		this.expression = expression;
		this.position = position;
		this.length = length;
	}

	/**
	 * @return expression
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * @return position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @return length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @return marker
	 */
	public String getMarker() {
		//		for(int i = 0; i < position; i++) {
		//			builder.append(" ");
		//		}
		//		for(int i = 0; i < length; i++) {
		//			builder.append("^");
		//		}
		final char c = '\0';
		return new String(new char[position]).replace(c, ' ') + new String(new char[length]).replace(c, '^');
	}
}
