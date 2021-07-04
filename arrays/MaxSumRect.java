package arrays;

/*
    Maximum Sum Rectangle In A 2D Matrix - Kadane's Algorithm
    
    Time: O(cols^2 * rows^2)
    Space: O(rows)
*/

public class MaxSumRect {

    static int startIndex = 0;
    static int endIndex = 0;

    int maxSubArraySum(int[] arr, int n, int k) {
        /*
            - Find maximum contiguous sum in an array in O(n) time
            - Save start in START_INDEX
            - Save end in END_INDEX
        */

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int runningSum = 0;

            for (int j = i; j < n; j++) {
                runningSum += arr[j];
                if (runningSum <= k) {
                    max = Math.max(max, runningSum);
                    startIndex = i;
                    endIndex = j;
                }

            }
        }

        return max;

    }

    void maxSumRect(int[][] M, int row, int col, int k) {
        int maxSum = Integer.MIN_VALUE;
        int endLeft = 0, endRight = 0, endTop = 0, endBottom = 0;

        int left = 0, right = 0;
        int temp[] = new int[row];
        int sum;

        for (left = 0; left < col; left++) {
            for (int i = 0; i < row; i++) { // temp initially holds all 0
                temp[i] = 0;
            }

            for (right = left; right < col; right++) {
                for (int i = 0; i < row; i++) { // For each row find the sum
                    temp[i] += M[i][right];
                }
                sum = maxSubArraySum(temp, row, k); // Find sum of rectangle (top, left) and (bottom right)

                if (sum > maxSum && sum <= k) { // Find maximum value of sum, then update corner points
                    maxSum = sum;
                    endLeft = left;
                    endRight = right;
                    endTop = startIndex;
                    endBottom = endIndex;
                }
            }
        }

        System.out.println("(Top, Left) " + endTop + " " + endLeft);
        System.out.println("(Bottom, Right) " + endBottom + " " + endRight);
        System.out.println("The Max Sum is:  " + maxSum);
    }


    public static void main(String[] args) {
        MaxSumRect maxSumRect = new MaxSumRect();

        // int[][] M = {
        //     {1, 2, -1, -4, -20},
        //     {-8, -3, 4, 2, 1},
        //     {3, 8, 10, 1, 3},
        //     {-4, -1, 1, 7, -6}
        //   };

        // int[][] M = { {2, 2, -1} };

        int[][] M = { {5,-4,-3,4},{-3,-4,4,5},{5,1,5,-4} };

        /*

        {  5,   -4,  -3,   4 }
        { -3,  -4,   4,   5 }
        {  5,    1,   5,  -4 } 

        { -3,  -4,   4,}
        {  5,    1,   5 } is 8 
        */

        /*
        [
            [5, -4, -3, 4],
            [-3, -4, 4, 5],
            [5, 1, 5, -4]
        ]

        [
            [5, -4],
            [-3, -4],
            [5, 1]
        ] is zero.

        */

        int row = M.length;
        int col = M[0].length;

        int k = 8;

        maxSumRect.maxSumRect(M, row, col, k);
    }
}
