/* *****************************************************************************
 *  Name:Wei Wang
 *  Date:06/02/2019
 *  Description:binary research using recursion for ascending array
 **************************************************************************** */

public class AscendBinary {
    private int[] num;
    private int x;

    public AscendBinary(int[] number, int x) {
        int n = number.length;
        num = new int[n];
        this.x = x;
        for (int i = 0; i < n; i++) {
            num[i] = number[i];
        }
    }

    private int find() {
        return binarySearch(0, num.length - 1, x);
    }

    private int binarySearch(int l, int u, int x) {
        if (l > u) {
            return -1;
        }
        else {
            int m = (l + u) >>> 1; /// to avoid potential overflow
            if (num[m] == x) {
                return m;
            }
            else {
                if (num[m] < x) {
                    return binarySearch(m + 1, u, x);
                }
                else {
                    return binarySearch(l, m - 1, x);
                }
            }
        }
    }

    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int n = args.length;
        int[] number = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            number[i] = Integer.parseInt(args[i + 1]);
        }
        AscendBinary test = new AscendBinary(number, x);
        System.out.print(test.find());
    }
}
