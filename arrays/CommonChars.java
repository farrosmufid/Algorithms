package arrays;

//import java.util.*;
//import java.io.*;

public class CommonChars {

    /*
        Time: O(n^2)
        Space: O(n * m)
    */

    public String solution1Helper(String A, String B) {
        StringBuilder sbA = new StringBuilder(A);
        StringBuilder sbB = new StringBuilder(B);

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < sbA.length(); i++) {
            Character x = sbA.charAt(i);
            if (sbB.indexOf(x.toString()) != -1) {
                sbB.deleteCharAt(sbB.indexOf(x.toString()));
                res.append(x.toString());
            }
        }
        return res.toString();
    }

    public String solution1(String[] words) {
        String res = "";
        String first = words[0];

        for (int i = 1; i < words.length; i++) {
            res = solution1Helper(first, words[i]);
            first = res;
        }

        return res;
    }

    public static void main(String[] args) {
        String A = "label";
        String B = "roller";
        String C = "ll";

        CommonChars commonChars = new CommonChars();

        String[] words = {A, B, C};

        String res = commonChars.solution1(words);
        System.out.println(res);
        
    }
}
