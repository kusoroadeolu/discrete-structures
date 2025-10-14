# QuerySet: Set Theory Operations in Java

A lightweight Java data structure that implements set theory operations with a functional, query-like API.

## Overview

QuerySet wraps a `HashSet` and provides SQL-inspired operations for working with sets. It combines classic set theory operations (union, intersection, difference) with predicate-based filtering and querying.

## Core Concepts

### Set Theory Operations

**Union** - Combines two sets, keeping all unique elements from both.
```java
QuerySet<Integer> a = new QuerySet<>(List.of(1, 2, 3));
QuerySet<Integer> b = new QuerySet<>(List.of(3, 4, 5));
a.union(b); // {1, 2, 3, 4, 5}
```
*Note: Union is mutable and modifies the original set.*

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

### Mutable vs Immutable Operations

- **Mutable**: `union()` - Designed for incrementally building up a set
- **Immutable**: `filter()`, `intersect()`, `difference()`, `symmetricDifference()` - Query operations that don't modify the original set, allowing for clean chaining and composition

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

## Additional Features

- `forEach(Consumer)` - Iterate over elements with a consumer
- `findFirst()` / `findLast()` - Retrieve first or last element
- `toList()` - Convert to an immutable List
- `isEmpty()` / `size()` - Basic set information
- `contains(E)` - Check if element exists

---

*Built as a learning project to explore set theory concepts and predicate-based functional programming in Java.*