

import java.util.Iterator;

public class LinkedListStack<T> implements Iterable<T> {

    private Node first = null;
    
    private class Node{
        T item;
        Node next;
    }
    
    public void push(T item){
        Node old = first;
        first = new Node();
        first.item=item;
        first.next=old;
    }
    
    public T pop(){
        T item = first.item;
        first = first.next;
        return item;
    }
    
    public boolean isEmpty(){
        return first==null;
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return new StackIterator();
    }
    
    private class StackIterator implements Iterator<T>{
        private Node current = first;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
           T item = current.item;
           current = current.next;
           return item;
        }
        
    }
    
}
