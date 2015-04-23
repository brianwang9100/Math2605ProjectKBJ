import java.math.BigDecimal;
/**
 * Operations class for Linear Algebra.
 *
 * @author Brian Wang
 * @version 1.0
 */
public class MatrixAlgebraBigDecimal {

    public static void fillZeroes(BigDecimal[][] arr) {
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[0].length; col++) {
                arr[row][col] = new BigDecimal("0");
            }
        }
    }

    public static MatrixBigDecimal transpose(MatrixBigDecimal m) {
        BigDecimal[][] holder = new BigDecimal[m.width][m.height];
        fillZeroes(holder);
        for (int row = 0; row < m.height; row++) {
            for (int col = 0; col < m.width; col++) {
                holder[col][row] = m.get(row, col);
            }
        }
        return new MatrixBigDecimal(holder);
    }

    public static MatrixBigDecimal matrixMultiply(MatrixBigDecimal m1, MatrixBigDecimal m2) {
        if (m1.width != m2.height) {
            throw new IllegalArgumentException("Cannot multiply a matrix"
                                                + " of width " + m1.width
                                                + " with a matrx of length "
                                                + m2.height + "!");
        }

        BigDecimal[][] holder = new BigDecimal[m1.height][m2.width];
        fillZeroes(holder);
        for (int i = 0; i < m1.height; i++) {
            for (int j = 0; j < m2.width; j++) {
                BigDecimal sum = new BigDecimal(0);
                for (int row = 0, col = 0; row < m2.height; row++, col++) {
                    BigDecimal temp = m1.get(i, col).multiply(m2.get(row, j));
                    sum = sum.add(temp);
                }
                holder[i][j] = sum;
            }
        }
        return new MatrixBigDecimal(holder);
    }

    public static MatrixBigDecimal matrixAdd(MatrixBigDecimal m1, MatrixBigDecimal m2) {
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

        BigDecimal[][] holder = new BigDecimal[m1.height][m1.width];
        fillZeroes(holder);
        for (int row = 0; row < m1.height; row++) {
            for (int col = 0; col < m1.width; col++) {
                holder[row][col] = m1.get(row, col).add(m2.get(row, col));
            }
        }
        return new MatrixBigDecimal(holder);
    }

    public static MatrixBigDecimal matrixSubtract(MatrixBigDecimal m1, MatrixBigDecimal m2) {
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

        BigDecimal[][] holder = new BigDecimal[m1.height][m1.width];
        fillZeroes(holder);
        for (int row = 0; row < m1.height; row++) {
            for (int col = 0; col < m1.width; col++) {
                holder[row][col] = m1.get(row, col).subtract(m2.get(row, col));
            }
        }
        return new MatrixBigDecimal(holder);
    }

    public static MatrixBigDecimal scalarMultiply(MatrixBigDecimal m1, BigDecimal scalar) {

        BigDecimal[][] holder = new BigDecimal[m1.height][m1.width];
        fillZeroes(holder);
        for (int row = 0; row < m1.height; row++) {
            for (int col = 0; col < m1.width; col++) {
                holder[row][col] = scalar.multiply(m1.get(row, col));
            }
        }
        return new MatrixBigDecimal(holder);
    }

    // public static BigDecimal det(MatrixBigDecimal m) {
    //     if (m.height != m.width) {
    //         throw new IllegalArgumentException("Matrix must be square!");
    //     }
    //     if (m.height == 2 && m.width == 2) {
    //         //a b
    //         //c d
    //         BigDecimal a = m.get(0, 0);
    //         BigDecimal b = m.get(0, 1);
    //         BigDecimal c = m.get(1, 0);
    //         BigDecimal d = m.get(1, 1);
    //         return a*d - b*c;
    //     } else {
    //         BigDecimal d = 0;
    //         for (int i = 0; i < m.width; i++) {
    //             BigDecimal[][]holder = new BigDecimal[m.height - 1][m.width - 1];
    //             for (int row = 1; row < m.height; row++) {
    //                 for (int col = 0, aCol = 0; col < m.width && col != i; col++) {
    //                     holder[row][aCol] = m.get(row, col);
    //                 }
    //             }
    //             Matrix temp = new Matrix(holder);
    //             d += BigDecimal.pow(-1, i) * m.get(1, i) * det(temp);
    //         }
    //         return d;
    //     }
    // }

    public static void swapRows(BigDecimal[][] backing, int row1, int row2) {
        BigDecimal[] temp = backing[row1];
        backing[row1] = backing[row2];
        backing[row2] = temp;
    }

    public static BigDecimal magnitudeVector(MatrixBigDecimal m) {
        if (m.width != 1) {
            throw new IllegalArgumentException("Not a vector");
        }
        BigDecimal squareSum = new BigDecimal("0");
        for (int i = 0; i < m.height; i++) {
            squareSum = squareSum.add(BigDecimalMath.pow(m.get(i, 0), new BigDecimal("2")));
        }

        return BigDecimalMath.pow(squareSum, new BigDecimal("0.5"));
    }

    public static MatrixBigDecimal unitVector(MatrixBigDecimal m) {
        if (m.width != 1) {
            throw new IllegalArgumentException("Not a vector");
        }
        BigDecimal magnitude = magnitudeVector(m);
        if (magnitude.equals(BigDecimal.ZERO)) {
            return m;
        }
        return scalarMultiply(m, new BigDecimal("1").divide(magnitude));
    }

    public static MatrixBigDecimal identityMatrix(int size) {

        BigDecimal[][] holder = new BigDecimal[size][size];
        fillZeroes(holder);
        for (int rowcol = 0; rowcol < holder.length; rowcol++) {
            holder[rowcol][rowcol] = new BigDecimal("1");
        }

        return new MatrixBigDecimal(holder);
    }

    public static MatrixBigDecimal vectorOfMatrix(MatrixBigDecimal m, int column) {
        if (column >= m.width) {
            throw new IllegalArgumentException("column exceeds width");
        }
        BigDecimal[][] holder = new BigDecimal[m.height][1];
        fillZeroes(holder);
        for (int row = 0; row < m.height; row++) {
            holder[row][0] = m.get(row, column);
        }

        return new MatrixBigDecimal(holder);
    }

    public static BigDecimal findAbsoluteMax(MatrixBigDecimal m) {
        BigDecimal absoluteMax = m.get(0, 0);
        for (int row = 0; row < m.height; row++) {
            for (int col = 0; col < m.width; col++) {
                BigDecimal value = m.get(row, col).abs();
                if (value.compareTo(absoluteMax) > 0) {
                    absoluteMax = value;
                }
            }
        }

        return absoluteMax;

    }

    public static MatrixBigDecimal copyOf(MatrixBigDecimal m) {
        BigDecimal[][] holder = new BigDecimal[m.height][m.width];
        fillZeroes(holder);
        for (int row = 0; row < m.height; row++) {
            for (int col = 0; col < m.width; col++) {
                holder[row][col] = m.get(row, col);
            }
        }
        return new MatrixBigDecimal(holder);
    }
}
