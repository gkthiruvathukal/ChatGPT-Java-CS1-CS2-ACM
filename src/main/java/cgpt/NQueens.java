package cgpt;

import java.util.*;

import org.apache.commons.cli.*;

public class NQueens {

  private int n;
  private int[] board;
  private Deque<Integer> deque;
  private BacktrackPolicy policy;

  public enum Strategy {
    LIFO,
    FIFO
  }

  public NQueens(int n, Strategy strategy) {
    this.n = n;
    board = new int[n];
    deque = new ArrayDeque<>();
    if (strategy == Strategy.LIFO) {
      policy = new LifoPolicy();
    } else if (strategy == Strategy.FIFO) {
      policy = new FifoPolicy();
    }
  }

  public boolean solve() {
    int row = 0;
    int col = 0;
    boolean backtrack = false;

    while (row < n) {
      if (!backtrack) {
        col = getNextValidColumn(row, col);
        if (col == -1) {
          backtrack = true;
          continue;
        }
        board[row] = col;
        policy.put(deque, row * n + col);
        row++;
        col = 0;
      } else {
        if (deque.isEmpty()) {
          return false;
        }
        int lastRowCol = policy.get(deque);
        row = lastRowCol / n;
        col = lastRowCol % n + 1;
        board[row] = -1;
        backtrack = false;
      }
    }

    return true;
  }

  private int getNextValidColumn(int row, int col) {
    while (col < n) {
      if (isColumnValid(col) && isDiagonalValid(row, col)) {
        return col;
      }
      col++;
    }
    return -1;
  }

  private boolean isColumnValid(int col) {
    for (int i = 0; i < n; i++) {
      if (board[i] == col) {
        return false;
      }
    }
    return true;
  }

  private boolean isDiagonalValid(int row, int col) {
    int i, j;

    for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
      if (board[i] == j) {
        return false;
      }
    }

    for (i = row, j = col; i >= 0 && j < n; i--, j++) {
      if (board[i] == j) {
        return false;
      }
    }

    return true;
  }

  public void print() {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i] == j) {
          System.out.print("1 ");
        } else {
          System.out.print("0 ");
        }
      }
      System.out.println();
    }
  }

  interface BacktrackPolicy {
    int get(Deque<Integer> deque);

    void put(Deque<Integer> deque, int value);
  }

  class LifoPolicy implements BacktrackPolicy {
    @Override
    public int get(Deque<Integer> deque) {
      return deque.pop();
    }

    @Override
    public void put(Deque<Integer> deque, int value) {
      deque.push(value);
    }
  }

  class FifoPolicy implements BacktrackPolicy {
    @Override
    public int get(Deque<Integer> deque) {
      return deque.poll();
    }

    @Override
    public void put(Deque<Integer> deque, int value) {
      deque.offer(value);
    }
  }

  public static void main(String[] args) {
    Options options = new Options();

    Option nOption = Option.builder("n")
        .argName("N")
        .hasArg()
        .desc("Number of queens")
        .required()
        .build();

    Option strategyOption = Option.builder("strategy")
        .argName("STRATEGY")
        .hasArg()
        .desc("Strategy to use (LIFO or FIFO)")
        .required()
        .build();

    options.addOption(nOption);
    options.addOption(strategyOption);

    CommandLineParser parser = new DefaultParser();
    HelpFormatter formatter = new HelpFormatter();

    int n = 0;
    Strategy strategy = null;

    try {
      CommandLine cmd = parser.parse(options, args);

      if (cmd.hasOption("n")) {
        n = Integer.parseInt(cmd.getOptionValue("n"));
      }

      String strategyString = cmd.getOptionValue("strategy");
      if (strategyString.equalsIgnoreCase("LIFO")) {
        strategy = Strategy.LIFO;
      } else if (strategyString.equalsIgnoreCase("FIFO")) {
        strategy = Strategy.FIFO;
      } else {
        System.err.println("Strategy must be either LIFO or FIFO.");
        formatter.printHelp("NQueens", options);
        System.exit(1);
      }
    } catch (ParseException e) {
      System.err.println(e.getMessage());
      formatter.printHelp("NQueens", options);
      System.exit(1);
    }

    if (n <= 0) {
      System.err.println("N must be a positive integer.");
      formatter.printHelp("NQueens", options);
      System.exit(1);
    }

    NQueens nQueens = new NQueens(n, strategy);
    boolean solved = nQueens.solve();

    if (solved) {
      nQueens.print();
    } else {
      System.out.println("No solution found for " + n + " queens.");
    }
  }
}
