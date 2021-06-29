package strings;

import java.util.Arrays;
import java.util.HashMap;

public class ValidAnagram {

    /*
        Follow up question: 
        What if the inputs contain unicode characters? 
        How would you adapt your solution to such case?

        Solution: 
        Use HashMap

        Time: O(n)
        Iterate string at length s

        Space: O(n)
        HashMap size grow with more varieties of characters
    */

    public boolean solution3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1 );
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) - 1);
        }

        for (Character c: map.keySet()) {
            if (map.get(c) != 0) { return false; }
        }

        return true;
    }

    /*
        Solution 2: HashMap
        Time: O(n)
        Iterate string at length s and t.

        Space: O(1)
        - Although we do use extra space, the space complexity
        is O(1) because the table's size stays constant no matter
        how large n is.
    */

    public boolean solution2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int map[] = new int[26];
        
        for (char c: s.toCharArray()) {
            map[c - 'a'] += 1;
        }

        for (char c: t.toCharArray()) {
            map[c - 'a'] -= 1;
            
            if (map[c - 'a'] < 0) { // negative value means something is different
                return false;
            }
        }
        return true;
    }

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
        String t = "aangrma";

        ValidAnagram va = new ValidAnagram();

        boolean res = va.solution3(s, t);
        System.out.println(res);
    }
}
