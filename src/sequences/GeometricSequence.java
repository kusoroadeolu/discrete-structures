package sequences;

import java.util.List;

public class GeometricSequence extends AbstractSequence implements Sequence{

    public GeometricSequence(double a, double d, List<Double> sequence, int numOfTerms) {
        if (d == 1) throw new IllegalArgumentException("Lowk a arithmetic seq");
        super(a, d, sequence, numOfTerms);
    }

    @Override
    public void addNextTerms(int nv) {
        for (int i = 1; i < nv + 1; i++){
            final int n = this.getSequence().size() + 1; //a(d ^ n)
            if(n > this.getNumOfTerms()) return;
            final double val = this.getNthTerm(n);
            this.sequence.add(val);
        }
    }

    @Override
    public double getNthTerm(int n) {
        try{
            return this.sequence.get(n - 1);
        } catch (IndexOutOfBoundsException _){
            return this.getA() * Math.pow(this.getD(), (n - 1));
        }
    }


    //Assuming r > 1
    @Override
    public double sumUpTo(int n) {
        if (n <= 1) return this.getA();
        // a * ((r ^ n) - 1) / r - 1
        if(this.getD() > 1) return this.sumWhenDIsGreater(n);
        else return this.sumWhenDIsLess(n);
    }

    public double sumToInfinity(){
        // a/ 1-r
        if(this.getD() < 1) return 0.0;
        return this.getA() / (1 - this.getD());
    }


    private double sumWhenDIsGreater(int n){
        final double rRaised = Math.pow(this.getD(), n) - 1.0;
        final double aMult = this.getA() * rRaised;
        return aMult / (this.getD() - 1.0);
    }

    private double sumWhenDIsLess(int n){
        final double rRaised = 1.0 - Math.pow(this.getD(), n);
        final double aMult = this.getA() * rRaised;
        return aMult / (1.0 - this.getD());
    }
}
