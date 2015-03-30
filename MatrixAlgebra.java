/**
 * Operations class for Linear Algebra.
 *
 * @author Brian Wang
 * @version 1.0
 */
public class MatrixAlgebra {

    public static Matrix transpose(Matrix m) {
        double[][] holder = new double[m.width][m.height];
        for (int row = 0; row < m.height; row++) {
            for (int col = 0; col < m.width; col++) {
                holder[col][row] = m.get(row, col);
            }
        }
        return new Matrix(holder);
    }

    public static Matrix matrixMultiply(Matrix m1, Matrix m2) {
        if (m1.width != m2.height) {
            throw new IllegalArgumentException("Cannot multiply a matrix"
                                                + " of width " + m1.width
                                                + " with a matrx of length "
                                                + m2.height + "!");
        }

        double[][] holder = new double[m1.height][m2.width];
        for (int i = 0; i < m1.height; i++) {
            for (int j = 0; j < m2.width; j++) {
                double sum = 0;
                for (int row = 0, col = 0; row < m2.height; row++, col++) {
                    sum += m1.get(i, col) * m2.get(row, j);
                }
                holder[i][j] = sum;
            }
        }
        return new Matrix(holder);
    }

    public static Matrix matrixAdd(Matrix m1, Matrix m2) {
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

    public static Matrix matrixSubtract(Matrix m1, Matrix m2) {
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
                holder[row][col] = m1.get(row, col) - m2.get(row, col);
            }
        }
        return new Matrix(holder);
    }

    public static Matrix scalarMultiply(Matrix m1, double scalar) {

        double[][] holder = new double[m1.height][m1.width];
        for (int row = 0; row < m1.height; row++) {
            for (int col = 0; col < m1.width; col++) {
                holder[row][col] = scalar * m1.get(row, col);
            }
        }
        return new Matrix(holder);
    }

    public static double det(Matrix m) {
        if (m.height != m.width) {
            throw new IllegalArgumentException("Matrix must be square!");
        }
        if (m.height == 2 && m.width == 2) {
            //a b
            //c d
            double a = m.get(0, 0);
            double b = m.get(0, 1);
            double c = m.get(1, 0);
            double d = m.get(1, 1);
            return a*d - b*c;
        } else {
            double d = 0;
            for (int i = 0; i < m.width; i++) {
                double[][]holder = new double[m.height - 1][m.width - 1];
                for (int row = 1; row < m.height; row++) {
                    for (int col = 0, aCol = 0; col < m.width && col != i; col++) {
                        holder[row][aCol] = m.get(row, col);
                    }
                }
                Matrix temp = new Matrix(holder);
                d += Math.pow(-1, i) * m.get(1, i) * det(temp);
            }
            return d;
        }
    }

    // public static Matrix inverse(Matrix m) {
    //     if (m.height != m.width) {
    //         throw new IllegalArgumentException("Matrix must be square!");
    //     }
    //     if (m.height == 2 && m.width == 2) {
    //         //a b
    //         //c d
    //         double a = m.get(0, 0);
    //         double b = m.get(0, 1);
    //         double c = m.get(1, 0);
    //         double d = m.get(1, 1);
    //         double[][] temp = {{d,-1*b},{-1*c,a}};
    //         double oneOverDet = 1.0/(a * d - b * c);
    //         return scalarMultiply(temp, oneOverDet);
    //     }
    //     double[][] inverseTemp = new double[m.length][m.width];
    //     for (int row = 0, col = 0;  row < m.height && col < m.width; row++, col++ ) {
    //
    //     }
    // }

    // public static Matrix findAxbSolution(Matrix l, Matrix r) {
    //     if (l.width != l.height || r.height != l.width || r.width != 1) {
    //         throw new IllegalArgumentException("A must be square, b must be of 1 width, and the height of b must be the same width as A.");
    //     }
    //     double[][] left = l.toArray();
    //     double[][] right = r.toArray();
    //     double[][] sol = new double[l.width][1];
    //     for (int i = 0; i < l.width; i++) {
    //         // find pivot row and swap
    //         int max = i;
    //         for (int j = i + 1; j < l.width; j++) {
    //             if (Math.abs(left[j][i]) > Math.abs(A.left[max][i])) {
    //                 max = j;
    //             }
    //         }
    //
    //         swapRows(left, i, max);
    //         swapRows(right, i, max);
    //
    //
    //         // singular
    //         if (left[i][i] == 0.0) throw new RuntimeException("Matrix is singular.");
    //
    //         // pivot within b
    //         for (int j = i + 1; j < l.width; j++)
    //             right[j][0] -= right[i][0] * left[j][i] / left[i][i];
    //
    //         // pivot within A
    //         for (int j = i + 1; j < l.width; j++) {
    //             double m = left[j][i] / left[i][i];
    //             for (int k = i+1; k < l.width; k++) {
    //                 left[j][k] -= left[i][k] * m;
    //             }
    //             left[j][i] = 0.0;
    //         }
    //     }
    //
    //     // back substitution
    //
    //     for (int j = N - 1; j >= 0; j--) {
    //         double t = 0.0;
    //         for (int k = j + 1; k < N; k++)
    //             t += left[j][k] * sol[k][0];
    //         sol[j][0] = (right[j][0] - t) / left[j][j];
    //     }
    //     return new Matrix(sol);
    //
    // }

    public static void swapRows(double[][] backing, int row1, int row2) {
        double[] temp = backing[row1];
        backing[row1] = backing[row2];
        backing[row2] = temp;
    }

    public static double magnitudeVector(Matrix m) {
        if (m.width != 1) {
            throw new IllegalArgumentException("Not a vector");
        }
        double squareSum = 0;
        for (int i = 0; i < m.height; i++) {
            squareSum += Math.pow(m.get(i, 0), 2);
        }

        return Math.sqrt(squareSum);
    }

    public static Matrix unitVector(Matrix m) {
        if (m.width != 1) {
            throw new IllegalArgumentException("Not a vector");
        }
        return scalarMultiply(m, 1.0 / magnitudeVector(m));
    }

    public static Matrix identityMatrix(int size) {

        double[][] holder = new double[size][size];
        for (int rowcol = 0; rowcol < holder.length; rowcol++) {
            holder[rowcol][rowcol] = 1;
        }

        return new Matrix(holder);
    }

    public static Matrix vectorOfMatrix(Matrix m, int column) {
        if (column >= m.width) {
            throw new IllegalArgumentException("column exceeds width");
        }
        double[][] holder = new double[m.height][1];
        for (int row = 0; row < m.height; row++) {
            holder[row][0] = m.get(row, 0);
        }

        return new Matrix(holder);
    }
}
