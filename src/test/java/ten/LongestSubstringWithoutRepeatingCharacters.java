package ten;

import java.util.HashMap;

import org.junit.Test;

import static org.junit.Assert.*;

public class LongestSubstringWithoutRepeatingCharacters {
/*
Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */

    @Test
    public void test1() {
        int result = solution1("abcabcbb");
        assertEquals(result, 3);
    }

    @Test
    public void test2() {
        int result = solution1("bbbbb");
        assertEquals(result, 1);
    }

    @Test
    public void test3() {
        int result = solution2("pwwkew");
        assertEquals(result, 3);
    }

    @Test
    public void test4() {
        int result = solution1("");
        assertEquals(result, 0);
    }

    @Test
    public void test5() {
        int result = solution1("au");
        assertEquals(result, 2);
    }

    @Test
    public void test6() {
        int result = solution2("dvdf");
        assertEquals(result, 3);
    }

    public int solution2(String s) {
        int result = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int j=0, i=0; j < s.length(); j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            result = Math.max(result, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return result;
    }

    public int solution1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int max = 1;
        for (int j = 0; j < s.length() - 1; j++) {
            if (max >= (s.length() - j)) {
                return max;
            }
            String dummy = String.valueOf(s.charAt(j));
            for (int i = j+1; i < s.length(); i++) {
                String c = String.valueOf(s.charAt(i));
                if (!dummy.contains(c)) {
                    dummy += c;
                    if (max < dummy.length())
                        max = dummy.length();
                } else {
                    if (max < dummy.length())
                        max = dummy.length();
                    break;
                }
            }
        }
        return max;
    }



}
