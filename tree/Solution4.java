package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Time measure of genrating pair of integers from a list of integers
 * @author debnayak
 *
 */

public class Solution4 {

    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int noData = sc.nextInt();
        int[] set = new int[noData];
        
        
        for( int j=0;j<noData;j++){
            set[j] = sc.nextInt();
        }
        
        //Arrays.sort(set);
        
      //  System.out.println(Arrays.toString(set));
        
        long startTime = System.currentTimeMillis();
        List<String> dataPairs = getPairsOfData(set, noData);
        //System.out.println(dataPairs.toString());
      //  System.out.println(dataPairs.size());
        System.out.println("time ::::: "+(System.currentTimeMillis()-startTime));
    }
    
    static  List<String> getPairsOfData(int[] elements,int length) {
        List<String> sets = new ArrayList<String>();
       // int length = elements.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                sets.add(elements[i] + "," + elements[j]);
            }
        }
        return sets;
    }
}
