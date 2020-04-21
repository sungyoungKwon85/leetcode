package ten;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/*
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"

Palindrome?
    문자열을 거꾸로 해도 원래의 문자열과 같은 문자열
 */
public class LongestPalindromicSubstring {

    @Test
    public void test1() {
        Assertions.assertThat(longestPalindrome("babad")).isEqualTo("bab");
    }

    @Test
    public void test2() {
        Assertions.assertThat(longestPalindrome("cbbd")).isEqualTo("bb");
    }

    @Test
    public void test3() {
        Assertions.assertThat(longestPalindrome("abcba")).isEqualTo("abcba");
    }

    @Test
    public void test4() {
        Assertions.assertThat(longestPalindrome("aa")).isEqualTo("aa");
    }

    @Test
    public void test5() {
        Assertions.assertThat(longestPalindrome("abc")).isEqualTo("a");
    }

    @Test
    public void test6() {
        Assertions.assertThat(longestPalindrome("abcda")).isEqualTo("a");
    }

    @Test
    public void test7() {
        Assertions.assertThat(longestPalindrome("abacab")).isEqualTo("bacab");
    }

    @Test
    public void test8() {
        Assertions.assertThat(longestPalindrome("aaabaaaa")).isEqualTo("aaabaaa");
    }

    @Test
    public void test9() {
        Assertions.assertThat(longestPalindrome("ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc"))
            .isEqualTo("ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc");
    }

    public String longestPalindrome(String s) {

        if (s.length() == 0) {
            return "";
        }

        String result = "" + s.charAt(0);

        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = s.length()-1; j > i; j--) {
                String newStr = s.substring(i, j+1);
                if (isPalindrome(newStr) && result.length() < newStr.length()) {
                    result = newStr;
                    if (s.equals(newStr)) {
                        return s;
                    }
                }
            }
        }
        return result;
    }

    private boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }


    public String longestPalindrome_fail(String s) {
        if (s.length() <= 1) return s;

        boolean hasMatched = false;
        int start = 0;
        int end = -1;

        for (int i = 0; i < s.length() - 1; i++) {
            int from = 0;
            int to = -1;
            int front = i;
            for (int j = s.length()-1; j > i ; j--) {
                if (front >= j) break;
                char sc = s.charAt(front);
                char ec = s.charAt(j);

                if (sc == ec) {
                    if (!hasMatched) {
                        if (end == -1 || (end - start < j - front)) {
                            from = front;
                            to = j;
                        }
                        hasMatched = true;
                    }
                    if (j - front <= 2) {
                        hasMatched = false;
                        break;
                    }
                    front++;
                } else {
                    if (hasMatched) {
                        from = 0;
                        to = -1;
                        hasMatched = false;
                    }
                }
            }
            if (to != -1) {
                start = from;
                end = to;
            }
        }

        if (end == -1) {
            return s.charAt(0) + "";
        }

        return s.substring(start, end+1);
    }

}
