

public class Queue<T> {
    private Node first;
    
    private Node last;
    
    
    private class Node{
        T item;
        Node next;
    }
    
    public boolean isEmpty(){
        return first == null;
    }
    public void enqueue(T item){
       
        Node old = last;
        last = new Node();
        last.item=item;
        last.next=null;
        if(isEmpty()){
            first = last;
        }
        old.next=last;
    }
    
    public T dequeue(){
        T item = first.item;
       // Node newFirst = first.next;
        first = first.next;
        if(isEmpty()) last = null;
        return item;
    }
}
