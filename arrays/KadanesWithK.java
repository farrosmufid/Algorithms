package arrays;

import java.util.TreeSet;

public class KadanesWithK {

    /*
        Time: O(n)
        Space: O(n)
    */
    public static void main(String[] args) {

        int k = 8;
        //int[] arr = {-2,-3,11};
        int[] arr = {-3, -2, 11};

        int max = Integer.MIN_VALUE;
        int curr = 0;
        
        TreeSet<Integer> set = new TreeSet<>();
        
        set.add(0);
        for (int i = 0; i < arr.length; i++) {
            curr += arr[i];
    
            int currMinusK = curr - k;
            Integer ceil = set.ceiling(currMinusK);
            if(ceil != null) {
                int currMinusCeil = curr - ceil;
                max = Math.max(max, currMinusCeil);
            }
            set.add(curr);
        }
        
        System.out.println("Max: " + max);
    }
}
