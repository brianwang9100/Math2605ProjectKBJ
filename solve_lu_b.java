import java.util.Scanner;
import java.io.IOException;
public class solve_lu_b {
    public static void main(String[] args) {
        solveLU();
    }

    public static void solveLU() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("(QRSolve) Enter name of the file to be read OR type NO to quit");
                String name = scanner.nextLine();
                if (name.equals("NO")) {
                    System.exit(0);
                }
                Matrix Ab = MatrixReader.readFile(name);
                System.out.println(Ab);
                Matrix A = MatrixReader.convertToA(Ab);
                System.out.println(A);
                Matrix b = MatrixReader.convertTob(Ab);
                System.out.println(b);
                solveLU(A, b);
            } catch(IOException e) {
                System.out.println("File name not valid");
                System.out.println();
            }
        }
    }

    public static void solveLU(Matrix A, Matrix b) {
        Matrix[] lu = lu_fact.factorLU(A);
        Matrix L = lu[0];
        Matrix U = lu[1];
        //Ly = b
        double[][] holder = new double[A.height][1];
        holder[0][0] = b.get(0, 0);
        int row;
        int col;
        for(row = 1; row < L.height; row++) {
            double bValue = b.get(row, 0);
            for (col = 0; col < row; col++) {
                bValue = bValue - (L.get(row, col) * b.get(col, 0));
            }
            holder[row][0] = bValue;
        }
        Matrix y = new Matrix(holder);

        //Ux = y;
        holder = new double[A.height][1];
        holder[A.height - 1][0] = (y.get(A.height - 1, 0) / L.get(L.height - 1, L.width - 1));
        for (row = L.height - 2; row >= 0; row--) {
            double yValue = y.get(row, 0);
            for (col = L.width = 1; col > row; col--) {
                yValue = yValue - (L.get(row, col)* y.get(col, 0));
            }
            holder[row][0] = (yValue / L.get(row, col));
        }

        Matrix x = new Matrix(holder);

        System.out.println("xSol:");
        System.out.println(x);

        Matrix Ax = MatrixAlgebra.matrixMultiply(A, x);
        Matrix errorMatrix = MatrixAlgebra.matrixSubtract(Ax, b);
        double error = MatrixAlgebra.findAbsoluteMax(errorMatrix);
        System.out.printf("||Ax - b|| Error = %.24f\n", error);
        System.out.println();
    }
}
