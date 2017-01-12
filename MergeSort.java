import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.IntFunction;

/**
 * Merge sort :
 * 
 * Divide array into two halves.
 * 
 * Compare 1st element from 1st half with 1st element from end half, 
 * whichever is less copy it to the start of the output array.
 * 
 * If 1st half exhausted(no more elements in 1st half to compare)
 *  copy the remaining 2nd half elements to output array.
 *  
 * If 2nd half exhausted copy remaining elements
 *  from 1st half to output array.
 * 
 * Repeat this two sort every subset and then merge.
 * 
 * @author debnayak
 *
 */

public class MergeSort {
    
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
    
    
    private static void sort(Comparable[] a, Comparable[] aux,int lo,int hi) {
        if(hi<=lo) return;
        int mid = lo+(hi-lo)/2;
        sort(a,aux,lo,mid);
        sort(a,aux,mid+1,hi);
        if(!isLess(a[mid+1],a[mid])) return;
        merge(a, aux, lo, mid, hi);
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
        MergeSort.sort(intArray,new Comparable[intArray.length],0,intArray.length-1);
        System.out.println(Arrays.toString(intArray));
        
    }
    
    private static <T, U> U[] convertArray(T[] from, Function<T, U> func, IntFunction<U[]> generator) {
        return Arrays.stream(from).map(func).toArray(generator);
    }


}
