package katas.stack;

public class Stack {

	private int size = 0;
	private int elements[];
	
	public Stack(int capacity) {
		this.elements = new int[capacity];
	}
	
	public int size() {
		return size;
	}
	
	public void push(int element) {
		if(size == elements.length)
			throw new Overflow();
		this.elements[size++] = element;
	}
	
	public int pop() {
		if(size == 0)
			throw new Underflow();
		return elements[--size];
	}
}
