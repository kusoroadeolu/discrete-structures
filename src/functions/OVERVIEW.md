# FUNCTIONS OVERVIEW

## A BRIEF INTRO
- A function is a rule that assigns an input to an output i.e. `f(x) = x * 3`; It is an abstract mathematical object that exists regardless of if anyone talks about it or not. Functions are total by default
</br> A function becomes invalid if any input passed through the function produces more than one output or no output at all.
</br> A relation is a rule which maps an input or multiple inputs to one or many outputs i.e. A DB Entity (e.g. One lecturer can teach many students), `can teach` here is the relation between the lecturer and students
</br> A partial function is a rule which assigns an input to an output or an undefined output. A total function does not allow an undefined output. An example of a partial function is the `Optional` class
</br> A function can also be described as an explicit formula that calculates the image of any input(i.e. a closed formula) 

- A domain is a set of valid predefined inputs given to a function that will produce a defined image. 
- A valid input can only produce the same value no matter the number of times it is passed through a specific function
- An image is basically the output an input passed through a function
- A codomain is a set of allowable outputs is the codomain. The codomain also does not have to adhere to the range of the function
- A range or the image of the function differs from the codomain in the sense that it is the set of all possible outputs from the given domain.

### Recursively Defined Functions

We can also describe a function recursively. For example a function whose base case is `f(0) = 0`, and it's formula `f(n + 1) = f(n) + f(n * 2) + 1`
</br> This function will compute the square of the n(its input). It can also be expressed as `f(n) =  f(n - 1) + f((n - 1) * 2) + 1`
</br> We can also express this programmatically as: 
```java
int simpleRecursiveFunction(int n){
    return n == 0 ? 0 : simpleFunction(n - 1) + (2 * (n - 1)) + 1;
}
```
More recursive examples can be found at [functions/recursivefunctions/RecursiveMain.java](functions/recursivefunctions/RecursiveMain.java)

### Surjections, Injections, Bijections
#### Surjections
A function is said to be surjective when the values in the domain maps to each element in the codomain (meaning that all the codomain is a subset of the range of the function)
</br> Given a function `f(n) = n + 3` where `n -> N` is the domain we can define the codomain as `C = {5,6,7,8,9}` but the range will be `{f(n): N > 4}`
</br> We can clearly see that the codomain is a subset of the range, hence this function given its domain, is surjective

#### Injections
A function is said to be injective when each of the values in the domain has a unique image in the codomain i.e. Each element in the codomain maps to at most one element in the domain
</br> Given a function `f(n) = n * 3` where `{1,2,3,4,5}` is the domain we can define the codomain as `C = {3,6,9,12,15,18}`

#### Bijection
A function is said to bijective when it is both injective and subjective, meaning its codomain is a subset of the range and each element in the codomain maps to at most one element in the domain


### Inverse Images and Functions

#### Inverse Images
Inverting an image means trying to find the values of `x` in the domain which maps to a specific `y` which exists in the codomain. This is very straight forward
**It can also be referred to as a preimage**
</br> For f(n) = n * 3 with domain {1,2,3,4,5}, the inverse image of 9 would be {3}, since f(3) = 9. 
</br> The inverse image of 7 would be the empty set {}, since no input maps to 7.


#### Inverse Functions
Inverting a function means trying to get the value of `x` in the domain which maps to any `y` which exists in the domain
</br> Given a function `f(n) = n * 3` where `{1,2,3,4,5}` is the domain we can define the codomain as `C = {3,6,9,12,15,18}`
</br> To invert this function to get input `x` from any image `y` in the codomain, we denote the function as y = n * 3 then make `n` the subject of the formula `n = y / 3`.
</br> The codomain of the original function becomes the domain of the inverted function and the domain of the original function now becomes the range of this function
**NOTE:** A function can only be inverted if it is bijective







