import java.util.function.Function;

public class Lagrange {
    public static Function<Double,Double> lagrangeFunction;

    static class Data
    {
        int x, y;

        public Data(int x, int y)
        {
            super();
            this.x = x;
            this.y = y;
        }

    };

    static double interpolate(Data f[], int xi, int n)
    {
        double result = 0; // Initialize result

        for (int i = 0; i < n; i++)
        {
            double term = f[i].y;
            for (int j = 0; j < n; j++)
            {
                if (j != i)
                    term = term*(xi - f[j].x) / (f[i].x - f[j].x);
            }

            result += term;
        }

        return result;
    }

    public static void calculateLagrange(Data[] f,int xi,int n){
        System.out.print("\n"+"The value at "+xi+" is "+(int)interpolate(f, xi, n));
    }

    public static Function<Double,Double> calculateFunction(Data[] f){

        lagrangeFunction = aDouble ->{
            double result1 = 0;

            for (int i = 0; i < f.length; i++)
            {
                double term = f[i].y;
                for (int j = 0; j < f.length; j++)
                {
                    if (j != i)
                        term = term*(aDouble - f[j].x) / (f[i].x - f[j].x);

                }

                result1 += term;

            }
            return  result1;
        };
        return lagrangeFunction;
    }
}
