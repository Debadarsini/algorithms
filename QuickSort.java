import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.IntFunction;

import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {
    
    
    private static int partition(Comparable[] a, int lo,int hi) {
        Comparable pivot = a[lo];
        int i = lo;
        int j = hi+1;
        while(i<=j){
            while(isLess(a[++i], pivot))
                if(i==hi) break; 
            
            while(isLess(pivot, a[--j]))
                if(j==lo) break;
            
            if(i>=j)break;
            exchange(a, i, j);
        }
        exchange(a, lo, j);
        return j;
    }
    
    
    private static void sort(Comparable[] a,int lo,int hi) {
        if(hi<=lo) return;
        int j = partition(a, lo, hi);
        sort(a,lo,j-1);
        sort(a,j+1,hi);
       
    }
    
    private static void sort(Comparable[] a){
        StdRandom.shuffle(a);
        sort(a,0,a.length-1);
        
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
        QuickSort.sort(intArray);
        System.out.println(Arrays.toString(intArray));
        
    }
    
    private static <T, U> U[] convertArray(T[] from, Function<T, U> func, IntFunction<U[]> generator) {
        return Arrays.stream(from).map(func).toArray(generator);
    }

}
