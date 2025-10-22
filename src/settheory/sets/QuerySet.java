package settheory.sets;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

//A data structure which contains a which supports set like query operations
public class QuerySet<E>{

    protected final Set<E> set;

    public QuerySet(){
        this.set = new LinkedHashSet<>();
    }

    public QuerySet(Collection<? extends E> values){
        this.set = new LinkedHashSet<>(values);
    }




    /**
     * Adds a non-null and a value that doesn't exist in this set to the set
     * @param val The value being added
     * @return the value that was added
     * */
    public E add(E val){
        requireNonNull(val,"Inserted value cannot be null");

        if(this.contains(val)){
            throw new IllegalArgumentException("This value already exists in the set");
        }

        this.set.add(val);
        return val;
    }

    /**
     * Performs the union of an instance of this class and another collection
     * @param values The collections we're performing a union with
     * </br>Note that this modifies the original set
     * * @return a unionized query set
     * */
    public QuerySet<E> union(Collection<? extends E> values){
        if(values == null){
            throw new IllegalArgumentException("Collection cannot be null");
        }

        for (E val : values){
            try{
                this.add(val);
            }catch (NullPointerException | IllegalArgumentException e){

            }
        }

        return this;
    }

    /**
     * Performs the union of an instance of this class and another query set
     * @param values The query set we're performing a union with
     * </br>Note that this modifies the original set
     * @return a unionized instance of this query set
     * */
    public QuerySet<E> union(QuerySet<? extends E> values){
        requireNonNull(values, "Collection cannot be null");

        for (E val : values.set){
            try{
                this.add(val);
            }catch (IllegalArgumentException e){

            }
        }

        return this;
    }


    /**
     * Performs the intersection of an instance of this class and another collection
     * @param values The collections we're intersecting with
     * </br>Note that this does not modify the original set
     * @return an intersected instance of a query set
     * */
    public QuerySet<E> intersect(Collection<? extends E> values){
        requireNonNull(values, "Collection cannot be null");
        final QuerySet<E> newSet = new QuerySet<>(values);
        return this.intersect(newSet);
    }

    /**
     * Performs the intersection of an instance of this class and another query set
     * @param values The query set we're intersecting with
     * </br>Note that this does not modify this query set
     * @return an intersected instance of this query set
     * */
    public QuerySet<E> intersect(QuerySet<E> values){
        requireNonNull(values, "Collection cannot be null");

        final QuerySet<E> intersectedSet = new QuerySet<>();
        final Predicate<E> predicate = v -> this.contains(v) && values.contains(v);

        for (E val : values.set){
            try{
                if(predicate.test(val)){
                    intersectedSet.add(val);
                }
            }catch (NullPointerException | IllegalArgumentException e){

            }
        }

        return intersectedSet;
    }

    /**
     * Filters values from the set based on the predicate given
     * </br>Note that this does not modify the original set
     * @param predicate The condition/predicate given
     * @return a filtered query set based on the predicate given
     * */
    public QuerySet<E> filter(Predicate<E> predicate){
        final QuerySet<E> filtered = new QuerySet<>();
        for (E val : this.set){
            if (predicate.test(val)){
                filtered.add(val);
            }
        }

        return filtered;
    }

    /**
     * Checks if any one value in the set satisfies the predicate
     * @param predicate The value we're checking for
     * @return returns true if any one value in the set satisfies the predicate and false otherwise
     * */
    public boolean anyMatch(Predicate<E> predicate){
        for (E val : this.set){
            if(predicate.test(val)){
                return true;
            }
        }

        return false;
    }



    /**
     * Checks if all values in the set satisfies the predicate
     * @param predicate The value we're checking for
     * @return returns true if all values in the set satisfies the predicate and false otherwise
     * */
    public boolean allMatch(Predicate<E> predicate){
        for (E val : this.set){
            if(!predicate.test(val)){
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if this set contains a value
     * @param val The value we're looking for
     * @return true if the value exists and false otherwise
     * */
    public boolean contains(E val){
        requireNonNull(val, "Value cannot be null");
        return this.set.contains(val);
    }

    /**
     * Iterates through every element in an array and performs and action with it
     * @param consumer The consumer which accepts the action to perform
     * */
    public void forEach(Consumer<? super E> consumer){
        for (E e : this.set){
            consumer.accept(e);
        }
    }

    /**
     * Performs the difference of an instance of this class and another collection
     * @param values The query set we're finding the diff against
     * </br>Note that this does not modify the original set
     * @return a query set of the values which exist in this class but not in the collection
     * */
    public QuerySet<E> difference(Collection<? extends E> values){
        requireNonNull(values, "Collection cannot be null");
        return this.difference(new QuerySet<>(values));
    }

    /**
     * Performs the difference of an instance of this class and another query set
     * @param values The query set we're finding the difference against
     * </br>Note that this does not modify the original set
     * @return a query set of the values which exist in this class but not in the collection
     * */
    public QuerySet<E> difference(QuerySet<E> values){
        requireNonNull(values, "Collection cannot be null");

        final QuerySet<E> dSet = new QuerySet<>();
        final Predicate<E> predicate = v -> this.contains(v) && !values.contains(v);

        for (E e : this.set){
            if(predicate.test(e)){
                try{
                    dSet.add(e);
                }catch (IllegalArgumentException ex){

                }
            }
        }

        return dSet;
    }


    /**
     * Performs the symmetric difference of an instance of this class and another collection
     * @param values The collection we're finding the symmetric difference with
     * </br>Note that this does not modify the original set
     * @return a query set of the values which exist in this class but not in the collection and vice versa
     * */
    public QuerySet<E> symmetricDifference(Collection<E> values){
        requireNonNull(values, "Collection cannot be null");
        return this.symmetricDifference(new QuerySet<>(values));
    }

    /**
     * Performs the symmetric difference of an instance of this class and another query set
     * @param values The query set we're finding the symmetric difference with
     * </br>Note that this does not modify the original set
     * @return a query set of the values which exist in this class but not in the collection and vice versa
     * */
    public QuerySet<E> symmetricDifference(QuerySet<E> values){
        requireNonNull(values, "Collection cannot be null");
        final QuerySet<E> symmetricSet = new QuerySet<>();
        return symmetricSet.union(values.difference(this)).union(this.difference(values));
    }

    /**
     * Checks if a query set is a subset of this query set
     * @return true if it is a subset and false otherwise
     * */
    public boolean isSubsetOf(QuerySet<E> values){
        for (E e : this.set){
            if(!values.set.contains(e)){
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if a collection is a subset of this query set
     * @return true if it is a subset and false otherwise
     * */
    public boolean isSubsetOf(Collection<E> values){
        requireNonNull(values, "Collection cannot be null");
        return this.isSubsetOf(new QuerySet<>(values));
    }

    /**
     * Checks if a collection is a super set of this query set
     * @return true if it is a super set and false otherwise
     * */
    public boolean isSupersetOf(Collection<E> values){
        requireNonNull(values, "Collection cannot be null");
        return this.isSupersetOf(new QuerySet<>(values));
    }

    /**
     * Checks if a query set is a super set of this query set
     * @return true if it is a super set and false otherwise
     * */
    public boolean isSupersetOf(QuerySet<E> values){
        requireNonNull(values, "Collection cannot be null");
        return values.isSubsetOf(this);
    }

    /**
     * @return  the count of elements in the set that fulfils the predicate
     * */
    public int count(Predicate<E> predicate){
        return this.filter(predicate).size();
    }

    /**
     * Checks if this set is empty
     * @return true if it is empty and false otherwise
     * */
    public boolean isEmpty(){
        return this.set.isEmpty();
    }

    /**
     * @return The number of elements in this set
     * */
    public int size(){
        return this.set.size();
    }

    /**
     * @return The first element in this set
     * */
    public E findFirst(){
        if(this.isEmpty()){
            return null;
        }
        return this.toList().getFirst();
    }

    /**
     * @return The last element in this set
     * */
    public E findLast(){
        if(this.isEmpty()){
            return null;
        }
        return this.toList().getLast();
    }

    /**
     * Converts an instance of this class to a list
     * @return An immutable list
     * */
    public List<E> toList(){
        return new ArrayList<>(this.set);
    }


    /**
     * Gets the power set of this query set
     * </br> Finds the all possible subsets by iterating from 0 to 2^n values. Uses binary rep to determine which values belong in which subsets
     * @return a set of all the possible subsets of this query set
     * */
    public Set<QuerySet<E>> getPowerSet(){
        final int size = this.size();
        final int numberOfSubsets = this.powerSetCount();
        final List<E> list = this.toList();
        final Set<QuerySet<E>> powerSet = new LinkedHashSet<>();

        for (int i = 0; i < numberOfSubsets; i++){
            QuerySet<E> qs = new QuerySet<>();

            for (int j = 0; j < size; j++){
                //Move 1 in binary by the value of j  i.e j = 1 (01), j = 5 (10000) and compare to the binary val of i
                if((i & (1 << j)) != 0){
                    qs.add(list.get(j));
                }
            }

            powerSet.add(qs);
        }

        return powerSet;
    }

    /**
     * @return true or false if the set size is equal to this class
     * */
    public boolean isEquivalentTo(QuerySet<E> set){
        return this.size() == set.size();
    }

    /**
     * @return A query set which is the union of all given sets
     * */
    public static <E> QuerySet<E> unionAll(QuerySet<E>... sets) {
        QuerySet<E> unionSet = new QuerySet<>();
        if(sets.length == 0){
            return unionSet;
        }
        for (int i = 0; i < sets.length; i++){
            unionSet.union(sets[i]);
        }

        return unionSet;
    }

    /**
     * @return A query set which is the intersection of all given sets
     * */
    public static <E> QuerySet<E> intersectAll(QuerySet<E>... sets) {
        QuerySet<E> intersectSet = new QuerySet<>();
        if(sets.length == 0){
            return intersectSet;
        }
        intersectSet.union(sets[0]);
        for (int i = 1; i < sets.length; i++){
            intersectSet = intersectSet.intersect(sets[i]);
        }

        return intersectSet;
    }

    /**
     * @return true or false if all the values in both sets are perfectly identical
     * */
    @Override
    public boolean equals(Object set) {
       if(!(set instanceof QuerySet<?> castSet)){
           return false;
       }

        if(castSet.size() != this.size()){
           return false;
       }

       return this.set.equals(castSet.set);
    }

    @Override
    public int hashCode() {
        return set.hashCode();
    }

    /**
     * @return  the number of possible subsets this query set will have
     * */
    public int powerSetCount() {
        return (int) Math.pow(2, this.size());
    }

}
