package tree;

import java.io.*;
import java.util.*;
/**
 * Kitty  calculation of distance between node implementation 1
 * @author debnayak
 *
 */
public class Solution3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        long startTime5 = System.currentTimeMillis();

        int noofData = 0;
        boolean swap = false;
        int element = sc.nextInt();
        int child1 = sc.nextInt();
        LinkedList<Integer>[] map1 = new LinkedList[n + 1];
        LinkedList link = new LinkedList();
        link.add(1);
        map1[1] = link;

        if (element == 1) {
            swap = true;
            LinkedList<Integer> stchildLink = new LinkedList<Integer>();
            stchildLink.add(child1);
            stchildLink.addAll(map1[element]);
            map1[child1] = stchildLink;
        } else {
            LinkedList<Integer> stparentLink = new LinkedList<Integer>();
            stparentLink.add(element);
            stparentLink.addAll(map1[child1]);
            map1[element] = stparentLink;
        }
        while (noofData < n - 2) {
            int parent = sc.nextInt();
            int child = sc.nextInt();
            if (swap) {
                LinkedList<Integer> childLink = new LinkedList<Integer>();
                childLink.add(child);
                childLink.addAll(map1[parent]);
                map1[child] = childLink;

            } else {
                LinkedList<Integer> parentLink = new LinkedList<Integer>();
                parentLink.add(parent);
                parentLink.addAll(map1[child]);
                map1[parent] = parentLink;

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
                Arrays.sort(set);
                for(int k=0;k<noData;k++){
                        int l = k+1;
                        while(l<noData){
                            int start = set[k];
                            int end = set[l];
                            LinkedList<Integer> queue = map1[start];
                            LinkedList<Integer> queue1 = map1[end];
                          //  long distance = findDistance(queue, queue1);
                          //  long distance = distance(queue.size(),queue1.size());
                             long distance = start*findDistance(queue, queue1)*end;
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

    /*static int findDistance(LinkedList<Integer> queue, int start, LinkedList<Integer> queue1, int end) {

        int commonelement = 0;
        int distance = 0;
        Iterator<Integer> i = queue1.listIterator();
        while (i.hasNext()) {
            commonelement = i.next();
            if (queue.contains(commonelement)) {
                break;
            } else{
                distance++;
            }
        }
        if (commonelement != 0) {
            Iterator<Integer> j = queue.listIterator();
            while (j.hasNext() && commonelement != j.next()) {
                distance++;
            }
        } else {
            distance = 0;
        }

        return distance;

    }*/
    
    static long distance( int c1, int c2){
        long d=0;
        if (c1 > c2) {
            d = c1 - c2;
            d =d+2*(c2-1);
        } else {
            d = c2 - c1;
            d =d+2*(c1-1);
        }
        return d;
       
        
    }
    static int findDistance(LinkedList<Integer> queue, LinkedList<Integer> queue1) {
        //int c1 = queue.size();
        //int c2 = queue1.size();
        int i;
        boolean intersectionFound=false;;
        Iterator<Integer> itr1 = queue.listIterator();
        Iterator<Integer> itr2 = queue1.listIterator();
       // for (i = 0; i < d; i++) {
         //   if (queue == null) {
           //     return -1;
            //}
            //itr1.next();
       // }
        int d =0;
        int value1 = itr1.next();
        int value2 = itr2.next();
   
        while (true) {
             if (value1>value2){
                d++;
                if(itr1.hasNext()){
                    value1=  itr1.next();
                }else{
                    break;
                }
                //continue;
            }else if(value1<value2){
                d++;
                if(itr2.hasNext()){
                    value2 =itr2.next();
                }else{
                    break;
                }
              //  continue;
            }else{
                d++;
                intersectionFound =true;
                break;
            }
                
        }
 
        if(intersectionFound){
            return d -1;
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