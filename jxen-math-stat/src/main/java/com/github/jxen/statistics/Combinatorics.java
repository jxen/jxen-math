package com.github.jxen.statistics;

/**
 * {@code Combinatorics} class provides varios combinatorics methods.
 *
 * @author Denis Murashev
 *
 * @since Math Statistics 0.1
 */
public final class Combinatorics {

	private Combinatorics() {
	}

	/**
	 * Provides binomial coefficient or combination.
	 *
	 * @param n n
	 * @param k k
	 * @return combination
	 */
	public static int combination(int n, int k) {
		int c = 1;
		for (int i = 1; i <= Math.min(k, n - k); i++) {
			c *= n + 1 - i;
			c /= i;
		}
		return c;
	}
}
