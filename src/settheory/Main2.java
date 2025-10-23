import settheory.sets.DomainSet;
import settheory.sets.QuerySet;
import settheory.tests.QuerySetTests;

void main() {
//    // Basic filtering
//    QuerySet<Integer> numbers = new QuerySet<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
//    System.out.println("Even numbers: " + numbers.filter(n -> n % 2 == 0).toList());
//    System.out.println("Numbers > 5: " + numbers.filter(n -> n > 5).toList());
//
//    // Set operations
//    QuerySet<String> setA = new QuerySet<>(List.of("apple", "banana", "cherry"));
//    QuerySet<String> setB = new QuerySet<>(List.of("banana", "date", "elderberry"));
//
//
//
//    System.out.println("\nSet A: " + setA.toList());
//    System.out.println("Set B: " + setB.toList());
//    System.out.println("Intersection: " + setA.intersect(setB).toList());
//    System.out.println("A - B: " + setA.difference(setB).toList());
//    System.out.println("Symmetric Diff: " + setA.symmetricDifference(setB).toList());
//
//    // Matching operations
//    QuerySet<Integer> nums = new QuerySet<>(List.of(2, 4, 6, 8));
//    System.out.println("\nAll even? " + nums.allMatch(n -> n % 2 == 0));
//    System.out.println("Any > 5? " + nums.anyMatch(n -> n > 5));
//    System.out.println("Count > 5: " + nums.count(n -> n > 5));
//
//    // Subset/Superset
//    QuerySet<Integer> small = new QuerySet<>(List.of(2, 4));
//    System.out.println("\n{2,4} subset of {2,4,6,8}? " + nums.isSupersetOf(small));
//
//    // Power Set
//    QuerySet<String> letters = new QuerySet<>(List.of("A", "B", "C"));
//    List<QuerySet<String>> powerSet = letters.getPowerSet().stream().toList();
//    System.out.println("\nPower set of {A, B, C}:");
//    System.out.println("Count: " + letters.powerSetCount() + " subsets");
//    powerSet.forEach(subset -> System.out.println("  " + subset.toList()));
//
//    // Equivalence and Equality
//    QuerySet<Integer> set1 = new QuerySet<>(List.of(1, 2, 3));
//    QuerySet<Integer> set2 = new QuerySet<>(List.of(3, 2, 1));
//    QuerySet<Integer> set3 = new QuerySet<>(List.of(4, 5, 6));
//    System.out.println("\n{1,2,3} equals {3,2,1} ? " + set1.equals(set2));
//    System.out.println("{1,2,3} equivalent to {4,5,6} ? " + set1.isEquivalentTo(set3));
//
//    // Multiple set operations
//    QuerySet<Integer> a = new QuerySet<>(List.of(1, 2, 3));
//    QuerySet<Integer> b = new QuerySet<>(List.of(5, 6 ,7));
//    QuerySet<Integer> c = new QuerySet<>(List.of(3, 4, 5));
//    System.out.println("\nUnion of all: " + QuerySet.unionAll(a, b, c).toList());
//    System.out.println("Intersection of all: " + QuerySet.intersectAll(a, b, c).toList());
//
//    // DomainSet with complement
//    QuerySet<Integer> universal = new QuerySet<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
//    DomainSet<Integer> evens = new DomainSet<>(n -> n % 2 == 0, universal);
//    evens.add(2);
//    evens.add(4);
//    evens.add(6);
//    System.out.println("\nEven numbers: " + evens.toList());
//    System.out.println("Complement (odds): " + evens.complement().toList());
//
//    Set<QuerySet.Pair<Integer, Integer>> cps = a.cartesianProduct(b);
//    cps.forEach(q -> IO.println("Pair: {%s, %s}".formatted(q.val1(), q.val2())));

    QuerySetTests.shouldSuccessfullySolveExercise1();
    QuerySetTests.shouldSuccessfullySolveExercise2();

}