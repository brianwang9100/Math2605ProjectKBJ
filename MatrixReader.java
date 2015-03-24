package Math2605ProjectKBJ;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MatrixReader {
    //only stores data from a.dat currently
    public static Matrix readFile() throws IOException {
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
    }
    public static Matrix readFile(Path path) throws IOException {
        double[][] numArray;
        int row = 0;
        int col = 0;
        for (String line : Files.readAllLines(path)) {
            for (String part : line.split(" ")) {
                col++;
            }
            row++;
        }
        numArray = new double[row][col];
        for (String line : Files.readAllLines(path)) {
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
    public static void main(String[] args) {
        try {
            System.out.print(readFile());
        } catch (IOException e) {
            System.out.println("Input threw IOException");
        }
    }
}
