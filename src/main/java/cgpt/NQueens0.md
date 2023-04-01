# Q

Can you show how to write the classic recursive solution of N-Queens in Java?

# A

See `NQueens0.java`.

This program defines a class NQueens with a main method to run the program. The solveNQueens method initializes the board and calls the recursive solve method. The solve method places a queen in each column one by one, then checks if it's safe to place the queen in the current row of that column. If it's safe, the method proceeds to the next column. If it reaches the last column, it means a solution has been found. If not, it backtracks and tries another row in the same column. The isSafe method checks if the current position is safe. The printSolution method prints the solution found.

# Analysis

See follow up question in next MD file.