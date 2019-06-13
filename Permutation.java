/* *****************************************************************************
 *  Name:Wei Wang
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> test = new RandomizedQueue<String>();
        int i = 0;
        String s;
        if (k != 0) {
            while (!StdIn.isEmpty()) {
                i = i + 1;
                s = StdIn.readString();
                if (test.size() == k) {
                    int sam = StdRandom.uniform(i);
                    if (sam < k) {
                        test.dequeue();
                        test.enqueue(s);
                    }
                }
                else {
                    test.enqueue(s);
                }
            }
        }
        while (k > 0) {
            System.out.println(test.dequeue());
            k = k - 1;
        }
    }
}

