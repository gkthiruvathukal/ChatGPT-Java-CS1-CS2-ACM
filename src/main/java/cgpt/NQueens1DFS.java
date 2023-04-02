package cgpt;

import java.util.Stack;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

public class NQueens1DFS {

    public static void main(String[] args) {
        ArgumentParser parser = ArgumentParsers.newFor("NQueens1DFS").build()
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


    public  void solveNQueens(int n) {
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
