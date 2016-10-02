package katas.varargs;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class VariableArgumentsTest {
    static <T> T[] append(T[] arr, T... lastElements) {
        int N = arr.length;
        arr = java.util.Arrays.copyOf(arr, N+lastElements.length);
        for(T element : lastElements)
            arr[N++] = element;
        return arr;
    }
    
    static <T> T[] prepend(T[] arr, T... firstElements) {
        final int N = arr.length;
        final int M = firstElements.length;
        arr = java.util.Arrays.copyOf(arr, N+M);
        System.arraycopy(arr, 0, arr, M, N);
        int i=0;
        for(T element : firstElements)
            arr[i++] = element;
        return arr;
    }

    @Test
    public void zeroArgs() {
        assertThat(variableArgs(), equalTo(new Object[] {}));
    }

    @Test
    public void oneArg() {
        assertThat(variableArgs(1), equalTo(new Object[] {1}));
    }
    
    @Test
    public void arrayOfTwo() {
        assertThat(variableArgs(new Object[] {1,2}), equalTo(new Object[] {1,2}));
    }
    
    @Test
    public void appendNothingToArray() {
        assertThat(variableArgs(append(new Object[] {1,2})), equalTo(new Object[] {1,2}));
    }
    
    @Test
    public void prependNothingToArray() {
        assertThat(variableArgs(prepend(new Object[] {1,2})), equalTo(new Object[] {1,2}));
    }
    
    @Test
    public void appendArgToArray() {
        assertThat(variableArgs(append(new Object[] {1,2},3)), equalTo(new Object[] {1,2,3}));
    }
    
    @Test
    public void prependArgToArray() {
        assertThat(variableArgs(prepend(new Object[] {2,3},1)), equalTo(new Object[] {1,2,3}));
    }
    
    @Test
    public void appendArrayToArray() {
        assertThat(variableArgs(append(new Object[] {1,2},new Object[] {3,4})), equalTo(new Object[] {1,2,3,4}));
    }    
    
    @Test
    public void prependArrayToArray() {
        assertThat(variableArgs(prepend(new Object[] {3,4},new Object[] {1,2})), equalTo(new Object[] {1,2,3,4}));
    }    

    private Object[] variableArgs(Object... params) {
        return params;
    }
}
