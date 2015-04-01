import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MatrixReader {
    //only stores data from a.dat currently
    /*public static Matrix readFile() {
        try {
            double[][] numArray = new double[4][4];
            int row = 0;
            int col = 0;
            for (String line : Files.readAllLines(Paths.get("a.dat"))) {
                for (String part : line.split(" ")) {
                    numArray[row][col] = Double.valueOf(part);
                    col++;
                }
                col = 0;
                row++;
            }
            return new Matrix(numArray);
        } catch (IOException e) {
            System.out.println("Improper file or path inputted");
            System.exit(0);
        }
        return null;
    }*/
    public static Matrix readFile(Path path) throws IOException {
        double[][] numArray;
        int row = 0;
        int col = 0;
        for (String line : Files.readAllLines(path)) {
            col = 0;
            for (String part : line.split(" ")) {
                col++;
            }
            row++;
        }
        numArray = new double[row][col];
        row = 0;
        col = 0;
        for (String line : Files.readAllLines(path)) {
            col = 0;
            for (String part : line.split(" ")) {
                numArray[row][col] = Double.valueOf(part);
                col++;
            }
            row++;
        }
        return new Matrix(numArray);
    }
    public static Matrix readFile(String s) throws IOException {
        return readFile(Paths.get(s));
    }

    public static Matrix convertToA(Matrix Ab) {
        double[][] holder = new double[Ab.height][Ab.width - 1];
        for (int row = 0; row < Ab.height; row++) {
            for (int col = 0; col < Ab.width - 1; col++) {
                holder[row][col] = Ab.get(row, col);
            }
        }
        return new Matrix(holder);
    }

    public static Matrix convertTob(Matrix Ab) {
        return MatrixAlgebra.vectorOfMatrix(Ab, Ab.width - 1);
    }
}
