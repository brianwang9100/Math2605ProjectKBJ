import java.util.Scanner;
import java.util.InputMismatchException;
import java.math.BigDecimal;
public class hilbert_driverBigDecimal {
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
                    MatrixBigDecimal H = createHilbertMatrix(n);
                    MatrixBigDecimal b = createb(n);

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

    private static MatrixBigDecimal createHilbertMatrix(int n) {
        BigDecimal[][] holder = new BigDecimal[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int denom = (row + 1) + (col + 1) - 1;
                holder[row][col] = BigDecimal.ONE.divide(new BigDecimal(denom));
            }
        }

        return new MatrixBigDecimal(holder);
    }

    private static MatrixBigDecimal createb(int n) {
        BigDecimal[][] holder = new BigDecimal[n][1];
        BigDecimal value = BigDecimalMath.pow(new BigDecimal("0.1"), new BigDecimal(n).divide(new BigDecimal("3.0")));
        for (int row = 0; row < n; row++) {
            holder[row][0] = value;
        }

        return new MatrixBigDecimal(holder);
    }

}
