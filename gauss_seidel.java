public class gauss_seidel {
    static final int maxIterations = 100000;
    public static Matrix gauss_seidel(Matrix mb, Matrix x0, double tol) {
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
                    result.set(row, 0,
                        Math.abs(((b.get(row, 0) - omega) /
                            m.get(row, row)) % 2));
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
        }
        //jacobi method
        //xk+1 = S^-1 * T * xk + S^-1 * b
        return result;
    }
}
