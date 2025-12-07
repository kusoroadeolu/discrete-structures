# Sequences
A mini sandbox I made to learn sequences.
</br> A sequence is simply an ordered list of numbers
</br> This project allows an intuitive api to calculate arithmetic and geometric sequences

## Arithmetic sequence
This is a sequence in the form a + ((n - 1) * d)

```java
import sequences.ArithmeticSequence;
import sequences.Sequence;
//First term, Common difference, Sequence, MaxNumOfTerms
Sequence arithmeticSequence = new ArithmeticSequence(1, 2, new ArrayList<>(), 5);
arithmeticSequence.addNextTerms(5); //Generates and adds the next 5 terms to the arithmetic sequence
arithmeticSequence.getNthTerm(5); //Gets the 5th nth term from a one based index list
arithmeticSequence.sumUpTo(5); //Finds the sum of this sequence of the 5
```

## Geometric sequence
This is a sequence in the form a * (r ^ (n - 1))
**NOTE:** A geometric sequence diverges when |r| >= 1 and converges when |r| < 1

```java
import sequences.ArithmeticSequence;
import sequences.GeometricSequence;
import sequences.Sequence;

//First term, Ratio, Sequence, Max Num Of Terms
Sequence geometricSequence = new GeometricSequence(3, 2.2, new ArrayList<>(), 5);
geometricSequence.addNextTerms(5); //Generates and adds the next 5 terms to the arithmetic sequence
geometricSequence.getNthTerm(5); //Gets the 5th nth term from a one based index list
geometricSequence.sumUpTo(5); //Finds the sum of this sequence of the 5
geometricSeqence.sumToInfinity() //Finds the sum of this sequence to infinity.
```

## Mean
### Arithmetic mean
This is the average of two numbers

```java
import sequences.MeanFinder;

double mean = MeanFinder.findArithmeticMean(10.0, 20.0);
IO.println(mean);
```

### Arithmetic mean
This is the square root of two numbers

```java
import sequences.MeanFinder;

double mean = MeanFinder.findGeometricMean(10.0, 20.0);
IO.println(mean);
```