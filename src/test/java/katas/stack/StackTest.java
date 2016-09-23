package katas.stack;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  StackTest.DegenerateTests.class,
  StackTest.ZeroCapacityTests.class,
  StackTest.TwoCapacityTests.class
})

public class StackTest {
    
    public static class DegenerateTests {

        private Stack stack;
        
        @Before
        public void setUp() {
            stack = BoundedStack.Make(2);
        }        
        
        @Test
        public void whenNewStack_shouldBeEmpty() {
            assertThat(stack.isEmpty(), equalTo(true));
        }
        
        @Test
        public void whenNewStack_shouldNotBeFull() {
            assertThat(stack.isFull(), equalTo(false));
        }
        
        @Test
        public void whenNewStack_sizeShouldBeZero() {
            assertThat(stack.size(), equalTo(0));
        }
        
        @Test(expected=katas.stack.Stack.Underflow.class)
        public void whenPoppingEmptyStack_shouldThrowUnderflow() {
            stack.pop();
        }
        
        @Test(expected=katas.stack.Stack.IllegalCapacity.class)
        public void whenCreatingStackWithNegativeSize_shouldThrowIllegalCapacity() {
            Stack negativeStack = BoundedStack.Make(-1);
        }
        
        @Test(expected=katas.stack.Stack.Empty.class)
        public void whenStackIsEmpty_topShouldThrowEmptyStack() {
            stack.top();
        }
    }
    
    public static class ZeroCapacityTests {

        private Stack stack;
        
        @Before
        public void setUp() {
            stack = BoundedStack.Make(0);
        }
        
        @Test
        public void isEmptyShouldBeTrue() {
            assertThat(stack.isEmpty(), equalTo(true));
        }
        
        @Test
        public void isFullShouldBeTrue() {
            assertThat(stack.isFull(), equalTo(true));
        }
        
        @Test
        public void sizeShouldBeZero() {
            assertThat(stack.size(), equalTo(0));
        }
                
        @Test(expected=katas.stack.Stack.Overflow.class)
        public void anyPushShouldOverflow() {
            stack.push(1);
        }
        
        @Test(expected=katas.stack.Stack.Underflow.class)
        public void anyPopShouldUnderflow() {
            stack.pop();
        }
        
        @Test(expected=katas.stack.Stack.Empty.class)
        public void topShouldThrowEmptyStack() {
            stack.top();
        }
        
        @Test
        public void findShouldReturnNull() {
            assertThat(stack.find(1), equalTo(null));
        }
    }
    
    public static class TwoCapacityTests {
        
        private Stack stack;
        
        @Before
        public void setUp() {
            stack = BoundedStack.Make(2);
        }
        
        @Test
        public void whenPushed_shouldNotBeEmpty() {
            stack.push(0);
            assertThat(stack.isEmpty(), equalTo(false));
        }
        
        @Test
        public void whenPushedTwice_shouldBeFull() {
            stack.push(1);
            stack.push(2);
            assertThat(stack.isFull(), equalTo(true));
        }
        
        @Test
        public void whenPushedOnce_sizeShouldBeOne() {
            stack.push(0);
            assertThat(stack.size(), equalTo(1));
        }
        
        @Test
        public void whenPushedAndPoppedOnce_sizeShouldBeZero() {
            stack.push(0);
            stack.pop();
            assertThat(stack.size(), equalTo(0));
        }

        @Test
        public void shouldPopZeroWhenZeroIsPushed() {
            stack.push(0);
            assertThat(stack.pop(), equalTo(0));
        }

        @Test
        public void shouldPopOneWhenOneIsPushed() {
            stack.push(1);
            assertThat(stack.pop(), equalTo(1));
        }
        
        @Test
        public void pushedElementsArePoppedInReverseOrder() {
            stack.push(1);
            stack.push(2);
            assertThat(stack.pop(), equalTo(2));
            assertThat(stack.pop(), equalTo(1));
        }
        
        @Test(expected=katas.stack.Stack.Overflow.class)
        public void whenFullStackIsPushed_shouldThrowOverflow() {
            stack.push(1);      
            stack.push(2);      
            stack.push(3);      
        }
                
        @Test
        public void whenOneIsPushed_oneShouldBeOnTop() {
            stack.push(1);
            assertThat(stack.top(), equalTo(1));
        }
        
        @Test
        public void givenStackWithOneAndTwo_shouldFindOneAndTwo() {
            stack.push(1);
            stack.push(2);
            assertThat(stack.find(1), equalTo(1));
            assertThat(stack.find(2), equalTo(0));
        }
        
        @Test
        public void givenStackWithoutTwo_shouldNotFindTwo() {
            stack.push(1);
            assertThat(stack.find(2), equalTo(null));
        }
    }
}
