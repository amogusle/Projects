import java.util.ArrayList;
import java.util.LinkedList;

public class LinkedListQueue<T> implements Queue<T> {
	
	private LinkedList<T> elements;
	
	public LinkedListQueue() {
        elements = new LinkedList<T>();
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
    // in a queue, new items are inserted at the "back" of the queue
    @Override
	public void enqueue(T item) {
        elements.add(item);
	}

    // this is the "return but don't delete" operation
    // in a queue, the "next" items are returned from the "front" of the queue
    @Override
	public T peek() {
        if (isEmpty())
		throw new IndexOutOfBoundsException("Queue is empty");
		return elements.get(0);
	}

    // this is the "delete and return" operation
    // in a queue, the "next" items are deleted from the "front" of the queue
    @Override
	public T dequeue() {
        if (isEmpty())
		throw new IndexOutOfBoundsException("Queue is empty");
		return elements.remove(0);
	}
	
	
} // end class LinkedListQueue
