import java.util.Scanner;
import java.io.IOException;
public class solve_qr_b {
    public static void main(String[] args) {
        solveQR();
    }
    public static void solveQR() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("(QRSolve) Enter name of the file to be read");
                System.out.println("(QRSolve) Type NO to quit");
                String name = scanner.nextLine();
                if (name.equals("NO")) {
                    System.exit(0);
                }
                Matrix Ab = MatrixReader.readFile(name);
                Matrix A = MatrixReader.convertToA(Ab);
                Matrix b = MatrixReader.convertTob(Ab);
                boolean complete = false;
                while (!complete) {
                    System.out.println("(QRSolve) Type HH for householder");
                    System.out.println("(QRSolve) Type G for givens");
                    System.out.println("(QRSolve) Type NO to exit");
                    String input = scanner.nextLine();
                    if (input.equals("HH")) {
                        solveQRHH(A, b);
                        complete = true;
                    } else if (input.equals("G")) {
                        solveQRG(A, b);
                        complete = true;
                    } else if (input.equals("NO")){
                        System.exit(0);
                    } else {
                        System.out.println("Invalid Input");
                    }
                }
            } catch(IOException e) {
                System.out.println("File name not valid");
                System.out.println();
            }
        }
    }

    public static void solveQRHH(Matrix A, Matrix b) {
        Matrix[] qr = qr_fact_househ.factorHH(A);
        solveQR(A, qr[0], qr[1], b);
    }
    public static void solveQRG(Matrix A, Matrix b) {
        Matrix[] qr = qr_fact_givens.factorG(A);
        solveQR(A, qr[0], qr[1], b);
    }

    public static void solveQR(Matrix A, Matrix Q, Matrix R, Matrix b) {
        //y = Q^tb
        Matrix QTranspose = MatrixAlgebra.transpose(Q);
        Matrix y = MatrixAlgebra.matrixMultiply(QTranspose, b);
        //Rx = y;
        int row;
        int col;
        double[][] holderX = new double[A.height][1];
        holderX[A.height - 1][0] = (y.get(A.height - 1, 0) / R.get(R.height - 1, R.width - 1));
        for (row = R.height - 2; row >= 0; row--) {
            double xValue = y.get(row, 0);
            for (col = R.width - 1; col > row; col--) {
                xValue -= R.get(row, col) * holderX[col][0];
            }
            holderX[row][0] = (double) xValue/R.get(row, col);
        }
        //395516953
        //173472348

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
