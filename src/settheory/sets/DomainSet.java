package settheory.sets;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

/**
 * Each operation in this domain set must abide by both the domain and the values in the universal set
 * */
public final class DomainSet<E> extends QuerySet<E>{

    private Predicate<E> domain; //The domain of this set
    private QuerySet<E> universalSet; //The universal set of this set

    public DomainSet(Predicate<E> domain, QuerySet<E> universalSet, Collection<? extends E> collection) {
        this.domain = domain;
        this.universalSet = universalSet;
        super(collection);
    }

    public DomainSet(Predicate<E> domain, QuerySet<E> universalSet) {
        this(domain, universalSet, Collections.emptySet());
    }

    public DomainSet(Predicate<E> domain, Collection<E> universalSet) {
        this(domain, new QuerySet<>(universalSet), Collections.emptySet());
    }

    public DomainSet(Predicate<E> domain) {
        this(domain, new QuerySet<>(), Collections.emptySet());
    }

    private DomainSet(){

    }
    /**
     * An implementation of query set which ensures every value in this set abides to the domain
     * */
    @Override
    public E add(E val) {
        requireNonNull(this.domain, "Domain cannot be null");
        if(!this.domain.test(val)){
            throw new IllegalArgumentException("Value does not abide to given domain");
        }

        //If the universal set is empty or null, that means this set allows all values that comply with the predicate
        if (universalSet != null && !universalSet.isEmpty() && !universalSet.contains(val)) {
            throw new IllegalArgumentException("Value not in universal set");
        }

        this.set.add(val);
        return val;
    }

    //Returns a query set containing the values in the universal set but not in the domain set
    public QuerySet<E> complement(){
        return universalSet.difference(this);
    }



}
