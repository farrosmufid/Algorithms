package backtrack;

class SolveSudoku {
    private int n = 3; // Box Size
    private int N = n * n; // Row Size
    
    private int[][] rows = new int[N][N + 1];
    private int[][] columns = new int[N][N + 1];
    private int[][] boxes = new int[N][N + 1];
    
    private char[][] board;
    
    private boolean solved = false;
    
    /*
        Check if we could place a value in the (row, col) cell
    */
    
    public boolean couldPlace(int val, int row, int col) {
        int boxIndex = (row / n) * n + col / n;
        
        return rows[row][val] + columns[col][val] + boxes[boxIndex][val] == 0;
    }
    
    /*
        Place a value in a (row, col) cell
    */
    
    public void placeValue(int val, int row, int col) {
        int boxIndex = (row / n) * n + col / n;
        
        rows[row][val] ++;
        columns[col][val] ++;
        boxes[boxIndex][val] ++;
        board[row][col] = (char)(val + '0');
        
        return;
    }
    
    /*
        Remove a value that does not lead to the solution
    */
    
    public void removeValue(int val, int row, int col) {
        int boxIndex = (row / n) * n + col / n;
        
        rows[row][val] --;
        columns[col][val] --;
        boxes[boxIndex][val] --;
        board[row][col] = '.';
        
        return;
    }
    
    /*
        Move row, col in the (row, col) cell
    */
    
    public void move(int row, int col) {
        /* 
            We are in the last cell
        */
        if ( (col == N - 1) && (row == N - 1) ) {
            this.solved = true;
        } 
        
        /*
            We are in the last column, we move to the
            next row
        */ 
        else if (col == N - 1) {
            backtrack(row + 1, 0);
        }
        /*
            Go to the next column
        */
        
        else {
            backtrack(row, col + 1);
        }
        
        return;
    }
    
    /*
        Backtrack Algorithm
    */
    
    public void backtrack(int row, int col) {
        /* 
            Cell is empty 
        */
        if (board[row][col] == '.') {
            /*
                Iterate values to put into the board
            */
            for (int val = 1; val <= 9; val++) {
                if (couldPlace(val, row, col) == true) {
                    placeValue(val, row, col);
                    move(row, col);
                    /*
                        Remove value if no values could be placed
                        and sudoku is not solved
                    */
                    if (!solved) {
                        removeValue(val, row, col);
                    }
                }
            }
        } else {
            move(row, col);
        }
        
        return;
    }
    
    public void solveSudoku(char[][] board) {
        this.board = board;
        
        /*
            Initialise board
        */
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char givenVal = this.board[i][j];
                /*
                    Get the numeric value and place it to
                    the board
                */
                if (givenVal != '.') {
                    int val = givenVal - '0';
                    placeValue(val, i, j);
                }
            }
        }
        
        /*
            Start the backtrack algorithm
        */
        backtrack(0, 0);
    }
}

public class SudokuSolver {
    public static void main(String[] args) {
        SolveSudoku sudokuSolver = new SolveSudoku();

        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };

        System.out.println("Input: ");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

        sudokuSolver.solveSudoku(board);

        System.out.println("Output: ");

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

     

    }
}

