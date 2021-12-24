package com.github.jxen.math.linear;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.jxen.math.common.MathException;
import org.junit.jupiter.api.Test;

class MatrixTest {

  @Test
  void testCreateFromArray2D() {
    double[][] array = {
        {1, 0},
        {0, 1},
    };
    Matrix actual = Matrix.of(array);
    assertEquals(2, actual.size());
    assertEquals(1, actual.get(0, 0));
  }

  @Test
  void testCreateFromArray2DFail() {
    double[][] array = {
        {1, 0},
        {0},
    };
    assertThrows(MathException.class, () -> Matrix.of(array));
  }

  @Test
  void testCreateFromArray() {
    double[] array = {1, 0, 0, 1};
    Matrix actual = Matrix.of(array);
    assertEquals(2, actual.size());
    assertEquals(1, actual.get(0, 0));
  }

  @Test
  void testCreateFromArrayFail() {
    double[] array = {1, 0, 0};
    assertThrows(MathException.class, () -> Matrix.of(array));
  }

  @Test
  void testCreateFromArrayFail2() {
    double[] array = {};
    assertThrows(MathException.class, () -> Matrix.of(array));
  }

  @Test
  void testIdentity() {
    Matrix actual = Matrix.identity(3);
    assertTrue(actual.isIdentity());
  }

  @Test
  void testNotIdentity1() {
    Matrix actual = Matrix.of(1, 1, 0, 1);
    assertFalse(actual.isIdentity());
  }

  @Test
  void testNotIdentity2() {
    Matrix actual = Matrix.of(0, 0, 0, 1);
    assertFalse(actual.isIdentity());
  }

  @Test
  void testMinorFail() {
    assertThrows(MathException.class, () -> Matrix.identity(1).minor(0, 0));
  }

  @Test
  void testMinor() {
    Matrix actual = Matrix.identity(3).minor(1, 1);
    assertEquals(2, actual.size());
    assertTrue(actual.isIdentity());
  }

  @Test
  void testDeterminant() {
    double actual = Matrix.identity(3).determinant();
    assertEquals(actual, 1);
  }

  @Test
  void testDeterminant1() {
    double actual = Matrix.identity(1).determinant();
    assertEquals(actual, 1);
  }

  @Test
  void testIsSingular() {
    assertFalse(Matrix.identity(3).isSingular());
  }

  @Test
  void testIsOrthogonal() {
    assertTrue(Matrix.identity(3).isOrthogonal());
  }

  @Test
  void testPlusFail() {
    Matrix m = Matrix.of(1, 1, 0, 1);
    assertThrows(MathException.class, () -> m.plus(Matrix.of(1)));
  }

  @Test
  void testPlus() {
    Matrix m = Matrix.of(1, 1, 0, 1);
    Matrix actual = m.plus(Matrix.of(1, 0, 1, 1));
    Matrix expected = Matrix.of(2, 1, 1, 2);
    assertEquals(expected, actual);
  }

  @Test
  void testMinusFail() {
    Matrix m = Matrix.of(1, 1, 0, 1);
    assertThrows(MathException.class, () -> m.minus(Matrix.of(1)));
  }

  @Test
  void testMinus() {
    Matrix m = Matrix.of(1, 1, 0, 1);
    Matrix actual = m.minus(Matrix.of(1, 0, 1, 1));
    Matrix expected = Matrix.of(0, 1, -1, 0);
    assertEquals(expected, actual);
  }

  @Test
  void testMultiply() {
    Matrix m = Matrix.of(1, 1, 0, 1);
    Matrix actual = m.multiply(2);
    Matrix expected = Matrix.of(2, 2, 0, 2);
    assertEquals(expected, actual);
  }

  @Test
  void testMultiplyMatrixFail() {
    Matrix m = Matrix.of(1, 0, 0, 1);
    assertThrows(MathException.class, () -> m.multiply(Matrix.of(1)));
  }

  @Test
  void testMultiplyMatrix() {
    Matrix m = Matrix.of(1, 0, 0, 1);
    Matrix actual = m.multiply(Matrix.of(1, 0, 0, 1));
    Matrix expected = Matrix.of(1, 0, 0, 1);
    assertEquals(expected, actual);
  }

  @Test
  void testMultiplyVectorFail() {
    Matrix m = Matrix.of(1, 0, 0, 1);
    assertThrows(MathException.class, () -> m.multiply(Column.of(1)));
  }

  @Test
  void testMultiplyVector() {
    Matrix m = Matrix.of(1, 0, 0, 1);
    Column actual = m.multiply(Column.of(1, 1));
    Column expected = Column.of(1, 1);
    assertEquals(expected, actual);
  }

  @Test
  void testTranspose() {
    Matrix m = Matrix.of(1, 1, 0, 1);
    Matrix actual = m.transpose();
    Matrix expected = Matrix.of(1, 0, 1, 1);
    assertEquals(expected, actual);
  }

  @Test
  void testInverseFail() {
    Matrix m = Matrix.of(1, 1, 1, 1);
    assertThrows(MathException.class, m::inverse);
  }

  @Test
  void testInverse1() {
    Matrix m = Matrix.of(1, 1, 0, 1);
    Matrix actual = m.inverse();
    Matrix expected = Matrix.of(1, -1, 0, 1);
    assertEquals(expected, actual);
  }

  @Test
  void testInverse2() {
    Matrix m = Matrix.of(0, 1, -1, 0);
    Matrix actual = m.inverse();
    Matrix expected = Matrix.of(0, -1, 1, 0);
    assertEquals(expected, actual);
  }

  @Test
  void testEigenValues1() {
    Matrix m = Matrix.of(1);
    double[] actual = m.eigenValues();
    double[] expected = {1};
    assertArrayEquals(expected, actual);
  }

  @Test
  void testEigenValues2() {
    Matrix m = Matrix.of(1, 0, 0, 2);
    double[] actual = m.eigenValues();
    double[] expected = {1, 2};
    assertArrayEquals(expected, actual);
  }

  @Test
  void testEigenValues3() {
    Matrix m = Matrix.of(0, 1, 1, 0);
    double[] actual = m.eigenValues();
    double[] expected = {-1, 1};
    assertArrayEquals(expected, actual, 1e-5);
  }

  @Test
  void testEigenValues4() {
    Matrix m = Matrix.of(1, 1, 1, 1);
    double[] actual = m.eigenValues();
    double[] expected = {0, 2};
    assertArrayEquals(expected, actual);
  }

  @Test
  void testEigenValuesFailure() {
    Matrix m = Matrix.identity(3);
    assertThrows(UnsupportedOperationException.class, m::eigenValues);
  }

  @Test
  void testEigenVectors1() {
    Matrix m = Matrix.of(1);
    Column[] actual = m.eigenVectors();
    Column[] expected = {Column.of(1)};
    assertArrayEquals(expected, actual);
  }

  @Test
  void testEigenVectors2() {
    Matrix m = Matrix.of(1, 0, 0, 1);
    Column[] actual = m.eigenVectors();
    Column[] expected = {Column.of(1, 0), Column.of(0, 1)};
    assertArrayEquals(expected, actual);
  }

  @Test
  void testEigenVectors3() {
    Matrix m = Matrix.of(0, 1, 1, 0);
    Column[] actual = m.eigenVectors();
    Column[] expected = {Column.of(1, 1), Column.of(1, -1)};
    assertArrayEquals(expected, actual);
  }

  @Test
  void testToString() {
    assertEquals("[1.0, 0.0]\n[0.0, 1.0]\n", Matrix.identity(2).toString());
  }

  @Test
  void testEqualsSame() {
    Matrix matrix = Matrix.identity(2);
    assertEquals(matrix, matrix);
  }

  @Test
  void testNotEquals() {
    assertNotEquals(Matrix.identity(1), 1);
  }

  @Test
  void testHashCode() {
    Matrix matrix = Matrix.identity(1);
    assertEquals(1072693341, matrix.hashCode());
  }
}
