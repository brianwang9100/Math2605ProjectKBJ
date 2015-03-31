import java.util.Scanner;
import java.io.IOException;

public class qr_fact_givens {
    public static void main(String[] args) {
        factorG();
    }

    public static void factorG() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("(GivensQR) Enter name of the file to be read");
                System.out.println("(GivensQR) Type NO to quit");
                String name = scanner.nextLine();
                if (name.equals("NO")) {
                    System.exit(0);
                }
                Matrix A = MatrixReader.readFile(name);
                factorG(A);
            } catch(IOException e) {
                System.out.println("File name not valid");
                System.out.println();
            }
        }

        // double[][] holder = {{4, 3, 0}, {3, 1, 0}, {0, 0 , 1}};
        // Matrix A = new Matrix(holder);
        // factorG(A);
    }

    public static Matrix[] factorG(Matrix a) {
        long startTime = System.nanoTime();
        if (a.height != a.width) {
            throw new IllegalArgumentException("Matrix must be square!");
        }
        Matrix[] qr = new Matrix[2];
        Matrix Q = MatrixAlgebra.identityMatrix(a.width);
        Matrix R = a;
        qr[0] = Q;
        qr[1] = R;
        factorG(qr);

        System.out.println("A:");
        System.out.println(a);
        System.out.println("Q:");
        System.out.println(qr[0]);
        System.out.println("R:");
        System.out.println(qr[1]);

        Matrix QR = MatrixAlgebra.matrixMultiply(qr[0], qr[1]);

        Matrix errorMatrix = MatrixAlgebra.matrixSubtract(QR, a);
        double error = MatrixAlgebra.findAbsoluteMax(errorMatrix);
        System.out.printf("||QR - A|| Error = %.24f\n", error);
        long timeElapsed = System.nanoTime() - startTime;
        System.out.println("Time Elapsed: " + timeElapsed + " nanoseconds");
        System.out.println();
        return qr;
    }

    private static void factorG(Matrix[] qr) {
        int pivotRow = 0;
        int pivotCol = 0;
        while (pivotRow != qr[1].height - 1 && pivotCol != qr[1].width - 1) {
            for (int row = pivotRow + 1; row < qr[1].height; row++) {
                double a = qr[1].get(pivotRow, pivotCol);
                double b = qr[1].get(row, pivotCol);
                if (b != 0) {
                    Matrix G = findGMatrix(a, b, pivotRow,
                    pivotCol, row, qr[1].width);
                    Matrix GTranspose = MatrixAlgebra.transpose(G);
                    qr[0] = MatrixAlgebra.matrixMultiply(qr[0], GTranspose);
                    qr[1] = MatrixAlgebra.matrixMultiply(G, qr[1]);


                }
            }
            pivotRow++;
            pivotCol++;
        }
    }

    private static Matrix findGMatrix(double a, double b, int pivotRow, int pivotCol, int row, int size) {
        if (a == 0 && b == 0) {
            throw new IllegalArgumentException("Matrix is sigular");
        }
        double sintheta = (-1.0) * b / Math.sqrt(a * a + b * b);
        double costheta = a / Math.sqrt(a * a + b * b);
        Matrix G = MatrixAlgebra.identityMatrix(size);

        G.set(pivotRow, pivotCol, costheta);
        G.set(pivotRow, pivotCol + (row - pivotRow), (-1.0)* sintheta);
        G.set(row, pivotCol, sintheta);
        G.set(row, pivotCol + (row - pivotRow), costheta);

        return G;
    }
}
