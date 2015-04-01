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
        double prevValue;
        double curValue = 0;
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

        while (error >= tol) {
            k++;
            if (k > maxIterations) {
                System.out.println("Does not converge after" + maxIterations + " iterations");
                break;
            }
            double omega;
            prevValue = curValue;
            for (int row = 0; row < m.height; row++) {
                omega = 0;
                for (int col = 0; col < m.width; col++) {
                    if (col != row) {
                        omega += (m.get(row, col) * result.get(col, 0));
                    }
                }
                //System.out.println(m);
                if (row < m.height && row < m.width) {
                    if (isBinary) {
                        result.set(row, 0,
                            Math.abs(((b.get(row, 0) - omega) /
                                m.get(row, row)) % 2));
                    } else {
                        result.set(row, 0,
                            (b.get(row, 0) - omega) /
                                m.get(row, row));
                    }
                }
            }
            curValue = MatrixAlgebra.magnitudeVector(result);
            //System.out.println(result);
            error = Math.abs(curValue - prevValue);
            //System.out.println("Error:" + error);
            //System.out.println(error);
        }
        //System.out.println("x = " + result);
        if (k <= maxIterations) {
            System.out.println("Converges based on tolerance after "+
                k + " iterations");
            System.out.println();
        }
        //jacobi method
        //xk+1 = S^-1 * T * xk + S^-1 * b
        return result;
    }
}
