package hashmaps;

import java.util.HashMap;

public class RomanToInteger {

    /*
        Solution 3:
        Left to Right without double hashmap

        Time: O(1)
        Space: O(1)
    */
    
    public int solution3(String s) {
        HashMap<String,Integer> map = new HashMap<>() { 
            {
                put("I", 1);
                put("V", 5);
                put("X", 10);
                put("L", 50);
                put("C", 100);
                put("D", 500);
                put("M", 1000);
            }
        };

        if (s.length() == 1) {
            return map.get(s);
        }

        int i = 0;
        int total = 0;
        String key = "";

        while (i < s.length()) {
            if (i < s.length() - 1) {
                // check if next value is greater
                int currentVal = map.get(s.substring(i, i+1));
                int nextVal = map.get(s.substring(i + 1, i + 2));
                if (nextVal > currentVal) {
                    total += (nextVal - currentVal);
                    i += 2;
                    continue;
                }
            }

            key = s.substring(i, i + 1);
            total += map.get(key);
            i++;
        }


        return total;
    }

    /*
        Solution 2:
        Right to Left

        Time: O(1)
        Space: O(1)

    */

    public int solution2(String s) {
        HashMap<String,Integer> map = new HashMap<>() { 
            {
                put("I", 1);
                put("V", 5);
                put("X", 10);
                put("L", 50);
                put("C", 100);
                put("D", 500);
                put("M", 1000);
            }
        };

        int total = 0;
        int lastIndex = s.length() - 1;
        String lastString = s.substring(lastIndex, lastIndex + 1);

        int lastValue = map.get(lastString);

        total = lastValue;

        for (int i = s.length() - 2; i >= 0; i--) {

            String secondLastString = s.substring(i, i+1);
            int secondLastValue = map.get(secondLastString);

            if (lastValue > secondLastValue) {
                total = total - secondLastValue;
            } else {
                total = total + secondLastValue;
            }

            lastValue = secondLastValue;

        }

        return total;


    }

    /*
        Solution 1:
        Left to Right

        Time: O(1)
        Space: O(1)
    */

    public int solution1(String s) {
        HashMap<String,Integer> map = new HashMap<>() { 
            {
                put("I", 1);
                put("IV", 4);
                put("V", 5);
                put("IX", 9);
                put("X", 10);
                put("XL", 40);
                put("L", 50);
                put("XC", 90);
                put("C", 100);
                put("CD", 400);
                put("D", 500);
                put("CM", 900);
                put("M", 1000);
            }
        };
    
        if (s.length() == 1) {
            return map.get(s);
        }
        
        int i = 0;
        int total = 0;
        String key = "";
        
        while (i < s.length()) {
            if (i < s.length() - 1) {
                key = s.substring(i, i + 2);
                if (map.containsKey(key)) {
                    total += map.get(key);
                    i += 2;
                    continue;
                }
            }
            
            key = s.substring(i, i + 1);
            total += map.get(key);
            i++;
        }
        
         return total;
    }
    public static void main(String[] args) {
        RomanToInteger romanToInteger = new RomanToInteger();

        String three = "III";
        String four = "IV";
        String nine = "IX";
        String fifty_eight = "LVIII";
        String one_thousand_nine_hundred_ninety_four  = "MCMXCIV" ;
        String six_hundred_twenty_one = "DCXXI";

        String[] values = {three, four, nine, fifty_eight, one_thousand_nine_hundred_ninety_four, six_hundred_twenty_one};

        // for (String val: values) {
        //     System.out.println(romanToInteger.solution1(val));
        // }

        // for (String val: values) {
        //     System.out.println(romanToInteger.solution2(val));
        // }

        for (String val: values) {
            System.out.println(romanToInteger.solution3(val));
        }

    }

}
