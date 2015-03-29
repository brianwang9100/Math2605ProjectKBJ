import java.util.Scanner;
public class qr_fact_househ {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of the file to be read");
        String name = scanner.nextLine();
        Matrix a = MatrixReader.readFile(name);

        Matrix[] qr = factorHH(a)

        System.out.println("Q:");
        System.out.println(qr[0]);
        System.out.println("R:");
        System.out.println(qr[1]);

        //still need to do error Need to make eigenvalue method

    }

    public static Matrix[] factorHH(Matrix a) {
        if (a.height != a.width) {
            throw new IllegalArgumentException("Matrix must be square!");
        }
        Matrix[] qr = new Matrix[2];
        Matrix Q = MatrixAlgebra.identity(a.width);
        Matrix R = a;
        qr[0] = Q;
        qr[1] = R;
        //qr[0] = Q
        //qr[1] = R
        factorHH(qr, a);
        return qr;
    }

    private static void factorHH(Matrix[] qr, Matrix current) {
        if (current.width != 1) {
            Matrix Q = qr[0];
            Matrix R = qr[1];
            Matrix HH = findHouseHolder(current);

            //puts matrix to equal original size
            Matrix converted = embedWithIdentity(HH, Q.width);

            //appends matrix to Q and R.
            qr[0] = matrixMultiply(Q, converted);
            qr[1] = matr’ixMultiply(converted, R);

            //recursively calls on the next H'
            Matrix HPrime = findHPrime(HH);
            factorHH(qr, HPrime);
        }
    }

    private static Matrix findHouseHolder(Matrix current) {
        // uTilda = v1 + v2
        // where v2 = unitVector * 1/||v1||
        Matrix v1 = MatrixAlgebra.vectorOfMatrix(current, 0);
        Matrix v2 = MatrixAlgebra.magnitudeVector(v1) * identityVector(current.height);
        Matrix u = MatrixAlgebra.matrixAdd(v1, v2)
        Matrix uTilda = MatrixAlgebra.unitVector(u);

        Matrix identityMatrix = MatrixAlgebra.identityMatrix(current.height);
        Matrix uTildaTransposed = MatrixAlgebra.transpose(uTilda);
        Matrix m = MatrixAlgebra.matrixMultiply(uTilda, uTildaTranspose);
        // m = uTilda*uTildaTranspose
        Matrix HH = MatrixAlgebra.matrixSubtract(identityMatrix, m);

        return HH;
    }


    private static Matrix embedWithIdentity(Matrix m, int size) {
        if (m.width == size) {
            return m;
        }
        double[][] holder = new double[size][size];
        for (int rowCol = 0; rowCol < size - m.length; i++) {
            holder[rowCol][rowCol] = 1;
        }
        for (int row = size - m.length, i = 0; i < m.height; row++, i++) {
            for (int col = size - m.width, j = 0; j < m.width; row++, j++;) {
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

    private static Matrix findHPrime(Matrix m) {
        double[][] holder = new double[m.height - 1][m.width - 1];
        for (int row = 0, i = 1; i < m.height; row++, i++) {
            for (int col = 0, j = 1; j < m.width; col++, j++) {
                holder[row][col] = m.get(i, j);
            }
        }

        return new Matrix(holder);
    }
}
