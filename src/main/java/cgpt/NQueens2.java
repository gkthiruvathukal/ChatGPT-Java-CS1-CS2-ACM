package cgpt;

import java.util.Stack;

public class NQueens2 {

    public static void main(String[] args) {
        int n = 4; // Set the number of queens
        NQueens2 nQueens = new NQueens2();
        nQueens.solveNQueens(n);
    }

    private void solveNQueens(int n) {
        int[] board = new int[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int col = stack.peek();
            boolean foundSafeRow = false;

            for (int row = board[col]; row < n; row++) {
                if (isSafe(board, row, col, n)) {
                    board[col] = row;
                    foundSafeRow = true;

                    if (col == n - 1) {
                        printSolution(board, n);
                        System.out.println();
                        foundSafeRow = false;
                    } else {
                        stack.push(col + 1);
                        break;
                    }
                }
            }

            if (!foundSafeRow) {
                board[col] = 0;
                stack.pop();

                if (!stack.isEmpty()) {
                    board[stack.peek()]++;
                }
            }
        }
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
