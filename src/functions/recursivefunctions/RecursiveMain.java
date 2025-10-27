void main() {
    int snails = this.snailsAfterNYears(2);
    IO.println("Snails after n years: " +  snails);

    int days = this.pushUpsAfterNDays(2);
    IO.println("Push ups after n days: " + days);

    int factorial = this.nthFactorial(10);
    IO.println("Nth factorial: " + factorial);

    int fibonacci = this.fibonacci(10);
    IO.println("Nth fibonacci: " + fibonacci);
}


int recursiveSquareRoot(int n){
    return n == 0 ? 0 : recursiveSquareRoot(n - 1) + (2 * (n - 1)) + 1;
}

//gives the number of snails in your terrarium n years after you built it,
// assuming you started with 3 snails and the number of snails doubles each year
//f(n) = f(n - 1) * 2 where f(0) = 3
int snailsAfterNYears(int yrs){
    return yrs == 0 ? 3 : snailsAfterNYears(yrs - 1) * 2;
}

//gives the number of push-ups you do n days after you started your push-ups challenge,
// assuming you could do 7 push-ups on day 0 and you can do 2 more push-ups each day.
//f(n) = f(n - 1) + 2 where f(0) = 7
int pushUpsAfterNDays(int days){
    return days == 0 ? 7 : pushUpsAfterNDays(days - 1) + 2;
}

// h:N→N defined by h(n)=n!. Recall that n!=1⋅2⋅3⋅⋯⋅(n−1)⋅n is the product of all numbers from 1 through n. We also define 0!=1.
//f(n) = f(n - 1) * n where f(0) = 1
int nthFactorial(int n){
    return n == 0 ? 1 : nthFactorial(n - 1) * n;
}

int fibonacci(int n){
   if(n == 1){
       return 0;
   }

   if (n == 2){
       return 1;
   }

   return fibonacci(n - 1) + fibonacci(n - 2);
}




