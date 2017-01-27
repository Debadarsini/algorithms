/******************************************************************************
 *  Compilation:  javac Board.java
 *  Execution:  java Board
 *
 ******************************************************************************/

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class Board {

    private int[][] blocks;

    private int size;

    private int zeroRow;

    private int zeroCol;

    public Board(int[][] blocks) {
        if (blocks == null)
            throw new NullPointerException("null input");
        this.size = blocks.length;
        this.blocks = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.blocks[i][j] = blocks[i][j];
                if (blocks[i][j] == 0) {
                    zeroRow = i;
                    zeroCol = j;
                }
            }
        }

        // construct a board from an n-by-n array of blocks
    }

    // (where blocks[i][j] = block in row i, column j)
    public int dimension() {
        // board dimension n
        return blocks.length;
    }

    public int hamming() {
        int hamming = 0;
        // number of blocks out of place
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (blocks[i][j] != 0 && blocks[i][j] != xyToID(i, j))
                    hamming++;
            }
        }
        return hamming;
    }

    public int manhattan() {
        // sum of Manhattan distances between blocks and goal
        int tile;
        int manhattan = 0;
        int expectedRow;
        int expectedColumn;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tile = blocks[i][j];
                if (tile == 0 || tile == xyToID(i, j))
                    continue;
                expectedRow = (tile-1) / size;
                expectedColumn = (tile-1) - expectedRow*size;
                manhattan += Math.abs(i - expectedRow) + Math.abs(j - expectedColumn);
            }
        }
        return manhattan;
    }

    public boolean isGoal() { // is this board the goal board?
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == size - 1 && j == size - 1)
                    continue;
                if (blocks[i][j] != xyToID(i, j))
                    return false;
            }
        }
        return true;
    }

    public Board twin() {
        // a board that is obtained by exchanging any pair of blocks
        int[][] twin = deepCopy(blocks);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                
                if ( twin[i][j] != 0) {
                    int  temp = twin[i][j];
                    if(i==size-1 && j == size-1){
                        if(twin[0][0] !=0){
                            twin[i][j] = twin[0][0];
                            twin[0][0] = temp;
                        }else{
                            twin[i][j] = twin[0][1];
                            twin[0][1] = temp;
                        }
                    }else if(j<size-1 && twin[i][j + 1] != 0){
                        twin[i][j] = twin[i][j + 1];
                        twin[i][j + 1] = temp;
                    }
                    return new Board(twin);
                }
            }
        }
        return new Board(twin);
    }

    @Override
    public boolean equals(Object y) {
        if (this == y)
            return true;
        if (y == null)
            return false;
        if (getClass() != y.getClass())
            return false;
        Board other = (Board) y;
        if (!Arrays.deepEquals(blocks, other.blocks))
            return false;
        if (size != other.size)
            return false;
        return true;
    }

    public Iterable<Board> neighbors() {
        Stack<Board> stack = new Stack();
        // all neighboring boards
        if (zeroRow > 0) {
            Board boardUP = new Board(swap(blocks, -1, 0));
            stack.push(boardUP);
        }

        if (zeroRow < size - 1) {
            Board boardDown = new Board(swap(blocks, 1, 0));
            stack.push(boardDown);
        }

        if (zeroCol > 0) {
            Board boardLeft = new Board(swap(blocks, 0, -1));
            stack.push(boardLeft);
        }

        if (zeroCol < size - 1) {
            Board boardRight = new Board(swap(blocks, 0, 1));
            stack.push(boardRight);
        }

        return stack;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(size + "\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    private int xyToID(int row, int col) {
        return (row) * size + (col) + 1;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board board = new Board(blocks);
        System.out.println(board.hamming());
        System.out.println(board.manhattan());
        System.out.println(board.neighbors());
        System.out.println("twin "+board.twin());
        // unit tests (not graded)s
    }

    private int[][] deepCopy(int[][] array) {
        int[][] copy = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copy[i][j] = array[i][j];
            }
        }
        return copy;
    }

    private int[][] swap(int[][] board, int rowOffset, int colOffset) {
        int[][] tempBoard = deepCopy(board);
        tempBoard[zeroRow][zeroCol] = blocks[zeroRow + rowOffset][zeroCol + colOffset];
        tempBoard[zeroRow + rowOffset][zeroCol + colOffset] = 0;
        return tempBoard;
    }
}
