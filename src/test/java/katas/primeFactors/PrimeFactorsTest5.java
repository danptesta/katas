package katas.primeFactors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


import org.junit.Test;

public class PrimeFactorsTest5 {
    private List<Integer> list(Integer... factors) {
        return Arrays.asList(factors);
    }

    private void assertPrimeFactors(int n, List<Integer> factors) {
        assertThat(primesOf(n), equalTo(factors));
    }
    
    @Test
    public void canFactorIntoPrimes() {
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

    private List<Integer> primesOf(int n) {
        List<Integer> factors = new ArrayList<Integer>();
        
        for (int divisor = 2; n > 1; divisor++)
            for (; n % divisor == 0; n /= divisor)
                factors.add(divisor);
        
        return factors;
    }
}
