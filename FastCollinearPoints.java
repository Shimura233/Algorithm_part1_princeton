/* *****************************************************************************
 *  Name:    Wei Wang
 *  Description:
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {
    private Point[] points;
    private int num = 0;
    private LineSegment[] myseg = new LineSegment[2];
    private seg[] tmp = new seg[2];

    private class seg implements Comparable<seg> {
        Point ini;
        Point end;

        public seg(Point ini, Point end) {
            this.ini = ini;
            this.end = end;
        }

        public int compareTo(seg that) {
            if (this.ini.compareTo(that.ini) < 0) return -1;
            if (this.ini.compareTo(that.ini) > 0) return 1;
            else {
                if (this.end.compareTo(that.end) < 0) return -1;
                else {
                    if (this.end.compareTo(that.end) > 0) return 1;
                    else return 0;
                }
            }
        }
    }


    public FastCollinearPoints(Point[] poi) {
        if (poi == null)
            throw new java.lang.IllegalArgumentException("your input points is null");
        points = Arrays.copyOf(poi, poi.length);
        int n = points.length;
        for (int i = 0; i < n; i++) {
            if (points[i] == null)
                throw new java.lang.IllegalArgumentException("some of the points are null");
            for (int j = i + 1; j < n; j++) {
                if (points[j] == null)
                    throw new java.lang.IllegalArgumentException("some of the points are null");
                if (points[i].compareTo(points[j]) == 0)
                    throw new java.lang.IllegalArgumentException("some of the points are repeated");
            }
        }

        int tmpnum = 0;
        for (int i = 0; i < n; i++) {
            Arrays.sort(points);
            Arrays.sort(points, points[i]
                    .slopeOrder()); // because the sort is stable, the previous sorting won't be harmed.
            int j = 1;
            while (j < n) {
                int cnt = 0;
                int ini = j;
                while (j + 1 < n && points[0].slopeTo(points[j]) == points[0]
                        .slopeTo(points[j + 1])) {

                    j++;
                    cnt++;

                }
                int end = j;
                j++;
                if (cnt >= 2) {
                    if (points[ini].compareTo(points[0]) > 0) ini = 0;
                    if (points[end].compareTo(points[0]) < 0) end = 0;

                    seg newseg = new seg(points[ini], points[end]);

                    if (tmpnum == tmp.length) {
                        seg[] newq = new seg[tmpnum * 2];
                        for (int ss = 0; ss < tmpnum; ss++) {
                            newq[ss] = tmp[ss];
                        }
                        tmp = newq;
                    }
                    tmp[tmpnum] = newseg;
                    tmpnum++;
                }
            }
        }

        tmp = Arrays.copyOf(tmp, tmpnum);
        Arrays.sort(tmp);
        int i = 0;
        while (i < tmpnum) {
            while (i + 1 < tmpnum && tmp[i].compareTo(tmp[i + 1]) == 0) {
                i++;
            }

            if (num == myseg.length) {
                LineSegment[] newq = new LineSegment[num * 2];
                for (int ss = 0; ss < num; ss++) {
                    newq[ss] = myseg[ss];
                }
                myseg = newq;
            }
            myseg[num] = new LineSegment(tmp[i].ini, tmp[i].end);
            num++;
            i++;
        }

    }

    public int numberOfSegments() {
        return num;
    }

    public LineSegment[] segments() {
        LineSegment[] output = Arrays.copyOf(myseg, num);
        return output;
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
