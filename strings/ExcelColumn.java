package strings;

import java.util.HashMap;

/*
    This problem is similar to converting number
    from base 26 to base 10
*/

public class ExcelColumn {

    /*
        Solution 2:
        Calculate from the front

        Time: O(n)
        Space: O(1)
    */

    public int solution2(String columnTitle) {
        int result = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            result *= 26;
            
            result += (columnTitle.charAt(i) - 'A' + 1);
        }
        
        return result;
        
    }

    /*
        Solution 1:
        Calculate from the back

        Time: 0(n)
        Space: O(1)

    */

    public int solution1(String columnTitle) {
        // create char map
        HashMap<Character, Integer> map = new HashMap<>();
        
        for (int i = 0; i < 26; i++) {
            map.put((char)('A' + i), i + 1);
        }
        
        // get result
        
        int res = 0;
        
        for (int i = 0; i < columnTitle.length(); i++) {
            char back = columnTitle.charAt(columnTitle.length() - 1 - i);
            int val = map.get(back);
            
            res += Math.pow(26, i) * val;
            
        }
        
        return res;
    }

    public static void main(String[] args) {
        ExcelColumn excelColumn = new ExcelColumn();
        String columnTitle = "ZY";

        System.out.println("Result: " + excelColumn.solution1(columnTitle) );
    }
}
