package tree;

import java.io.*;
import java.util.*;

/**
 * Kitty  calculation of distance between node implementation 2
 * @author debnayak
 *
 */

public class Solution10 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        long startTime5 = System.currentTimeMillis();

        int noofData = 0;
        boolean swap = false;
        int element = sc.nextInt();
        int child1 = sc.nextInt();
        int[] map1 = new int[n + 1];
    //    LinkedList link = new LinkedList();
      //  link.add(1);
        map1[1] = 0;

        if (element == 1) {
            swap = true;
            map1[child1] = element;
        } else {
            map1[element] = child1;
        }
        while (noofData < n - 2) {
            int parent = sc.nextInt();
            int child = sc.nextInt();
            if (swap) {
                 map1[child] = parent;

            } else {
                map1[parent] = child;

            }
            noofData++;
        }
       
         for (int i = 0; i < q; i++) {

            int noData = sc.nextInt();
            if (noData <= 1) {
                sc.nextInt();
                System.out.println(0);
                // break;
            } else {
                int[] set = new int[noData];
                for (int j = 0; j < noData; j++) {
                    set[j] = sc.nextInt();
                }
                long sumDistance = 0;
               // Arrays.sort(set);
                for(int k=0;k<noData;k++){
                        int l = k+1;
                        while(l<noData){
                            int start = set[k];
                            int end = set[l];
                            long distance = start*findDistance(map1,start,end)*end;
                            sumDistance += distance;
                            l++;
                        }
                }
                System.out.println(sumDistance % (1000000000l + 7));

           
            }

        }
       System.out.println("time taken "+(System.currentTimeMillis()-startTime5));

        // inOrderTraversal(root);

    }

    private static int findDistance(int[] map1, int start, int end) {
        // TODO Auto-generated method stub
        int value1 = map1[start];
        
        int value2 = map1[end];
       // if(value1==value2 && value1 ==1){
         //   return 2;
        //}
        int d=0;
        boolean intersectionFound = false;
        while (true) {
            if (value1>value2){
               d++;
               start = map1[start];
               value1 = map1[start];
               if(start==1){
                   break;
               }
               //continue;
           }else if(value1<value2){
               d++;
               end = map1[end];
               value2 = map1[end];
               if(end==1){
                   break;
               }
             //  continue;
           }else{
               d+=2;
               intersectionFound =true;
               break;
           }
               
       }

       if(intersectionFound){
           return d;
       }return 0;

    }

   
    

    static List<String> getPairsOfData(int[] elements, int length) {
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