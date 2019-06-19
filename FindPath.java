/* *****************************************************************************
 *  Name: Wei Wang
 *  Date: June 19 2019
 *  Description: find a path in char matrix
 **************************************************************************** */


public class FindPath {
    private boolean[][] mark;
    private char[][] matrix;
    private int row;
    private int col;
    private final static int[][] next = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        row = rows;
        col = cols;
        mark = new boolean[rows][cols];
        this.matrix = new char[rows][cols];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.matrix[i][j] = matrix[index++];
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (findPath(0, i, j, str)) return true;
            }
        }
        return false;
    }

    private boolean findPath(int pathInit, int i, int j,
                             char[] str) { // from this point:matrix[i][j], can you find a path from str[pathInit] to the end?
        if (pathInit == str.length) return true;
        if (i < 0 || j < 0 || i >= row || j >= col || mark[i][j]
                || matrix[i][j] != str[pathInit]) return false;
        mark[i][j] = true;
        for (int[] s : next) {
            if (findPath(pathInit + 1, i + s[0], j + s[1], str)) return true;
        }
        mark[i][j] = false;
        return false;
    }

    public static void main(String[] args) {
        FindPath test = new FindPath();
        char[] matrix = args[0].toCharArray();
        int row = Integer.parseInt(args[1]);
        int col = Integer.parseInt(args[2]);
        char[] str = args[3].toCharArray();
        System.out.print(test.hasPath(matrix, row, col, str));
    }
}

