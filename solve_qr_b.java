import java.util.Scanner;
import java.io.IOException;
public class solve_qr_b {
    public static void main(String[] args) {
        solveQR();
    }
    public static void solveQR() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("(QRSolve) Enter name of the file to be read OR type NO to quit");
                String name = scanner.nextLine();
                if (name.equals("NO")) {
                    System.exit(0);
                }
                Matrix Ab = MatrixReader.readFile(name);
                Matrix A = MatrixReader.convertToA(Ab);
                Matrix b = MatrixReader.convertTob(Ab);
                boolean complete = false;
                while (!complete) {
                    System.out.println("(QRSolve) Type HH for householder");
                    System.out.println("(QRSolve) Type G for givens");
                    System.out.println("(QRSolve) Type HH for householder");
                    System.out.println("(QRSolve) Type NO to exit");
                    String input = scanner.nextLine();
                    if (input.equals("HH")) {
                        solveQRHH(A, b);
                        complete = true;
                    } else (input.equals("G")) {
                        solveQRG(A, b);
                        complete = true;
                    } else (input.equals("NO")){
                        System.exit(0);
                    } else {
                        System.out.println("Invalid Input");
                    }
                }
            } catch(IOException e) {
                System.out.println("File name not valid");
                System.out.println();
            }
        }
    }

    public static void solveQRHH(Matrix A, Matrix b) {
        Matrix[] qr = qr_fact_househ.factorHH(A);

    }
    public static void solveQRG(Matrix A, Matrix b) {
        Matrix[] qr = qr_fact_givens.factorG(A);

    }
}
