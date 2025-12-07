import relations.Pair;
import relations.Relation;
import relations.RelationPropertyChecker;
import settheory.sets.QuerySet;

void main() {

    QuerySet<Integer> p = new QuerySet<>(List.of(1, 2, 3));
    Relation r = new Relation(new QuerySet<>(List.of(new Pair(1, 2), new Pair(2, 1), new Pair(1, 3))));
    boolean val = RelationPropertyChecker.isSymmetric(r);
    IO.println(val);
}

