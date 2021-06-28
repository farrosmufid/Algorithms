package arrays;

import java.util.Arrays;
import java.util.HashSet;

public class IntersectionOfTwo {

    /*
        Assumption: nums1 and nums2 are sorted
        Time: O(n)
        Space: O(1)
    */

    public int[] twoSetAcceptDuplicates(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                nums1[k] = nums1[i];
                i++;
                j++;
                k++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        return Arrays.copyOf(nums1, k);
    }
    /*
        Time: O(n + m) in the average case and
        O(n * m) in the worst case
        Space: O(n + m)

    */

    public int[] twoSetBuiltInSolution (int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for (Integer n: nums1) { set1.add(n); }
        for (Integer n: nums2) { set2.add(n); }

        set1.retainAll(set2);

        int[] result = new int[set1.size()];

        int i = 0;
        for (Integer val: set1) {
            result[i++] = val;
        }

        return result;
    }

    /*
        Time: O(n + m)
        Space: O(n + m)
    */

    public int[] twoSetSolutionHelper(HashSet<Integer> set1, HashSet<Integer> set2) {
        int[] result = new int[set1.size()];

        int i = 0;

        for (Integer val: set1) {
            if (set2.contains(val)) {
                result[i++] = val;
            }
        }

        return Arrays.copyOf(result, i);
    }

    public int[] twoSetSolution (int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        for (Integer n: nums1) {
            set1.add(n);
        }

        for (Integer n: nums2) {
            set2.add(n);
        }

        if (set1.size() < set2.size()) {
            return twoSetSolutionHelper(set1, set2);
        } else {
            return twoSetSolutionHelper(set2, set1);
        }
    }


    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1, 2, 3, 4, 4, 4};
        int[] nums2 = {1, 1, 2, 3, 3, 3, 3, 4, 4, 4, 4};

        IntersectionOfTwo intersect = new IntersectionOfTwo();

        // int[] result = intersect.twoSetSolution(nums1, nums2);

        // int[] result = intersect.twoSetBuiltInSolution(nums1, nums2);

        int[] result = intersect.twoSetAcceptDuplicates(nums1, nums2);

        System.out.print("Result ");
        for (int r: result) {
            System.out.print(r + " ");
        }
    }
}
