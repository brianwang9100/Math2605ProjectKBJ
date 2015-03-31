public class jacobi {
    public static int jacobi(Matrix mb, Matrix x0, double tol) {
        Matrix m = new Matrix(
            new double[mb.height][mb.width - 1]);
        //t = l + u
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
        double error = tol;
        double prevValue;
        double curValue = 0;
        int k = 0;
        Matrix xk = x0;
        while (error >= tol) {
            k++;
            double omega;
            Matrix result = new Matrix(new double[m.height][1]);
            prevValue = curValue;
            for (int row = 0; row < m.height; row++) {
                omega = 0;
                for (int col = 0; col < m.width; col++) {
                    if (row != col) {
                        omega += (m.get(row, col) * xk.get(col, 0));
                    }
                }
                result.set(row, 0,
                    (b.get(row, 0) - omega) / m.get(row, row));
            }
            curValue = MatrixAlgebra.magnitudeVector(result);
            xk = result;
            error = Math.abs(curValue - prevValue);
        }
        return k;
        //jacobi method
        //xk+1 = S^-1 * T * xk + S^-1 * b
    }
    public static void main(String[] args) {
        //double[][] matrix1 = {{8, 2, 9}, {4, 9, 4}, {6, 7, 9}};
        //double[][] matrix2 = {{3}, {4}, {5}};
        double[][] x0 = {{1}, {1}};
        double[][] matrix = {{2, 1, 11}, {5, 7, 13}};
        //double[][] matrix2 = {{11}, {13}};
        Matrix mb = new Matrix(matrix);
        //Matrix b = new Matrix(matrix2);
        System.out.println(jacobi(mb, new Matrix(x0), .2));
    }
}
