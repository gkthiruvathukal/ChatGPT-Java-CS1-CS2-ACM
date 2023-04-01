package cgpt;

public class NQueens0 {

    public static void main(String[] args) {
        int n = 4; // Set the number of queens
        NQueens0 nQueens = new NQueens0();
        nQueens.solveNQueens(n);
    }

    private void solveNQueens(int n) {
        int[][] board = new int[n][n];
        if (solve(board, 0, n)) {
            printSolution(board, n);
        } else {
            System.out.println("No solution found for " + n + " queens.");
        }
    }

    private boolean solve(int[][] board, int col, int n) {
        if (col == n) {
            return true;
        }

        for (int row = 0; row < n; row++) {
            if (isSafe(board, row, col, n)) {
                board[row][col] = 1;

                if (solve(board, col + 1, n)) {
                    return true;
                }

                // Backtrack
                board[row][col] = 0;
            }
        }

        return false;
    }

    private boolean isSafe(int[][] board, int row, int col, int n) {
        // Check row
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        // Check upper diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check lower diagonal
        for (int i = row, j = col; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    private void printSolution(int[][] board, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
