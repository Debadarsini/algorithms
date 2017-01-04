/******************************************************************************
 *  Compilation:  javac Deque.java
 *  Execution:  java Deque < input.txt
 *
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.In;

/**
 * 
 * @author Debadarsini Nayak
 *         <p>
 *         A double-ended queue or deque (pronounced "deck") is a generalization
 *         of a stack and a queue that supports adding and removing items from
 *         either the front or the back of the data structure. Create a generic
 *         data type Deque that implements the following API:
 *         </p>
 *
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {
    // first node
    private Node first;

    // Last node
    private Node last;

    private int size;

    // Node represents
    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        Node(Node prev, Item element, Node next) {
            this.prev = prev;
            this.item = element;
            this.next = next;
        }
    }

    public Deque() { // construct an empty deque
        first = null;
    }

    /**
     * returns true if deck empty
     * 
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * returns the size of deck
     * 
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Adds element at the start of the deck
     * 
     * @param item
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException(" item is null");
        }
        Node old = first;
        first = new Node(null, item, old);
        if (old == null) 
            last = first;
        else
            old.prev = first;
        size++;
    }

    /**
     * Adds element at the end of deck
     * 
     * @param item
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException(" item is null");
        }

        Node old = last;
        last = new Node(old, item, null);
        if (old == null)
            first = last;
        else
            old.next = last;
        size++;
    }

    /**
     * Remove element from start of the deck
     * 
     * @return
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException(" No element prsent in deck");
        }
        Item item = first.item;
        Node next = first.next;
        first.item = null;
        first.next = null;
        first = next;
        // Node newFirst = first.next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        return item;
    }

    /**
     * Removes element from deck
     * 
     * @return
     */
    public Item removeLast() {
        if (last == null) {
            throw new NoSuchElementException(" No element prsent in deck");
        }
        Item item = last.item;
        last = last.prev;
        if (last == null)
            first = null;
        else
            last.next = null;
        size--;
        return item;
    }

    /**
     * Returns an iterator instance
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    /**
     * List iterator class
     * 
     * @author debnayak
     *
     */
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException(" method not supported");
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException(" No element prsent in deck");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    public static void main(String[] args) {
        // String fileName = "/Users/debnayak/Downloads/queues/tale.txt";
        In in = new In(args[0]);
        String[] atrings = in.readAllLines();
        Deque<String> deque = new Deque<String>();
        int count = 0;
        for (String str : atrings) {
            deque.addLast(str);
            count++;
            if (count == 5 || count == 10 || count == 15) {
                deque.removeLast();
            }

            if (count == 3 || count == 6 || count == 9) {
                deque.removeFirst();
            }
        }

        count = deque.size;
        System.out.println(" count " + deque.size);

        for (int i = 0; i < count; i++) {
            deque.removeFirst();
        }
        System.out.println(deque.size());
    }

}
