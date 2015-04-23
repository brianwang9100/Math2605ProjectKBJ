import java.util.Scanner;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class lu_factBigDecimal {
    private static int SCALE = 10;
    public static void main(String[] args) {
        factorLU();
    }

    public static void factorLU() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("(LU) Enter name of the file to be read");
                System.out.println("(LU) Type NO to quit");
                String name = scanner.nextLine();
                if (name.equals("NO")) {
                    System.exit(0);
                }
                MatrixBigDecimal A = MatrixReaderBigDecimal.readFile(name);
                factorLU(A);
            } catch(IOException e) {
                System.out.println("File name not valid");
                System.out.println();
            }
        }
        // BigDecimal[][] matrix = {{8, 2, 9}, {4, 9, 4}, {6, 7, 9}};
        // Matrix A = new Matrix(matrix);
        // factorLU(A);
    }

    //lu[0] = L;
    //lu[1] = U;
    public static MatrixBigDecimal[] factorLU(MatrixBigDecimal a) {
        // long startTime = System.nanoTime();
        //make a copy of the matrix to not mess with original
        MatrixBigDecimal copy = MatrixAlgebraBigDecimal.copyOf(a);
        MatrixBigDecimal L = MatrixAlgebraBigDecimal.identityMatrix(a.width);
        MatrixBigDecimal U = MatrixAlgebraBigDecimal.identityMatrix(a.width);
        MatrixBigDecimal[] lu = new MatrixBigDecimal[2];
        //setup LU with identity matrices.
        lu[0] = L;
        lu[1] = U;
        factorLU(lu, copy);

        System.out.println("A:");
        System.out.println(a);

        System.out.println("L:");
        System.out.println(lu[0]);
        System.out.println("U:");
        System.out.println(lu[1]);

        MatrixBigDecimal LU = MatrixAlgebraBigDecimal.matrixMultiply(lu[0], lu[1]);

        MatrixBigDecimal errorMatrix = MatrixAlgebraBigDecimal.matrixSubtract(LU, a);
        BigDecimal error = MatrixAlgebraBigDecimal.findAbsoluteMax(errorMatrix);
        System.out.printf("||LU - A|| Error = %.24f\n", error.doubleValue());
        // long timeElapsed = System.nanoTime() - startTime;
        // System.out.println("Time Elapsed: " + timeElapsed + " nanoseconds");
        System.out.println();

        return lu;
    }

    private static void factorLU(MatrixBigDecimal[] lu, MatrixBigDecimal m) {
        MatrixBigDecimal L = lu[0];

        int pivotRow = 0;
        int pivotCol = 0;
        while (pivotRow != m.height - 1 && pivotCol != m.width - 1) {
            BigDecimal pivotValue = m.get(pivotRow, pivotCol);
            if (!pivotValue.equals(new BigDecimal("0"))) {                for (int row = pivotRow + 1; row < m.height; row++) {

                    BigDecimal valueToBeChanged = m.get(row, pivotCol);
                    BigDecimal scalar = valueToBeChanged.divide(pivotValue, SCALE, RoundingMode.HALF_UP);
                    if (!scalar.multiply(pivotValue).add(valueToBeChanged).equals(new BigDecimal("0"))) {
                        scalar = scalar.multiply(new BigDecimal("-1"));
                    }
                    m.rowScalarMultiply(scalar, pivotRow);
                    m.rowAdd(pivotRow, row);
                    m.rowScalarMultiply(new BigDecimal("1").divide(scalar, SCALE, RoundingMode.HALF_UP), pivotRow);
                    // for (int col = 0; col < m.width; col++) {
                    //     BigDecimal tempPValue = m.get(pivotRow, col);
                    //     BigDecimal value = m.get(row, col);
                    //     m.set(row, col, value + scalar * tempPValue);
                    // }

                    L.set(row, pivotCol, scalar.multiply(BigDecimal.ONE));
                }
            }
            pivotRow++;
            pivotCol++;
        }
        lu[0] = L;
        lu[1] = m;
    }
}
