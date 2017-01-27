import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.IntFunction;

public class MaxPQ <Key extends Comparable<Key>>{
    
    private Key[] a;
    public Key[] getA() {
        return a;
    }

    private int n;

    public MaxPQ(int capacity){
        a = (Key[]) new Comparable[capacity+1];
    }
    
    public void insert(Key element){
        a[++n] = element;
        swim(n);
    }
    
    
    private void swim(int k) {
       while(k>1 && isLess(k/2, k)){
           exchange(k, k/2);
           k = k/2;
       }
    }

    public Key removeMax(){
        Key element = a[1];
        exchange(1, n);
        n--;
        sink(1);
        a[n+1] = null;
        return element;
        
    }
    
    
    private void sink(int i) {
        int child ;
        while(2*i<n){
            child = 2*i;
            if(child<n&&isLess(child, child+1)){
                child++;
            }
            
            if(!isLess(i, child))break;
            exchange(i, child);
            i = child;
        }
    }

    private  boolean isLess(int i , int j){
        return a[i].compareTo(a[j])<0;
    }
    
    
    private  void exchange( int i , int j){
        Key temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }
    
    public static void main(String [] args){
        Scanner scanner =  new Scanner(System.in);
        System.out.println(" enter user input ");
        
        String[] stringInput = scanner.next().split(",");
     
        Integer[] intArray = convertArray(stringInput, Integer::parseInt, Integer[]::new);
        MaxPQ pq = new MaxPQ<>(intArray.length);
        for(int i = 0;i<intArray.length;i++){
            pq.insert(intArray[i]);
        }
        
        System.out.println(Arrays.toString(pq.a));
        int j = 0;
        while(j<intArray.length){
            System.out.println(pq.removeMax());
            j++;
        }
        
        
    }
    
    private static <T, U> U[] convertArray(T[] from, Function<T, U> func, IntFunction<U[]> generator) {
        return Arrays.stream(from).map(func).toArray(generator);
    }
}
