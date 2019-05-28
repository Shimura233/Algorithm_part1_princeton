/* *****************************************************************************
 *  Name:Wei Wang
 *  Date:05/28/2019
 *  Description:algorithm, part 1, assignment 1-percolation
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF grid;
    private boolean[] openind;
    private int numberOfOpenSites;
    private int num;
    private WeightedQuickUnionUF gridforbackwash;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n should be larger than 0");
        }
        num = n;
        grid = new WeightedQuickUnionUF(n * n + 2);
        gridforbackwash = new WeightedQuickUnionUF(n * n + 1);
        openind = new boolean[n * n + 2];
    }

    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {

            int ind = (row - 1) * num + col;
            numberOfOpenSites += 1;
            openind[ind] = true;

            if (col > 1) {
                if (isOpen(row, col - 1)) {
                    grid.union(ind - 1, ind);
                    gridforbackwash.union(ind - 1, ind);

                }
            }
            if (col < num) {
                if (isOpen(row, col + 1)) {
                    grid.union(ind, ind + 1);
                    gridforbackwash.union(ind, ind + 1);
                }
            }
            if (row < num) {
                if (isOpen(row + 1, col)) {
                    grid.union(ind, ind + num);
                    gridforbackwash.union(ind, ind + num);
                }
            }
            if (row > 1) {
                if (isOpen(row - 1, col)) {
                    grid.union(ind, ind - num);
                    gridforbackwash.union(ind, ind - num);
                }
            }
            if (row == num) {
                grid.union(num * num + 1, ind);
            }
            if (row == 1) {
                grid.union(0, ind);
                gridforbackwash.union(0, ind);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        int ind = (row - 1) * num + col;
        return openind[ind];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        int ind = (row - 1) * num + col;
        return gridforbackwash.connected(0, ind);
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        return grid.connected(0, num * num + 1);
    }

    private void validate(int row, int col) {
        if (row < 1 || row > num || col < 1 || col > num) {
            throw new IllegalArgumentException("row index or column index is out of the range");
        }
    }
}
