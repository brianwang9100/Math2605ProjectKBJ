public class power_method {

        public static Result<Double, Matrix, Integer> largestEigen(Matrix a, Matrix u, double tol) {

            Matrix prevValue = new Matrix(new double[u.height][1]);
            for (int i = 0; i < prevValue.height; i++) {
                prevValue.set(i, 0, 0);
            }
            Matrix vector = u;
            int counter = 0;
            Matrix approx = new Matrix(new double[vector.height][1]);
            double tolCounter =  MatrixAlgebra.magnitudeVector(vector) - MatrixAlgebra.magnitudeVector(prevValue);
            while(tolCounter > tol && counter < 100) {
                Matrix iteration = MatrixAlgebra.matrixMultiply(a, vector);
                Matrix approximation = new Matrix(new double[iteration.height][1]);
                for (int i = 0; i < approximation.height; i++) {
                    double value = iteration.get(i, 0) / (iteration.get(iteration.height - 1, 0));
                    approximation.set(i, 0, value);
                }
                Matrix error = new Matrix(new double [approximation.height][1]);
                for (int i = 0; i < approximation.height; i++) {
                    double value = Math.abs(prevValue.get(i, 0) - approximation.get(i, 0));
                    error.set(i, 0, value);
                }
                for (int i = 0; i < vector.height; i++) {
                    approx.set(i, 0, approximation.get(i,0));
                }
                double oldPrevValueLength = MatrixAlgebra.magnitudeVector(prevValue);
                prevValue = approximation;
                vector = iteration;
                tolCounter = Math.abs(oldPrevValueLength - MatrixAlgebra.magnitudeVector(prevValue));
                counter++;
            }
            if (counter >= 100) {
                throw new RuntimeException("Over 100 iterations. Stopped. Does not converge.");
            }
            Matrix eVector = approx;
            Matrix aau = MatrixAlgebra.matrixMultiply(a, approx);
            double eValue = dotProd(aau, approx) / dotProd(approx,approx);
            return new Result<Double, Matrix, Integer>(eValue, eVector, counter);
            

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

        public static void main(String[] args) {
            double[][] leslie =
                {{0,.6,1.1,.9,.1,0,0,0,0},
                {.7,0,0,0,0,0,0,0,0},
                {0,.85,0,0,0,0,0,0,0},
                {0,0,.9,0,0,0,0,0,0},
                {0,0,0,.9,0,0,0,0,0},
                {0,0,0,0,.88,0,0,0,0},
                {0,0,0,0,0,.8,0,0,0},
                {0,0,0,0,0,0,.77,0,0},
                {0,0,0,0,0,0,0,.40,0}};
            Matrix leslieMatrix = new Matrix(leslie);
            double[][] x0 =
                {{5.1875},{4.445}, {1.2495}, {1.4535}, {1.4580}, {1.6632}, {1.408000}, {1.047200}, {0.3696}};
            Matrix n = new Matrix(x0);
            System.out.println(largestEigen(leslieMatrix, n, .00000001));
        }
    }
