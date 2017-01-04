
/******************************************************************************
 *  Compilation:  javac PercolationStats.java
 *  Execution:  java PercolationStats
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * 
 * This class provides percolation statics like mean, standard deviation , low
 * endpoint of 95% confidence interval,high endpoint of 95% confidence interval
 * 
 * This class creates a n*n grid with all sites blocked for each iteration of
 * percolation.
 * 
 * Starts with a random site and opens site one by one till the grid percolates.
 * 
 * The fraction of sites that are opened when the system percolates , calculates
 * percolate thershold
 * 
 * @author Debadarsini Nayak
 *
 */

public class PercolationStats {

    private double[] t;

    private int trials;

    /**
     * perform trials independent experiments on an n-by-n grid
     * 
     * @param n
     *            : Grid size
     * @param trials
     *            : No of trials
     * @throws IllegalArgumentException
     *             if {@code n <= 0}
     */
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException(" provide a cube " + "size and trial number");
        }

        this.trials = trials;
        t = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            double openSites = 0;
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (!percolation.isOpen(row, col)) {
                    openSites++;
                    percolation.open(row, col);
                }

            }
            t[i] = openSites / (n * n);
        }

    }

    /**
     * Returns sample mean of percolation threshold
     * 
     * @return mean of fraction of sites of open during percolation
     */
    public double mean() {
        return StdStats.mean(t);
    }

    /**
     * Returns sample standard deviation of percolation threshold
     * 
     * @return standard deviation from mean
     */
    public double stddev() {
        return StdStats.stddev(t);
    }

    /**
     * returns low endpoint of 95% confidence interval
     * 
     * @return double
     */
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / (Math.sqrt(trials)));
    }

    /**
     * Returns high endpoint of 95% confidence interval
     * 
     * @return high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / (Math.sqrt(trials)));
    }

    public static void main(String[] args) {

        while (!StdIn.isEmpty()) {
            int gridSize = StdIn.readInt();
            int trials = StdIn.readInt();
            PercolationStats stats = new PercolationStats(gridSize, trials);
            // stats,

            System.out.println(stats.mean());

            System.out.println(stats.stddev());

            System.out.println(stats.confidenceLo() + " , " + stats.confidenceHi());
        }

    }
}
