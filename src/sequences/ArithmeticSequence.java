package sequences;

import java.util.List;

public class ArithmeticSequence extends AbstractSequence implements Sequence{
    public ArithmeticSequence(double a, double d, List<Double> sequence, int numOfTerms) {
        super(a, d, sequence, numOfTerms);
    }

    @Override
    public void addNextTerms(int nv){
        for (int i = 1; i < nv + 1; i++){
            final int n = this.getSequence().size() + 1; //a + (n - 1)d
            if(n > this.getNumOfTerms()) return;
            final double val = this.getNthTerm(n);
            this.sequence.add(val);
        }
    }

    @Override
    public double getNthTerm(int n){
        try {
            return this.sequence.get(n - 1);
        }catch (IndexOutOfBoundsException _){
            return this.getA() + ((n - 1) * this.getD());
        }
    }


    @Override
    public double sumUpTo(int n) {
        final double nDiv = n/2.0;
        final double aMult = 2.0 * this.getA();
        final double dMult = this.getD() * (n - 1);
        final double addN = aMult + dMult;
        return nDiv * addN;
    }

    @Override
    public double sumToInfinity() {
        throw new IllegalArgumentException("Arithmetic seq doesnt support sum to infinity");
    }
}
