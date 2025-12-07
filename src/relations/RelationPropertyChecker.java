package relations;

import settheory.sets.QuerySet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static relations.Status.ABSENT;
import static relations.Status.FOUND;

public class RelationPropertyChecker {

    public static boolean isReflexive(QuerySet<Integer> set, Relation r){
        final List<Pair> pairs = r.pairs().toList();
        final Set<Pair> ps = new HashSet<>(set.size());
        set.forEach(i -> ps.add(new Pair(i, i)));
        final int expected = set.size();
        int count = 0;

        for (Pair pair : pairs) {
            if (!ps.add(pair)) count++;
        }

        return expected == count;
    }

    public static boolean isSymmetric(Relation r){
        final HashMap<Pair, Status> map = new HashMap<>();
        r.forEach(p -> {
            if(p.isSelfPair()) return;
            Pair reverse = p.reverse();
            if(map.containsKey(reverse)) map.put(reverse, FOUND);
            else map.put(p, ABSENT);
        });

        final int mapSize = map.size();
        final int foundSize = (int) map.values().stream().filter(s -> s == FOUND).count();
        return mapSize == foundSize;
    }

    public static boolean isAsymmetric(Relation r){
        final Set<Pair> set = new HashSet<>();
        final List<Pair> pairs = r.pairs().toList();

        for (Pair p : pairs){
            if(p.isSelfPair()) continue;
            final Pair reverse = p.reverse();
            if(set.contains(reverse))return false;
            set.add(p);
        }

        return true;
    }

    public static boolean isTransitive(Relation r){
        return false;
    } //TBD



}

enum Status{
    FOUND,
    ABSENT
}
