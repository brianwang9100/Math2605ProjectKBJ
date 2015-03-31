import java.util.Scanner;
import java.util.InputMismatchException;
public class hilbert_driver {
    public static void main(String[] args) {
        hilbertSolve();
    }

    public static void hilbertSolve() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("(HilbertSolve) Type the size n of Hilbert to create");
            System.out.println("(HilbertSolve) Type NO to exit");
            try {

                String input = scanner.nextLine();
                if (input.equals("NO")) {
                    System.exit(0);
                } else {
                    int n = Integer.parseInt(input);
                    Matrix H = createHilbertMatrix(n);
                    Matrix b = createb(n);

                    System.out.println("-------------------------------------");
                    System.out.println("Hilbert with LU Factorization");
                    System.out.println("-------------------------------------");
                    solve_lu_b.solveLU(H, b);

                    System.out.println("-------------------------------------");
                    System.out.println("Hilbert with QR HouseHolder Factorization");
                    System.out.println("-------------------------------------");
                    solve_qr_b.solveQRHH(H, b);

                    System.out.println("-------------------------------------");
                    System.out.println("Hilbert with QR Givens Factorization");
                    System.out.println("-------------------------------------");
                    solve_qr_b.solveQRG(H, b);
                }
            } catch(NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }
    }

    private static Matrix createHilbertMatrix(int n) {
        double[][] holder = new double[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                holder[row][col] = 1.0 / ((row + 1) + (col + 1) - 1);
            }
        }

        return new Matrix(holder);
    }

    private static Matrix createb(int n) {
        double[][] holder = new double[n][1];
        double value = Math.pow((0.1), n / 3.0);
        for (int row = 0; row < n; row++) {
            holder[row][0] = value;
        }

        return new Matrix(holder);
    }

}
