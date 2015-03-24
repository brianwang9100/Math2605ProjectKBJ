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

    }

    /**
     * Multiplies a matrix and vector together
     * @param m object representation of Matrix
     * @param v object representation of Vector
     * @return <code>Vector</code> new mutiplied matrix result
     * @throws <code>IllegalOperandException</code> if width of matrix !=
     * length of vector
     */
    public static Vector dotProductMultiply(Matrix m1, Matrix m2)
        throws IllegalOperandException {
        if (m1.width() != m2.height) {
            throw new IllegalOperandException("Cannot multiply a matrix"
                                                + " of width " + m1.width
                                                + " with a matrx of length "
                                                + m2.height + "!");
        }

        double[][] holder = new double[m1.height][m2.width];
        // for (int holdIndex = 0; holdIndex < holder.length; holdIndex++) {
        //     double sumOfProducts = 0;
        //     for (int index = 0; index < v.getLength(); index++) {
        //         sumOfProducts = sumOfProducts
        //                         + (m.get(holdIndex, index) * v.get(index));
        //     }
        //     holder[holdIndex] = sumOfProducts;
        // }
        return new Matrix(holder);
    }

    /**
     * Adds two matrices together
     * @param m1 object representation of Matrix
     * @param m2 object representation of Matrix
     * @return <code>Matrix</code> new added matrix result
     * @throws <code>IllegalOperandException</code> if dimensions of matrices
     * do not match
     */
    public static Matrix matrixAdd(Matrix m1, Matrix m2)
        throws IllegalOperandException {
        if (!(m1.height == m2.height
            && m1.width == m2.width)) {
            throw new IllegalOperandException("Added matrices must be of"
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
}
