import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/* *****************************************************************************
 *  Name: Wei Wang
 *  Date: 2019/06/27
 *  Description: intersection of 2 sets of 2D points/algorithms, part 1, week 2, interview problems
 **************************************************************************** */
class StackInList<item> implements Iterable<item> { //evaluate infix expression
    Node first = null;

    class Node {
        item item;
        Node next;
    }

    public void push(item s) {
        Node oldfirst = first;
        first = new Node();
        first.item = s;
        first.next = oldfirst;

    }

    public item pop() {
        item s = first.item;
        first = first.next;
        return s;
    }

    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public Iterator<item> iterator() {
        return new stackIterator();
    }

    public class stackIterator implements Iterator<item> {
        Node cur = first;

        @Override
        public item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            item s = cur.item;
            cur = cur.next;
            return s;
        }

        @Override
        public boolean hasNext() {
            return cur != null;
        }
    }
}

class Point implements Comparable<Point> {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public int compareTo(Point that) {
        if (this.x < that.x) return -1;
        if (this.x > that.x) return 1;
        else return 0;
    }

    public boolean isEqual(Point that) {
        return ((this.x == that.x) && (this.y == that.y));
    }

    public void printPoint() {
        System.out.println(x + " " + y);
    }
}

public class InterSection {
    private StackInList<Point> inter = new StackInList<Point>();
    private Point[] a;
    private Point[] b;

    InterSection(Point[] a, Point[] b) {
        this.a = a;
        this.b = b;
    }

    private boolean myless(Point a, Point b) {
        return a.compareTo(b) < 0;
    }

    private void myswap(Point[] a, int i, int j) {
        Point tmp;
        tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public void printA() {
        for (int i = 0; i < a.length; i++) {
            a[i].printPoint();
        }
    }

    public void printB() {
        for (int i = 0; i < b.length; i++) {
            b[i].printPoint();
        }
    }

    public void mysort(Point[] t) {
        int N = t.length;
        int i = 1;
        while (i < N / 3) i = 3 * i + 1;
        while (i >= 1) {
            for (int h = i; h < N; h++) {
                for (int j = h; j >= i; j = j - i) {
                    if (myless(t[j], t[j - i])) myswap(t, j, j - i);
                    else break;
                }
            }
            i = i / 3;
        }
    }

    public void findInter() {
        mysort(a);
        mysort(b);
        int n1 = a.length;
        int n2 = b.length;
        int i = 0;
        int j = 0;
        for (int k = 0; k < (n1 + n2); k++) {
            if (i >= n1) break;
            if (j >= n2) break;
            if (myless(a[i], b[j])) i++;
            else {
                if (a[i].isEqual(b[j])) inter.push(a[i]);
                j++;
            }
        }
        while (!inter.isEmpty()) {
            Point a = inter.pop();
            a.printPoint();
        }
    }

    public static void main(String[] args) {
        System.out.println("please give set 1");
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        String[] a = str.split(" ");
        Point[] setOne = new Point[a.length];
        for (int i = 0; i < a.length; i++) {
            String[] tmp = a[i].split(",");
            Point p = new Point(Double.parseDouble(tmp[0]), Double.parseDouble(tmp[1]));
            setOne[i] = p;
        }
        System.out.println("please give set 2");

        str = input.nextLine();
        String[] b = str.split(" ");
        Point[] setTwo = new Point[b.length];
        for (int i = 0; i < b.length; i++) {
            String[] tmp = b[i].split(",");
            Point p = new Point(Double.parseDouble(tmp[0]), Double.parseDouble(tmp[1]));
            setTwo[i] = p;
        }
        InterSection test = new InterSection(setOne, setTwo);
        test.findInter();
    }
}
