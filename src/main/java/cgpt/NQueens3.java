package cgpt;

import java.util.LinkedList;
import java.util.Queue;

public class NQueens3 {

    public static void main(String[] args) {
        int n = 4; // Set the number of queens
        NQueens3 nQueens = new NQueens3();
        nQueens.solveNQueens(n);
    }

    private void solveNQueens(int n) {
        int[] board = new int[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        while (!queue.isEmpty()) {
            int col = queue.poll();
            boolean foundSafeRow = false;

            while (board[col] < n) {
                if (isSafe(board, board[col], col, n)) {
                    foundSafeRow = true;
                    if (col == n - 1) {
                        printSolution(board, n);
                        System.out.println();
                    } else {
                        queue.add(col + 1);
                        break;
                    }
                }
                board[col]++;
            }

            if (!foundSafeRow) {
                if (!queue.isEmpty()) {
                    board[col] = 0;
                    board[queue.peek() - 1]++;
                }
            } else if (col == n - 1) {
                board[col] = 0;
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
