public class jacobi {
    public static double jacobi(Matrix m, Matrix b, double tol) {
        /*//set l d and u

        //s = d
        Matrix s = new Matrix(
            new double[m.height][m.width]);
        //t = l + u
        Matrix t = new Matrix(
            new double[m.height][m.width]);
        for (int row = 0; row < m.height; row++) {
            for (int col = 0; col < m.width; col++) {
                if (col == row) {
                    s.set(row, col, m.get[row, col]);
                } else {
                    t.set(row, col, m.get[row, col]);
                }
            }
        }*/
        double error = tol;
        double prevValue;
        double curValue = 0;
        double[][] xkArr = {{1}, {1}};
        int k = 0;
        Matrix xk = new Matrix(xkArr);
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
            System.out.println(result);
            error = Math.abs(curValue - prevValue);
            System.out.println("Error:" + error);
            //System.out.println(error);
        }
        return error;
        //jacobi method
        //xk+1 = S^-1 * T * xk + S^-1 * b
    }
    public static void main(String[] args) {
        //double[][] matrix1 = {{8, 2, 9}, {4, 9, 4}, {6, 7, 9}};
        //double[][] matrix2 = {{3}, {4}, {5}};
        double[][] matrix1 = {{2, 1}, {5, 7}};
        double[][] matrix2 = {{11}, {13}};
        Matrix m = new Matrix(matrix1);
        Matrix b = new Matrix(matrix2);
        System.out.println(jacobi(m, b, .2));
    }
}
