public class FindPrime {
    public static void main(String[] args) {
        int bond = 0;
        if (args.length == 1) {
            bond = Integer.parseInt(args[0]);
        }
        if (bond < 2) {
            throw new IllegalArgumentException("bond should be larger or equal to 2");
        }
        boolean[] isNotPrime = new boolean[bond + 1];
        for (int i = 2; i <= bond; i++) {
            if (!isNotPrime[i]) {
                for (int j = 2; i * j <= bond; j++) {
                    isNotPrime[i * j] = true; //any number that is not a prime must be a multiple of a prime
                    //any number that's been marked as prime must be prime.
                }
            }
        }
        int num = 0;
        int lineNum = 0;
        for (int i = 2; i <= bond; i++) {
            if (!isNotPrime[i]) {
                System.out.print(i + " ");
                num++;
                lineNum++;
                if (lineNum < 10) {
                    continue;
                }
                System.out.print("\n");
                lineNum = 0;
            }
        }
        System.out.print("\n" + num);
    }
}
