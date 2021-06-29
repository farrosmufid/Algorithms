package arrays;

import java.util.HashSet;

public class ContainsDuplicate {

    public boolean solution1(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        
        for (int n: nums) {
            if (set.contains(n)) {
                return true;
            }
            set.add(n);
        }
        
        return false;
    }
    public static void main(String[] args) {
        ContainsDuplicate containsDuplicate = new ContainsDuplicate();
        int[] nums = { 1, 2, 3, 1 };
        int[] nums2 = { 1, 2, 3, 4 };
        int[] nums3 = { 1, 1, 1, 3, 3, 4, 3, 2, 4, 2 };
        
        System.out.println(containsDuplicate.solution1(nums));
        System.out.println(containsDuplicate.solution1(nums2));
        System.out.println(containsDuplicate.solution1(nums3));
    }
    
}
