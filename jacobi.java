public class jacobi {
    static final int maxIterations = 100000;
    public static Matrix jacobi(Matrix mb, Matrix x0, double tol) {
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
                    omega = Math.abs(omega % 2);
                }
                if (row < m.height && row < m.width) {
                    result.set(row, 0,
                        Math.abs(((b.get(row, 0) - omega) /
                            m.get(row, row)) % 2));
                }
            }
            curValue = MatrixAlgebra.magnitudeVector(result);
            xk = result;
            error = Math.abs(curValue - prevValue);
        }
        if (k <= maxIterations) {
            System.out.println("Converges based on tolerance after "+
                k + " iterations");
        }
        return xk;
    }
    public static void main(String[] args) {
        //double[][] matrix1 = {{8, 2, 9}, {4, 9, 4}, {6, 7, 9}};
        //double[][] matrix2 = {{3}, {4}, {5}};
        //double[][] x0 = {{1}, {1}};
        //double[][] matrix = {{2, 1, 11}, {5, 7, 13}};
        //double[][] matrix2 = {{11}, {13}};
        //Matrix mb = new Matrix(matrix);
        //Matrix b = new Matrix(matrix2);
        //System.out.println(jacobi(mb, new Matrix(x0), .2));
    }
}
