package settheory.tests;

import settheory.sets.QuerySet;

public class Assertions {

    /**
     * Prints the result of a given operation label and its corresponding QuerySet
     * e.g. print("A ∆ B", symAB) => "A ∆ B = {1, 3, 5, 7, 8, 9}"
     */
    public static <E> void print(String label, QuerySet<E> set) {
        System.out.printf("%s = %s%n", label, formatSet(set));
    }

    /**
     * Prints a custom assertion result.
     * e.g. shouldAssert(a.isSubsetOf(c), "A ⊆ C");
     */
    public static void shouldAssert(boolean assertion, String message) {
        if (assertion) {
            System.out.println(message + " — PASSED");
        } else {
            System.out.println(message + " — FAILED");
        }
    }

    /**
     * Converts a QuerySet into clean mathematical set notation.
     * e.g. {1, 2, 3}
     */
    private static <E> String formatSet(QuerySet<E> set) {
        if (set == null || set.isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        var list = set.toList();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
    }
}
