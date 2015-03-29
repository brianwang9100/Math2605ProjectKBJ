import java.util.Scanner;
public class lu_fact {
    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);
        // System.out.println("Enter name of the file to be read");
        // String name = scanner.nextLine();
        // Matrix a = MatrixReader.readFile(name);

        double[][] matrix = {{8, 2, 9}, {4, 9, 4}, {6, 7, 9}};
        Matrix m = new Matrix(matrix);
        Matrix[] lu = factorLU(m);

        System.out.println("L:");
        System.out.println(qr[0]);
        System.out.println("U:");
        System.out.println(qr[1]);

        //still need to do error Need to make eigenvalue method
    }

    public static Matrix factorLU(Matrix m) {
        if (a.height != a.width) {
            throw new IllegalArgumentException("Matrix must be square!");
        }
        Matrix[] lu = new Matrix[2];
        Matrix L = MatrixAlgebra.identity(a.width);
        Matrix U = MatrixAlgebra.identity(a.width);
        lu[0] = Q;
        lu[1] = R;
        //qr[0] = Q
        //qr[1] = R
        factorLU(lu, m);
        return lu;
    }

    private static void factorLU(double[] lu, Matrix m) {
        Matrix L = lu[0];
        Matrix U = m;

        int pivotRow = 0;
        int pivotCol = 0;
        while (pivotRow != m.height - 1 && pivotCol != m.width - 1) {
            double pivotValue = m.get(pivotRow, pivotCol);
            for (int row = pivotRow + 1; row < m.height; row++) {
                double valueToBeChanged = m.get(row, pivotCol);
                double scalar = pivotValue/valueToBeChanged;
                if (scalar * valueToBeChange + pivotValue != 0) {
                    scalar *= -1;
                }
                m.rowScalarMultiply(scalar, row);
                m.rowAdd(pivotRow, row);

                L.set(row, pivotCol, -1 * scalar);
            }
            pivotRow++;
            pivotCol++;
        }
        lu[0] = L;
        lu[1] = m;
    }


}
