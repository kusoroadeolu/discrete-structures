import sequences.ArithmeticSequence;
import sequences.Sequence;

void main() {
    Sequence a = new ArithmeticSequence(1, 2, new ArrayList<>(), 25);
    int x = (int) a.getNthTerm(1);
    a.addNextTerms(3);
    a.getSequence().forEach(IO::println);
    //IO.println(x);

}