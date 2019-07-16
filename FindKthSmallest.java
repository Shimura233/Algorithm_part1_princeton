import java.util.Scanner;

public class FindKthSmallest {
    int[] a;
    int[] b;
    int k;

    public FindKthSmallest(int[] a, int[] b, int k) {
        this.a = a;
        this.b = b;
        this.k = k;
    }

    public int Find() {
        int lenS = b.length;
        int lenL = a.length;
        int[] s = b;
        int[] l = a;
        if (a.length < b.length) {
            lenS = a.length;
            lenL = b.length;
            s = a;
            l = b;
        }
        if (k <= lenS) {
            return getUpMedian(s, l, 0, k - 1, 0, k - 1);
        } else {
            if (k > lenL) {
                if (l[k - lenS - 1] >= s[lenS - 1]) return l[k - lenS - 1];
                if (s[k - lenL - 1] >= l[lenL - 1]) return s[k - lenL - 1];
                return getUpMedian(s, l, k - lenL, lenS - 1, k - lenS, lenL - 1);
            } else {
                if (l[k - lenS - 1] >= s[lenS - 1]) return l[k - lenS - 1];
                return getUpMedian(s, l, 0, lenS - 1, k - lenS, k - 1);

            }
        }
    }

    private int getUpMedian(int[] a, int[] b, int al, int ah, int bl, int bh) {

        while (al < ah) {
            int mid1 = (al + ah) / 2;
            int mid2 = (bl + bh) / 2;
            int offset = (al - ah + 1) & 1 ^ 1;
            if (a[mid1] == b[mid2]) return a[mid1];
            else {
                if (a[mid1] > b[mid2]) {
                    ah = mid1;
                    bl = mid2 + offset;
                } else {
                    al = mid1 + offset;
                    bh = mid2;
                }
            }

        }
        if (a[al] < b[al]) return a[al];
        else return b[al];
    }

    public static void main(String[] args) {
        System.out.println("please enter your two sorted arrays");
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] num = str.split(",");
        int[] a = new int[num.length];
        for (int i = 0; i < num.length; i++) {
            a[i] = Integer.parseInt(num[i]);
        }
        str = in.nextLine();
        num = str.split(",");
        int[] b = new int[num.length];
        for (int i = 0; i < num.length; i++) {
            b[i] = Integer.parseInt(num[i]);
        }
        System.out.println("please give your k");
        int k = Integer.parseInt(in.nextLine());
        FindKthSmallest test = new FindKthSmallest(a, b, k);
        System.out.println("the kth smallest is " + test.Find());
    }
}
