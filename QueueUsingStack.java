import java.util.Scanner;

public class QueueUsingStack<T>{
    
    private LinkedListStack<T> first;
    
    private LinkedListStack<T> second;
    
    QueueUsingStack(){
        first = new LinkedListStack<T>();
        second = new LinkedListStack<T>();
    }
    
    public void enqueue(T item){
        first.push(item);
    }
    
    public T dequeue(){
        while(!first.isEmpty()){
            second.push(first.pop());
        }
        return second.pop();
    }
    
    public boolean isEmpty(){
        return second == null;
    }
    
    public static void main(String [] args){
        Scanner scanner =  new Scanner(System.in);
        System.out.println(" enter user input");
        QueueUsingStack<String> queue = new QueueUsingStack<>();
        int count =0;
        while(scanner.hasNext()){
            queue.enqueue(scanner.next());
            if(count++==10)
                break;
        }
        for(int i =0;i<count;i++){
            System.out.println(queue.dequeue());
        }
    }
}
