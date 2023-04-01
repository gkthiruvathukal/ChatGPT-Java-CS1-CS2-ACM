package cgpt;

import java.util.LinkedList;
import java.util.Queue;

public class NQueens1BFS {

    public static void main(String[] args) {
        int n = 4; // Set the number of queens
        NQueens1BFS nQueens = new NQueens1BFS();
        nQueens.solveNQueens(n);
    }

    private static class BoardState {
        int[] board;
        int col;

        BoardState(int[] board, int col) {
            this.board = board.clone();
            this.col = col;
        }
    }

    private void solveNQueens(int n) {
        Queue<BoardState> queue = new LinkedList<>();
        for (int row = 0; row < n; row++) {
            int[] initialBoard = new int[n];
            initialBoard[0] = row;
            queue.add(new BoardState(initialBoard, 1));
        }

        while (!queue.isEmpty()) {
            BoardState currentState = queue.poll();
            int[] board = currentState.board;
            int col = currentState.col;

            if (col == n) {
                printSolution(board, n);
                System.out.println();
                continue;
            }

            for (int row = 0; row < n; row++) {
                if (isSafe(board, row, col, n)) {
                    int[] nextBoard = board.clone();
                    nextBoard[col] = row;
                    queue.add(new BoardState(nextBoard, col + 1));
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
