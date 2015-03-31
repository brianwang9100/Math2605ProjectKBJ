public class decoding {
    public static void decoding(Matrix a) {
        double[][] x0 = {{0}, {0}};
        Gauss_seidel.gauss_seidel(a, new Matrix(x0), .01);
    }
    public static void main(String[] args) {
        Encoding.encoding(5);
        Matrix y0 = Encoding.y0;
        Matrix a0 = Encoding.a0;
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
    }
}