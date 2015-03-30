import java.util.Scanner;
public class lu_fact {
    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);
        // System.out.println("Enter name of the file to be read");
        // String name = scanner.nextLine();
        // Matrix a = MatrixReader.readFile(name);

        double[][] matrix = {{8, 2, 9}, {4, 9, 4}, {6, 7, 9}};
        Matrix A = new Matrix(matrix);
        Matrix[] lu = factorLU(A);

        System.out.println("A:");
        System.out.println(A);

        System.out.println("L:");
        System.out.println(lu[0]);
        System.out.println("U:");
        System.out.println(lu[1]);

        Matrix LU = MatrixAlgebra.matrixMultiply(lu[0], lu[1]);

        Matrix errorMatrix = MatrixAlgebra.matrixSubtract(LU, A);
        double error = MatrixAlgebra.findAbsoluteMax(errorMatrix);
        System.out.printf("||LU - A|| Error = %.3f\n", error);
    }

    //lu[0] = L;
    //lu[1] = U;
    public static Matrix[] factorLU(Matrix m) {
        if (m.height != m.width) {
            throw new IllegalArgumentException("Matrix must be square!");
        }
        Matrix copy = MatrixAlgebra.copyOf(m);
        Matrix L = MatrixAlgebra.identityMatrix(m.width);
        Matrix U = MatrixAlgebra.identityMatrix(m.width);
        Matrix[] lu = new Matrix[2];
        lu[0] = L;
        lu[1] = U;
        factorLU(lu, copy);

        return lu;
    }

    private static void factorLU(Matrix[] lu, Matrix m) {
        Matrix L = lu[0];
        Matrix U = m;

        int pivotRow = 0;
        int pivotCol = 0;
        while (pivotRow != m.height - 1 && pivotCol != m.width - 1) {
            double pivotValue = m.get(pivotRow, pivotCol);
            for (int row = pivotRow + 1; row < m.height; row++) {
                double valueToBeChanged = m.get(row, pivotCol);
                double scalar = valueToBeChanged/pivotValue;
                if (scalar * pivotValue + valueToBeChanged != 0) {
                    scalar *= -1;
                }
                m.rowScalarMultiply(scalar, pivotRow);
                m.rowAdd(pivotRow, row);
                m.rowScalarMultiply(1/scalar, pivotRow);

                //assuming that the code works, this line gets rid of
                // //any floating point errors
                // m.set(row, pivotCol, 0);

                L.set(row, pivotCol, -1 * scalar);
            }
            pivotRow++;
            pivotCol++;
        }
        lu[0] = L;
        lu[1] = m;
    }
}
