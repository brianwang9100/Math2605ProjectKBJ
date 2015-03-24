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
            throw new MatrixIndexOutOfBoundsException(i + "," + j
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
        if (i < 0 || i > height || j < 0 || j > width) {
            throw new MatrixIndexOutOfBoundsException(i + "," + j
                                                        + " does not exist in"
                                                        + " matrix of height "
                                                        + height
                                                        + " and width "
                                                        + width + ".");
        }
        double temp = matrix[i][j];
        matrix[i][j] = input;
        return temp;
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
