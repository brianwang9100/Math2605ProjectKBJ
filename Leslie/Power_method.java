public class power_method {
    //instance variables
    private Matrix a;
    private double tol;
    private Matrix u;
    private static int iterationsCount;

    public static final int MAX_ITERATIONS = 100;

    public power_method(Matrix a, double tol, Matrix u) {
        this.a = a;
        this.tol = tol;
        this.u = u;
        iterationsCount = 0;
    }

    public static Matrix getLargestEvector(Matrix a) {
        
        Matrix vector = new Matrix( new double[a.height][1] );
        for (int i = 0; i < vector.height; i++) {
            vector.set(i, 0, 1);
        }
        return getLargestEvector(a, 8.0, vector);
    }

    public static Matrix getLargestEvector(Matrix a, double tol, Matrix u) {
        if (a.width != u.height) {
            throw new RuntimeException("dimensions don't match");
        }

        Matrix eVector = largestEvectorRec(a, tol, u);
        double[][] result = new double[eVector.height][1];
        Matrix returnMatrix = new Matrix(result);
        double largestEntry = findLargestEntry(eVector);
        for (int i = 0; i < eVector.height; i++) {
            double input = eVector.get(i, 0) / largestEntry;
            returnMatrix.set(i, 0, input);
        }
        return returnMatrix;
    }

    private static Matrix largestEvectorRec(Matrix a, double tol, Matrix u) {
        
        Matrix iteration = MatrixAlgebra.matrixMultiply(a, u);
        iterationsCount++;
        if (iterationsCount != MAX_ITERATIONS) {
            largestEvectorRec(a, tol, iteration);
        }
        return iteration;
    }

    public static double getLargestEvalue(Matrix a) {
        Matrix x = getLargestEvector(a);
        Matrix Ax = MatrixAlgebra.matrixMultiply(a, x);
        double res = dotProd(Ax, x) / dotProd(x,x);
        return res;
    }

    public static double dotProd(Matrix a, Matrix b){
        if(a.height != b.height){
            throw new IllegalArgumentException("dimensions don't agree");
        }
        double sum = 0;
        for(int i = 0; i < a.height; i++){
            sum += a.get(i, 0) * b.get(i, 0);
        }
        return sum;
    }

    private static double findLargestEntry(Matrix m) {
        double currentMax = 0;
        for (int i = 0; i < m.height; i++) {
            if (Math.abs(m.get(i, 0)) > currentMax) {
                currentMax = m.get(i, 0);
            }
        }
        return currentMax;
    }
}
