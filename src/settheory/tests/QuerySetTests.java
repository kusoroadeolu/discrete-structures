package settheory.tests;

import settheory.sets.DomainSet;
import settheory.sets.QuerySet;

import java.util.List;

/**
 * This class is basically made to test my query set implementation against my actual class exercises
 * */
public class QuerySetTests {

    public static void shouldSuccessfullySolveExercise1(){
        //Set the universal set to be null to indicate all natural numbers that comply to the given predicate i.e 𝐴 = {𝑥|𝑥 ∈ ℕ 𝑎𝑛𝑑 𝑥 ≥ 5}
        DomainSet<Integer> a = new DomainSet<>(x -> x >= 5, null,  List.of(6, 7, 8, 9));
        QuerySet<Integer> b = new QuerySet<>(List.of(10, 12, 16, 20));
        //C is the set of all even natural numbers  i.e 𝐶 = {𝑥|(∃𝑦)(𝑦 ∈ ℕ 𝑎𝑛𝑑 𝑥 = 2𝑦}
        DomainSet<Integer> c = new DomainSet<>(x -> x % 2 == 0, null, List.of(2, 4, 6, 8, 10, 12, 14, 16, 18, 20));


        //Now let's assert both operations from the text in the pdf i.e 𝐵 ⊆ 𝐶, 𝐴 ⊆ 𝐶
        IO.println("=== Exercise 1 Results ===");
        Assertions.shouldAssert(!a.isSubsetOf(c), "A should not be a subset of c");
        Assertions.shouldAssert(b.isSubsetOf(c), "B should be a subset of c");
        IO.println("==========================");

    }

    public static void shouldSuccessfullySolveExercise2(){
        QuerySet<Integer> a = new QuerySet<>(List.of(1, 2, 3, 6, 8));
        QuerySet<Integer> b = new QuerySet<>(List.of(2, 5, 6, 7, 9));
        QuerySet<Integer> c = new QuerySet<>(List.of(4, 5, 6, 8));

        IO.println("=== Exercise 2 Results ===");

        // a. Symmetric diff of A and B -> 𝐴∆𝐵
        QuerySet<Integer> symAB = a.symmetricDifference(b);
        Assertions.print("A ∆ B", symAB);

        // b. Symmetric diff of A and C -> 𝐴∆𝐶
        QuerySet<Integer> symAC = a.symmetricDifference(c);
        Assertions.print("A ∆ C", symAC);

        // c. Symmetric diff of A, B and C -> (𝐴∆𝐵)∆𝐶
        QuerySet<Integer> symABC = a.symmetricDifference(b).symmetricDifference(c);
        Assertions.print("(A ∆ B) ∆ C", symABC);

        // d. Symmetric diff of B against (A ∩ C) -> 𝐵∆(𝐴 ∩ 𝐶)
        QuerySet<Integer> symB_AsectC = b.symmetricDifference(a.intersect(c));
        Assertions.print("B ∆ (A ∩ C)", symB_AsectC);

        // e. Intersection with A of (B ∆ C) -> 𝐴 ∩(𝐵∆𝐶)
        QuerySet<Integer> A_sect_BsymC = a.intersect(b.symmetricDifference(c));
        Assertions.print("A ∩ (B ∆ C)", A_sect_BsymC);

        // f. Union with C of (A ∆ B)
        QuerySet<Integer> Cu_AsymB = c.union(a.symmetricDifference(b));
        Assertions.print("C ∪ (A ∆ B)", Cu_AsymB);

        // g. (B ∆ A) ∩ (B ∆ C)
        QuerySet<Integer> BsymA_sect_BsymC = b.symmetricDifference(a).intersect(b.symmetricDifference(c));
        Assertions.print("(B ∆ A) ∩ (B ∆ C)", BsymA_sect_BsymC);

        // h. (A ∩ B) ∆ (A ∩ C)
        QuerySet<Integer> AsectB_sym_AsectC = a.intersect(b).symmetricDifference(a.intersect(c));
        Assertions.print("(A ∩ B) ∆ (A ∩ C)", AsectB_sym_AsectC);

        // i. (C ∪ A) ∆ (C ∪ B)
        QuerySet<Integer> CuA_sym_CuB = c.union(a).symmetricDifference(c.union(b));
        Assertions.print("(C ∪ A) ∆ (C ∪ B)", CuA_sym_CuB);

        IO.println("==========================");


    }

    public static void shouldSuccessfullySolveExercise3Part1(){
        IO.println("=== Exercise 3 Part 1 Results ===");
        QuerySet<Integer> a = new QuerySet<>(List.of(1,2,3,4,5));
        QuerySet<Integer> b = new QuerySet<>(List.of(0,3,6));

        //a. A ∪ B
        QuerySet<Integer> AuB = a.union(b);
        Assertions.print("A ∪ B", AuB);

        //b. A - B
        QuerySet<Integer> AdiffB = a.difference(b);
        Assertions.print("A - B", AdiffB);

        //c. A ∩ B
        QuerySet<Integer> AsectB = a.intersect(b);
        Assertions.print("A ∩ B", AsectB);

        IO.println("==========================");
    }

    public static void shouldSuccessfullySolveExercise3Part2(){
        IO.println("=== Exercise 3 Part 2 Results ===");
        QuerySet<Integer> a = new QuerySet<>(List.of(0,2,4,6,8,10));
        QuerySet<Integer> b = new QuerySet<>(List.of(0,1,2,3,4,5,6));
        QuerySet<Integer> c = new QuerySet<>(List.of(4,5,6,7,8,9,10));

        //a. A ∩ B ∩ C
        QuerySet<Integer> AsectB_sectC = a.intersect(b).intersect(c);
        Assertions.print("A ∩ B ∩ C", AsectB_sectC);

        //b. A ∪ B ∪ C
        QuerySet<Integer> AuBuC = a.union(b).union(c);
        Assertions.print("A ∪ B ∪ C", AuBuC);

        //c. (A ∪ B) ∩ C
        QuerySet<Integer> AuB_sectC = a.union(b).intersect(c);
        Assertions.print("(A ∪ B) ∩ C", AuB_sectC);

        //d. (A ∩ B) ∪ C
        QuerySet<Integer> AsectB_uC = a.intersect(b).union(c);
        Assertions.print("(A ∩ B) ∪ C", AsectB_uC);

        //e. A ∩ (B - C)
        QuerySet<Integer> Asect_BdiffC = a.intersect(b.difference(c));
        Assertions.print("A ∩ (B - C)", Asect_BdiffC);

        //f. (A ∩ B) ∪ (A ∩ C)
        QuerySet<Integer> AsectB_u_AsectC = a.intersect(b).union(a.intersect(c));
        Assertions.print("(A ∩ B) ∪ (A ∩ C)", AsectB_u_AsectC);

        //f. (A ∩ B') ∪ (A ∩ C'), since there is no given universal set, we assume the universal set to be the set of all real numbers
        QuerySet<Integer> AsectBcomp_u_AsectCcomp = a.intersect(b).union(a.intersect(c));
        Assertions.print("(A ∩ B) ∪ (A ∩ C)", AsectBcomp_u_AsectCcomp);

        IO.println("==========================");
    }

}
