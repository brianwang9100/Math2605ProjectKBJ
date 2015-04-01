import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

public class convolutional_codes_driver {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print(
            "(ConvolutionalCodes) " +
            "Enter a length for binary stream x: ");
        int n = Integer.parseInt(s.nextLine());
        encoding.encoding(n);
        System.out.println("\n");
        System.out.print("(ConvolutionalCodes) " +
            "Enter the path of the .dat file containing " +
            "the augmented matrix [A][b] for Jacobi and Gauss-Seidel: ");
        Matrix ab = null;
        try {
            String input = s.nextLine();
            System.out.println("\nMatrix in file: ");
            ab = MatrixReader.readFile(input);
        } catch (IOException e) {
            System.out.println("Improper file or path inputted");
            System.exit(0);
        }
        System.out.println(ab);
        System.out.println();
        System.out.print("(ConvolutionalCodes) " +
            "Enter the path of the .dat file containing " +
            "the initial guess vector x0: ");
        Matrix x0 = null;
        try {
            String input = s.nextLine();
            System.out.println("\nMatrix in file: ");
            x0 = MatrixReader.readFile(input);
        } catch (IOException e) {
            System.out.println("Improper file or path inputted");
            System.exit(0);
        }
        System.out.println(x0);
        System.out.println();
        System.out.print("(ConvolutionalCodes) Enter the error tolerance number: ");
        double tol = Double.parseDouble(s.nextLine());
        jacobi.jacobi(ab, x0, tol);
        gauss_seidel.gauss_seidel(ab, x0, tol);

        System.out.println("(ConvolutionalCodes) Enter the numbers for the y input string"
            + " one by one, type d when done: ");
        ArrayList yList = new ArrayList();
        while (s.hasNextDouble()) {
            yList.add(s.nextDouble());
        }
        double[][] y = new double[yList.size()][1];
        for (int i = 0; i < y.length; i++) {
            y[i][0] = (double) yList.get(i);
        }
        s.nextLine();

        decoding.decoding(new Matrix(y));
        System.out.println();
    }
}