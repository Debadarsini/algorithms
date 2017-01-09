import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.IntFunction;

/**
 * Compared with left side elements and swapped.
 * Takes around n^2/4 time.
 * If sorted takes linear time to validate. 
 * If array is in desecendant order worst performance : n^2
 * @author debnayak
 *
 */
public class InsertionSort {
    
    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (isLess(a[j], a[j-1])) {
                    exchange(a, j, j-1);
                }else 
                    break;
            }
         //   System.out.println(Arrays.toString(a));
           
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
        InsertionSort.sort(intArray);
        System.out.println(Arrays.toString(intArray));
        
    }
    
    private static <T, U> U[] convertArray(T[] from, Function<T, U> func, IntFunction<U[]> generator) {
        return Arrays.stream(from).map(func).toArray(generator);
    }

}
