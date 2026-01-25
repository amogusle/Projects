import java.util.ArrayList;

public class ArrayListStack <T> implements Stack<T> {

	private ArrayList<T> elements; 
	
	public ArrayListStack() {
		elements = new ArrayList<T>();
	}
	
    @Override
	public int size() {	
		return elements.size();
	}
	
    @Override
	public boolean isEmpty() {
		return elements.size() == 0;
		}
	
	
    // this is the "insert" operation
    // in a stack, new items are inserted at the "top" of the stack
    @Override
	public void push(T item) {
		elements.add(item);
		//elements.add(0, item);
	}
	
    // this is the "return but don't delete" operation
    // in a stack, the "next" items are returned from the "top" of the stack
    @Override
	public T peek() {
		if (isEmpty())
			throw new IndexOutOfBoundsException("Stack is empty");
			return elements.get(elements.size()-1);
		
		//return elements.get(0);
	}
	
    // this is the "delete and return" operation
    // in a stack, the "next" items are deleted from the "top" of the stack 
    @Override
	public T pop() {
		if (isEmpty())
			throw new IndexOutOfBoundsException("Stack is empty");
			return elements.remove(elements.size()-1);
		
	}
	
} // end class ArrayListStack
