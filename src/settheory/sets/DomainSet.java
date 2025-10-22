package settheory.sets;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

import static java.util.Objects.*;
import static java.util.Objects.requireNonNull;

/**
 * Each operation in this domain set must abide by both the domain and the values in the universal set
 * */
public class DomainSet<E> extends QuerySet<E>{

    private Predicate<E> domain; //The domain of this set
    private QuerySet<E> universalSet; //The universal set of this set

    public DomainSet(Predicate<E> domain, QuerySet<E> universalSet) {
        this.domain = domain;
        this.universalSet = universalSet;
        super();
    }

    public DomainSet(Predicate<E> domain, Collection<E> universalSet) {
        this(domain, new QuerySet<>(universalSet));
    }

    private DomainSet(){

    }

    @Override
    public E add(E val) {
        requireNonNull(this.domain, "Domain cannot be null");
        requireNonNull(this.universalSet, "Universal set cannot be null");
        if(!this.domain.test(val)){
            throw new IllegalArgumentException("Value does not abide to given domain");
        }

        if (!this.universalSet.contains(val)){
            throw new IllegalArgumentException("Value is not in universal set");
        }

        this.set.add(val);
        return val;
    }

    //Returns a query set containing the values in the universal set but not in the domain set
    public QuerySet<E> complement(){
        return universalSet.difference(this);
    }



}
