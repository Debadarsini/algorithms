

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LinkedListStack<T> implements Iterable<T> {

    private Node first = null;
    
    private int max;
    
    private class Node{
        T item;
        Node next;
    }
    
    public void push(T item){
        if(item ==null) throw new NullPointerException(" element is null");
        if(item instanceof Integer && (Integer)item>max){
            max = (Integer)item;
        }
        Node old = first;
        first = new Node();
        first.item=item;
        first.next=old;
    }
    
    public T pop(){
        if(isEmpty()){
            throw new NoSuchElementException(" elment not found");
        }
        T item = first.item;
        first = first.next;
        return item;
    }
    
    //Max operation
    public int max(){
        return max;
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
    
    public static void main(String [] args){
        Scanner scanner =  new Scanner(System.in);
        System.out.println(" enter user input");
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        int count =0;
        while(scanner.hasNext()){
            stack.push(scanner.nextInt());
            if(count++==10)
                break;
        }
        System.out.println(stack.max());
    }
}
