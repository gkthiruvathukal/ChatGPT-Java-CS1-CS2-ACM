package cgpt;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.util.LinkedList;
import java.util.Queue;

public class NQueens2BFS {

    public static void main(String[] args) {
        ArgumentParser parser = ArgumentParsers.newFor("NQueens2BFS").build()
                .defaultHelp(true)
                .description("Solve the N-Queens problem using a Breadth-First Search strategy.");
        parser.addArgument("-n", "--number-of-queens")
                .setDefault(8)
                .type(Integer.class)
                .help("The number of queens to use (default: 8)");

        Namespace ns;
        try {
            ns = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.handleError(e);
            return;
        }

        int n = ns.getInt("number_of_queens");
        NQueens2BFS nQueens = new NQueens2BFS();
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

    public void solveNQueens(int n) {
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
