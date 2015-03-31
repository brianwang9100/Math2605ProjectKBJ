import java.util.Scanner;
import java.io.IOException;

public class qr_fact_househ {
    public static void main(String[] args) {
        factorHH();
    }

    public static void factorHH() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("(HouseHolderQR) Enter name of the file to be read");
                System.out.println("(HouseHolderQR) Type NO to quit");
                String name = scanner.nextLine();
                if (name.equals("NO")) {
                    System.exit(0);
                }
                Matrix A = MatrixReader.readFile(name);
                factorHH(A);
            } catch(IOException e) {
                System.out.println("File name not valid");
                System.out.println();
            }
        }

        // double[][] holder = {{4, 3, 0}, {3, 1, 0}, {0, 0 , 1}};
        // Matrix A = new Matrix(holder);
        // factorHH(A);
    }

    public static Matrix[] factorHH(Matrix a) {
        long startTime = System.nanoTime();
        if (a.height != a.width) {
            throw new IllegalArgumentException("Matrix must be square!");
        }
        Matrix copy = MatrixAlgebra.copyOf(a);
        Matrix[] qr = new Matrix[2];
        Matrix Q = MatrixAlgebra.identityMatrix(a.width);
        Matrix R = a;
        qr[0] = Q;
        qr[1] = R;
        //qr[0] = Q
        //qr[1] = R
        factorHH(qr, copy, 0);

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

    private static void factorHH(Matrix[] qr, Matrix current, int index) {
        if (current.width != 1) {
            Matrix Q = qr[0];
            Matrix R = qr[1];
            Matrix HH = findHouseHolder(current);
            System.out.println("HH");
            System.out.println(HH);


            //puts matrix to equal original size
            Matrix converted = embedWithIdentity(HH, Q.width);

            //appends matrix to Q and R.
            qr[0] = MatrixAlgebra.matrixMultiply(Q, converted);
            qr[1] = MatrixAlgebra.matrixMultiply(converted, R);

            //recursively calls on the next H'
            Matrix HPrime = findHPrime(qr[1], index + 1);
            factorHH(qr, HPrime, index + 1);
        }
    }

    private static Matrix findHouseHolder(Matrix current) {

        //steps to find uTilda
        Matrix v1 = MatrixAlgebra.vectorOfMatrix(current, 0);

        double magnitudeV1 = MatrixAlgebra.magnitudeVector(v1);
        Matrix identityV = identityVector(current.height);
        Matrix v2 = MatrixAlgebra.scalarMultiply(identityV, magnitudeV1);
        Matrix u = MatrixAlgebra.matrixSubtract(v1, v2);

        //H = I - 2uu^t
        Matrix identityMatrix = MatrixAlgebra.identityMatrix(current.height);
        Matrix uTransposed = MatrixAlgebra.transpose(u);
        Matrix m = MatrixAlgebra.matrixMultiply(u, uTransposed);
        System.out.println("uu^t");
        System.out.println(m);
        double magnitude = MatrixAlgebra.magnitudeVector(u);
        double scalar;
        if (magnitude == 0) {
            scalar = 1;
        } else {
            scalar = 2/Math.pow(magnitude, 2);
        }
        Matrix twoM = MatrixAlgebra.scalarMultiply(m, scalar);
        Matrix HH = MatrixAlgebra.matrixSubtract(identityMatrix, twoM);

        return HH;
    }


    private static Matrix embedWithIdentity(Matrix m, int size) {
        if (m.width == size) {
            return m;
        }
        double[][] holder = new double[size][size];
        for (int rowCol = 0; rowCol < size; rowCol++) {
            holder[rowCol][rowCol] = 1;
        }
        for (int row = size - m.height, i = 0; i < m.height; row++, i++) {
            for (int col = size - m.width, j = 0; j < m.width; col++, j++) {
                holder[row][col] = m.get(i, j);
            }
        }
        return new Matrix(holder);
    }

    private static Matrix identityVector(int size) {
        double[][] holder = new double[size][1];
        holder[0][0] = 1;
        return new Matrix(holder);
    }

    private static Matrix findHPrime(Matrix m, int index) {


        double[][] holder = new double[m.height - index][m.width - index];
        for (int row = 0, i = index; i < m.height; row++, i++) {
            for (int col = 0, j = index; j < m.width; col++, j++) {
                holder[row][col] = m.get(i, j);
            }
        }

        return new Matrix(holder);
    }
}
