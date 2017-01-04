/******************************************************************************
 *  Compilation:  javac Permutation.java
 *  Execution:  java Permutation 3 < input.txt
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]); // takes a command-line integer k
        RandomizedQueue<String> randomQ = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            randomQ.enqueue(item);
        }

        
        for (int j = 0; j < k; j++) {
            StdOut.print(randomQ.dequeue() + '\n');
        }

    }
}
