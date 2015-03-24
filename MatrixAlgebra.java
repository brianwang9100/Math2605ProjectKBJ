package Math2605ProjectKBJ;

import java.util.Vector;

/**
 * Operations class for Linear Algebra.
 *
 * @author Brian Wang
 * @version 1.0
 */
public class MatrixAlgebra {

    public static Matrix transpose(Matrix m) {
        double[][] holder = new double[m.height][m.width];
        for (int row = 0; row < m.height; row++) {
            for (int col = 0; col < m.width; col++) {
                holder[col][row] = m.get(row, col)
            }
        }
        return new Matrix(holder);
    }

<<<<<<< Updated upstream

    public static Matrix dotProductMultiply(Matrix m1, Matrix m2)
        throws IllegalArgumentException {
=======
    public static Matrix matrixMultiply(Matrix m1, Matrix m2)
        throws IllegalOperandException {
>>>>>>> Stashed changes
        if (m1.width() != m2.height) {
            throw new IllegalArgumentException("Cannot multiply a matrix"
                                                + " of width " + m1.width
                                                + " with a matrx of length "
                                                + m2.height + "!");
        }

        double[][] holder = new double[m1.height][m2.width];
        for (int i = 0; i < m1.height; i++) {
            for (int j = 0; j < m2.width; j++) {

            }
        }
        return new Matrix(holder);
    }

    public static Matrix matrixAdd(Matrix m1, Matrix m2)
        throws IllegalArgumentException {
        if (!(m1.height == m2.height
            && m1.width == m2.width)) {
            throw new IllegalArgumentException("Added matrices must be of"
                                                + " same dimensions.\n"
                                                + "Matrix 1 of width "
                                                + m1.width
                                                + " and height "
                                                + m1.height
                                                + " does not have the same"
                                                + " dimensions as Matrix 2,"
                                                + " which has width "
                                                + m2.width  + " height "
                                                + m2.height + ".");
        }

        double[][] holder = new double[m1.height][m1.width];
        for (int row = 0; row < m1.height; row++) {
            for (int col = 0; col < m1.width; col++) {
                holder[row][col] = m1.get(row, col) + m2.get(row, col);
            }
        }
        return new Matrix(holder);
    }

    publ
}
