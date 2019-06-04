/* *****************************************************************************
 *  Name:Wei Wang
 *  Date:06/03/2019
 *  Description:Drop egg problem: an array x_1,...x_n, find the turning point that'>0 for the first time
 **************************************************************************** */

public class Dropeggs {
    private int[] num;

    public Dropeggs(int[] number) {
        int n = number.length;
        num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = number[i];
        }
    }

    public int find1() { // logT eggs, 2logT tosses
        int i = 1;
        while (i - 1 < num.length && num[i - 1] < 0) {
            i = i * 2;
        }
        if (i - 1 >= num.length) {
            return binarySearch(i / 2, num.length);
        }
        else {
            return binarySearch(i / 2, i);
        }
    }

    public int find2() { // 2 eggs, 2sqrt(n) tosses
        int par = (int) Math.sqrt(num.length);
        int i = 1;
        while (i * par <= num.length) {
            if (num[i * par - 1] < 0) {
                i = i + 1;
            }
            else {
                return sequentialSearch((i - 1) * par + 1, i * par);
            }
        }
        return sequentialSearch((i - 1) * par + 1, num.length);

    }

    public int find3() { // 2 eggs, sqrt(2n) tosses
        int par = ((int) Math.sqrt(2 * num.length)) + 1;
        int sumpar = par;
        while (sumpar - 1 < num.length && num[sumpar - 1] < 0) {
            par = par - 1;
            sumpar += par;
        }
        if (sumpar - 1 >= num.length) {
            return sequentialSearch(sumpar - par + 1, num.length);
        }
        else {
            return sequentialSearch(sumpar - par + 1, sumpar);
        }
    }

    public int find4() { // 2 eggs, <= 2sqrt(2T) tosses
        int par = 1;
        int sumpar = par;
        while (sumpar - 1 < num.length && num[sumpar - 1] < 0) {
            par += 1;
            sumpar += par;
        }
        if (sumpar - 1 >= num.length) {
            return sequentialSearch(sumpar - par + 1, num.length);
        }
        else {
            return sequentialSearch(sumpar - par + 1, sumpar);
        }
    }

    private int sequentialSearch(int l, int u) {
        while (l - 1 < u && num[l - 1] < 0) {
            l = l + 1;
        }
        if (l - 1 == u) {
            return -1;
        }
        else {
            return l;
        }
    }

    private int binarySearch(int l, int u) {
        int indexl = l - 1;
        int indexu = u - 1;
        int m = (indexl + indexu) >>> 1;
        if (indexl == indexu - 1) {
            if (num[indexl] > 0) {
                return l;
            }
            else {
                if (num[indexu] < 0) return -1;
                return u;
            }
        }
        else {
            if (0 < num[m]) {
                return binarySearch(l, m + 1);
            }
            else {
                return binarySearch(m + 1, u);
            }
        }
    }

    public static void main(String[] args) {
        int n = args.length;
        int[] number = new int[n];
        for (int i = 0; i < n; i++) {
            number[i] = Integer.parseInt(args[i]);
        }
        Dropeggs test = new Dropeggs(number);
        System.out.println("result of find 1 is " + test.find1());
        System.out.println("result of find 2 is " + test.find2());
        System.out.println("result of find 3 is " + test.find3());
        System.out.print("result of find 4 is " + test.find4());
    }
}
