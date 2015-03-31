import java.util.Scanner;
import java.io.IOException;
public class lu_fact {
    public static void main(String[] args) {
        factorLU();
    }

    public static void factorLU() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("(LU) Enter name of the file to be read");
                System.out.println("(LU) Type NO to quit");
                String name = scanner.nextLine();
                if (name.equals("NO")) {
                    System.exit(0);
                }
                Matrix A = MatrixReader.readFile(name);
                factorLU(A);
            } catch(IOException e) {
                System.out.println("File name not valid");
                System.out.println();
            }
        }
        // double[][] matrix = {{8, 2, 9}, {4, 9, 4}, {6, 7, 9}};
        // Matrix A = new Matrix(matrix);
        // factorLU(A);
    }

    //lu[0] = L;
    //lu[1] = U;
    public static Matrix[] factorLU(Matrix a) {
        long startTime = System.nanoTime();
        //make a copy of the matrix to not mess with original
        Matrix copy = MatrixAlgebra.copyOf(a);
        Matrix L = MatrixAlgebra.identityMatrix(a.width);
        Matrix U = MatrixAlgebra.identityMatrix(a.width);
        Matrix[] lu = new Matrix[2];
        //setup LU with identity matrices.
        lu[0] = L;
        lu[1] = U;
        factorLU(lu, copy);

        System.out.println("A:");
        System.out.println(a);

        System.out.println("L:");
        System.out.println(lu[0]);
        System.out.println("U:");
        System.out.println(lu[1]);

        Matrix LU = MatrixAlgebra.matrixMultiply(lu[0], lu[1]);

        Matrix errorMatrix = MatrixAlgebra.matrixSubtract(LU, a);
        double error = MatrixAlgebra.findAbsoluteMax(errorMatrix);
        System.out.printf("||LU - A|| Error = %.24f\n", error);
        long timeElapsed = System.nanoTime() - startTime;
        System.out.println("Time Elapsed: " + timeElapsed + " nanoseconds");
        System.out.println();

        return lu;
    }

    private static void factorLU(Matrix[] lu, Matrix m) {
        Matrix L = lu[0];

        int pivotRow = 0;
        int pivotCol = 0;
        while (pivotRow != m.height - 1 && pivotCol != m.width - 1) {
            double pivotValue = m.get(pivotRow, pivotCol);
            if (pivotValue != 0) {                for (int row = pivotRow + 1; row < m.height; row++) {

                    double valueToBeChanged = m.get(row, pivotCol);
                    double scalar = valueToBeChanged/pivotValue;
                    if (scalar * pivotValue + valueToBeChanged != 0) {
                        scalar *= -1;
                    }
                    m.rowScalarMultiply(scalar, pivotRow);
                    m.rowAdd(pivotRow, row);
                    m.rowScalarMultiply(1 / scalar, pivotRow);
                    // for (int col = 0; col < m.width; col++) {
                    //     double tempPValue = m.get(pivotRow, col);
                    //     double value = m.get(row, col);
                    //     m.set(row, col, value + scalar * tempPValue);
                    // }

                    L.set(row, pivotCol, -1 * scalar);
                }
            }
            pivotRow++;
            pivotCol++;
        }
        lu[0] = L;
        lu[1] = m;
    }
}
