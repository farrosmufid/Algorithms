package backtrack;

import java.util.HashSet;
import java.util.Set;

public class NQueens2 {
    private int size;
    
    public int totalNQueens(int n) {
        this.size = n;
        
        return backtrack(0, new HashSet<>(), new HashSet<>(), new HashSet<>());
    }
    
    private int backtrack(int row, Set<Integer> diagonals, Set<Integer> antiDiagonals, Set<Integer> cols) {
        // Base case, N-Queens have been placed
        if (row == this.size) {
            return 1;
        }
        
        int solutions = 0;
        
        for (int col = 0; col < size; col++) {
            int currDiagonal = row - col;
            int currAntiDiagonal = row + col;
            
            // Queen is not placeable 
            // Continue of Queen is under attack
            if (diagonals.contains(currDiagonal) || antiDiagonals.contains(currAntiDiagonal) || cols.contains(col)) {
                continue;
            }

            // Queen is not under attack
            // Add Queen to the board
            // Place Queen
            cols.add(col);
            diagonals.add(currDiagonal);
            antiDiagonals.add(currAntiDiagonal);
            
            // Move to the next row with updated board state
            solutions += backtrack(row + 1, diagonals, antiDiagonals, cols);
            
            // Remove the Queen from the board since we already explore all valid paths
            cols.remove(col);
            diagonals.remove(currDiagonal);
            antiDiagonals.remove(currAntiDiagonal);
        }
        
        return solutions;
    }
    public static void main(String[] args) {
        NQueens2 nQueens2 = new NQueens2();
        
        int totalNQueens = nQueens2.totalNQueens(4);
        
        System.out.println("Output: " + totalNQueens);
    }
}
