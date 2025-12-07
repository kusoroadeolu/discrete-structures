# Relation
This mini project demonstrates a practical approach towards relations in discrete structures
</br> A relation is represented as a `QuerySet` of type `Pair`.
</br> A pair is a type which houses two `Integer` types

```java
import relations.Pair;
import settheory.sets.QuerySet;

Pair p = new Pair(1, 2);
QuerySet<Pair> pairs = new QuerySet<>(List.of(p));
Relation r = new Relation(pairs);
```

## Relation Parser
This class allows you to derive a relation from a string using the following format:

```java
import relations.RelationParser;

String str = "{(1, 2) (2, 3) (3, 4)}";
Relation relation = RelationParser.parseStringToRelation(str);
relation.forEach(IO::println);
// Output: Pair[first = 1, second = 2] Pair[first = 2, second = 3] Pair[first = 3, second = 4]
```
This class also allows you to derive a query set from a string
```java
import relations.RelationParser;
import settheory.sets.QuerySet;

String str = "{1, 2, 3, 4}";
QuerySet<Integer> set = RelationParser.parseStringToSet(str);
set.forEach(IO::println);
// Output: 1, 2, 3, 4
```

## Composite Checker
A composite relation between two relations `R` and `S` occurs when the second element of a pair at idx i in `R` is equals to the first element of a pair at idx i in `S`
</br> This class allows us to get the composite relation of two relation.

```java
import relations.CompositeChecker;
import relations.Pair;
import relations.Relation;
import settheory.sets.QuerySet;

Relation r = new Relation(new QuerySet<>(List.of(new Pair(1, 2))));
Relation s = new Relation(new QuerySet<>(List.of(new Pair(2, 3))));
Relation composite = CompositeChecker.getComposite(r, s);
composite.forEach(IO::println);
// Output: Pair[first = 1, second = 3]
```


## Properties of relations
The `RelationPropertyChecker` class is used to check the props of relations

#### Reflexive 
A relation `R` is said to be reflexive on a set `A` if for every element in the set A there exists a self pair of that element in `R`

```java
import relations.Relation;
import relations.RelationPropertyChecker;
import settheory.sets.QuerySet;

QuerySet<Integer> a = new QuerySet<>(List.of(1, 2))
Relation r = new Relation(new QuerySet<>(List.of(new Pair(1, 1), new Pair(2, 2), new Pair(2, 3))));
boolean isReflexive = RelationPropertyChecker.isReflexive(a, r);
IO.println(isReflexive); //Prints true
```

#### Symmetric
A relation `R` is said to be symmetric if for every pair `(a, b) E R` there exists a pair `(b, a) E R`

```java
import relations.Relation;
import relations.RelationPropertyChecker;
import settheory.sets.QuerySet;

Relation r = new Relation(new QuerySet<>(List.of(new Pair(1, 2), new Pair(3, 2), new Pair(2, 3))));
boolean isSymmetric = RelationPropertyChecker.isSymmetric(r);
IO.println(isSymmetric); //Prints true
```

#### Asymmetric
A relation `R` is said to be asymmetric on a set `A` if there exists a pair `(a, b) E R` and `(b, a) E R` only if `a == b`
```java
import relations.Relation;
import relations.RelationPropertyChecker;
import settheory.sets.QuerySet;

Relation r = new Relation(new QuerySet<>(List.of(new Pair(1, 2), new Pair(3, 2), new Pair(2, 3))));
boolean isAsymmetric = RelationPropertyChecker.isAsymmetric(r);
IO.println(isAsymmetric); //Prints false

Relation r1 = new Relation(new QuerySet<>(List.of(new Pair(1, 2), new Pair(3, 2))));
boolean isAsymmetric1 = RelationPropertyChecker.isAsymmetric(r1);
IO.println(isAsymmetric1); //Prints true
```


#### Transitive
A relation `R` is said to be transitive on a set `A` if there exists a pair `(a, b) E R` and `(b, c) E R` then there must exist a pair `(a, c) E R` 
```java
import relations.Relation;
import relations.RelationPropertyChecker;
import settheory.sets.QuerySet;

Relation r = new Relation(new QuerySet<>(List.of(new Pair(1, 2), new Pair(2, 3), new Pair(1, 3))));
boolean isTransitive = RelationPropertyChecker.isTransitive(r);
IO.println(isTransitive); //Prints true
```