import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.IntFunction;

public class HeapSort {
    
    
    private  boolean isLess(Comparable a , Comparable b){
        return a.compareTo(b)<0;
    }
    
    
    private  void exchange( Comparable[] a,int i , int j){
        Comparable temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }
    private void sink(int i,int n, Comparable[] array) {
        int child ;
        while(2*i<n){
            child = 2*i;
            if(child<n-1&&isLess(array[child], array[child+1])){
                child++;
            }
            
            if(!isLess(array[i], array[child]))break;
            exchange(array,i, child);
            i = child;
        }
    }
    
    public Comparable[] sort(Comparable[] input){
        Comparable[] output = new Comparable[input.length];
        for(int j=0;j<input.length;j++){
            output[j]=input[j];
        }
        int length = input.length; 
        for(int k= length/2;k>=0;k--){
            sink(k,length,output);
        }
        return output;
    }
    
    
    public Comparable[] sortDown(Comparable[] input){
        Comparable[] output = new Comparable[input.length];
        for(int j=0;j<input.length;j++){
            output[j]=input[j];
        }
        int length = output.length-1; 
        while(length >0){
            exchange(output, 0, length--);
            sink(0, length, output);
        }
        
        return output;
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
        Comparable[] heap = pq.getA();
        
        Comparable[] input = new Comparable[intArray.length];
        for(int j=1;j<heap.length;j++){
            input[j-1]=heap[j];
        }
        
        HeapSort sort = new HeapSort();
        System.out.println(Arrays.toString(sort.sort(input)));
        System.out.println(Arrays.toString(sort.sortDown(input)));
         
    }
    
    private static <T, U> U[] convertArray(T[] from, Function<T, U> func, IntFunction<U[]> generator) {
        return Arrays.stream(from).map(func).toArray(generator);
    }
}
