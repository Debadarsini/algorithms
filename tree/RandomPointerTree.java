package tree;

public class RandomPointerTree {

    Node clonTree(Node node){
        
        if (node == null) {
            return null;
        }
        
        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
        return node;
        
    }
    
    public static void main(String [] args){
        Node node = new Node<Integer>(1);
        node.left = new Node(2);
        node.right = new Node(3);
        node.left.left = new Node(4);
        node.left.right = new Node(5);
        node.random = node.left.right;
        node.left.left.random = node;
        node.left.right.random = node.right;
    }
}

class Node<T> {
    T data;
    Node<T> left;
    Node<T> right;
    Node<T> random;

    public Node(T data) {
        this.data = data;
        left = right = null;
        random = null;
    }

}
