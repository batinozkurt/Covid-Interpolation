import java.util.Arrays;
import java.util.function.Function;

public class DirectMethod {

    public static Function<Double,Double> directMethodFunction;

    public static final double[] interpLinear(double[] x, double[] y, double[] xi) throws IllegalArgumentException {

        if (x.length != y.length) {
            throw new IllegalArgumentException("X and Y must be the same length");
        }
        if (x.length == 1) {
            throw new IllegalArgumentException("X must contain more than one value");
        }
        double[] dx = new double[x.length - 1];
        double[] dy = new double[x.length - 1];
        double[] slope = new double[x.length - 1];
        double[] intercept = new double[x.length - 1];

        for (int i = 0; i < x.length - 1; i++) {
            dx[i] = x[i + 1] - x[i];
            if (dx[i] == 0) {
                throw new IllegalArgumentException("X must be montotonic. A duplicate " + "x-value was found");
            }
            if (dx[i] < 0) {
                throw new IllegalArgumentException("X must be sorted");
            }
            dy[i] = y[i + 1] - y[i];
            slope[i] = dy[i] / dx[i];
            intercept[i] = y[i] - x[i] * slope[i];
        }

        double[] yi = new double[xi.length];
        for (int i = 0; i < xi.length; i++) {
            if ((xi[i] > x[x.length - 1]) || (xi[i] < x[0])) {
                yi[i] = Double.NaN;
            }
            else {
                int loc = Arrays.binarySearch(x, xi[i]);
                if (loc < -1) {
                    loc = -loc - 2;
                    yi[i] = slope[loc] * xi[i] + intercept[loc];
                }
                else {
                    yi[i] = y[loc];
                }
            }
        }
        System.out.println("\n"+"The value at "+xi[0]+" is "+Arrays.toString(yi));
        return yi;
    }

    public static final double[] calculateLinear(double[] x, double[] y, double[] xi){
        return interpLinear(x,y,xi);
    }
    public static Function<Double,Double> calculateFunction(double[] x, double[] y){

        double[] dx = new double[x.length - 1];
        double[] dy = new double[x.length - 1];
        double[] slope = new double[x.length - 1];
        double[] intercept = new double[x.length - 1];

        directMethodFunction = aDouble -> {
            for (int i = 0; i < x.length - 1; i++) {
                dx[i] = x[i + 1] - x[i];
                if (dx[i] == 0) {
                    throw new IllegalArgumentException("X must be monotonic. A duplicate " + "x-value was found");
                }
                if (dx[i] < 0) {
                    throw new IllegalArgumentException("X must be sorted");
                }
                dy[i] = y[i + 1] - y[i];
                slope[i] = dy[i] / dx[i];
                intercept[i] = y[i] - x[i] * slope[i];
            }
            double yi1 =0;
            for (int i = 0; i < 1; i++) {
                if ((aDouble > x[x.length - 1]) || (aDouble < x[0])) {
                    yi1 = Double.NaN;
                }
                else {
                    int loc = Arrays.binarySearch(x, aDouble);
                    if (loc < -1) {
                        loc = -loc - 2;
                        yi1 = slope[loc] * aDouble + intercept[loc];
                    }
                    else {
                        yi1 = y[loc];
                    }
                }
            }

            return yi1;
        };
        return directMethodFunction;
    }
}
