package arrays;

public class Kadane {

    /*
        Time: O(n)
        Space: O(1)
    */

    public int kadane(int[] nums) {
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);  
        }

        return maxSoFar;
    }

    public static void main(String[] args) {
        Kadane kadane = new Kadane();

        int[] arr = {-2,-3,11};

        int res = kadane.kadane(arr);
        System.out.println("Res: " + res);

    }
}
