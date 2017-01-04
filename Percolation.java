/******************************************************************************
 *  Compilation:  javac Percolation.java
 *  Execution:  java Percolation
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Creates a grid of size n*n. Initializes all values to 0. 0 - Blocked Site 1 -
 * Open Site
 * <p>
 * Creates an instance of WeightedQuickUnionUF of n*n+2 size.
 * 
 * Two extra entries are for virtual lower root and virtual upper root.
 * 
 * Provides functionality to open and connect sites using WeightedQuickUnionUF.
 * 
 * Also decides whether the block is ready for percolation or not.
 * </p>
 * 
 * @author Debadarsini Nayak
 *
 */
public class Percolation {
    private int[][] sites;
    private int size;
    private int virtualLow;
    private int virtualsTop;
    private int totalQuickFind;
    private WeightedQuickUnionUF uf;

    /**
     * Creates a grid of size n and creates WeightedQuickUnionUF structure with
     * n*n+2
     * 
     * @param n
     *            : Size of the grid
     * @throws IllegalArgumentException
     *             if {@code n <= 0}
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException(" provide a valid site size");
        }

        totalQuickFind = n * n;
        virtualLow = totalQuickFind;
        virtualsTop = totalQuickFind + 1;
        uf = new WeightedQuickUnionUF(totalQuickFind + 2);

        this.size = n;
        sites = new int[n + 1][n + 1];
    }

    /**
     * Opens a site situated at site[row][col]. Connects to adjacent sites in
     * all 4 directions if it is open. If row is 1 then connects to lower
     * virtual site. If row is n then connects to Higher virtual site
     * 
     * @param row
     *            : Row number
     * @param col
     *            : Column number
     * @throws IndexOutOfBoundsException
     *             if {@code (row>n || row<1 || col>n || col<1)}
     */
    public void open(int row, int col) {
        validateIndices(row, col);
        if (isOpen(row, col)) {
            return;
        }
        // Opens the site
        sites[row][col] = 1;

        int prevRow = row - 1;
        int prevCol = col - 1;

        // Creates connection between sites[prevRow][col] and sites[row][col]
        if (prevRow > 0 && sites[prevRow][col] == 1 && !uf.connected(xyToID(row, col), xyToID(prevRow, col))) {
            uf.union(xyToID(row, col), xyToID(prevRow, col));
        }

        // Creates connection between sites[row][Prevcol] and sites[row][col]
        if (prevCol > 0 && sites[row][prevCol] == 1 && !uf.connected(xyToID(row, col), xyToID(row, prevCol))) {
            uf.union(xyToID(row, col), xyToID(row, prevCol));
        }

        // Creates connection between sites[nextRow][col] and sites[row][col]
        int nextRow = row + 1;
        if (nextRow <= size && sites[nextRow][col] == 1 && !uf.connected(xyToID(row, col), xyToID(nextRow, col))) {
            uf.union(xyToID(row, col), xyToID(nextRow, col));
        }

        // Creates connection between sites[row][nextCol] and sites[row][col]
        int nextCol = col + 1;
        if (nextCol <= size && sites[row][nextCol] == 1 && !uf.connected(xyToID(row, col), xyToID(row, nextCol))) {
            uf.union(xyToID(row, col), xyToID(row, nextCol));
        }

        // Creates connection with virtual root on top
        if (row == 1) {
            uf.union(virtualLow, xyToID(row, col));
        }

        // Creates connection with virtual root on buttom
        if (row == size) {
            uf.union(virtualsTop, xyToID(row, col));
        }
    }

    /**
     * returns true if site is open
     * 
     * @param row
     *            : row number
     * @param col
     *            : column number
     * @return true if site situated at [row][col] is open
     * @throws IndexOutOfBoundsException
     *             if {@code (row>n || row<1 || col>n || col<1)}
     */
    public boolean isOpen(int row, int col) {
        validateIndices(row, col);
        return sites[row][col] == 1;
    }

    /**
     * returns true if site is open and connected to all sites surronding
     * 
     * @param row
     *            : row number
     * @param col
     *            : column number
     * @return returns true if site is open and connected to all sites
     *         surronding
     * @throws IndexOutOfBoundsException
     *             if {@code (row>n || row<1 || col>n || col<1)}
     */
    public boolean isFull(int row, int col) {
        validateIndices(row, col);
        return sites[row][col] == 1 && uf.connected(xyToID(row, col), virtualLow);
    }

    /**
     * validates indices value
     * 
     * @param row
     * @param col
     */
    private void validateIndices(int row, int col) {
        if (row > size || row < 1 || col > size || col < 1) {
            throw new IndexOutOfBoundsException("index  " + "is not between 1 and " + size);
        }
    }

    /**
     * returns true if virtual roots are connected.
     * 
     * @return true if virtual roots are connected.
     */
    public boolean percolates() {
        return uf.connected(virtualLow, virtualsTop);
    }

    /**
     * maps two dimensional array to single dimensional array index.
     * 
     * @param row
     *            : row number
     * @param col
     *            : column number
     * @return : true
     */
    private int xyToID(int row, int col) {
        return (row - 1) * size + (col - 1);
    }

    public static void main(String[] args) {
        int gridSize = StdIn.readInt();
        Percolation per = new Percolation(gridSize);
        while (!StdIn.isEmpty()) {
            int row = StdIn.readInt();
            int col = StdIn.readInt();
            per.open(row, col);
            System.out.println(per.percolates());
            System.out.println(" is full " + per.isFull(1, 3));
        }
    }

}
