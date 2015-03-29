import java.util.Scanner;
public class lu_fact {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of the file to be read");
        String name = scanner.nextLine();
        Matrix a = MatrixReader.readFile(name);

        // double[][] matrix = {1};
        Matrix m = new Matrix(matrix);
        Matrix[] lu = factorLU(m);

        System.out.println("L:");
        System.out.println(lu[0]);
        System.out.println("U:");
        System.out.println(lu[1]);

        //still need to do error Need to make eigenvalue method
    }

    public static Matrix[] factorLU(Matrix m) {
        if (m.height != m.width) {
            throw new IllegalArgumentException("Matrix must be square!");
        }
        Matrix[] lu = new Matrix[2];
        Matrix L = MatrixAlgebra.identityMatrix(m.width);
        Matrix U = MatrixAlgebra.identityMatrix(m.width);
        lu[0] = L;
        lu[1] = U;
        factorLU(lu, m);
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
                double scalar = pivotValue/valueToBeChanged;
                if (scalar * valueToBeChanged + pivotValue != 0) {
                    scalar *= -1;
                }
                m.rowScalarMultiply(scalar, row);
                m.rowAdd(pivotRow, row);

                //assuming that the code works, this line gets rid of
                //any floating point errors
                m.set(row, pivotCol, 0);

                L.set(row, pivotCol, -1 * scalar);
            }
            pivotRow++;
            pivotCol++;
        }
        lu[0] = L;
        lu[1] = m;
    }
}
