package Math2605ProjectKBJ;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MatrixReader {
    //only stores data from a.dat currently
    public static double[][] readFile() throws IOException {
        double[][] numArray = new double[4][4];
        int height = 0;
        int width = 0;
        for (String line : Files.readAllLines(Paths.get("Math2605ProjectKBJ/a.dat"))) {
            for (String part : line.split(" ")) {
                numArray[width][height] = Double.valueOf(part);
                width++;
            }
            width = 0;
            height++;
        }
        return numArray;
    }
    public static void main(String[] args) {
        try {
            double[][] numArray = readFile();
            for(double[] a : numArray) {
                for(double b : a) {
                    System.out.print(b + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Input threw IOException");
        }
    }
}