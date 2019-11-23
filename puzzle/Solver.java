import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Solver {
    private class SearchNode {
        SearchNode previous;
        int move;
        int priority;
        Board cur;
        SearchNode(SearchNode previous, int move, int priority, Board cur) {
            this.previous = previous;
            this.move = move;
            this.priority = priority;
            this.cur = cur;//没有重新开内存
        }
    }
    private MinPQ<SearchNode> stages;
    private MinPQ<SearchNode> twinstages;
    private Board initialboard;
    private boolean solved;
    private boolean solvable;
    private int finalmove;
    private ArrayList<Board> path;
    private class Mycomparator implements Comparator<SearchNode> {
        public int compare(SearchNode a, SearchNode b) {
            if (a.priority < b.priority) return -1;
            else {
                if (a.priority > b.priority) return 1;
                else return 0;
            }
        }
    }
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("your initial board is null");
        }
        stages = new MinPQ<>(new Mycomparator());
        twinstages = new MinPQ<>(new Mycomparator());
        initialboard = initial;//与外面的board还相干，但因为board没有改变的接口，所以也无所谓
   }
    private void solve() {
        if (initialboard.isGoal()) {
            getPath();
            solvable = true;
            solved = true;
            finalmove = 0;
            return;
        }
        Board twinboard = initialboard.twin();
SearchNode firstnode = new SearchNode(null, 0, initialboard.manhattan(), initialboard);
SearchNode firstnodetwin = new SearchNode(null, 0, twinboard.manhattan(), twinboard);
stages.insert(firstnode);
twinstages.insert(firstnodetwin);
tofind:
while (true) {
SearchNode tmp = stages.delMin();
SearchNode prev = tmp.previous;
Board current = tmp.cur;
int newmove = tmp.move + 1;
for (Board b:current.neighbors()) {
    int totarget = b.manhattan();
    if (totarget == 0) {
        finalmove = newmove;
        solved = true;
        getPath(tmp, b);
        solvable = true;
        //reach the target,output path
        break tofind;
    }
    if (prev == null || !b.equals(prev.cur)) {
        stages.insert(new SearchNode(tmp, newmove, totarget + newmove, b));
    }
}
tmp = twinstages.delMin();
prev = tmp.previous;
current = tmp.cur;
newmove = tmp.move + 1;
for (Board b : current.neighbors()) {
    int totarget = b.manhattan();
    if (totarget == 0) {
        solvable = false;
        solved = true;
        break tofind;
    }
    if (prev == null || !b.equals(prev.cur)) {
        twinstages.insert(new SearchNode(tmp, newmove, totarget + newmove, b));
    }
}
}
    }
    private void getPath() {
        path = new ArrayList<>();
        path.add(initialboard);
    }
    private void getPath(SearchNode curnode, Board finalboard) {
        path = new ArrayList<>();
        path.add(finalboard);
        while (curnode != null) {
            path.add(0,curnode.cur);
            curnode = curnode.previous;
        }
    }

    public boolean isSolvable() {
if (!solved) {
solve();
}
return solvable;
    }

    public int moves() {
if (!solved) {
    solve();
}
if (!solvable) {
    return -1;
}
else return finalmove;
    }
    public Iterable<Board> solution() {
if (!solved) {
    solve();
}
if (!solvable) {
    return null;
}
else return path;
    }
    public static void main(String[] args) {

        int[][] tiles = {{0, 3, 1}, {4, 5, 2}, {7, 8, 6}};
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output

        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution())
            StdOut.println(board);
    }
}
