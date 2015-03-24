package Math2605ProjectKBJ;

/**
 * Mutable abstraction of Matrix.
 *
 * @author Brian Wang
 * @version 1.2
 */
public class Matrix {

    /*
    Create final instance variables
    */
    private double[][] matrix;
    public int height;
    public int width;

    /**
     * Initialize instance variables
     * @param matrix 2D array representation of Matrix
     */
    public Matrix(double[][] matrix) {
        this.matrix = matrix;
        height = matrix.length;
        width = matrix[0].length;
    }

    /**
     * Gets value located at specified row and column
     * @param i row
     * @param j column
     * @return double located at row i and column j in matrix
     */
    public double get(int i, int j) {
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
     * @return double located at row i and column j in matrix
     */
    public double set(int row, int col, double input) {
        if (row < 0 || row > height || col < 0 || col > width) {
            throw new IllegalArgumentException(row + "," + col
                                                        + " does not exist in"
                                                        + " matrix of height "
                                                        + height
                                                        + " and width "
                                                        + width + ".");
        }
        double temp = matrix[row][col];
        matrix[row][col] = input;
        return temp;
    }

    @Override
    public boolean isEquals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }

        Matrix other = (Matrix)o;
        if (!(other.height == this.height && other.width == this.width)) {
            return false;
        }

        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                if (other.get(row, col) != this.get(row, col)) {
                    return false;
                }
            }
        }

        return true;
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
                finalList = finalList + matrix[row][col] + "    ";
            }
            finalList += "\n";
        }
        return finalList;
    }
}
