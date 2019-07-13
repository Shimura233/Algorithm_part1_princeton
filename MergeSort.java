/* *****************************************************************************
 *  Name: wei wang
 *  Date: July 13
 *  Description: mergesort with aux and a alternation
 **************************************************************************** */

public class MergeSort {
    int[] a;
    int[] aux;


    public void mySort(int[] a) {
        this.a = a;
        aux = new int[a.length];
        for (int i = 0; i < a.length; i++) aux[i] = a[i];
        mySort(this.a, aux, 0, a.length - 1);
    }

    private void mySort(int[] a, int[] aux, int l, int h) {
        if (l == h) return;
        int m = (l + h) / 2;
        mySort(aux, a, l, m);
        mySort(aux, a, m + 1, h);
        mymerge(a, aux, l, m, h);
    }//mysort can sort a. thus,aux is sorted, and after mymerge, a is sorted. logicly consistent

    private void mymerge(int[] a, int[] aux, int l, int m, int h) {

        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= h) {
            if (aux[p1] < aux[p2]) a[l++] = aux[p1++];
            else a[l++] = aux[p2++];
        }
        if (p1 > m) {
            while (p2 <= h) a[l++] = aux[p2++];
        }
        else {
            while (p1 <= m) a[l++] = aux[p1++];
        }

    }

    public static void main(String[] args) {
        int[] a = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            a[i] = Integer.parseInt(args[i]);
        }
        MergeSort test = new MergeSort();
        test.mySort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
