package tree;
import java.io.*;
import java.util.*;
/**
 * Swaps nodes at a given a level
 */
public class SwapNodes {
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
        }
    }

    public static void main(String[] args) {
        long l = 10^9;
        System.out.println(l);
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Node root = null;
        if(N!=0){
            root = new Node(1);
        }
        
        int dataNos =0;
        while(dataNos<N*2){
            int nodes = 0;
            nodes = getNoofNodes(nodes,root,height(root));
            int[] elements = new int[2*nodes];
            for(int i =0;i<elements.length;i++){
                elements[i] = sc.nextInt();
                dataNos++;
            }
            insertData(root,elements);
        }
        
        int noOfSwaps = sc.nextInt();
        
        //inorderTraversal(root);
       // System.out.println("");
        for(int j=0;j<noOfSwaps ;j++){
            int level = sc.nextInt();
            swapNodes(root,1,level);
            inorderTraversal(root);
            System.out.println("");
        }
        
    }
    
     private static void insertData(Node root, int[] elements) {
         if(root == null){
             root = new Node(elements[0]);
             return;
         }else{
            Queue<Node> q = new LinkedList();
             q = getQueue(q,root,height(root));
             for(int i=0;i<elements.length;i=i+2){
                 Node parent = q.peek();
                 q.remove();
                 if(elements[i]!=-1){
                     parent.left = new Node(elements[i]);
                 }
                 
                 if(elements[i+1]!=-1){
                     parent.right =new Node( elements[i+1]);
                 }
                 
             }
         }
     
     }
    
     static void printLevelOrder(Node node) {
        if (node == null) {
            return;
        }

        int height = height(node);
        for(int i = 0; i<=height;i++){
            printGivenLevel(node,i);
        }
    }
    
   static Queue getQueue(Queue<Node> q,Node node, int level){
            if(node == null)
                return q;
            if(level ==1){
                q.add(node);
            }
            else if (level >1) {
                q = getQueue(q,node.left, level-1);
                q = getQueue(q,node.right, level-1);
            }
        
        return q;
    }
    
     static int getNoofNodes(int no,Node node, int level){
            if(node == null)
                return no;
            if(level ==1){
               no++;
            }
            else if (level >1) {
               no =  getNoofNodes(no,node.left,  level-1);
               no = getNoofNodes(no, node.right,level-1);
            }
        
        return no;
    }

    private static void printGivenLevel(Node node, int level) {
        // TODO Auto-generated method stub
        if(node == null)
            return;
        if(level ==1)
            System.out.println(node.data);
        else if (level >1) {
            printGivenLevel(node.left, level-1);
            printGivenLevel(node.right, level-1);
        }
    }

    private static int height(Node node) {
        if (node == null) {
            return 0;
        } else {
            int lheight = height(node.left);
            int rheight = height(node.right);
         
            if (lheight > rheight)
                return lheight + 1;
            else
                return rheight + 1;
        }
    }

    
    static void swapNodes(Node node, int level, int start)
    {
             if(node == null)
                 return;
            if(level%start ==0){
                Node temp = node.left;
                node.left = node.right;
                node.right = temp;
            }
        
            swapNodes(node.left,level+1, start);
            swapNodes(node.right,level+1, start);
    }
     
      static void inorderTraversal(Node root){
        if(root!=null){
            inorderTraversal(root.left);
            System.out.print(root.data+" ");
            inorderTraversal(root.right);
        }
    }
}
