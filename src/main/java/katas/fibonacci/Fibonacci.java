package katas.fibonacci;

public class Fibonacci {
    
    public static int fibonacci(int n) {
        if(n < 0)
            throw new NegativeValue();
 
        if(n < 2)
            return n;
        else
            return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
