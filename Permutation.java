/* *****************************************************************************
 *  Name:Wei Wang
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> test = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            test.enqueue(StdIn.readString());
        }
        while (k > 0) {
            System.out.println(test.dequeue());
            k = k - 1;
        }

    }
}
