
public class power_method {

        public static Result<Double, Matrix, Integer> largestEigen(Matrix a, Matrix u, double tol) {
            //zero vector created to do initial
            Matrix prevValue = new Matrix(new double[u.height][1]);
            for (int i = 0; i < prevValue.height; i++) {
                prevValue.set(i, 0, 0);
            }
            Matrix vector = u;
            Matrix approximation = null;
            int counter = 0;
            
            while(Math.abs(prevValue.get(0,0) - vector.get(0,0)) > tol && counter < 300) {
                //multiply Axn n=1,2,3...
                Matrix iteration = MatrixAlgebra.matrixMultiply(a, vector);
                
                //divide by lowest value
                approximation = new Matrix(new double[iteration.height][1]);
                for (int i = 0; i < approximation.height; i++) {
                    double value = iteration.get(i, 0) / (iteration.get(iteration.height - 1, 0));
                    approximation.set(i, 0, value);
                }
                
                //check error
                Matrix error = new Matrix(new double [approximation.height][1]);
                for (int i = 0; i < approximation.height; i++) {
                    double value = Math.abs(prevValue.get(i, 0) - approximation.get(i, 0));
                    error.set(i, 0, value);
                }
                
                //update
                prevValue = approximation;
                vector = iteration;
                counter++;
                
            }
            if (counter > 300) {
                throw new RuntimeException("Over 300 iterations. Stopped.");
            }
            Matrix eVector = approximation;
            
            //finding eValue
            Matrix aau = MatrixAlgebra.matrixMultiply(a, approximation);
            double eValue = dotProd(aau, approximation) / dotProd(approximation,approximation);
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
            Matrix m = new Matrix(new double[2][2]);
            m.set(0, 0, 2);
            m.set(0, 1, -12);
            m.set(1, 0, 1);
            m.set(1, 1, -10);
            

            System.out.println(m);
            Matrix n = new Matrix(new double[2][1]);
            n.set(0,0,1);
            n.set(1,0,1);
            System.out.println(n);
            System.out.println(largestEigen(m, n, .01));
        }
        
    }

