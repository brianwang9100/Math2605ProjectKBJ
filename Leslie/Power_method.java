
public class power_method {

    public static Result<Double, Matrix, Integer> largestEigen(Matrix a, Matrix u, double tol) {
        
        //Number of Iterations
        int iterations = 0;

        double old = 0;
        Matrix au = MatrixAlgebra.matrixMultiply(a, u);
        double error = Math.abs(old - au.get(0, 0) / au.get(0, au.height - 1));
        while (error > tol && iterations < 500) {
            iterations++;
            old = au.get(0, 0);
            Matrix i = MatrixAlgebra.matrixMultiply(a, au);
            au = i;
            error = Math.abs(old - i.get(0, 0) / i.get(0, i.height - 1));
        }
        
        if (iterations >= 300) {
            System.out.println("Does not converge on 300th iteration. Stopping.");
        }
        
        Matrix aau = MatrixAlgebra.matrixMultiply(a, au);
        double eValue = dotProd(aau, au) / dotProd(au,au);
        Matrix eVector = new Matrix(new double[0][au.height -1]);
        for (int i = 0; i < au.height; i++) {
            eVector.set(i, 0, au.get(i, 0) / au.get(0, au.height - 1));
        }
        return new Result<Double, Matrix, Integer>(eValue, eVector, iterations);
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
        m.set(0, 1, 1);
        m.set(1, 0, 1);
        m.set(1, 1, 4);
        System.out.println(m);
        Matrix n = new Matrix(new double[2][1]);
        n.set(0,0,1);
        n.set(1,0,1);
        System.out.println(largestEigen(m, n, 8));
    }
    
}
