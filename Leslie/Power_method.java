public class power_method {

        public static Result<Double, Matrix, Integer> largestEigen(Matrix a, Matrix u, double tol) {
            //zero vector created to do initial
            Matrix prevValue = new Matrix(new double[u.height][1]);
            for (int i = 0; i < prevValue.height; i++) {
                prevValue.set(i, 0, 0);
            }
            Matrix vector = u;
            //System.out.println("Pre-Loop magnitude:" + MatrixAlgebra.magnitudeVector(vector));
            int counter = 0;
            Matrix approx = new Matrix(new double[vector.height][1]);
            //System.out.println((Math.abs(prevValue.get(0,0) - vector.get(0,0)) > tol));
            //int debug = 0;
            //System.out.println("Pre-Loop magnitude of PrevVal:" + MatrixAlgebra.magnitudeVector(prevValue));
            double tolCounter =  MatrixAlgebra.magnitudeVector(vector) - MatrixAlgebra.magnitudeVector(prevValue);
            while(tolCounter > tol && counter < 1000) {
                //multiply Axn n=1,2,3...
                Matrix iteration = MatrixAlgebra.matrixMultiply(a, vector);
                //System.out.println("Iteration: \n" + iteration);

                //divide by lowest value
                Matrix approximation = new Matrix(new double[iteration.height][1]);
                for (int i = 0; i < approximation.height; i++) {
                    double value = iteration.get(i, 0) / (iteration.get(iteration.height - 1, 0));
                    approximation.set(i, 0, value);
                }

                //System.out.println("Approximation: \n" + approximation);

                //check error
                Matrix error = new Matrix(new double [approximation.height][1]);
                for (int i = 0; i < approximation.height; i++) {
                    double value = Math.abs(prevValue.get(i, 0) - approximation.get(i, 0));
                    error.set(i, 0, value);
                }
//                System.out.println("Error: \n" + error);

                //update
                for (int i = 0; i < vector.height; i++) {
                    approx.set(i, 0, approximation.get(i,0));
                }
//                System.out.println("approx: \n" + approx);
                double oldPrevValueLength = MatrixAlgebra.magnitudeVector(prevValue);
//                System.out.println("OldPrevValueLength: \n" + oldPrevValueLength);
                prevValue = approximation;
                vector = iteration;

                tolCounter = Math.abs(oldPrevValueLength - MatrixAlgebra.magnitudeVector(prevValue));
//                System.out.println("VectorLength:" + MatrixAlgebra.magnitudeVector(vector));
//                System.out.println("prevValueLength:" + MatrixAlgebra.magnitudeVector(prevValue));
//                System.out.println("TolCount:" + tolCounter);
                counter++;

                //debug
                //debug++;
            }
            if (counter >= 1000) {
                throw new RuntimeException("Over 1000 iterations. Stopped. Does not converge.");
            }


            Matrix eVector = approx;

            //finding eValue
            Matrix aau = MatrixAlgebra.matrixMultiply(a, approx);
            //System.out.println("Numerator: \n" + aau);
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
                {{0,1.2,1.1,.9,.1,0,0,0,0},
                {.7,0,0,0,0,0,0,0,0},
                {0,.85,0,0,0,0,0,0,0},
                {0,0,.9,0,0,0,0,0,0},
                {0,0,0,.9,0,0,0,0,0},
                {0,0,0,0,.88,0,0,0,0},
                {0,0,0,0,0,.8,0,0,0},
                {0,0,0,0,0,0,.77,0,0},
                {0,0,0,0,0,0,0,.40,0}};
            Matrix leslieMatrix = new Matrix(leslie);
            System.out.println(leslieMatrix);
            double[][] x0 =
                {{2.1},{1.9}, {1.8}, {2.1}, {2.0}, {1.7}, {1.2}, {0.9}, {0.5}};
            Matrix n = new Matrix(x0);
            System.out.println(n);
            System.out.println(largestEigen(leslieMatrix, n, .00000000000000001));
        }
    }
