package relations;

import settheory.sets.QuerySet;

import java.util.*;

import static relations.Status.ABSENT;
import static relations.Status.FOUND;

public class RelationPropertyChecker {

    public static boolean isReflexive(QuerySet<Integer> a, Relation r){
        final List<Pair> pairs = r.pairs().toList();
        final Set<Pair> ps = new HashSet<>(a.size());
        a.forEach(i -> ps.add(new Pair(i, i)));
        final int expected = a.size();
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
        final QuerySet<Pair> pairs = r.pairs();
        final List<Pair> ps = pairs.toList();
        final Set<Pair> seen = new HashSet<>();
        for (int i = 0; i < pairs.size(); i++){
            final Pair pi = ps.get(i);
            if (pi.isSelfPair()) continue;

            for (int j = i + 1; j < pairs.size(); j++){
                final Pair pj = ps.get(j);

                if(!pi.canCompose(pj)) continue;

                final Pair compose = pi.compose(pj);
                if (seen.contains(compose)) continue;

                if (!pairs.contains(compose)) return false;
                else seen.add(compose);
            }
        }

        return true;
    }



}

enum Status{
    FOUND,
    ABSENT
}
