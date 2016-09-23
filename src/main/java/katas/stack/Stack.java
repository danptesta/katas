package katas.stack;

public interface Stack {

    boolean isEmpty();
    
    boolean isFull();
    
    int size();

    void push(int element);

    int pop();
    
    int top();
    
    Integer find(int element);

    public class Overflow extends RuntimeException {
    }

    public class Underflow extends RuntimeException {
    }

    public class IllegalCapacity extends RuntimeException {
    }
    
    public class Empty extends RuntimeException {
    }
}
