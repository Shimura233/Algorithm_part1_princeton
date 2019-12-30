import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> points;
    public PointSET() {
        points = new TreeSet<>();
    }                               // construct an empty set of points
    public boolean isEmpty()  {
        return points.isEmpty();
    }                    // is the set empty?
    public int size(){
        return points.size();
    }                         // number of points in the set
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("your argument is null");
        }
        points.add(p);
    }              // add the point to the set (if it is not already in the set)
    public boolean contains(Point2D p){
        if (p == null) {
            throw new IllegalArgumentException("your argument is null");
        }
        return points.contains(p);
    }            // does the set contain point p?
    public void draw() {
        for (Point2D p:points) {
            p.draw();
        }
    }                        // draw all points to standard draw
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("your argument is null");
        }
Queue<Point2D> result = new Queue<>();
for (Point2D p:points) {
    if (rect.contains(p)) result.enqueue(p);//method必须是Queue有的，如果仅是实现类有，也不行

}
return result;
    }             // all points that are inside the rectangle (or on the boundary)
    public           Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("your argument is null");
        }
double distance = -1;
Point2D result = null;
for (Point2D ps:points) {
    double current = p.distanceSquaredTo(ps);
    if (distance == -1 || current < distance)  {
        distance = current;
result = ps;
    }
}
return result;
    }            // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args) {
PointSET test = new PointSET();
Point2D p = new Point2D(0.4,0.5);
test.insert(p);
test.insert(new Point2D(0.7,0.8));
test.draw();
    }
}
