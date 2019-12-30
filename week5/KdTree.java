import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;



public class KdTree {
    private final int k = 2;
    private class Node {
        double[] coordinates = new double[k];
        Node left;
        Node right;
        int N = 1;
        int keyloc;
        Node(double[] coord, int keyloc) {
            for (int i = 0; i < k; i ++) {
                coordinates[i] = coord[i];
            }
            this.keyloc = keyloc;
        }
    }
    private Node root;
    public  KdTree() {

    }                              // construct an empty set of points
    public  boolean isEmpty() {
        return root == null;
    }                     // is the set empty?
    public int size() {
        return (root == null)? 0:root.N;
    }                        // number of points in the set
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("your argument is null");
        }
        if (contains(p)) return;
        double[] coord = new double[k];
        coord[0] = p.x();
        coord[1] = p.y();
        root = insert(root, coord, 0);
    }              // add the point to the set (if it is not already in the set)
    private Node insert(Node x, double[] coord, int keyloc) {
        if (x == null) {
            return new Node(coord, keyloc);
        }
        if (x.coordinates[keyloc] < coord[keyloc]) {
            x.right = insert(x.right, coord, (keyloc + 1) % k);
        } else {
            x.left = insert(x.left, coord, (keyloc + 1) % k);
        }
        x.N = size(x.right) + size(x.left) + 1;
        return x;
    }
    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }
    public   boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("your argument is null");
        }
        double[] coord = new double[k];
        coord[0] = p.x();
        coord[1] = p.y();
        return contains(root, coord);
    }           // does the set contain point p?
    private boolean contains(Node x, double[] coord) {
        if (x == null) return false;
        int key = x.keyloc;
        if (x.coordinates[key] == coord[key]) {
            for (int i = 0; i < k; i ++) {
                if (x.coordinates[i] != coord[i]) return contains(x.left, coord);
            }
            return true;
        }
        if (x.coordinates[key] < coord[key]) {
            return contains(x.right, coord);
        } else {
            return contains(x.left, coord);
        }
    }
    public void draw() {
draw(root,0, 1, 0, 1);
    }
    private void draw(Node x, double left, double right, double down, double up) {
        if (x == null) return;
        int key = x.keyloc;
        StdDraw.setPenRadius(0.005);
        if (key == 0) {
StdDraw.setPenColor(StdDraw.RED);
double x0 = x.coordinates[0];
StdDraw.line(x0, up, x0, down);
            StdDraw.setPenRadius(0.02);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.point(x0,x.coordinates[1]);
draw(x.left, left, x0, down, up);
draw(x.right, x0, right, down, up);
        } else {
StdDraw.setPenColor(StdDraw.BLUE);
double y0 = x.coordinates[1];
StdDraw.line(left, y0, right, y0);
StdDraw.setPenRadius(0.02);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.point(x.coordinates[0],y0);
draw(x.left, left, right, down, y0);
draw(x.right, left, right, y0, up);
        }
    }
    // draw all points to standard draw

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("your argument is null");
        }
        Queue<Point2D> result = new Queue<>();
        range(rect, result, root);
        return result;
    }            // all points that are inside the rectangle (or on the boundary)

    private void range(RectHV rect, Queue<Point2D> result, Node x) {
        if (x == null) return;
        Point2D toadd = new Point2D(x.coordinates[0], x.coordinates[1]);
        if (rect.contains(toadd)) result.enqueue(toadd);
        int key = x.keyloc;
        if (key == 0) {
            if (x.coordinates[0] >= rect.xmin()) range(rect, result, x.left);
            if (x.coordinates[0] < rect.xmax()) range(rect, result, x.right);
        } else {
            if (x.coordinates[1] >= rect.ymin()) range(rect, result, x.left);
            if (x.coordinates[1] < rect.ymax()) range(rect,result, x.right);
        }
    }

    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("your argument is null");
        }
        if (root == null) return null;
        Point2D rootp = new Point2D(root.coordinates[0],root.coordinates[1]);
        return nearest(p, root, rootp.distanceSquaredTo(p), 0, 1, 0, 1);

    }            // a nearest neighbor in the set to point p; null if the set is empty
private Point2D nearest(Point2D p, Node x, double prevminimum, double left, double right, double down, double up) {
        if (x == null) return null;
        int key = x.keyloc;
        Point2D curp = new Point2D(x.coordinates[0], x.coordinates[1]);
        Point2D result = null;
        double curdistance = curp.distanceSquaredTo(p);
        if (curdistance <= prevminimum) {
            prevminimum = curdistance;
            result = curp;
        }
        if (key == 0) {
            if (curp.x() < p.x()) {
                Point2D minright = nearest(p, x.right, prevminimum, curp.x(), right, down, up);
                if (minright != null) {
                    prevminimum = minright.distanceSquaredTo(p);
                    result = minright;
                }
                double potentialminimum = new RectHV(left, down, curp.x(), up).distanceSquaredTo(p);
                if (potentialminimum < prevminimum) {
                    Point2D minleft = nearest(p, x.left, prevminimum, left, curp.x(), down, up);
                    if (minleft != null) {
                        result = minleft;
                    }
                }
            } else {
                Point2D minleft2 = nearest(p, x.left, prevminimum, left, curp.x(), down, up);
                if (minleft2 != null) {
                    prevminimum = minleft2.distanceSquaredTo(p);
                    result = minleft2;
                }
                double potentialminimum2 = new RectHV(curp.x(),down,right,up).distanceSquaredTo(p);
                if (potentialminimum2 < prevminimum) {
                    Point2D minright2 = nearest(p, x.right, prevminimum, curp.x(), right, down, up);
                    if (minright2 != null) {
                        result = minright2;
                    }
                }
            }
        } else {
            if (curp.y() < p.y()) {
                Point2D minup = nearest(p, x.right, prevminimum, left, right, curp.y(), up);
                if (minup != null) {
                    prevminimum = minup.distanceSquaredTo(p);
                    result = minup;
                }
                double potentialminimum = new RectHV(left, down, right, curp.y()).distanceSquaredTo(p);
                if (potentialminimum < prevminimum) {
                    Point2D mindown = nearest(p, x.left, prevminimum, left, right, down, curp.y());
                    if (mindown != null) {
                        result = mindown;
                    }
                }
            } else {
                Point2D mindown2 = nearest(p, x.left, prevminimum, left, right, down, curp.y());
                if (mindown2 != null) {
                    prevminimum = mindown2.distanceSquaredTo(p);
                    result = mindown2;
                }
                double potentialminimum2 = new RectHV(left, curp.y(),right,up).distanceSquaredTo(p);
                if (potentialminimum2 < prevminimum) {
                    Point2D minup2 = nearest(p, x.right, prevminimum, left, right, curp.y(), up);
                    if (minup2 != null) {
                        result = minup2;
                    }
                }
            }
        }

        return result;
}
    public static void main(String[] args) {
        /*RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);
        StdDraw.enableDoubleBuffering();
        KdTree kdtree = new KdTree();
        while (true) {
            if (StdDraw.isMousePressed()) {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                StdOut.printf("%8.6f %8.6f\n", x, y);
                Point2D p = new Point2D(x, y);
                if (rect.contains(p)) {
                    StdOut.printf("%8.6f %8.6f\n", x, y);
                    kdtree.insert(p);
                    StdDraw.clear();
                    kdtree.draw();
                    StdDraw.show();
                }
            }
            StdDraw.pause(20);
        }*/
        KdTree kdtree = new KdTree();
        kdtree.insert(new Point2D(0.372,0.497));
        kdtree.insert(new Point2D(0.564,0.413));
        kdtree.insert(new Point2D(0.226,0.577));
        kdtree.insert(new Point2D(0.144,0.179));
        kdtree.insert(new Point2D(0.083,0.51));
        kdtree.insert(new Point2D(0.32,0.708));
        kdtree.insert(new Point2D(0.417,0.362));
        kdtree.insert(new Point2D(0.862,0.825));
        kdtree.insert(new Point2D(0.785,0.725));
        kdtree.insert(new Point2D(0.499,0.208));
        kdtree.draw();
        Point2D targetp = new Point2D(0.2,0.64);
        StdDraw.setPenColor(StdDraw.GREEN);
        targetp.draw();
        Point2D testnear = kdtree.nearest(targetp);
        StdDraw.setPenColor(StdDraw.GRAY);
        testnear.draw();
    }

    }
