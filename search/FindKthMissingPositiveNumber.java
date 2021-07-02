package search;

/*
    Problem:
        - Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
        - Find the kth positive integer that is missing from this array.

    Example 1:

        Input: arr = [2,3,4,7,11], k = 5
        Output: 9
        Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.

    Example 2:

        Input: arr = [1,2,3,4], k = 2
        Output: 6
        Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.

*/

import java.util.HashSet;
import java.util.Set;


public class FindKthMissingPositiveNumber {

    /*
        Solution 3:
            - Binary Search
        Time: O(n)
        Space: O(1)
    */

    /*

        array without missing integers = [1, 2, 3, 4, 5]
        input array = [2, 3, 4, 7, 11]

        There are arr[idx] - idx - 1 missing positive numbers
        before the idx

        e.g. idx = 2
        4 - 2 - 1 = 1 missing positive numbers
        before the idx 2

    */

    public int solution3(int[] arr, int k) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int pivot = left + (right - left) / 2;

            // search right

            if (arr[pivot] - pivot - 1 < k) {
                left = pivot + 1;
            }

            // search left

            else {
                right = pivot - 1;
            }
        }

        /*
            In the end, left = right + 1,
            So, this can be simplified into left + k
        */

        return arr[right] + k - (arr[right] - right - 1);
    }


    /*
        Solution 2:
            - Brute force without set
        Time: O(n)
        Space: O(1)

    */

    public int solution2(int[] arr, int k) {
         // if kth missing less than arr[0]
         if (k <= arr[0] - 1) {
             return k;
         }

         // reduce k by the missing number in the front

         k -= arr[0] - 1;

         int n = arr.length;

         for (int i = 0; i < n - 1; i++) {
             // missing number between two val

             int currMissing = arr[i + 1] - arr[i] - 1;

             // missing value is between that the two number (offset)

             if (k <= currMissing) {
                 return arr[i] + k;
             }

             // proceed further

             k -= currMissing;

         }

         // add the rest value for k

         return arr[n - 1] + k;



    }

    /*
        Solution 1:
            - Brute force and set

        Time: O(n)
        Space: O(n)
    */

    public int solution1(int[] arr, int k) {
        Set<Integer> set = new HashSet<Integer>();
        
        for (int val: arr) {
            set.add(val);
        }
        
        int count = 0;
        int lastIndex = arr.length - 1;
        int biggestVal = arr[lastIndex];
        
        int missing = -1;
        
        for (int i = 1; i <= biggestVal; i++) {
            if (!set.contains(i)) {
                missing = i;
                count++;
            }
            
            if (count == k) {
                return missing;
            }
        }
        
        return biggestVal + k - count;
        
    }
    public static void main(String[] args) {
        FindKthMissingPositiveNumber findKthMissingPositiveNumber = new FindKthMissingPositiveNumber();

        int[] arr = {2, 3, 4, 7, 11};
        int search = 5;

        int[] arr2 = {1, 2, 3, 4 };
        int search2 = 2;

        // System.out.println(findKthMissingPositiveNumber.solution1(arr, search));
        // System.out.println(findKthMissingPositiveNumber.solution1(arr2, search2));

        // System.out.println(findKthMissingPositiveNumber.solution2(arr, search));
        // System.out.println(findKthMissingPositiveNumber.solution2(arr2, search2));

        System.out.println(findKthMissingPositiveNumber.solution3(arr, search));
        System.out.println(findKthMissingPositiveNumber.solution3(arr2, search2));
    }
}
