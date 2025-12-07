package sequences;

public class MeanFinder {
    private MeanFinder(){}

    public static double findArithmeticMean(double a, double b){
        return (a + b) / 2;
    }

    public static double findGeometricMean(double a, double b){
        return Math.sqrt(a*b);
    }

}
