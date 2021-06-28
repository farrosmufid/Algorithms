package arrays;

public class IslandPerimeter {

    /*
        Time: O(n^2)
        Space: O(1)
    */

    public int islandPerimeter(int[][] grid) {
        int count = 0;
        int up, down, left, right;
        
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                int val = grid[r][c];
                if (val == 1) {
                    if (r == 0) {
                        up = 0;
                    } else {
                        up = grid[r-1][c];
                    } // up
                    
                    if (c == 0) {
                        left = 0;
                    } else {
                        left = grid[r][c-1];
                    } // left
                    
                    if (r == grid.length - 1) {
                        down = 0;
                    } else {
                        down = grid[r + 1][c];
                    }
                    
                    if (c == grid[0].length - 1) {
                        right = 0;
                    } else {
                        right = grid[r][c + 1];
                    }
                    
                    count += (4 - (up + left + down + right));
                    
                } // end val
            }
        }
        
        return count;
    }
    public static void main(String[] args) {
        IslandPerimeter islandPerimeter = new IslandPerimeter();

        int[][] grid = { {0,1,0,0} , {1,1,1,0}, {0,1,0,0}, {1,1,0,0}};

        int result = islandPerimeter.islandPerimeter(grid);

        System.out.println("Result: " + result);
    }
}
