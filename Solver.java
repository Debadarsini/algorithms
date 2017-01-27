/******************************************************************************
 *  Compilation:  javac Solver.java
 *  Execution:  java Solver
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private class Node implements Comparable<Node> {
        private Board board;

        private int moves;

        private Node prev;

        public Node(Board b, int m, Node p) {
            board = b;
            moves = m;
            prev = p;
        }

        public int compareTo(Node o) {
            int c = (board.manhattan() + moves) - (o.board.manhattan() + o.moves);
            if (c == 0) {
                return board.manhattan() - o.board.manhattan();
            }
            return c;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Node other = (Node) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (board == null) {
                if (other.board != null)
                    return false;
            } 
            else if (!board.equals(other.board))
                return false;
            return true;
        }

        private Solver getOuterType() {
            return Solver.this;
        }
    }

    private Stack<Board> solve;

    private boolean solvable;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null)
            throw new NullPointerException(" input is null");
        solve = new Stack<Board>();
        solvable = true;

        MinPQ<Node> pq = new MinPQ<Node>();
        MinPQ<Node> pqtwin = new MinPQ<Node>();

        Board contrast = initial.twin();

        Node iniNode = new Node(initial, 0, null);
        Node twinNode = new Node(contrast, 0, null);
        pq.insert(iniNode);
        pqtwin.insert(twinNode);

        Node borig = null;
        Node btwin = null;

        while (true) {
            borig = pq.delMin();
            btwin = pqtwin.delMin();

            if (borig.board.isGoal()) {
                while (borig != null) {
                    solve.push(borig.board);
                    borig = borig.prev;
                }
                solvable = true;
                break;
            }

            if (btwin.board.isGoal()) {
                solvable = false;
                break;
            }

            for (Board b : borig.board.neighbors()) {
                Node no = new Node(b, borig.moves + 1, borig);
                if (borig.prev == null || !borig.prev.equals(no)) {
                    pq.insert(no);
                }
            }

            for (Board twiniter : btwin.board.neighbors()) {
                Node no = new Node(twiniter, btwin.moves + 1, btwin);
                if (btwin.prev == null || !btwin.prev.equals(no)) {
                    pqtwin.insert(no);
                }
            }
        }

    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if no solution
    public int moves() {
        if (solvable) {
            return solve.size() - 1;
        }

        return -1;
    }

    // sequence of boards in a shortest solution; null if no solution
    public Iterable<Board> solution() {
        if (solvable) {
            return solve;
        }

        return null;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}