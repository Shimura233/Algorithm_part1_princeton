import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;

public class Board {
    private int dim;
    private int[][] tiles;
    public Board(int[][] tiles) {
        dim = tiles.length;
        this.tiles = new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }
    public String toString() {
StringBuilder s = new StringBuilder();
s.append(dim+"\n");
for (int i = 0; i < dim; i++) {
    for (int j = 0; j < dim; j++) {
        s.append(" "+tiles[i][j]);
    }
    s.append("\n");
}
return s.toString();
    }

    public int dimension() {

        return dim;
    }
    public int hamming() {
        int count = 0;
        for (int i = 0; i < dim - 1; i++) {
            for (int j = 0; j < dim; j++) {

                if (tiles[i][j] != i * dim + j + 1) count ++;
            }
        }
        for (int j = 0; j < dim - 1; j++) {
            if (tiles[dim - 1][j] != (dim-1) * dim + j +1 ) count++;
        }
return count;
    }
    public int manhattan() {
int count = 0;
for (int i = 0; i < dim; i++) {
    for (int j = 0; j < dim; j++) {
        int num = tiles[i][j];
        if (num == 0) continue;
        int targetrow = (num % dim == 0)? (num / dim - 1) : (num / dim);
        int targetcol = ((num % dim == 0)? dim:(num % dim)) - 1;
        count += Math.abs(i - targetrow) + Math.abs(j - targetcol);
    }
}
return count;
    }
    public boolean isGoal() {
        for (int i = 0; i < dim - 1; i ++) {
            for (int j = 0; j < dim; j ++) {

                if (tiles[i][j] != i * dim + j + 1) return false;
            }
        }
        for (int j = 0; j < dim - 1; j++) {
            if (tiles[dim - 1][j] != (dim-1) * dim + j +1 ) return false;
        }
        return true;
    }
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null || getClass() != y.getClass()) return false;
        Board that = (Board) y;
        if (this.dimension() != that.dimension()) return false;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (this.tiles[i][j] != that.tiles[i][j]) return false;
            }
        }
        return true;
    }
    private int[][] swap(int i, int j, int targeti, int targetj) {
        int[][] swaped = new int[dim][dim];
        for (int l = 0; l < dim; l ++) {
            for (int m = 0; m < dim; m ++) {
                swaped[l][m] = tiles[l][m];
            }
        }
        int tmp = swaped[i][j];
        swaped[i][j] = swaped[targeti][targetj];
        swaped[targeti][targetj] = tmp;
        return swaped;
    }
    public Iterable<Board> neighbors() {
        int[][] move = {{0,1},{0,-1},{-1,0},{1,0}};
        ArrayList<Board> result = new ArrayList<>();
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (tiles[i][j] == 0) {
                    for (int[] m:move) {
                        if (m[0] + i < dim && m[0] + i >=0 && m[1] + j >=0 && m[1] + j < dim) {
                            result.add(new Board(swap(i,j,i+m[0],j+m[1])));
                        }
                    }
                    return result;
                }
            }
        }
        return result;
    }

    public Board twin() {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim - 1; j++) {
                if (tiles[i][j] != 0 && tiles[i][j + 1] != 0) {
                    return new Board(swap(i,j,i,j+1));
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
