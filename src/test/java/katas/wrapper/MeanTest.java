package katas.wrapper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class MeanTest {
    private Integer[] ints(Integer... ints) {
        return ints;
    }

    @Test
    public void whenEmpty_returnNull() {
        assertMean(ints(), null);
    }

    private void assertMean(Integer[] ints, BigDecimal expectedMean) {
        assertThat(mean(ints), equalTo(expectedMean));
    }
    
    @Test
    public void oneNumber() {
        assertMean(ints(1), new BigDecimal(1));
    }
    
    @Test
    public void multipleNumbers() {
        assertMean(ints(1,2), new BigDecimal(1.5));
        System.out.println("mean = " + mean(10,11,11,12,12,12,12,12,13,13,13,14));
    }

    private BigDecimal mean(Integer... ints) {
        if(ints.length == 0)
            return null;
        int sum = 0;
        for(int i : ints) {
            sum += i;
        }
        return new BigDecimal((double) sum / ints.length);
   }
}
