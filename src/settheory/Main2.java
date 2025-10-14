import settheory.QuerySet;

void main() {
    // Basic filtering
    QuerySet<Integer> numbers = new QuerySet<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    System.out.println("Even numbers: " + numbers.filter(n -> n % 2 == 0).toList());
    System.out.println("Numbers > 5: " + numbers.filter(n -> n > 5).toList());

    // Set operations
    QuerySet<String> setA = new QuerySet<>(List.of("apple", "banana", "cherry"));
    QuerySet<String> setB = new QuerySet<>(List.of("banana", "date", "elderberry"));

    System.out.println("\nSet A: " + setA.toList());
    System.out.println("Set B: " + setB.toList());
    System.out.println("Intersection: " + setA.intersect(setB).toList());
    System.out.println("A - B: " + setA.difference(setB).toList());
    System.out.println("Symmetric Diff: " + setA.symmetricDifference(setB).toList());

    // Matching operations
    QuerySet<Integer> nums = new QuerySet<>(List.of(2, 4, 6, 8));
    System.out.println("\nAll even? " + nums.allMatch(n -> n % 2 == 0));
    System.out.println("Any > 5? " + nums.anyMatch(n -> n > 5));
    System.out.println("Count > 5: " + nums.count(n -> n > 5));

    // Subset/Superset
    QuerySet<Integer> small = new QuerySet<>(List.of(2, 4));
    System.out.println("\n{2,4} subset of {2,4,6,8}? " + nums.isSupersetOf(small));
}