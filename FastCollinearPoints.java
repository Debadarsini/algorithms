/******************************************************************************
 *  Compilation:  javac FastCollinearPoints.java
 *  Execution:  java FastCollinearPoints
 *
 ******************************************************************************/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 * Fast colinear points : Uses fastest way / sorting way to find colinear
 * points. it is possible to solve the problem much faster than the brute-force
 * solution described above. Given a point p, the following method determines
 * whether p participates in a set of 4 or more collinear points.
 * 
 * <p>
 * Think of p as the origin. For each other point q, determine the slope it
 * makes with p. Sort the points according to the slopes they makes with p.
 * Check if any 3 (or more) adjacent points in the sorted order have equal
 * slopes with respect to p. If so, these points, together with p, are
 * collinear. Applying this method for each of the n points in turn yields an
 * efficient algorithm to the problem. The algorithm solves the problem because
 * points that have equal slopes with respect to p are collinear, and sorting
 * brings such points together. The algorithm is fast because the bottleneck
 * operation is sorting.
 * </p>
 * 
 * @author Debadarsini
 *
 */
public class FastCollinearPoints {
    private List<LineSegment> lineSegments = new ArrayList<>();

    /**
     * Logic to handle collinear of in sorted array
     * 
     * @param points
     */
    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 or more points
        if (points == null || points.length <= 0)
            throw new NullPointerException("argument is null");
        Point[] pointsClone = points.clone();
        Arrays.sort(pointsClone);
        checkDuplicatedEntries(pointsClone);
        int length = points.length;
        for (int p = 0; p < length; p++) {
            Point[] arrayCopy = pointsClone.clone();
            Arrays.sort(arrayCopy, pointsClone[p].slopeOrder());
            assert pointsClone[p].compareTo(arrayCopy[0]) == 0;
            int q = 1;
            while (q < length - 1) {
                int r = q + 1;
                while (r < length && Double.compare(arrayCopy[0].slopeTo(arrayCopy[q]),
                        arrayCopy[0].slopeTo(arrayCopy[r])) == 0) {
                    // System.out.println(r-q);
                    r++;
                }

                if (r - q >= 3 && arrayCopy[0].compareTo(arrayCopy[q]) < 0) {
                    lineSegments.add((new LineSegment(arrayCopy[0], arrayCopy[r - 1])));
                }
                q = r;
            }

        }
    }

    public int numberOfSegments() {
        // the number of line segments
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        // the line segments
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }

    private void checkDuplicatedEntries(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException("Duplicated entries in given points.");
            }

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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
