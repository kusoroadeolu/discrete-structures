package settheory;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

//A data structure which contains a which supports DB like query operations
public class QuerySet<T>{

    private final Set<T> set;

    public QuerySet(){
        this.set = new HashSet<>();
    }

    /**
     * Adds a non-null and a value that doesn't exist in this set to the set
     * @param val The value being added
     * @return the value that was added
     * */
    public T add(T val){
        if(val == null){
            throw new IllegalArgumentException("Inserted value cannot be null");
        }

        if(this.set.contains(val)){
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
    public QuerySet<T> union(Collection<? extends T> values){
        if(values == null){
            throw new IllegalArgumentException("Collection cannot be null");
        }

        for (T val : values){
            try{
                this.add(val);
            }catch (IllegalArgumentException e){

            }
        }

        return this;
    }

    /**
     * Performs the union of an instance of this class and another query set
     * @param values The query set we're performing a union with
     * </br>Note that this modifies the original set
     * * @return a unionized instance of this query set
     * */
    public QuerySet<T> union(QuerySet<T> values){
        if(values == null){
            throw new IllegalArgumentException("Collection cannot be null");
        }

        for (T val : values.set){
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
     * </br>Note that this modifies the original set
     * * @return an intersected instance of this query set
     * */
    public QuerySet<T> intersect(Collection<? extends T> values){
        if(values == null){
            throw new IllegalArgumentException("Collection cannot be null");
        }
        QuerySet<T> intersectedSet = new QuerySet<>();
        Predicate<T> predicate = v -> this.set.contains(v) && values.contains(v);

        for (T val : values){
            try{
                if(predicate.test(val)){
                    intersectedSet.add(val);
                }
            }catch (IllegalArgumentException e){

            }
        }

        this.set.clear();
        this.union(intersectedSet);

        return this;
    }

    /**
     * Performs the intersection of an instance of this class and another query set
     * @param values The query set we're intersecting with
     * </br>Note that this modifies the original set
     * * @return an intersected instance of this query set
     * */
    public QuerySet<T> intersect(QuerySet<T> values){
        if(values == null){
            throw new IllegalArgumentException("Collection cannot be null");
        }

        QuerySet<T> intersectedSet = new QuerySet<>();
        Predicate<T> predicate = v -> this.set.contains(v) && values.set.contains(v);

        for (T val : values.set){
            try{
                if(predicate.test(val)){
                    intersectedSet.add(val);
                }
            }catch (IllegalArgumentException e){

            }
        }

        this.set.clear();
        this.union(intersectedSet);

        return this;
    }

    /**
     * Filters values from the set based on the predicate given
     * </br>Note that this does not modify the original set
     * @param predicate The condition/predicate given
     * @return a filtered query set based on the predicate given
     * */
    public QuerySet<T> filter(Predicate<T> predicate){
        final QuerySet<T> filtered = new QuerySet<>();
        for (T val : this.set){
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
    public boolean anyMatch(Predicate<T> predicate){
        for (T val : this.set){
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
    public boolean allMatch(Predicate<T> predicate){
        for (T val : this.set){
            if(!predicate.test(val)){
                return false;
            }
        }

        return true;
    }

    /**
     * Iterates through every element in an array and performs and action with it
     * @param consumer The consumer which accepts the action to perform
     * */
    public void forEach(Consumer<? super T> consumer){
        for (T t : this.set){
            consumer.accept(t);
        }
    }





}
