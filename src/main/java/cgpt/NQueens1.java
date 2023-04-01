package cgpt;

public class NQueens1 {

    public static void main(String[] args) {
        int n = 4; // Set the number of queens
        NQueens1 nQueens = new NQueens1();
        nQueens.solveNQueens(n);
    }

    private void solveNQueens(int n) {
        int[] board = new int[n];
        if (solve(board, 0, n)) {
            printSolution(board, n);
        } else {
            System.out.println("No solution found for " + n + " queens.");
        }
    }

    private boolean solve(int[] board, int col, int n) {
        if (col == n) {
            return true;
        }

        for (int row = 0; row < n; row++) {
            if (isSafe(board, row, col, n)) {
                board[col] = row;

                if (solve(board, col + 1, n)) {
                    return true;
                }

                // Backtrack
                board[col] = -1;
            }
        }

        return false;
    }

    private boolean isSafe(int[] board, int row, int col, int n) {
        for (int i = 0; i < col; i++) {
            if (board[i] == row) {
                return false;
            }

            if (Math.abs(board[i] - row) == Math.abs(i - col)) {
                return false;
            }
        }

        return true;
    }

    private void printSolution(int[] board, int n) {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (board[col] == row) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }
}
