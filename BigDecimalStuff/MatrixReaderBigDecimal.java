import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.math.BigDecimal;


public class MatrixReaderBigDecimal {
    //only stores data from a.dat currently
    /*public static Matrix readFile() {
        try {
            BigDecimal[][] numArray = new BigDecimal[4][4];
            int row = 0;
            int col = 0;
            for (String line : Files.readAllLines(Paths.get("a.dat"))) {
                for (String part : line.split(" ")) {
                    numArray[row][col] = BigDecimal.valueOf(part);
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
    public static MatrixBigDecimal readFile(Path path) throws IOException {
        BigDecimal[][] numArray;
        int row = 0;
        int col = 0;
        for (String line : Files.readAllLines(path)) {
            col = 0;
            for (String part : line.split(" ")) {
                col++;
            }
            row++;
        }
        numArray = new BigDecimal[row][col];
        row = 0;
        col = 0;
        for (String line : Files.readAllLines(path)) {
            col = 0;
            for (String part : line.split(" ")) {
                numArray[row][col] = new BigDecimal(part);
                col++;
            }
            row++;
        }
        return new MatrixBigDecimal(numArray);
    }
    public static MatrixBigDecimal readFile(String s) throws IOException {
        return readFile(Paths.get(s));
    }

    public static MatrixBigDecimal convertToA(MatrixBigDecimal Ab) {
        BigDecimal[][] holder = new BigDecimal[Ab.height][Ab.width - 1];
        for (int row = 0; row < Ab.height; row++) {
            for (int col = 0; col < Ab.width - 1; col++) {
                holder[row][col] = Ab.get(row, col);
            }
        }
        return new MatrixBigDecimal(holder);
    }

    public static MatrixBigDecimal convertTob(MatrixBigDecimal Ab) {
        return MatrixAlgebraBigDecimal.vectorOfMatrix(Ab, Ab.width - 1);
    }
}
