
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

	// just maintain one tail index
	private int		tail;
	private int		count;
	private Item[]	q;
	
    // construct an empty randomized queue
	public RandomizedQueue() {
		q = (Item[]) new Object[1];
		tail = 0;
		count = 0;
	}
	
    // is the queue empty?
	public boolean isEmpty() { return count == 0; }
	
    // return the number of items on the queue
	public int size() { return count; }
	
	private void resize(int n) {
		Item[] copy = (Item[]) new Object[n];
		for (int i = 0; i < count; i++)
			copy[i] = q[i];
		q = copy;
	}
	
	
    // add the item	   
	public void enqueue(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException("null item not permitted");
		
		if (count == q.length) resize(2 * q.length);
		q[tail++] = item;
		count++;
	}
	
    // delete and return a random item
	public Item dequeue() {
		if (isEmpty())
			throw new java.util.NoSuchElementException("Queue empty");
		
		int index = StdRandom.uniform(tail);
		Item item = q[index];
		
		// simply move the last item into the void
		q[index]	= q[--tail];
		q[tail] 	= null;
		count--;
		if (count > 0 && count == q.length / 4)
			resize(q.length / 2);
		
		return item;
	}
	
    // return (but do not delete) a random item
	public Item sample() { return q[StdRandom.uniform(count)]; }
	
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() { return new ArrayIterator(); }
	
	private class ArrayIterator implements Iterator<Item> {
		private int i = 0;
		
		public boolean hasNext() { return i < count; }
		public void remove() {
			throw new java.lang.UnsupportedOperationException("remove operation currently not supported");
		}
		public Item	next() {
			if (i > count)
				throw new java.util.NoSuchElementException("no more items");
			return q[i++];
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
		
		StdOut.println(q.isEmpty());
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		q.enqueue(5);
		q.enqueue(6);
		q.enqueue(7);
		StdOut.println(q.size());
		
		for (Integer i : q)
			StdOut.print(i + " ");
		StdOut.println();
		
		StdOut.println(q.dequeue());
		
		for (Integer i : q)
			StdOut.print(i + " ");
		StdOut.println();
		
		StdOut.println(q.dequeue());
		StdOut.println(q.dequeue());
		StdOut.println(q.size());
		
		for (Integer i : q)
			StdOut.print(i + " ");
		StdOut.println();
	}

}
