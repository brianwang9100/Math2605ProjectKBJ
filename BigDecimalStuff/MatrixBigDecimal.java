import java.math.BigDecimal;
/**
 * Mutable abstraction of Matrix.
 *
 * @author Brian Wang
 * @version 1.2
 */
public class MatrixBigDecimal {

    /*
    Create final instance variables
    */
    private BigDecimal[][] matrix;
    public int height;
    public int width;

    /**
     * Initialize instance variables
     * @param matrix 2D array representation of Matrix
     */
    public MatrixBigDecimal(BigDecimal[][] matrix) {
        this.matrix = matrix;
        height = matrix.length;
        width = matrix[0].length;
    }

    /**
     * Gets value located at specified row and column
     * @param i row
     * @param j column
     * @return BigDecimal located at row i and column j in matrix
     */
    public BigDecimal get(int i, int j) {
        if (i < 0 || i > height || j < 0 || j > width) {
            throw new IllegalArgumentException(i + "," + j
                                                        + " does not exist in"
                                                        + " matrix of height "
                                                        + height
                                                        + " and width "
                                                        + width + ".");
        }
        return matrix[i][j];
    }

    /**
     * Sets input at specified row and height
     * @param row row
     * @param col column
     * @return BigDecimal located at row i and column j in matrix
     */
    public BigDecimal set(int row, int col, BigDecimal input) {
        if (row < 0 || row > height || col < 0 || col > width) {
            throw new IllegalArgumentException(row + "," + col
                                                        + " does not exist in"
                                                        + " matrix of height "
                                                        + height
                                                        + " and width "
                                                        + width + ".");
        }
        BigDecimal temp = matrix[row][col];
        matrix[row][col] = input;
        return temp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }

        MatrixBigDecimal other = (MatrixBigDecimal)o;
        if (!(other.height == this.height && other.width == this.width)) {
            return false;
        }

        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                if (other.get(row, col).equals(this.get(row, col))) {
                    return false;
                }
            }
        }

        return true;
    }

    public void rowScalarMultiply(BigDecimal scalar, int row) {
        for (int col = 0; col < width; col++) {
            BigDecimal temp = this.get(row, col);
            this.set(row, col, scalar.multiply(temp));
        }
    }
    //row 2 = row 1 + row 2
    //second one is changed
    public void rowAdd(int row1, int row2) {
        for (int col = 0; col < width; col++) {
            BigDecimal row1Value = this.get(row1, col);
            BigDecimal row2Value = this.get(row2, col);
            this.set(row2, col, row1Value.add(row2Value));
        }
    }

    /**
     * Gets String representation of matrix.
     * Columns separated by tabs, rows by new lines.
     * @return String representation of matrix.
     */
    public String toString() {
        String finalList = "";
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                finalList = finalList + String.format("%12.6f", matrix[row][col].doubleValue());
            }
            finalList += "\n";
        }
        return finalList;
    }

    public String toStringStream() {
        String finalList = "";
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                finalList = finalList + (int) matrix[row][col].intValue();
            }
            finalList += " ";
        }
        return finalList;
    }

    public String toStringStreamCropLast() {
        String finalList = "";
        for (int row = 0; row < height - 3; row++) {
            for (int col = 0; col < width; col++) {
                finalList = finalList + (int) matrix[row][col].intValue();
            }
            finalList += " ";
        }
        return finalList;
    }

    public String toStringBinary() {
        String finalList = "";
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                finalList = finalList + (int) matrix[row][col].intValue() +
                " ";
            }
            finalList += "\n";
        }
        return finalList;
    }

    public String toStringBinaryCropFirst() {
        String finalList = "";
        for (int row = 3; row < height; row++) {
            for (int col = 0; col < width; col++) {
                finalList = finalList + (int) matrix[row][col].intValue() +
                " ";
            }
            finalList += "\n";
        }
        return finalList;
    }

    public String toStringBinaryCropLast() {
        String finalList = "";
        for (int row = 0; row < height - 3; row++) {
            for (int col = 0; col < width; col++) {
                finalList = finalList + (int) matrix[row][col].intValue() +
                " ";
            }
            finalList += "\n";
        }
        return finalList;
    }

    public BigDecimal[][] toArray(){
        return matrix;
    }


}
