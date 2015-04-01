import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;
public class jacobi {
    static final int maxIterations = 100000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("(Jacobi) Enter name of the file to be read for augmented matrix A|b");
                System.out.println("(Jacobi) Type NO to quit");
                String name = scanner.nextLine();
                if (name.equals("NO")) {
                    System.exit(0);
                }
                Matrix Ab = MatrixReader.readFile(name);
                boolean done1 = false;
                while(!done1) {
                    try {
                        System.out.println("(Jacobi) Enter name of the file to be read for initial vector x0");
                        System.out.println("(Jacobi) Type NO to quit");
                        String input1 = scanner.nextLine();
                        if (input1.equals("NO")) {
                            System.exit(0);
                        }
                        Matrix x0 = MatrixReader.readFile(input1);
                        boolean done2 = false;
                        while (!done2) {
                            System.out.println("(Jacobi) Enter the tolerance in DECIMAL FORM");
                            System.out.println("(Jacobi) Type NO to exit");
                            try {
                                String input = scanner.nextLine();
                                if (input.equals("NO")) {
                                    System.exit(0);
                                } else {
                                    double tol = Double.parseDouble(input);
                                    jacobi(Ab, x0, tol, false);
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
    public static Matrix jacobi(Matrix mb, Matrix x0, double tol, boolean isBinary) {
        System.out.println("-------------------------------------");
        System.out.println("Jacobi");
        System.out.println("-------------------------------------");
        Matrix m = new Matrix(
            new double[mb.height][mb.width - 1]);
        Matrix b = new Matrix(
            new double[m.height][1]);
        for (int row = 0; row < mb.height; row++) {
            for (int col = 0; col < mb.width; col++) {
                if (col == mb.width - 1) {
                    b.set(row, 0, mb.get(row, col));
                } else {
                    m.set(row, col, mb.get(row, col));
                }
            }
        }
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
        double error = tol;
        double prevValue;
        double curValue = 0;
        int k = 0;
        Matrix xk = x0;
        while (error >= tol) {
            k++;
            if (k > maxIterations) {
                System.out.println("Does not converge after " + maxIterations + " iterations");
                break;
            }
            double omega;
            Matrix result = new Matrix(new double[m.height][1]);
            prevValue = curValue;
            for (int row = 0; row < m.height; row++) {
                omega = 0;
                for (int col = 0; col < m.width; col++) {
                    if (row != col) {
                        omega += (m.get(row, col) * xk.get(col, 0));
                    }
                    omega = Math.abs(omega);
                }
                if (row < m.height && row < m.width) {
                    if (isBinary) {
                        result.set(row, 0,
                            Math.abs(((b.get(row, 0) - omega) /
                                m.get(row, row))));
                    } else {
                        result.set(row, 0,
                            Math.abs(((b.get(row, 0) - omega) /
                                m.get(row, row)) % 2));
                    }
                }
            }
            curValue = MatrixAlgebra.magnitudeVector(result);
            xk = result;
            error = Math.abs(curValue - prevValue);
        }
        if (k <= maxIterations) {
            System.out.println("Converges based on tolerance after " +
                k + " iterations");
        }
        return xk;
    }
}
