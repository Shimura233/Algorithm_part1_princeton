/* *****************************************************************************
 *  Name:Wei Wang
 *  Date:06/02/2019
 *  Description:find a interger in a bitonic array with time complexity ~2logN compares
 **************************************************************************** */

public class Binomic {
    private int[] num;

    public Binomic(int[] number) {
        int n = number.length;
        num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = number[i];
        }

    }

    public int[] find(int x) {

        return binoticFind(0, num.length - 1, x);
    }

    private int ascendBinarySearch(int l, int u, int x) {
        if (l > u) {
            return -1;
        }
        else {
            int m = (l + u) >>> 1;
            if (num[m] == x) {
                return m;
            }
            else {
                if (num[m] < x) {
                    return ascendBinarySearch(m + 1, u, x);
                }
                else {
                    return ascendBinarySearch(l, m - 1, x);
                }
            }
        }
    }

    private int descendBinarySearch(int l, int u, int x) {
        if (l > u) {
            return -1;
        }
        else {
            int m = (l + u) >>> 1;
            if (num[m] == x) {
                return m;
            }
            else {
                if (num[m] < x) {
                    return descendBinarySearch(l, m - 1, x);
                }
                else {
                    return descendBinarySearch(m + 1, u, x);
                }
            }
        }
    }

    private int[] binoticFind(int l, int u, int x) {
        int m = (l + u) >>> 1;
        int[] result = { -1, -1 };
        if (l > u || l == u) {
            if (l > u) {
                return result;
            }
            else {
                if (num[m] == x) result[0] = l;
                return result;
            }
        }
        else {
            if (num[m + 1] > num[m]) {
                if (x > num[m]) {
                    return binoticFind(m + 1, u, x);
                }
                else {
                    if (x == num[m]) {
                        result[0] = m;
                        result[1] = descendBinarySearch(m + 1, u, x);
                        return result;
                    }
                    else {
                        result[0] = ascendBinarySearch(l, m - 1, x);
                        result[1] = descendBinarySearch(m + 1, u, x);
                        return result;
                    }
                }
            }
            else {
                if (x > num[m]) {
                    return binoticFind(l, m - 1, x);
                }
                else {
                    if (x == num[m]) {
                        result[0] = m;
                        result[1] = ascendBinarySearch(l, m - 1, x);
                        return result;
                    }
                    else {
                        result[0] = ascendBinarySearch(l, m - 1, x);
                        result[1] = descendBinarySearch(m + 1, u, x);
                        return result;
                    }
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
        Binomic test = new Binomic(number);
        int[] result = test.find(x);
        System.out.print(result[0] + " " + result[1]);
    }
}
