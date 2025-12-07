package relations;

import settheory.sets.QuerySet;

import java.util.List;
import java.util.function.*;

public record Relation(QuerySet<Pair> pairs) {

    public void forEach(Consumer<? super Pair> consumer){
        pairs.forEach(consumer);
    }

    public void printAll(){
        this.forEach(IO::println);
    }

    public static Relation createRelation(QuerySet<Integer> set1, QuerySet<Integer> set2, BiPredicate<Integer, Integer> bo){
        final Relation relation = new Relation(new QuerySet<>());
        final List<Integer> l1 = set1.toList();
        final List<Integer> l2 = set2.toList();

        final QuerySet<Pair> set = new QuerySet<>();
        for (int i = 0; i < set1.size(); i++){
            final int p1 = l1.get(i);
            for (int j = 0; i < set2.size(); i++){
                final int p2 = l2.get(j);
                if(bo.test(p1, p2)) set.add(new Pair(p1, p2));
            }
        }

        return new Relation(set);
    }
}
