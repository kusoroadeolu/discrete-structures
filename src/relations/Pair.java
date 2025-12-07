package relations;

import java.util.Objects;

public record Pair(
        int first,
        int second
) {

    public boolean equals(Object other){
        if(!(other instanceof Pair(int first1, int second1))) return false;
        return (first == first1) && (second == second1);
    }


    public int hashCode(){
        return Objects.hash(first, second);
    }


    public Pair reverse(){
        return new Pair(second, first);
    }

    public boolean isSelfPair(){
        return first == second;
    }

}
