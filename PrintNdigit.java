/* *****************************************************************************
 *  Name: Wei Wang
 *  Date: June 19 2019
 *  Description: print number from 1 to the largest N digit integer
 **************************************************************************** */

public class PrintNdigit {
    char[] number;

    public PrintNdigit(int n) {
        number = new char[n];
    }

    public void printStart(int digit) {
        if (digit == number.length) {
            printDigit();
            return; //ending is very important
        }
        for (int i = 0; i < 10; i++) {
            number[digit] = (char) (i + '0');
            printStart(digit + 1);

        }
        return;
    }

    private void printDigit() {
        int i = 0;
        while (i < number.length && number[i] == '0') {
            i = i + 1;
        }
        while (i < number.length) {
            System.out.print(number[i++]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        PrintNdigit test = new PrintNdigit(Integer.parseInt(args[0]));
        test.printStart(0);
    }
}
