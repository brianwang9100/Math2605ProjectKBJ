
public class Result<A, B, C> {
    A a;
    B b;
    C c;
    public Result(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public String toString() {
        String retString = "Approx: EigenValue \n" + a + ", \n\n" + "Approx: EigenVector\n" + b + " \n" + "Number of Iterations: \n" + c;
        return retString;
    }
}
