package strings;

import java.util.Arrays;

public class ValidAnagram {


    /*
        Solution 1: Sorting

        Time: O(nlogn)
        - Sorting requires nlogn time
        - Comparing two string costs nlogn time
        - nlogn dominates

        Space: O(1)
        - In java toCharArray() makes a copy of string, 
        therefore it costs O(n) time
        - However, we ignore this because it depends on how 
        the function is defined. For example, the function
        parameter types can be changed to char[]

    */

    public boolean solution1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        char charS[] = s.toCharArray();
        char charT[] = t.toCharArray();
        
        Arrays.sort(charS);
        Arrays.sort(charT);
        
        return Arrays.equals(charS, charT);
    }
    public static void main(String[] args) {
        String s = "anagram";
        String t = "nanagram";

        ValidAnagram va = new ValidAnagram();

        boolean res = va.solution1(s, t);
        System.out.println(res);


    }
}
