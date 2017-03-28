package tree;

import java.io.*;
import java.util.*;

public class Solution1 {
    static class Node{
        int data;
        List<Node> children;
        Node(int data){
            this.data = data;
        }
    }

    public static void main(String[] args) {
      Node root = null;
      Scanner sc = new Scanner(System.in);
      int n = sc.nextInt();
      int q = sc.nextInt();
      int noofData = 0;
     
      while(noofData <n-1){
          int parent = sc.nextInt();
          int child = sc.nextInt();
           Node parentNode  = null;
          if(null == root){
              root = new Node(parent);
              parentNode = root;
          }else{
              parentNode = getParent(root,parent);
          }
          
          if(null == parentNode.children){
              List<Node> children =  new ArrayList<Node>();
              children.add(new Node(child));
              parentNode.children = children;
          }else{
              parentNode.children.add(new Node(child));
          }
          noofData++;
      }
        
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
                    String [] data = dataPairArr[k].split(",");
                    sumDistance+= Integer.parseInt(data[0])*findDistance(Integer.parseInt(data[0]),Integer.parseInt(data[1]),root)*Integer.parseInt(data[1]);
                }
               
                System.out.println(sumDistance%(1000000000l+7));
                
            }
        }
        
      //inOrderTraversal(root);
        
    }
    
    static int findDistance(int start, int end, Node root){
       Queue<Node> queue = new LinkedList();
       findStart(queue,root,start,root);
       int distance=0;
       while(!queue.isEmpty()){
           Node node = queue.poll();
          // distance++;
           Queue<Node> queue1 = new LinkedList();
           findStart(queue1,node,end,root);
           if(!queue1.isEmpty()){
               distance +=queue1.size();
           }
       }
      
       return distance;
    }
    
   

    private static void findStart(Queue<Node> queue, Node root, int start, Node head) {
        // TODO Auto-generated method stub
        while (root != null) {
            if (root.data == start) {
                if (!queue.contains(root)) {
                    queue.add(root);
                }
                break;
            } else if (null != root.children) {
                start = findNode(root,start,queue);
                if (start!=0) {
                    findStart(queue, head, start, head);
            //        break;
                } else {
                    break;
                }

            } else {
                break;
            }
        }
    }
    
    static int findNode(Node currentNode,int start,Queue<Node> queue){
            for(Node node : currentNode.children){
                if(node.data == start){
                    if(!queue.contains(node)){
                        queue.add(node);
                    }
                    start = currentNode.data;
                 //   currentNode = node;
                    return start;
                }
            }
            for(Node node : currentNode.children){
                if(node != null  && node.children != null){
                    return findNode(node,start,queue);
                }else{
                    continue;
                }
            }
      
      return 0;
      
        //return 0;
    }

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
    
    static Node getParent(Node root,int parent ){
        if(root != null){
            if(root.data == parent){
                return root;
            }else if(null != root.children){
                for(Node node : root.children){
                  if(node.data == parent){
                      return node;
                  }else{
                      getParent(node,parent);
                  }
                }
            }
        }
        return root;
    }
    
    static void inOrderTraversal(Node root){
        if(null != root){
            System.out.println(root.data);
            if(null != root.children){
                for(Node node : root.children){
                    inOrderTraversal(node);
                }
              
          }
        }
            
       
    }
    
}