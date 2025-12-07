package sequences;

import java.util.List;

public interface Sequence {
    //One Index
     void addNextTerms(int n);
    //One Index
    double getNthTerm(int n);

    List<Double> getSequence();

    double sumUpTo(int n);

    double sumToInfinity();
}
