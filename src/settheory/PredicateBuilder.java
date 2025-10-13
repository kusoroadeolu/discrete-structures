package settheory;

import java.util.function.Predicate;

//A class which performs predicate operations and allows clean chaining of predicate ops
public class PredicateBuilder<T>{

    private Predicate<T> predicate;

    private PredicateBuilder(Predicate<T> predicate){
        this.predicate = predicate;
    }

    /**
     * Compares two predicates against each other, given both predicates are true and assigns this predicate to the result predicate
     * @param givenPredicate The predicate we're comparing against
     * @return this instance of this class
     * */
    public PredicateBuilder<? super T> and(Predicate<? super T> givenPredicate){
        this.predicate = v -> this.predicate.test(v) && givenPredicate.test(v);
        return this;
    }

    /**
     * Compares two predicates against each other, given one predicate is true and assigns this predicate to the result predicate
     * @param givenPredicate The predicate we're comparing against
     * @return this instance of this class
     * */
    public PredicateBuilder<? super T> or(Predicate<? super T> givenPredicate){
        this.predicate = v -> this.predicate.test(v) || givenPredicate.test(v);
        return this;
    }

    /**
     * Reverses the value of this predicate
     * @return this instance of this class
     * */
    public PredicateBuilder<? super T> or(){
        this.predicate = v -> !this.predicate.test(v);
        return this;
    }

    public static <T>PredicateBuilder<T> builder(Predicate<T> predicate){
        return new PredicateBuilder<>(predicate);
    }

    public Predicate<? super T> build(){
        return this.predicate;
    }











}
