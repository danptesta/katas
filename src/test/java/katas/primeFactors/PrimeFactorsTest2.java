package katas.primeFactors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matcher;
import org.junit.Test;

public class PrimeFactorsTest2 {
    private Matcher<List<Integer>> isListOf(Integer... ints) {
        return is(Arrays.asList(ints));
    }

    @Test
    public void factors() {
        assertThat(primeFactorsOf(1), isListOf());
        assertThat(primeFactorsOf(2), isListOf(2));
        assertThat(primeFactorsOf(3), isListOf(3));
        assertThat(primeFactorsOf(4), isListOf(2,2));
        assertThat(primeFactorsOf(5), isListOf(5));
        assertThat(primeFactorsOf(6), isListOf(2,3));
        assertThat(primeFactorsOf(7), isListOf(7));
        assertThat(primeFactorsOf(8), isListOf(2,2,2));
        assertThat(primeFactorsOf(9), isListOf(3,3));
    }

    private List<Integer> primeFactorsOf(int n) {
        ArrayList<Integer> factors = new ArrayList<Integer>();        
        for (int divisor = 2; n > 1; divisor++) {
            for (; n % divisor == 0; n /= divisor)
                factors.add(divisor);
        }
        return factors;
    }
}
