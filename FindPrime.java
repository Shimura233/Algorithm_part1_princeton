public class FindPrime {
    public static void main(String[] args) {
        int bond = 10000;
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
        for (int i = 2; i <= bond; i++) {
            if (!isNotPrime[i]) {
                System.out.print(i + " ");
                num++;
            }
        }
        System.out.print("\n" + num);
    }
}
