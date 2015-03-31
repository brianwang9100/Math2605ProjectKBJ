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
                System.out.println("(LUSolve) Enter name of the file to be read");
                System.out.println("(LUSolve) Type NO to quit");
                String name = scanner.nextLine();
                if (name.equals("NO")) {
                    System.exit(0);
                }
                Matrix Ab = MatrixReader.readFile(name);
                Matrix A = MatrixReader.convertToA(Ab);
                Matrix b = MatrixReader.convertTob(Ab);
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
        double[][] holderY = new double[A.height][1];
        holderY[0][0] = b.get(0, 0);
        int row;
        int col;
        for(row = 1; row < L.height; row++) {
            double yValue = b.get(row, 0);
            for (col = 0; col < row; col++) {
                yValue -= L.get(row, col) * holderY[col][0];
            }
            holderY[row][0] = yValue;
        }
        Matrix y = new Matrix(holderY);

        //Ux = y;
        double[][] holderX = new double[A.height][1];
        holderX[A.height - 1][0] = (y.get(A.height - 1, 0) / U.get(U.height - 1, U.width - 1));
        for (row = U.height - 2; row >= 0; row--) {
            double xValue = y.get(row, 0);
            for (col = U.width - 1; col > row; col--) {
                xValue -= U.get(row, col) * holderX[col][0];
            }
            holderX[row][0] = (double) xValue/U.get(row, col);
        }

        Matrix x = new Matrix(holderX);

        System.out.println("xSol:");
        System.out.println(x);

        Matrix Ax = MatrixAlgebra.matrixMultiply(A, x);
        Matrix errorMatrix = MatrixAlgebra.matrixSubtract(Ax, b);
        double error = MatrixAlgebra.findAbsoluteMax(errorMatrix);
        System.out.printf("||Ax - b|| Error = %.24f\n", error);
        System.out.println();
    }
}
