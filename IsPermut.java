/* *****************************************************************************
 *  Name:wei wang
 *  Date:July 12
 *  Description:interview problem2_algorithm part 1
 **************************************************************************** */

import java.util.Arrays;
import java.util.Scanner;

public class IsPermut {
    public static boolean isPermutation(int[] a, int[] b) {
        if (a.length != b.length) return false;
        Arrays.sort(a);
        Arrays.sort(b);

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String a = input.nextLine();
        String b = input.nextLine();
        String[] seta = a.split(",");
        String[] setb = b.split(",");
        int[] numa = new int[seta.length];
        int[] numb = new int[setb.length];
        for (int i = 0; i < seta.length; i++) {
            numa[i] = Integer.parseInt(seta[i]);
        }
        for (int i = 0; i < setb.length; i++) {
            numb[i] = Integer.parseInt(setb[i]);
        }
        System.out.println(IsPermut.isPermutation(numa, numb));

    }
}
