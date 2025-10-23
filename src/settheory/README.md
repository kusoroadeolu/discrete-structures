# QuerySet: Set Theory Operations in Java

A lightweight Java data structure that implements set theory operations with a functional, query-like API.

## Overview

QuerySet wraps a `LinkedHashSet` and provides Set inspired operations for working with sets. It combines classic set theory operations (union, intersection, difference) with predicate-based filtering and querying.

## Core Concepts

### Set Theory Operations

**Union** - Combines two sets, keeping all unique elements from both.
```java
QuerySet<Integer> a = new QuerySet<>(List.of(1, 2, 3));
QuerySet<Integer> b = new QuerySet<>(List.of(3, 4, 5));
a.union(b); // {1, 2, 3, 4, 5}
```

**Intersection** - Returns elements that exist in both sets.
```java
QuerySet<Integer> a = new QuerySet<>(List.of(1, 2, 3));
QuerySet<Integer> b = new QuerySet<>(List.of(2, 3, 4));
a.intersect(b); // {2, 3}
```

**Difference** - Returns elements in the first set but not in the second.
```java
QuerySet<Integer> a = new QuerySet<>(List.of(1, 2, 3));
QuerySet<Integer> b = new QuerySet<>(List.of(2, 3, 4));
a.difference(b); // {1}
```

**Symmetric Difference** - Returns elements that exist in either set, but not in both.
```java
QuerySet<Integer> a = new QuerySet<>(List.of(1, 2, 3));
QuerySet<Integer> b = new QuerySet<>(List.of(2, 3, 4));
a.symmetricDifference(b); // {1, 4}
```

### Predicate-Based Operations

QuerySet uses Java's `Predicate<T>` functional interface for filtering and testing operations.

**Filter** - Returns a new QuerySet containing only elements that match the predicate.
```java
QuerySet<Integer> numbers = new QuerySet<>(List.of(1, 2, 3, 4, 5, 6));
QuerySet<Integer> evens = numbers.filter(n -> n % 2 == 0); // {2, 4, 6}
```

**anyMatch** - Tests if at least one element satisfies the predicate.
```java
boolean hasEven = numbers.anyMatch(n -> n % 2 == 0); // true
```

**allMatch** - Tests if all elements satisfy the predicate.
```java
boolean allPositive = numbers.allMatch(n -> n > 0); // true
```

**count** - Counts elements that satisfy the predicate.
```java
int evenCount = numbers.count(n -> n % 2 == 0); // 3
```

### Subset and Superset Operations

**isSubsetOf** - Checks if all elements in the given collection exist in this QuerySet.
```java
QuerySet<Integer> a = new QuerySet<>(List.of(1, 2, 3, 4));
boolean isSubset = a.isSubsetOf(List.of(1, 2)); // true
```

**isSupersetOf** - Checks if this QuerySet contains all elements from the given collection.
```java
boolean isSuperset = a.isSupersetOf(List.of(1, 2)); // true
```

## Design Decisions

###  Immutable Operations
- **Immutable**: `union()` `filter()`, `intersect()`, `difference()`, `symmetricDifference()` - Query operations that don't modify the original set, allowing for clean chaining and composition

### Why Predicates?

Predicates allow you to pass around test logic as behavior rather than values. Unlike booleans which represent a single true/false state, predicates can test complex conditions and work across any type through generics. This functional approach makes operations composable and reusable.

## Usage Example

```java
QuerySet<String> users = new QuerySet<>(List.of(
    "Alice", "Bob", "Charlie", "David", "Eve"
));

// Filter users with names longer than 4 characters
QuerySet<String> longNames = users.filter(name -> name.length() > 4);

// Check if any user's name starts with 'E'
boolean hasE = users.anyMatch(name -> name.startsWith("E"));

// Combine with another set
QuerySet<String> moreUsers = new QuerySet<>(List.of("Frank", "Grace"));
users.union(moreUsers);

// Find users in first set but not second
QuerySet<String> uniqueToFirst = users.difference(moreUsers);
```

### Power Set Operations

**Power Set** - Returns all possible subsets of the set, including the empty set and the set itself.
```java
QuerySet<String> set = new QuerySet<>(List.of("A", "B", "C"));
Set<QuerySet<String>> powerSet = set.getPowerSets();
// Returns: [{}, {A}, {B}, {C}, {A,B}, {A,C}, {B,C}, {A,B,C}]
// Size: 2^n where n is the number of elements (2^3 = 8)
```
*Implementation uses bit manipulation to efficiently generate all combinations.*

**Power Set Count** - Returns the number of subsets (2^n).
```java
int count = set.powerSetCount(); // 8 for a set of 3 elements
```

### Set Equality and Equivalence

**equals** - Checks if two sets contain exactly the same elements.
```java
QuerySet<Integer> a = new QuerySet<>(List.of(1, 2, 3));
QuerySet<Integer> b = new QuerySet<>(List.of(3, 2, 1));
boolean equal = a.equals(b); // true (order doesn't matter)
```

**isEquivalentTo** - Checks if two sets have the same cardinality (size).
```java
QuerySet<Integer> a = new QuerySet<>(List.of(1, 2, 3));
QuerySet<Integer> b = new QuerySet<>(List.of(4, 5, 6));
boolean equivalent = a.isEquivalentTo(b); // true (both have 3 elements)
```

### Multiple Set Operations

**unionAll** - Combines multiple sets into one.
```java
QuerySet<Integer> a = new QuerySet<>(List.of(1, 2));
QuerySet<Integer> b = new QuerySet<>(List.of(2, 3));
QuerySet<Integer> c = new QuerySet<>(List.of(3, 4));
QuerySet<Integer> result = QuerySet.unionAll(a, b, c); // {1, 2, 3, 4}
```

**intersectAll** - Finds common elements across multiple sets.
```java
QuerySet<Integer> a = new QuerySet<>(List.of(1, 2, 3));
QuerySet<Integer> b = new QuerySet<>(List.of(2, 3, 4));
QuerySet<Integer> c = new QuerySet<>(List.of(2, 3, 5));
QuerySet<Integer> result = QuerySet.intersectAll(a, b, c); // {2, 3}
```

### DomainSet - Sets with Constraints

DomainSet extends QuerySet to support universal sets and complement operations. It enforces that all elements must satisfy a domain predicate and optionally exist within a universal set.
```java
// Create a domain set with a finite universal set
QuerySet<Integer> universal = new QuerySet<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
DomainSet<Integer> evens = new DomainSet<>(
        n -> n % 2 == 0,  // domain predicate
        universal,
        List.of(2, 4, 6)  // initial elements
);

evens.add(8); // OK - passes predicate and in universal set
evens.add(3); // Throws exception - not in domain
evens.add(12); // Throws exception - not in universal set

// Get complement (odd numbers from universal set)
QuerySet<Integer> odds = evens.complement(); // {1, 3, 5, 7, 9}

// Create a domain set representing infinite sets
// Pass null or empty universal set to allow any value that passes the predicate
DomainSet<Integer> allEvens = new DomainSet<>(
        n -> n % 2 == 0,  // domain predicate
        null,             // no universal set constraint
        List.of(2, 4, 6, 100, 1000)  // any even numbers allowed
);
```

### Cartesian Product
**Cartesian Product** - Returns all possible ordered pairs from two sets

```java
import settheory.sets.QuerySet;

QuerySet<Integer> a = new QuerySet<>(List.of(1, 2));
QuerySet<Integer> b = new QuerySet<>(List.of(3, 4));
Set<QuerySet.Pair<Integer, Integer>> cp = a.cartesianProduct(b);
//Returns {1, 3}, {1, 4}, {2, 3}, {3, 4}
```
The result is a set of Pair<E, E> objects where order matters and duplicates in elements are preserved. For sets of size m and n, the cartesian product will have m × n pairs.


## Additional Features

- `forEach(Consumer)` - Iterate over elements with a consumer
- `findFirst()` / `findLast()` - Retrieve first or last element
- `toList()` - Convert to an immutable List
- `isEmpty()` / `size()` - Basic set information
- `contains(E)` - Check if element exists

---

*Built as a learning project to explore set theory concepts and predicate-based functional programming in Java.*
