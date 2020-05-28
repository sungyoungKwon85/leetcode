package codility.aidev;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CheapLetterDeletion {
/*
You are given a string S. Deletion of the K-th letter of S costs C[K]. After deleting a letter, the costs of deleting other letters do not change. For example, for S = "ab" and C = [1, 3], after deleting 'a', deletion of 'b' will still cost 3.

You want to delete some letters from S to obtain a string without two identical letters next to each other. What is the minimum total cost of deletions to achieve such a string?

Write a function:

class Solution { public int solution(String S, int[] C); }

that, given string S and array C of integers, both of length N, returns the minimum cost of all necessary deletions.

Examples:

1. Given S = "abccbd" and C = [0, 1, 2, 3, 4, 5], the function should return 2. You can delete the first occurrence of 'c' to achieve "abcbd".

2. Given S = "aabbcc" and C = [1, 2, 1, 2, 1, 2], the function should return 3. By deleting all letters with a cost of 1, you can achieve string "abc".

3. Given S = "aaaa" and C = [3, 4, 5, 6], the function should return 12. You need to delete all but one letter 'a', and the lowest cost of deletions is 3+4+5=12.

4. Given S = "ababa" and C = [10, 5, 10, 5, 10], the function should return 0. There is no need to delete any letter.
 */
    @Test
    public void test1() {
        int result = solution("abccbd", new int[]{0, 1, 2, 3, 4, 5});
        assertEquals(result, 2);
    }

    @Test
    public void test2() {
        int result = solution("aabbcc", new int[]{1, 2, 1, 2, 1, 2});
        assertEquals(result, 3);
    }

    @Test
    public void test3() {
        int result = solution("aaaa", new int[]{3, 4, 5, 6});
        assertEquals(result, 12);
    }

    @Test
    public void test4() {
        int result = solution("ababa", new int[]{10, 5, 10, 5, 10});
        assertEquals(result, 0);
    }

    public int solution(String S, int[] C) {
        int result = 0;
        char prev = S.charAt(0);
        int prevIndex = 0;
        for (int i = 1; i < S.length(); i++) {
            char now = S.charAt(i);
            if (prev == now) {
                int less = C[prevIndex] < C[i] ? prevIndex : i;
                result += C[less];
            }
            prevIndex = i;
            prev = now;
        }
        return result;
    }

}
