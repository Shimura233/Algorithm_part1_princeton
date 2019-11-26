/* *****************************************************************************
 *  Name:wei wang
 *  Date:05/27/2019
 *  Description:algorithm, part 1, assignment1
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {
    private double[] rate;
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException(
                    "n should be larger than 0 and tirals should be larger than 0");
        }
        rate = new double[trials];
        int row;
        int col;
        for (int i = 0; i < trials; i++) {
            Percolation test = new Percolation(n);
            while (!test.percolates()) {
                row = StdRandom.uniform(1, n + 1);
                col = StdRandom.uniform(1, n + 1);
                test.open(row, col);
            }
            rate[i] = (double) test.numberOfOpenSites() / (double) (n * n);
        }
        mean = StdStats.mean(rate);
        stddev = StdStats.stddev(rate);
        confidenceHi = mean + 1.96 * stddev / java.lang.Math.sqrt(trials);
        confidenceLo = mean - 1.96 * stddev / java.lang.Math.sqrt(trials);
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return confidenceLo;
    }

    public double confidenceHi() {
        return confidenceHi;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        int trials = StdIn.readInt();
        PercolationStats perc = new PercolationStats(n, trials);
        StdOut.println("mean                    =" + perc.mean());
        StdOut.println("stddev                  =" + perc.stddev());
        StdOut.println(
                "95% confidence interval = [" + perc.confidenceLo() + "," + perc.confidenceHi()
                        + "]");
    }
}
