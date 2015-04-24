import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;
public class gauss_seidel {
    static final int maxIterations = 100000;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("(Gauss-Seidel) Enter name of the file to be read for augmented matrix A|b");
                System.out.println("(Gauss-Seidel) Type NO to quit");
                String name = scanner.nextLine();
                if (name.equals("NO")) {
                    System.exit(0);
                }
                Matrix Ab = MatrixReader.readFile(name);
                boolean done1 = false;
                while(!done1) {
                    try {
                        System.out.println("(Gauss-Seidel) Enter name of the file to be read for initial vector x0");
                        System.out.println("(Gauss-Seidel) Type NO to quit");
                        String input1 = scanner.nextLine();
                        if (input1.equals("NO")) {
                            System.exit(0);
                        }
                        Matrix x0 = MatrixReader.readFile(input1);
                        boolean done2 = false;
                        while (!done2) {
                            System.out.println("(Gauss-Seidel) Enter the tolerance in DECIMAL FORM");
                            System.out.println("(Gauss-Seidel) Type NO to exit");
                            try {
                                String input = scanner.nextLine();
                                if (input.equals("NO")) {
                                    System.exit(0);
                                } else {
                                    double tol = Double.parseDouble(input);
                                    gauss_seidel(Ab, x0, tol, false);
                                    done2 = true;
                                }
                            } catch(NumberFormatException e) {
                                System.out.println("Invalid input");
                            }
                        }
                        done1 = true;
                    } catch(IOException e) {
                        System.out.println("File name not valid");
                        System.out.println();
                    }
                }
            } catch(IOException e) {
                System.out.println("File name not valid");
                System.out.println();
            }
        }
    }
    public static Matrix gauss_seidel(Matrix mb, Matrix x0, double tol, boolean isBinary) {
        System.out.println("-------------------------------------");
        System.out.println("Gauss-Seidel");
        System.out.println("-------------------------------------");
        double error = tol;
        int k = 0;
        if (x0.height != mb.height) {
            Matrix temp = new Matrix(new double[mb.height][1]);
            for (int j = 0; j < mb.height; j++) {
                if (j >= x0.height) {
                    temp.set(j, 0, 0);
                } else {
                    temp.set(j, 0, x0.get(j, 0));
                }
            }
            x0 = temp;
        }
        Matrix result = x0;
        Matrix prevResult;

        Matrix s = new Matrix(
            new double[mb.height][mb.width - 1]);
        Matrix t = new Matrix(
            new double[mb.height][mb.width - 1]);
        Matrix b = new Matrix(
            new double[mb.height][1]);
        for (int row = 0; row < mb.height; row++) {
            for (int col = 0; col < mb.width; col++) {
                if (col == mb.width - 1) {
                    b.set(row, 0, mb.get(row, col));
                } else if (col > row) {
                    t.set(row, col, mb.get(row, col));
                } else {
                    s.set(row, col, mb.get(row, col));
                }
            }
        }
        Matrix sInv = MatrixAlgebra.invertLowerTriangular(s);
        Matrix sInvB = MatrixAlgebra.matrixMultiply(sInv, b);
        Matrix negSInvT = MatrixAlgebra.scalarMultiply(
            MatrixAlgebra.matrixMultiply(sInv, t), -1);

        while (error >= tol) {
            k++;
            if (k > maxIterations) {
                System.out.println("Does not converge after " + maxIterations + " iterations");
                break;
            }
            prevResult = result;
            
            result = MatrixAlgebra.matrixAdd(
                MatrixAlgebra.matrixMultiply(
                negSInvT, result), sInvB);

            error = MatrixAlgebra.magnitudeVector(
                MatrixAlgebra.matrixSubtract(result, prevResult));
        }
        if (k <= maxIterations) {
            System.out.println("Converges based on tolerance after "+
                k + " iterations");
            System.out.println();
        }
        if (isBinary) {
            for (int row = 0; row < result.height; row++) {
                result.set(row, 0,
                    Math.abs(result.get(row, 0) % 2));
            }
        }
        return result;
    }
}
