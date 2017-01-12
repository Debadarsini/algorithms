import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.IntFunction;

public class MergerButtomUp {

    
    private static void merge(Comparable[] a, Comparable[] aux,int lo,int mid,int hi) {
        
        //Why??? Any alternative
        for(int k = lo;k<=hi;k++){
            aux[k] = a[k];
        }
        
        int i = lo, j = mid+1;
        
        for(int k=lo;k<=hi;k++){
            if(i>mid) a[k]=aux[j++];
            else if(j>hi) a[k]=aux[i++];
            else if (isLess(aux[j],aux[i])) a[k]=aux[j++];
            else  a[k]=aux[i++];
        }

    }
    
    
    private static void sort(Comparable[] a) {
        int n = a.length;
        Comparable [] aux = new Comparable[n];
        
        for(int i =1 ; i<n;i=2*i){
          for ( int lo =0;lo<n-i;lo +=2*i){
              merge(a, aux, lo, lo+i-1, Math.min(lo+2*i-1, n-1));
          }
        }
        
    
    }
    
    private static boolean isLess(Comparable a , Comparable b){
        return a.compareTo(b)<0;
    }
    
    
    private static void exchange(Comparable[] array, int i , int j){
        Comparable temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }
    
    public static void main(String [] args){
        Scanner scanner =  new Scanner(System.in);
        System.out.println(" enter user input ");
        
        String[] stringInput = scanner.next().split(",");
     
        Integer[] intArray = convertArray(stringInput, Integer::parseInt, Integer[]::new);
        MergerButtomUp.sort(intArray);
        System.out.println(Arrays.toString(intArray));
        
    }
    
    private static <T, U> U[] convertArray(T[] from, Function<T, U> func, IntFunction<U[]> generator) {
        return Arrays.stream(from).map(func).toArray(generator);
    }

}
