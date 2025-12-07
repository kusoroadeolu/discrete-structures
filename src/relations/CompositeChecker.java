package relations;

import settheory.sets.QuerySet;

import java.util.List;

public class CompositeChecker {

    public static Relation getComposite(Relation r, Relation s){
        if(s.pairs().size() != r.pairs().size()) return new Relation(new QuerySet<>());
        final List<Pair> rList = r.pairs().toList();
        final List<Pair> sList = s.pairs().toList();

        final QuerySet<Pair> composite = new QuerySet<>();

        for (int i = 0; i < sList.size(); i++){
            final Pair p = compositePair(rList.get(i), sList.get(i));
            if(p != null) composite.add(p);
        }

        return new Relation(composite);
    }

    private static Pair compositePair(Pair a, Pair b){
        if(a.canCompose(b)) return null;
        return new Pair(a.first(), b.second());
    }


}
