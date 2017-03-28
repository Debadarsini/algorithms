package tree;

import java.io.*;
import java.util.*;

/**
 * Kitty calculator using BFS
 * @author debnayak
 *
 */

public class Solution2 {
   

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
      Scanner sc = new Scanner(System.in);
      
      
      int n = sc.nextInt();
      int q = sc.nextInt();
      int noofData = 0;
      LinkedList<Integer> adj[] = new LinkedList[n+1] ;
    //  for (int i=0; i<n+1; ++i)
      //    adj[i] = new LinkedList();
     
      boolean swap = false;
    //  Map<Integer,Integer> map1 = new HashMap<Integer,Integer>();
    //  List<String> pairs = new ArrayList<String>();
      while(noofData<n-1){
          int parent = sc.nextInt();
          int child = sc.nextInt();
         // if(pairs.contains(parent+","+child) || pairs.contains(child+","+parent)){
           //   continue;
          //}
      //    if(noofData==0&&child==1){
        //      swap = true;
          //}
          //if(swap){
            //  int temp = child;
              //child = parent;
              //parent = temp;
          //}
          if(adj[child]==null){
              adj[child] = new LinkedList();
          }
          adj[child].add(parent);
          
          if(adj[parent]==null){
              adj[parent] = new LinkedList();
          }
          adj[parent].add(child);
       //   pairs.add(parent+","+child);
          noofData++;
      }
      System.out.println("time taken1 "+(System.currentTimeMillis()-startTime));
      
      Map<String,Integer> map = new HashMap<String,Integer>();
        for(int i =0 ;i<q;i++){
            int noData = sc.nextInt();
            if(noData<=1){
                sc.nextInt();
                System.out.println(0);
               // break;
            }else{
                int [] set = new int[noData];
                for(int j =0 ; j<noData;j++){
                    set[j] = sc.nextInt();
                }
                Set<String> dataPairs = getPairsOfData(set);
                int dataPairsLength = dataPairs.size();
                String [] dataPairArr = new String[dataPairsLength];
                dataPairs.toArray(dataPairArr);
              
                long sumDistance=0;
                for(int k=0;k<dataPairsLength;k++){
                    Integer distance = map.get(dataPairArr[k]);
                    String [] data = dataPairArr[k].split(",");
                    if(distance==null){
                     distance = map.get(data[1]+","+data[0]);
                    }
                    if(distance==null){
                       distance = BFS(Integer.parseInt(data[0]),Integer.parseInt(data[1]),n,adj);
                       map.put(dataPairArr[k], distance);
                    }
                    sumDistance+= Integer.parseInt(data[0])*
                            distance*
                            Integer.parseInt(data[1]);
                }
                System.out.println(sumDistance%(1000000000l+7));
                
            }
        }
        
        
      //inOrderTraversal(root);
        System.out.println("time taken4 "+(System.currentTimeMillis()-startTime));
               
    }
    
    
    static  int BFS(int s,int e,int length,LinkedList<Integer> adj[] )
    {
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[length+1];
 
        // Create a queue for BFS
        Queue<Integer> queue = new LinkedList<Integer>();
       
        // Mark the current node as visited and enqueue it
        visited[s]=true;
        queue.add(s);
       boolean nodeFound=false;
       int distance =0;
        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
           // System.out.print(s+" ");
 
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext())
            {
                
                int n = i.next();
                
                if (!visited[n])
                {
                   
                    if(n==e){
                        nodeFound =true;
                        break;
                    }
                    distance++;
                    visited[n] = true;
                    queue.add(n);
                   
                }
            }
            if(nodeFound){
                break;
            }
            
        }
        if(nodeFound){
            return distance;
        }
        return 0;
    }
    /*static int findDistance(int start, int end,LinkedList<Integer> root[],int length){
       Queue<Integer> queue = BFS(start,length,root);
       Queue<Integer> queue1 =  BFS(end,length,root);
       int commonelement = 0;
       int distance = 0;
       while(!queue1.isEmpty()){
            commonelement = queue1.poll();
           if(queue.contains(commonelement)){
               break;
           }else{
               distance++;
           }
       }
      if(commonelement !=0){ 
           while(queue.peek()!= null && commonelement!=queue.poll()){
               distance++;
           }
      }else{
          distance=0;
      }
       
       return distance;
       
    }*/
    
   

    /*private static void findStart(Queue<Integer> queue, int[] root, 
            int start) {
        // TODO Auto-generated method stub
        while(root[start] !=0){
            queue.add(start);
            start = root[start];
        }
    }*/
    
   static Set<String> getPairsOfData(int [] elements){
        Set<String> sets = new HashSet<String>();
        int length = elements.length;
        for(int i = 0 ; i < length; i ++){
          for(int j = i+1 ; j < length; j ++){
            sets.add(elements[i]+","+elements[j]);    
          }
        }
        return sets;
    }
    
    
    
}