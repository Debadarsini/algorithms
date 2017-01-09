import java.util.Arrays;
import java.util.Scanner;

public class FindMinDifference {

    public int[] sortArray(int[] input){
        return input;
    }
    
    public int findMinDiff(int[] input, int noOfElement,int startIndex, int min){
        int newMin =input[startIndex+noOfElement-1] - input[startIndex];
        if(newMin<min){
             min = newMin;
        }
        
        return findMinDiff(input, noOfElement, startIndex, min);
    }
    
    public static void main(String [] args){
        FindMinDifference minDiff = new FindMinDifference();
        
        Scanner scanner =  new Scanner(System.in);
        System.out.println(" enter user input ");
        
        String[] stringInput = scanner.next().split(",");
     
        int [] array = Arrays.stream(stringInput).mapToInt(Integer::parseInt).toArray();
        
        System.out.println(" enter no of elements ");
        
        System.out.println(minDiff.findMinDiff(array, scanner.nextInt(), 0, -1));
        scanner.close();
    }
    
}
