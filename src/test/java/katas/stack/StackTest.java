package katas.stack;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class StackTest {
	private Stack stack;

	@Before
	public void setUp() {
		stack = new Stack(2);
	}
	
	
	@Test
	public void newStackShouldBeEmpty() {
		assertThat(stack.size(), equalTo(0));
	}
	
	@Test
	public void sizeAfterPushShouldBeOne() {
		stack.push(0);
		assertThat(stack.size(), equalTo(1));
	}
	
	@Test
	public void sizeAfterPushAndPopShouldBeZero() {
		stack.push(0);
		stack.pop();
		assertThat(stack.size(), equalTo(0));
	}
	
	@Test(expected=katas.stack.Underflow.class)
	public void shouldThrowUnderflowWhenPoppingEmptyStack() {
		stack.pop();
	}
	
	@Test(expected=katas.stack.Overflow.class)
	public void shouldThrowOverflowWhenFullStackIsPushed() {
		stack.push(1);		
		stack.push(2);		
		stack.push(3);		
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
}
