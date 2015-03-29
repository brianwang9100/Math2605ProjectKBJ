import java.util.Scanner;
public class qr_fact_househ {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of the file to be read");
        String name = scanner.nextLine();
        Matrix a = MatrixReader.readFile(name);

        Matrix[] qr = factorG(a)

        System.out.println("Q:");
        System.out.println(qr[0]);
        System.out.println("R:");
        System.out.println(qr[1]);

        //still need to do error Need to make eigenvalue method
    }

    public static Matrix factorG(Matrix a) {
        if (a.height != a.width) {
            throw new IllegalArgumentException("Matrix must be square!");
        }
        Matrix[] qr = new Matrix[2];
        Matrix Q = MatrixAlgebra.identityMatrix(a.width);
        Matrix R = a;
        qr[0] = Q;
        qr[1] = R;
        //qr[0] = Q
        //qr[1] = R
        factorG(qr, a);
        return qr;
    }

    private static void factorG(qr, a) {

    }
}
