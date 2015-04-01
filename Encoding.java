import java.util.LinkedList;
public class encoding {
    public static void encoding(int n) {
        //make random stream x
        Matrix x = new Matrix(new double[n + 3][1]);
        for (int i = 0; i < 3; i++) {
            x.set(i, 0, 0);
        }
        for (int i = 0; i < n; i++) {
            x.set(i, 0, (int)(Math.random() * 2));
        }
        /*
        //testing
        double[][] xArr = {{0},{0},{0},{1},{0},{1},{1},{0}};
        Matrix x = new Matrix(xArr);*/
        //make matrices A0 and A1
        Matrix a0 = new Matrix(new double[n + 3][n + 3]);
        int a0index1 = 0;
        int a0index2 = 1;
        int a0index3 = 3;
        for (int row = 0; row < n + 3; row++) {
            for (int col = 0; col < n + 3; col++) {
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
        Matrix a1 = new Matrix(new double[n + 3][n + 3]);
        int a1index1 = 0;
        int a1index2 = 2;
        int a1index3 = 3;
        for (int row = 0; row < n + 3; row++) {
            for (int col = 0; col < n + 3; col++) {
                if (col == a1index1 || col == a1index2
                    || col == a1index3) {
                    a1.set(row, col, 1);
                } else {
                    a1.set(row, col, 0);
                }
            }
            a1index1++;
            a1index2++;
            a1index3++;
        }
        Matrix y0 = MatrixAlgebra.matrixMultiply(a0, x);
        //mod by 2
        for (int i = 0; i < y0.height; i++) {
            y0.set(i, 0, y0.get(i, 0) % 2);
        }
        //System.out.println(y0);

        Matrix y1 = MatrixAlgebra.matrixMultiply(a1, x);
        //mod by 2
        for (int i = 0; i < y1.height; i++) {
            y1.set(i, 0, y1.get(i, 0) % 2);
        }
        //System.out.println(y1);

        for (int i = 0; i < y0.height; i++) {
            System.out.println("" + (int) y0.get(i, 0)
                + (int) y1.get(i, 0));
        }
    }
    public static void main(String[] args) {
        encoding(5);
    }
}
