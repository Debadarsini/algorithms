import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.IntFunction;

/**
 * Same as insertion sort.
 * 
 * Only swap happens for a particular index.
 * 
 * Index is calculated using 3*h+1 formula.
 * 
 * It starts from higher index. 
 * Every iteration h reduces by h/3.
 * 
 * It is h sort. Instead of elements being swapped with previous position , 
 * it swapped with h position.
 * 
 * Worst case - n^3/2.
 * 
 * Practically it is very less.Linearthmic - i.e. nlogn
 * 
 * Used in embeded systems.. less code...
 * @author debnayak
 *
 */
public class ShellSort {
    
    public static void sort(Comparable[] a) {
        int n = a.length;
        
        int  h = 1;
        
        //Find out h
        
        while(h <n/3) 
            h = 3*h+1;
        
        while(h>=1){
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h; j-=h) {
                    if (isLess(a[j], a[j-h])) {
                        exchange(a, j, j-h);
                    }else 
                        break;
                }
                System.out.println(Arrays.toString(a));
               
            }
            h = h/3;
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
        ShellSort.sort(intArray);
        System.out.println(Arrays.toString(intArray));
        
    }
    
    private static <T, U> U[] convertArray(T[] from, Function<T, U> func, IntFunction<U[]> generator) {
        return Arrays.stream(from).map(func).toArray(generator);
    }

}
