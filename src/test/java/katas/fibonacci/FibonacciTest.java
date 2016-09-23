package katas.fibonacci;

import static katas.fibonacci.Fibonacci.fibonacci;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    katas.fibonacci.FibonacciTest.DegenerateTests.class,
    katas.fibonacci.FibonacciTest.GoldTests.class
})
public class FibonacciTest {

    public static class DegenerateTests {

        @Test
        public void zero_shouldBeZero() throws Exception {
            assertThat(fibonacci(0), equalTo(0));
        }

        @Test
        public void one_shouldBeOne() throws Exception {
            assertThat(fibonacci(1), equalTo(1));
        }

        @Test(expected=katas.fibonacci.NegativeValue.class)
        public void whenNegativeValueIsGiven_shouldThrowNativeValue() throws Exception {
            fibonacci(-1);
        }
    }
    
    public static class GoldTests {
        
        @Test
        public void two_shouldBeOne() throws Exception {
            assertThat(fibonacci(2), equalTo(1));
        }
        
        @Test
        public void three_shouldBeTwo() throws Exception {
            assertThat(fibonacci(3), equalTo(2));
        }
        
        @Test
        public void four_shouldBeThree() throws Exception {
            assertThat(fibonacci(4), equalTo(3));
        }
        
        @Test
        public void five_shouldBeFive() throws Exception {
            assertThat(fibonacci(5), equalTo(5));
        }
        
        @Test
        public void six_shouldBeEight() throws Exception {
            assertThat(fibonacci(6), equalTo(8));
        }
    }
}
