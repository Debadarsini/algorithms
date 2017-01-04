/******************************************************************************
 *  Compilation:  javac RandomizedQueue.java
 *  Execution:  java RandomizedQueue < input.txt
 *
 ******************************************************************************/

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 
 * @author debnayak
 *         <p>
 *         A randomized queue is similar to a stack or queue, except that the
 *         item removed is chosen uniformly at random from items in the data
 *         structure
 *         </p>
 *
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;

    private int size = 0;

    /**
     * intialize the array
     */
    public RandomizedQueue() {
        items = (Item[]) new Object[2];
    }

    /**
     * returns true if arry is empty
     * 
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
        // is the queue empty?
    }

    /**
     * returns size of the array
     * 
     * @return
     */
    public int size() {
        return size;
        // return the number of items on the queue
    }

    /**
     * Adds one item
     * 
     * @param item
     */
    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException(" Item is null");
        if (size == items.length) {
            resize(2 * items.length);
        }
        items[size++] = item;
    }

    /**
     * Resizes the array
     * 
     * @param i
     */
    private void resize(int i) {
        Item[] copy = (Item[]) new Object[i];
        for (int j = 0; j < size; j++) {
            copy[j] = items[j];
        }
        items = copy;
    }

    /**
     * Deques a random item
     * 
     * @return
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException(" Array empty");
        }
        int loc = StdRandom.uniform(size);
        Item item = items[loc];
        if (size - 1 != loc) 
            items[loc] = items[size-1];
        items[--size] = null;
        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    /**
     * Returns random sample item
     * 
     * @return
     */
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException(" Array empty");
        }
        return items[StdRandom.uniform(size)];
        // return (but do not remove) a random item
    }

    /**
     * Returns an iterator
     */
    public Iterator<Item> iterator() {
        // return an independent iterator over items in random order
        return new ListIterator();
    }

    /**
     * List iterator class
     * 
     * @author debnayak
     *
     */
    private class ListIterator implements Iterator<Item> {
        private Item[] current;
        
        private int length = size;
        
        private int index = 0;

        ListIterator(){
            this.current = (Item[]) new Object[length];
            for (int j = 0; j < length; j++) {
                current[j] = items[j];
            }
            StdRandom.shuffle(current);  
        }
        
        @Override
        public boolean hasNext() {
            return length != index;
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
            return current[index++];
        }

    }

    public static void main(String[] args) {
        RandomizedQueue<String> randomQueue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            randomQueue.enqueue(item);
            randomQueue.dequeue();
            randomQueue.enqueue(item);
            randomQueue.dequeue();
            randomQueue.enqueue(item);
        }

        int count = randomQueue.size();
        System.out.println(" count " + count);

        Iterator<String> itr1 = randomQueue.iterator();
        while (itr1.hasNext()) {
            System.out.println(" itration1 " + itr1.next());
        }

        System.out.println(" PEACE ");
        Iterator<String> itr = randomQueue.iterator();
        while (itr.hasNext()) {
            System.out.println(" itration " + itr.next());
        }

        for (int i = 0; i < count; i++) {
            System.out.println(randomQueue.dequeue());
        }
        System.out.println(randomQueue.size());
        System.out.println(Arrays.toString(randomQueue.items));

    }
}

