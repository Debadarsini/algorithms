/******************************************************************************
 *  Compilation:  javac BruteCollinearPoints.java
 *  Execution:  java BruteCollinearPoints
 *
 ******************************************************************************/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 * Bruteforce way of checking collinear. Checks whether the 4 points p, q, r,
 * and s are collinear, check whether the three slopes between p and q, between
 * p and r, and between p and s are all equal.
 * 
 * @author Debadarsini
 *
 */
public class BruteCollinearPoints {

    private List<LineSegment> lineSegments = new ArrayList<>();

    /**
     * Logic to find colinear points
     * 
     * @param points
     */
    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        if (points == null || points.length <= 0)
            throw new NullPointerException("argument is null");
        int length = points.length;
        Point[] pointsClone = points.clone();
        Arrays.sort(pointsClone);
        checkDuplicatedEntries(pointsClone);
        for (int p = 0; p < length - 3; p++) {
            for (int q = p + 1; q < length - 2; q++) {
                double slopePQ = pointsClone[p].slopeTo(pointsClone[q]);
                for (int r = q + 1; r < length - 1; r++) {
                    if (Double.compare(slopePQ, pointsClone[p].slopeTo(pointsClone[r])) == 0) {
                        for (int s = r + 1; s < length; s++) {
                            if (Double.compare(slopePQ, pointsClone[p].slopeTo(pointsClone[s])) == 0)
                                lineSegments.add(new LineSegment(pointsClone[p], pointsClone[s]));
                        }
                    }
                }
            }
        }

    }

    /**
     * returns no of lineSegments
     * 
     * @return
     */
    public int numberOfSegments() {
        return lineSegments.size();
    }

    /**
     * Returns segments present
     * 
     * @return
     */
    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }

    private void checkDuplicatedEntries(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0)
                throw new IllegalArgumentException("Duplicated entries in given points.");
        }
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
