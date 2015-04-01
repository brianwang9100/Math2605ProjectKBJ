public class decoding {
    /*public static void decoding(Matrix a) {
        double[][] x0 = {{0}, {0}};
        gauss_seidel.gauss_seidel(a, new Matrix(x0), .00000001);
    }
    public static void main(String[] args) {
        encoding.encoding(5);
        Matrix y0 = encoding.y0;
        Matrix a0 = encoding.a0;
        Matrix aug = new Matrix(
            new double[y0.height][
            y0.width + a0.width]);
        for (int row = 0; row < aug.height; row++) {
            for (int col = 0; col < aug.width; col++) {
                if (col == aug.width - 1) {
                    aug.set(row, col, y0.get(row, 0));
                } else {
                    aug.set(row, col, a0.get(row, col));
                }
            }
        }
        decoding(aug);
    }*/
    public static void decoding(Matrix y) {
        System.out.println("-------------------------------------");
        System.out.println("Decoding");
        System.out.println("-------------------------------------");
        double[][] x0 = {{0}, {0}};
        double[][] arry0 = new double[y.height][1];
        for (int i = 0; i < y.height; i++) {
            arry0[i][0] = y.get(i, 0) / 10;
        }
        Matrix y0 = new Matrix(arry0);
        System.out.println(y0.toStringBinary());
        Matrix a0 = new Matrix(new double[y0.height][y0.height]);
        int a0index1 = 0;
        int a0index2 = 1;
        int a0index3 = 3;
        for (int row = 0; row < y0.height; row++) {
            for (int col = 0; col < y0.height; col++) {
                if (col == a0index1 || col == a0index2
                    || col == a0index3) {
                    a0.set(row, col, 1);
                } else {
                    a0.set(row, col, 0);
                }
            }
            a0index1++;
            a0index2++;
            a0index3++;
        }
        Matrix aug = new Matrix(
            new double[y0.height][
            y0.width + a0.width]);
        for (int row = 0; row < aug.height; row++) {
            for (int col = 0; col < aug.width; col++) {
                if (col == aug.width - 1) {
                    aug.set(row, col, y0.get(row, 0));
                } else {
                    aug.set(row, col, a0.get(row, col));
                }
            }
        }
        System.out.println(jacobi.jacobi(aug, new Matrix(x0), .00000001).toStringBinaryCropFirst());
        System.out.println(gauss_seidel.gauss_seidel(aug, new Matrix(x0), .00000001).toStringBinaryCropFirst());
    }
}