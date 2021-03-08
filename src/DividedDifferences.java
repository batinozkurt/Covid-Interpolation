import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.function.Function;

public class DividedDifferences {
    static double[] B;
    static Function<Double, Double> function1;

    static double proterm(int i, double value, double x[])
    {
        double pro = 1;
        for (int j = 0; j < i; j++) {
            pro = pro * (value - x[j]);
        }
        return pro;
    }

    static void dividedDiffTable(double x[], double y[][], int n)
    {
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                y[j][i] = (y[j][i - 1] - y[j + 1][i - 1]) / (x[j] - x[i + j]);
            }
        }

    }

    static double applyFormula(double value, double x[],
                              double y[][], int n)
    {
        double sum = y[0][0];

        for (int i = 1; i < n; i++) {
            sum = sum + (proterm(i, value, x) * y[0][i]);
        }
        return sum;
    }

    static void printDiffTable(double y[][],int n)
    {
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.HALF_UP);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                String str1 = df.format(y[i][j]);
                System.out.print(String.format("%1$-"+15+ "s", str1));
            }
            System.out.println("");
        }
    }

    public static void calculateDD(double x[],double y[][],int n,double value) {

        dividedDiffTable(x, y, n);

        printDiffTable(y,n);

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);

        System.out.println("\nValue at "+df.format(value)+" is " +df.format((int)applyFormula(value, x, y, n)));
    }

    public static Function<Double, Double> calculateFunction(double[] x, double[][] y, int n){
        dividedDiffTable(x,y,n);
        B=new double[x.length];

        for (int i=0;i<x.length;i++){
            B[i] = y[0][i];
        }

        function1 = (Double aDouble) -> {
            double function = 0 ;
            for (int i = 0; i< x.length; i++) {
                double multiplier = 1;
                for (int j = 0; j<i;j++){
                    multiplier = multiplier * (aDouble- x[j]);
                }
                if (i==0)
                    function=B[i];
                else function = function + B[i]*multiplier;
            }
            return function;
        };
        return function1;
    }

}
