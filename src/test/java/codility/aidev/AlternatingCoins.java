package codility.aidev;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlternatingCoins {
/*
There are N coins, each showing either heads or tails. We would like all the coins to form a sequence of alternating heads and tails. What is the minimum number of coins that must be reversed to achieve this?

Write a function:

class Solution { public int solution(int[] A); }

that, given an array A consisting of N integers representing the coins, returns the minimum number of coins that must be reversed. Consecutive elements of array A represent consecutive coins and contain either a 0 (heads) or a 1 (tails).


Examples:

1. Given array A = [1, 0, 1, 0, 1, 1], the function should return 1. After reversing the sixth coin, we achieve an alternating sequence of coins [1, 0, 1, 0, 1, 0].

2. Given array A = [1, 1, 0, 1, 1], the function should return 2. After reversing the first and fifth coins, we achieve an alternating sequence [0, 1, 0, 1, 0].

3. Given array A = [0, 1, 0], the function should return 0. The sequence of coins is already alternating.

4. Given array A = [0, 1, 1, 0], the function should return 2. We can reverse the first and second coins to get the sequence: [1, 0, 1, 0].
 */
    @Test
    public void test1() {
        int solution = solution(new int[]{1, 0, 1, 0, 1, 1});
        assertEquals(solution, 1);
    }

    @Test
    public void test2() {
        int solution = solution(new int[]{1,1,0,1,1});
        assertEquals(solution, 2);
    }

    @Test
    public void test3() {
        int solution = solution(new int[]{1,0,1});
        assertEquals(solution, 0);
    }

    @Test
    public void test4() {
        int solution = solution(new int[]{0,1,1,0});
        assertEquals(solution, 2);
    }

    public int solution(int[] A) {
        if (A.length == 1) {
            return 0;
        }

        boolean prev1 = A[0] == 1 ? true : false;
        int result1 = 0;

        boolean prev2 = A[0] == 1 ? false : true;
        int result2 = 1;

        for (int i = 1; i < A.length; i++) {
            boolean now = A[i] == 1 ? true : false;
            if (prev1 == now) {
                result1++;
                prev1 = !prev1;
            } else {
                prev1 = now;
            }
            if (prev2 == now) {
                result2++;
                prev2 = !prev2;
            } else {
                prev2 = now;
            }
        }
        return result1 > result2 ? result2 : result1;
    }
}
