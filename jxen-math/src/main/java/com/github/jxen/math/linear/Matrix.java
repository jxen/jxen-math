package com.github.jxen.math.linear;

import com.github.jxen.math.common.MathException;
import com.github.jxen.math.common.MathUtil;
import java.util.Arrays;

/**
 * {@code Matrix} class represents square matrix.
 *
 * @author Denis Murashev
 * @since Math 0.1
 */
public final class Matrix {

	private static final String ERROR_NOT_SQUARE = "The matrix is not square";
	private static final String ERROR_SIZE = "The matrices have different sizes";

	private final double[][] m;

	private Matrix(double[][] m) {
		this.m = m.clone();
	}

	/**
	 * Creates matrix using given elements.
	 *
	 * @param m matrix elements
	 * @return matrix
	 */
	public static Matrix of(double[][] m) {
		for (double[] r : m) {
			if (r.length != m.length) {
				throw new MathException(ERROR_NOT_SQUARE);
			}
		}
		double[][] matrix = new double[m.length][m.length];
		for (int i = 0; i < m.length; i++) {
			System.arraycopy(m[i], 0, matrix[i], 0, m.length);
		}
		return new Matrix(matrix);
	}

	/**
	 * Creates matrix using given elements.
	 *
	 * @param m matrix elements
	 * @return matrix
	 */
	public static Matrix of(double... m) {
		int n = (int) Math.sqrt(m.length);
		if (n < 1 || n * n != m.length) {
			throw new MathException(ERROR_NOT_SQUARE);
		}
		double[][] matrix = new double[n][n];
		for (int i = 0; i < n; i++) {
			System.arraycopy(m, i * n, matrix[i], 0, n);
		}
		return new Matrix(matrix);
	}

	/**
	 * Creates identity matrix of given size.
	 *
	 * @param n matrix size
	 * @return matrix
	 */
	public static Matrix identity(int n) {
		double[][] m = new double[n][n];
		for (int i = 0; i < n; i++) {
			m[i][i] = 1;
		}
		return new Matrix(m);
	}

	/**
	 * @return matrix size
	 */
	public int size() {
		return m.length;
	}

	/**
	 * @param i row
	 * @param j column
	 * @return element
	 */
	public double get(int i, int j) {
		return m[i][j];
	}

	/**
	 * @param i row
	 * @param j column
	 * @return {@code true} if element is equal to zero
	 */
	public boolean isZero(int i, int j) {
		return m[i][j] == 0;
	}

	/**
	 * @param i row
	 * @param j column
	 * @return {@code true} if element is equal to one
	 */
	public boolean isOne(int i, int j) {
		return m[i][j] == 1;
	}

	/**
	 * Checks if it is identity matrix.
	 *
	 * @return result of the check
	 */
	public boolean isIdentity() {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				if (i == j && !isOne(i, j)) {
					return false;
				}
				if (i != j && !isZero(i, j)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * @param i row
	 * @param j column
	 * @return minor matrix
	 */
	public Matrix minor(int i, int j) {
		if (m.length < 2) {
			throw new MathException("Minor does not exist for matrix of size " + m.length);
		}
		double[][] values = new double[m.length - 1][m.length - 1];
		for (int k = 0; k < i; k++) {
			System.arraycopy(m[k], 0, values[k], 0, j);
			System.arraycopy(m[k], j + 1, values[k], j, m.length - j - 1);
		}
		for (int k = i + 1; k < m.length; k++) {
			System.arraycopy(m[k], 0, values[k - 1], 0, j);
			System.arraycopy(m[k], j + 1, values[k - 1], j, m.length - j - 1);
		}
		return new Matrix(values);
	}

	/**
	 * @return determinant
	 */
	public double determinant() {
		if (m.length < 1) {
			return 0;
		}
		if (m.length == 1) {
			return m[0][0];
		}
		if (m.length == 2) {
			return m[0][0] * m[1][1] - m[0][1] * m[1][0];
		}
		double det = 0;
		double sign = 1;
		for (int i = 0; i < m.length; i++) {
			det += sign * m[0][i] * minor(0, i).determinant();
			sign *= -1;
		}
		return det;
	}

	/**
	 * @return {@code true} if matrix is singular
	 */
	public boolean isSingular() {
		return determinant() == 0;
	}

	/**
	 * @return {@code true} if matrix is orthogonal
	 */
	public boolean isOrthogonal() {
		return multiply(transpose()).isIdentity();
	}

	/**
	 * Adds given matrix to current.
	 *
	 * @param matrix matrix
	 * @return sum
	 */
	public Matrix plus(Matrix matrix) {
		if (m.length != matrix.m.length) {
			throw new MathException(ERROR_SIZE);
		}
		double[][] values = new double[m.length][m.length];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				values[i][j] = m[i][j] + matrix.m[i][j];
			}
		}
		return new Matrix(values);
	}

	/**
	 * Subtracts given matrix to current.
	 *
	 * @param matrix matrix
	 * @return difference
	 */
	public Matrix minus(Matrix matrix) {
		if (m.length != matrix.m.length) {
			throw new MathException(ERROR_SIZE);
		}
		double[][] values = new double[m.length][m.length];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				values[i][j] = m[i][j] - matrix.m[i][j];
			}
		}
		return new Matrix(values);
	}

	/**
	 * Multiplies given value to current matrix.
	 *
	 * @param value value
	 * @return product
	 */
	public Matrix multiply(double value) {
		double[][] values = new double[m.length][m.length];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				values[i][j] = m[i][j] * value;
			}
		}
		return new Matrix(values);
	}

	/**
	 * Multiplies given matrix to current.
	 *
	 * @param matrix matrix
	 * @return product
	 */
	public Matrix multiply(Matrix matrix) {
		if (matrix.size() != size()) {
			throw new MathException("Incompatible matrices");
		}
		double[][] values = new double[m.length][m.length];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				values[i][j] = 0;
				for (int k = 0; k < m.length; k++) {
					values[i][j] += m[i][k] * matrix.m[k][j];
				}
			}
		}
		return new Matrix(values);
	}

	/**
	 * Multiplies given column to current.
	 *
	 * @param column column
	 * @return product
	 */
	public Column multiply(Column column) {
		if (column.size() != size()) {
			throw new MathException("Incompatible matrix and column");
		}
		double[] values = new double[m.length];
		for (int i = 0; i < m.length; i++) {
			values[i] = 0;
			for (int j = 0; j < m.length; j++) {
				values[i] += m[i][j] * column.get(j);
			}
		}
		return Column.of(values);
	}

	/**
	 * @return transposed matrix
	 */
	public Matrix transpose() {
		double[][] values = new double[m.length][m.length];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				values[i][j] = m[j][i];
			}
		}
		return new Matrix(values);
	}

	/**
	 * @return inversed matrix
	 */
	public Matrix inverse() {
		double[][] a = of(m).m;
		double[][] b = identity(m.length).m;
		for (int i = 0; i < m.length; i++) {
			if (isZero(i, i)) {
				for (int j = i + 1; j < m.length; j++) {
					if (!isZero(j, i)) {
						swap(a, b, i, j);
						break;
					}
				}
			}
			if (a[i][i] == 0) {
				throw new MathException("Matrix cannot be reversed");
			}
			subtract(a, b, i, i, (a[i][i] - 1) / a[i][i]);
			for (int j = i + 1; j < m.length; j++) {
				subtract(a, b, i, j, a[j][i]);
			}
		}
		for (int i = 1; i < m.length; i++) {
			for (int j = 0; j < i; j++) {
				subtract(a, b, i, j, a[i - 1][i]);
			}
		}
		return new Matrix(b);
	}

	private void swap(double[][] a, double[][] b, int i, int j) {
		double[] r = a[i];
		a[i] = a[j];
		a[j] = r;
		r = b[i];
		b[i] = b[j];
		b[j] = r;
	}

	private void subtract(double[][] a, double[][] b, int i, int j, double c) {
		for (int k = 0; k < m.length; k++) {
			a[j][k] -= a[i][k] * c;
			b[j][k] -= b[i][k] * c;
		}
	}

	/**
	 * @return eigen values
	 */
	public double[] eigenValues() {
		if (size() > 2) {
			throw new UnsupportedOperationException("Eigen values is not supported yet for matrices of size " + size());
		}
		if (size() == 1) {
			return new double[]{m[0][0]};
		}
		return MathUtil.roots(1, -(m[0][0] + m[1][1]), m[0][0] * m[1][1] - m[0][1] * m[1][0]);
	}

	/**
	 * @return eigen vectors
	 */
	public Column[] eigenVectors() {
		if (size() == 1) {
			return new Column[]{Column.of(m[0][0])};
		}
		if (isZero(0, 1) && isZero(1, 0)) {
			return new Column[]{Column.of(m[0][0], 0), Column.of(0, m[1][1])};
		}
		double[] lambda = eigenValues();
		return new Column[]{Column.of(m[0][1], m[0][0] - lambda[0]), Column.of(m[0][1], m[0][0] - lambda[1])};
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (double[] r : m) {
			builder.append(Arrays.toString(r)).append('\n');
		}
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		return prime + Arrays.deepHashCode(m);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Matrix other = (Matrix) obj;
		return Arrays.deepEquals(m, other.m);
	}
}
