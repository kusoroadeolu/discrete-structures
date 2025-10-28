import functions.FunctionChecker;
import settheory.sets.DomainSet;
import settheory.sets.QuerySet;

void main() {
    Predicate<Integer> domainRule = x -> x > 3; // All natural numbers greater than 3
    DomainSet<Integer> domain = new DomainSet<>(domainRule, new QuerySet<>(),List.of(5, 6, 7, 9, 10));
    QuerySet<Integer> coDomain = new QuerySet<>(List.of(10, 12, 14, 16, 18, 20));

    FunctionChecker<Integer, Integer> checker = new FunctionChecker<>(x -> (x * 2), domain, coDomain);

    //Expected range {10,12,14,18,20}
    StringBuilder val = new StringBuilder("Range -> {");
    checker.getRange().forEach(e -> val.append(e).append(", "));
    IO.println(val.substring(0, val.length() - 2) + "}");

    //Should not be surjective
    IO.println("Is surjective: " + checker.isSurjective());

    //Should be injective
    IO.println("Is injective: " + checker.isInjective());

    //Should not be bijective
    IO.println("Is bijective: " + checker.isBijective());

    //Should return 9
    checker.inverseImage(18).forEach(e -> IO.println("Inverse image: " + e));

    //Should return nothing
    checker.inverseImage(17).forEach(e -> IO.println("Inverse image? " + e));
}