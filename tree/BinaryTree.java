package tree;

import java.awt.HeadlessException;
import java.util.Stack;

public class BinaryTree {

    void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }

    void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.data);

    }

    void printLevelOrder(Node node) {
        if (node == null) {
            return;
        }

        int height = height(node);
        for(int i = 0; i<=height;i++){
            printGivenLevel(node,i);
        }
    }

    private void printGivenLevel(Node node, int level) {
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
         
            return 1+Math.max(lheight, rheight);
        }
    }
    
    public static void printInOrderWithoutRecurrsion(Node node){
        if(node == null){
            return;
        }
        
        Stack<Node> stack = new Stack();
        pushPopNode(node, stack);
    }

    private static void pushPopNode(Node node, Stack<Node> stack) {
        while(node!=null){
            stack.push(node);
            node = node.left;
        }
        
        while(!stack.isEmpty()){
            Node currentNode = stack.pop();
            System.out.println(currentNode.data);
            if(currentNode.right!=null){
                pushPopNode(currentNode.right, stack);
            }
        }
    }
    
    
    public static int diameter(Node root){
        if(root == null){
            return 0;
        }
        
        int lheight =height(root.left);
        int rheight = height(root.right);
        
        int ldia = diameter(root.left);
        
        int rdia = diameter(root.right);
        
        System.out.println("lheight   "+lheight+"rheight "+rheight+"ldia "+ldia+"rdia "+rdia);
        
        return Math.max(lheight+rheight+1, Math.max(ldia,rdia));
    }

    public static void main(String[] args) {
        BinaryTree b = new BinaryTree();
        Node<Integer> node = new Node<Integer>(1);
        node.left = new Node<Integer>(2);
        
        node.right = new Node<Integer>(3);
        node.left.left = new Node<Integer>(4);
        node.left.left.left = new Node<Integer>(7);
        node.left.left.right = new Node<Integer>(8);
        node.left.right = new Node<Integer>(5);
        node.left.right.left = new Node<Integer>(6);
        node.left.right.left.left = new Node<Integer>(10);
    //    b.inOrder(node);
        System.out.println("=========================");
      //  b.printInOrderWithoutRecurrsion(node);
        System.out.println(b.diameter(node));
    }
}

class Node<T> {
    T data;
    Node<T> left;
    Node<T> right;

    public Node(T data) {
        this.data = data;
        left = right = null;
    }

}
