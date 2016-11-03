package katas.primeFactors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class PrimeFactorsTest3 {
    private void assertPrimeFactors(int n, List<Integer> factors) {
        assertThat(primeFactors(n), equalTo(factors));
    }

    private List<Integer> list(Integer... ints) {
        return Arrays.asList(ints);
    }

    @Test
    public void givenNumber_returnsPrimeFactors() {
        assertPrimeFactors(1, list());
        assertPrimeFactors(2, list(2));
        assertPrimeFactors(3, list(3));
        assertPrimeFactors(4, list(2,2));
        assertPrimeFactors(5, list(5));
        assertPrimeFactors(6, list(2,3));
        assertPrimeFactors(7, list(7));
        assertPrimeFactors(8, list(2,2,2));
        assertPrimeFactors(9, list(3,3));
        assertPrimeFactors(2*3*3*5*7*7*11, list(2,3,3,5,7,7,11));
    }

    /*
     * Change Log:
     * test:  nothing()
     * test:  primeFactors(1)
     * transform: {} -> nil
     * transform: nil -> constant
     * test: primeFactors(2)
     * transform: constant -> scalar
     * transform: split unconditional -> if
     * test: primeFactors(3)
     * refactor: clean up test code
     * test: primeFactors(4) 
     * transform: split unconditional -> if
     * transform: split unconditional -> if
     * refactor:  move last if block
     * test: primeFactors(5)
     * test: primeFactors(6)
     * test: primeFactors(7)
     * test: primeFactors(8)
     * transform: if -> while
     * refactor: while -> for
     * refactor: remove horrible braces on for loop
     * test: primeFactors(9)
     * transform: constant -> scalar (defined before first if block)
     * transform: if -> while
     * refactor: remove last if block (no longer used)
     * refactor: while -> for
     * refactor: remove horrible braces on for loop
     * test: primeFactors(2*3*3*5*7*7*11)
     */
    private List<Integer> primeFactors(int n) {
        ArrayList<Integer> factors = new ArrayList<Integer>();
        for (int divisor = 2; n > 1; divisor++)
            for (; n % divisor == 0; n /= divisor)
                factors.add(divisor);
        return factors;
    }
}
