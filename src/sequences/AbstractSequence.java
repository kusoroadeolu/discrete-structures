package sequences;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSequence {
    private final double a;
    private final double d;
    private final int numOfTerms;
    protected final List<Double> sequence;


    public AbstractSequence(double a, double d, List<Double> sequence, int numOfTerms) {
        if(sequence == null) sequence = new ArrayList<>();
        this.sequence = sequence;
        this.d = d;
        this.a = a;
        this.numOfTerms = numOfTerms;
        sequence.addFirst(a);
    }



    public double getA() {
        return a;
    }


    public double getD() {
        return d;
    }


    public int getNumOfTerms() {
        return numOfTerms;
    }

    public List<Double> getSequence() {
        return List.copyOf(this.sequence);
    }


}
