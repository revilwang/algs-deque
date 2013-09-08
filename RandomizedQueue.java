
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    // just maintain one tail index
    private int        tail;
    private int        count;
    private Item[]    q;
    
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
            throw new java.lang.NullPointerException();
        
        if (count == q.length) resize(2 * q.length);
        q[tail++] = item;
        count++;
    }
    
    // delete and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        
        int index = StdRandom.uniform(tail);
        Item item = q[index];
        
        // simply move the last item into the void
        q[index]    = q[--tail];
        q[tail]     = null;
        count--;
        if (count > 0 && count == q.length / 4)
            resize(q.length / 2);
        
        return item;
    }
    
    // return (but do not delete) a random item
    public Item sample() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();

        return q[StdRandom.uniform(count)];
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() { return new ArrayIterator(); }
    
    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;
        private Item[] array;
        
        public ArrayIterator() {
            array = (Item[]) new Object[count];
            for (int j = 0; j < count; j++)
                array[j] = q[j];
            for (int j = 0; j < count; j++) {
                int r = StdRandom.uniform(j+1);
                Item tmp = array[j];
                array[j] = array[r];
                array[r] = tmp;
            }
        }

        public boolean hasNext() { return i < count; }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next() {
            if (i >= count)
                throw new java.util.NoSuchElementException();
            return array[i++];
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> z = new RandomizedQueue<Integer>();
        z.enqueue(1);
        z.enqueue(4);
        z.enqueue(3);
        z.enqueue(6);
        z.enqueue(7);
        z.enqueue(8);
        z.enqueue(41);
        z.enqueue(53);
        StdOut.printf("size of queue: %d\n", z.size());

        for (int i : z) {
            StdOut.printf("outer i = %d\n", i);
            for (int j : z) {
                StdOut.printf(" %d ", j);
            }
            StdOut.println();
        }
    }
}
