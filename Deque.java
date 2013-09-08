
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    
    private Node sentinel;
    private int  count;
    
    private class Node {
        private Item item;
        private Node prev;
        private Node next;
    }
    
    // construct an empty deque
    public Deque() {
        sentinel = new Node();
        sentinel.item = null;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        count = 0;
    }
    
    // is the deque empty?
    public boolean isEmpty() {
        return count == 0;
    }
    
    // return the number of items on the deque
    public int size() {
        return count;
    }
    
    // insert the item at the front
    public void addFirst(Item item) {    
        if (item == null)
            throw new java.lang.NullPointerException("can't add a null item");
        
        Node front = new Node();
        
        front.item = item;
        front.prev = sentinel;
        front.next = sentinel.next;
        
        sentinel.next.prev = front;
        sentinel.next = front;
        count++;
    }
       
    // insert the item at the end
    public void addLast(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException("can't add a null item");
        
        Node last = new Node();
        
        last.item = item;
        last.prev = sentinel.prev;
        last.next = sentinel;
        
        sentinel.prev.next = last;
        sentinel.prev = last;
        count++;
    }
    
    // delete and return the item at the front
    public Item removeFirst() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Deque empty");
        
        Node first = sentinel.next;
        Item fronItem = first.item;
        
        sentinel.next = first.next;
        first.next.prev = sentinel;
        
        first = null;
        count--;
        return fronItem;
    }
    
    // delete and return the item at the end
    public Item removeLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Deque empty");
        
        Node last = sentinel.prev;
        Item endItem = last.item;
        
        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        
        last = null;
        count--;
        return endItem;
    }
    
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() { return new ListIterator(); }
    
    private class ListIterator implements Iterator<Item> {
        private Node current = sentinel.next;
        
        public boolean hasNext() { return current != sentinel; }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next() {
            if (current == sentinel)
                throw new java.util.NoSuchElementException();
            Item item = current.item;
            current   = current.next;
            return item;
        }
    }
    
}
